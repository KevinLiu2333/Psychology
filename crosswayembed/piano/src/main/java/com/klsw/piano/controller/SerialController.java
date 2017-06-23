package com.klsw.piano.controller;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.klsw.piano.service.TbSerialRecordService;
import com.klsw.piano.service.TbSerialnumbersService;
import com.klsw.piano.service.TbUserAvailableService;
import com.klsw.piano.util.FileUploadConfig;
import com.klsw.piano.util.LoginHelper;
import com.klsw.pianoCommon.api.model.TbPmuser;
import com.klsw.pianoCommon.api.model.TbSerialnumbers;
import com.klsw.pianoCommon.api.model.TbSerialrecord;
import com.klsw.pianoCommon.api.model.TbUseravailable;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 序列号相关操作
 *
 * @author Hkj
 */
@Controller
@RequestMapping(value = "/serial")
public class SerialController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final int PAGE_SIZE = 5;

    @Resource
    private FileUploadConfig fileUploadConfig;

    @Resource
    private TbSerialnumbersService serialNumberService;

    @Resource
    private TbUserAvailableService userAvailableService;

    @Resource
    private TbSerialRecordService serialRecordService;

    /************************************* 后台部分 *************************************/
    /**
     * 后台：序列号列表
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "index")
    public String serialList(HttpServletRequest request, Model model) {
        if (!LoginHelper.validate(request)) {
            return "redirect:/user/toLogin";
        }
        // 获取查询选择条件
        String serialType = request.getParameter("serialtype");
        String searchStr = request.getParameter("searchstr");
        if (StringUtils.isEmpty(searchStr)) {
            searchStr = "";
        }
        if (StringUtils.isEmpty(serialType)) {
            serialType = "2";
        }

        // 获取当前要展示的页数（第几页）
        String pageNum = request.getParameter("pageId");
        if (StringUtils.isEmpty(pageNum)) {
            pageNum = "1";
        }

        // 定义分页查询： pageNum——要展示的页数 PAGE_SIZE——每页要展示的条数
        PageHelper.startPage(Integer.parseInt(pageNum), PAGE_SIZE);
        PageHelper.orderBy("AddDatetime DESC");

        // 创建保存查询出的数据的集合
        List<TbSerialnumbers> serialInfoList = Lists.newArrayList();

        try {
            serialInfoList = serialNumberService.selectLikeName(searchStr, serialType);
            PageInfo<TbSerialnumbers> pageInfo = new PageInfo<TbSerialnumbers>(serialInfoList);
            model.addAttribute("serialType", serialType);
            model.addAttribute("serialInfoList", pageInfo);
            model.addAttribute("searchStr", searchStr);
            return "Serial/number_pool";
        } catch (Exception e) {
            logger.error("msg", e);
            return "Serial/number_pool";
        }

    }

    /**
     * 后台：进行序列号修改界面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "toEditSerial")
    public String toEditSerial(HttpServletRequest request, Model model) {
        if (!LoginHelper.validate(request)) {
            return "redirect:/user/toLogin";
        }
        // 获取要编辑的sn序列号
        String number = request.getParameter("number");

        try {
            // 查询sn信息
            TbSerialnumbers tbSerialnumbers = new TbSerialnumbers();
            tbSerialnumbers.setNumber(number);
            tbSerialnumbers = serialNumberService.selectOne(tbSerialnumbers);

            // 绑定sn信息到页面
            if (tbSerialnumbers != null) {
                // 查询sn用户信息以获取sn用户昵称
                TbUseravailable tbUseravailable = new TbUseravailable();
                tbUseravailable.setNewnumber(number);
                tbUseravailable = userAvailableService.selectOne(tbUseravailable);
                if (tbUseravailable != null) {
                    tbSerialnumbers.setNickname(tbUseravailable.getNickname());
                }
                model.addAttribute("tbSerialNumbers", tbSerialnumbers);
            }
            return "Serial/number_edit";
        } catch (Exception e) {
            logger.error("msg", e);
            return "redirect:/serial/index";
        }
    }

    /**
     * 后台：修改序列号中上锁和欠费信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "mdySerial")
    @ResponseBody
    public String MdySerial(HttpServletRequest request) {
        // 判断用户是否登录
        HttpSession session = request.getSession();
        TbPmuser user = (TbPmuser) session.getAttribute("tbPmuser");
        if (user == null) {
            return "<script>alert('请登录后重试！');window.location.href='/User/toLogin'</script>";
        }

        try {
            Boolean isOverdue = null;
            Boolean isLocked = null;
            String number = request.getParameter("number");
            if (!StringUtils.isEmpty(request.getParameter("isOverdue"))) {
                isOverdue = Boolean.parseBoolean((request.getParameter("isOverdue")));
            }
            if (!StringUtils.isEmpty(request.getParameter("isLocked"))) {
                isLocked = Boolean.parseBoolean((request.getParameter("isLocked")));
            }
            TbSerialnumbers sn = new TbSerialnumbers();
            sn.setNumber(number);
            sn = serialNumberService.selectOne(sn);
            if (sn != null) {
                sn.setIsoverdue(isOverdue);
                sn.setIslocked(isLocked);
                serialNumberService.updateByPrimaryKeySelective(sn);
            }
            return "<script>alert('修改成功！');window.location.href='/serial/index';</script>";
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
            return "<script>alert('修改失败！请重试！');window.location.href='/serial/index';</script>";
        }
    }

    /**
     * 后台：删除序列号
     *
     * @param request 请求
     * @return 返回
     */
    @RequestMapping(value = "deleteSerial")
    @ResponseBody
    public String deleteSerial(HttpServletRequest request) {
        // 判断用户是否登录
        HttpSession session = request.getSession();
        TbPmuser user = (TbPmuser) session.getAttribute("tbPmuser");
        if (user == null) {
            return "<script>alert('请登录后重试！');window.location.href='/User/toLogin'</script>";
        }

        // 获取要删除的序列号number
        String message = "";
        String number = request.getParameter("number");
        TbSerialnumbers tbSerialnumbers = new TbSerialnumbers();
        tbSerialnumbers.setNumber(number);
        try {
            int delNum = serialNumberService.delete(tbSerialnumbers);
            if (delNum == 1) {
                message = "<script>alert('删除成功！');window.location.href='/serial/index'</script>";
                return message;
            } else {
                message = "<script>alert('请注意!数据库无此数据！');window.location.href='/serial/index'</script>";
                return message;
            }
        } catch (Exception e) {
            logger.error("msg", e);
            message = "<script>alert('删除失败！请重试！');window.location.href='/serial/index'</script>";
            return message;
        }
    }

    /**
     * 后台：导出序列号
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "exportSerial")
    @ResponseBody
    public String exportSerial(HttpServletRequest request) {
        if (!LoginHelper.validate(request)) {
            return "<script>alert('您还未登录');window.location='/user/toLogin';</script>";
        }
        // 获取页面传过来的参数
        String serialNoStr = request.getParameter("serialNo");
        String inputExportZone = request.getParameter("inputExportZone");
        String isOverdueStr = request.getParameter("selectIsOverdue");
        int serialNo = 0;
        String exportZone = "";
        Boolean isOverdue = null;
        if (serialNoStr != null) {
            serialNo = new Integer(request.getParameter("serialNo"));
        }
        if (inputExportZone != null) {
            exportZone = inputExportZone;
        }
        if ("欠费".equals(isOverdueStr)) {
            isOverdue = true;
        } else if ("不欠费".equals(isOverdueStr)) {
            isOverdue = false;
        }

        // 创建Excel文件的对象
        HSSFWorkbook wb = new HSSFWorkbook();
        // 添加一个sheet
        HSSFSheet sheet = wb.createSheet();
        wb.setSheetName(0, "Sheet1");

        // 获取list数据
        int count;
        TbSerialnumbers tbSerialnumbers = new TbSerialnumbers();
        tbSerialnumbers.setIsexport(false);
        List<TbSerialnumbers> serialnumbers = Lists.newArrayList();
        try {
            serialnumbers = serialNumberService.select(tbSerialnumbers);
            if (serialnumbers == null) {
                count = 0;
            } else {
                count = serialnumbers.size();
            }
            if (count < serialNo) {
                getSerial(serialNo - count);
            }

            List<TbSerialnumbers> list = Lists.newArrayList();
            TbSerialnumbers serial = new TbSerialnumbers();
            serial.setIsused(false);
            serial.setIsexport(false);
            list = serialNumberService.select(serial);// 需要得到serialNo数量的数据

            // 给sheet1添加第一行的头部标题
            HSSFRow row = sheet.createRow(0);// 创建第一行
            row.createCell(0).setCellValue("序列号");
            row.createCell(1).setCellValue("添加时间");
            row.createCell(2).setCellValue("是否欠费");
            row.createCell(3).setCellValue("导出对象");

            // 将数据逐步写入sheet各个行(从第二行开始，第一行为标题行，已设过)
            for (int i = 0; i < list.size(); i++) {
                HSSFRow rowTemp = sheet.createRow(i + 1);// 创建行
                String sn = list.get(i).getNumber().toString().toUpperCase();
                String snShow = "";
                int a = 0;
                for (int j = sn.length() - 1; j >= 0; j--) {
                    a++;
                    snShow = String.valueOf(sn.charAt(j)) + snShow;
                    if (a == 4) {
                        a = 0;
                        snShow = "-" + snShow;
                    }
                }
                rowTemp.createCell(0).setCellValue(snShow.replaceAll("-", "").trim());
                rowTemp.createCell(1).setCellValue(
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(list.get(i).getAdddatetime()).toString());
                rowTemp.createCell(2).setCellValue(isOverdue == null ? "未设置" : isOverdue.toString());
                rowTemp.createCell(3).setCellValue(exportZone);

                String snid = list.get(i).getId();
                TbSerialnumbers ts = new TbSerialnumbers();
                ts.setId(snid);
                ts = serialNumberService.selectOne(ts);
                ts.setIsexport(true);
                ts.setExportzone(exportZone);
                ts.setIsoverdue(isOverdue);
                serialNumberService.updateByPrimaryKey(ts);
            }

            // 存入工作表
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String fileName = "序列号导出 - " + simpleDateFormat.format(new Date()).toString() + ".xlsx";
            String fileUrl = request.getContextPath() + "/excel/";
            String filePath = fileUploadConfig.getSavePath() + "/excel";
            File directory = new File(filePath);
            if (!directory.isDirectory()) {
                directory.mkdirs();
            }
            filePath += "/" + fileName;
            File fileOut = new File(filePath);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileOut));
            wb.write(bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            wb.close();
            fileUrl += fileName;
            return "<script>alert('导出成功!');</script><a href='" + fileUrl
                    + "'><button>下载到本地</button></a><br/><a href='/serial/index'><button>返回序列号池</button></a>";
        } catch (Exception e) {
            logger.error("msg", e);
            return "<script>alert('发生异常!请稍后重试!');window.location.href='/serial/index';</script>";
        }
    }

    /************************************* 接口部分 *************************************/
    /**
     * 接口： 嵌入式修改用户名
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/UpdateUname")
    @ResponseBody
    public String updateName(HttpServletRequest request) {
        String uName = request.getParameter("uname");
        String code = request.getParameter("code");
        if (StringUtils.isEmpty(uName) || StringUtils.isEmpty(code)) {
            return "1";
        }

        try {
            TbSerialnumbers tbSerialnumbers = new TbSerialnumbers();
            tbSerialnumbers.setNumber(code);
            tbSerialnumbers = serialNumberService.selectOne(tbSerialnumbers);
            if (tbSerialnumbers == null) {
                return "2"; // 查无此序列号
            }
            if (!tbSerialnumbers.getIsused()) {
                return "3"; // 序列号还未使用过
            }

            TbUseravailable tbUseravailable = new TbUseravailable();
            tbUseravailable.setNewnumber(code);
            tbUseravailable = userAvailableService.selectOne(tbUseravailable);
            if (tbUseravailable == null) {
                return "2"; // 序列号对应用户不存在
            }
            if (tbUseravailable.getNickname().equals(uName)) {
                return "5"; // 与原昵称相同
            }

            TbUseravailable tbUseravailable1 = new TbUseravailable();
            tbUseravailable1.setNickname(uName);
            tbUseravailable1 = userAvailableService.selectOne(tbUseravailable1);
            if (tbUseravailable1 != null) {
                return "4"; // 该昵称已存在
            }

            tbUseravailable.setNickname(uName);
            userAvailableService.updateByPrimaryKeySelective(tbUseravailable);
        } catch (Exception e) {
            logger.error("msg", e);
            return "6";
        }
        return "0";
    }

    /**
     * 接口：更新序列号(换板子)
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/UpdateSerial")
    @ResponseBody
    public String updateSerial(HttpServletRequest request) {
        // 获取请求过来的旧序列号和新序列号
        String oldCode = request.getParameter("oldcode");
        String newCode = request.getParameter("newcode");

        // 定义返回的xml类型字符串
        String xmlStr = "";

        // 若新、旧序列号中有一个为空，则返回表单不完整，返回状态1
        if (StringUtils.isEmpty(newCode) || StringUtils.isEmpty(oldCode)) {
            xmlStr = "<?xml version='1.0' encoding='utf-8'?>" + "<userdata createuser='false'> "
                    + "<dataconnection> <status>1<status> </dataconnection> </userdata>";
            return xmlStr;
        }

        // 创建序列号对象
        TbSerialnumbers serial1 = new TbSerialnumbers();
        TbSerialnumbers serial2 = new TbSerialnumbers();
        serial1.setNumber(newCode);
        serial2.setNumber(oldCode);
        try {
            serial1 = serialNumberService.selectOne(serial1);// 通过新序列号查询
            serial2 = serialNumberService.selectOne(serial2);// 通过旧序列号查询

            // 旧序列号不存在，返回状态4
            if (serial2 == null) {
                xmlStr = "<?xml version='1.0' encoding='utf-8'?>" + "<userdata createuser='false'> "
                        + "<dataconnection> <status>4<status> </dataconnection> </userdata>";
                return xmlStr;
            }

            // 旧序列号未使用，返回状态5
            if (!serial2.getIsused()) {
                xmlStr = "<?xml version='1.0' encoding='utf-8'?>" + "<userdata createuser='false'> "
                        + "<dataconnection> <status>5<status> </dataconnection> </userdata>";
                return xmlStr;
            }

            // 新序列号不存在，返回状态2
            if (serial1 == null) {
                xmlStr = "<?xml version='1.0' encoding='utf-8'?>" + "<userdata createuser='false'> "
                        + "<dataconnection> <status>2<status> </dataconnection> </userdata>";
                return xmlStr;
            }

            // 新序列号已使用，返回状态3
            if (serial1.getIsused()) {
                xmlStr = "<?xml version='1.0' encoding='utf-8'?>" + "<userdata createuser='false'> "
                        + "<dataconnection> <status>3<status> </dataconnection> </userdata>";
                return xmlStr;
            }

            // 创建序列号对应用户对象
            TbUseravailable tbUseravailable = new TbUseravailable();
            tbUseravailable.setNewnumber(oldCode);

            // 通过旧序列号查询对应用户
            tbUseravailable = userAvailableService.selectOne(tbUseravailable);

            if (tbUseravailable == null) { // 旧序列号对应用户不存在时，返回状态4
                xmlStr = "<?xml version='1.0' encoding='utf-8'?>" + "<userdata createuser='false'> "
                        + "<dataconnection> <status>4<status> </dataconnection> </userdata>";
                return xmlStr;
            } else { // 旧序列号对应用户存在时，启用新序列号，并将用户序列号更新，然后插入一条更新记录，返回状态0和用户昵称
                // 启用新序列号
                serial1.setIsused(true);
                serial1.setActivationdate(new Date());
                serialNumberService.updateByPrimaryKey(serial1);

                // 将旧序列号对应用户的序列号进行更新
                tbUseravailable.setNewnumber(newCode);
                userAvailableService.updateByPrimaryKey(tbUseravailable);

                // 插入一条序列号更新记录
                TbSerialrecord tbSerialrecord = new TbSerialrecord();
                tbSerialrecord.setId(generateID());
                tbSerialrecord.setUseravailableid(tbUseravailable.getId());
                tbSerialrecord.setSerialno(newCode);
                tbSerialrecord.setAdddatetime(new Date());
                serialRecordService.insert(tbSerialrecord);

                String nickName = tbUseravailable.getNickname();
                xmlStr = "<?xml version='1.0' encoding='utf-8'?><userdata createuser='false'>"
                        + "<dataconnection><status>0<status><nickName>" + nickName + "</nickName>"
                        + "</dataconnection> </userdata>";
                return xmlStr;
            }
        } catch (Exception e) {
            logger.error("msg", e);
            return e.getMessage();
        }
    }

    /**
     * 接口：嵌入式用户注册
     *
     * @param request 请求
     * @return
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    public String regist(HttpServletRequest request) {
        // 通过请求获取用户名和序列号
        String uName = request.getParameter("uname");
        String code = request.getParameter("code");

        if (StringUtils.isEmpty(uName) || StringUtils.isEmpty(code)) {
            logger.error("1");
            return "1";
        }

        try {
            TbSerialnumbers tbSerialnumbers = new TbSerialnumbers();
            tbSerialnumbers.setNumber(code);
            tbSerialnumbers = serialNumberService.selectOne(tbSerialnumbers);

            TbUseravailable tbUseravailable = new TbUseravailable();
            tbUseravailable.setNickname(uName);
            tbUseravailable = userAvailableService.selectOne(tbUseravailable);

            if (tbSerialnumbers == null) {
                logger.error("2");
                return "2";
            }

            if (tbSerialnumbers.getIsused()) {
                logger.error("3");
                return "3";
            }

            if (tbUseravailable != null) {
                logger.error("4");
                return "4";
            }

            TbUseravailable useravailable = new TbUseravailable();
            useravailable.setId(generateID());
            useravailable.setAdddatetime(new Date());
            useravailable.setIsactivation(true);
            useravailable.setNewnumber(code);
            useravailable.setNickname(uName);
            userAvailableService.insert(useravailable);

            TbSerialrecord tbSerialrecord = new TbSerialrecord();
            tbSerialrecord.setId(generateID());
            tbSerialrecord.setUseravailableid(useravailable.getId());
            tbSerialrecord.setAdddatetime(new Date());
            tbSerialrecord.setSerialno(code);
            serialRecordService.insert(tbSerialrecord);

            tbSerialnumbers.setIsused(true);
            tbSerialnumbers.setAdddatetime(new Date());
            serialNumberService.updateByPrimaryKeySelective(tbSerialnumbers);

        } catch (Exception e) {
            logger.error("msg", e);
            return "5";
        }
        return "0";
    }

    /**
     * 接口：通过SN注册
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/RegisterNoUserName")
    @ResponseBody
    public String registNoUserName(HttpServletRequest request) {
        //判断传入的序列号是否为空
        String codes = request.getParameter("code");
        logger.error(codes);
        if (StringUtils.isEmpty(codes)) {
            return "{\"errmsg\":\"序列号不能为空\",\"errmsg\":\"SN is null\",\"username\":\"\"}";
        }

//		 String head = "";
//		if (!StringUtils.isEmpty(request.getParameter("head"))) {
//			 head = request.getParameter("head");
//		}
        //定义用户名
        String uname = "";
        try {
            //生成可用的用户名
            TbUseravailable useravailable = null;
            boolean cross = true;
            while (cross) {
                uname = getUserName();
                useravailable = new TbUseravailable();
                useravailable.setNickname(uname);
                if (userAvailableService.select(useravailable).isEmpty()) {
                    cross = false;
                }
            }

            // 先查序列号池有没有这个序列号并且是未使用的
            TbSerialnumbers serial = new TbSerialnumbers();
            serial.setNumber(codes.toUpperCase());
            serial = serialNumberService.selectOne(serial);
            String uaGuid = UUID.randomUUID().toString().replaceAll("-", "").trim();
            if (serial == null) {
                return "{\"errmsg\":\"序列号不存在\",\"errmsg_en\":\"SN is not exist\",\"username\":\"\"}";
            }
            if (serial.getIsused()) {
                return "{\"errmsg\":\"序列号已被使用\",\"errmsg_en\":\"SN is used\",\"username\":\"\"}";
            }

            // 存入用户信息
            TbUseravailable ua = new TbUseravailable();
            ua.setId(uaGuid);
            ua.setAdddatetime(new Date());
            ua.setIsactivation(true);
            ua.setNewnumber(codes.toUpperCase());// 序列号
            ua.setNickname(uname);// 用户名昵称
            // ua.HeadPic = head;
            userAvailableService.insert(ua);

            // 存入序列号记录
            TbSerialrecord sr = new TbSerialrecord();
            sr.setId(UUID.randomUUID().toString().replaceAll("-", "").trim());
            sr.setAdddatetime(new Date());
            sr.setUseravailableid(uaGuid);
            sr.setSerialno(codes.toUpperCase());
            serialRecordService.insert(sr);

            serial.setIsused(true);
            serial.setActivationdate(new Date());
            serialNumberService.updateByPrimaryKey(serial);

        } catch (Exception e) {
            logger.error("error");
            logger.error("msg", e);
            return "{\"errmsg\":\"激活失败，请重试\",\"errmsg_en\":\"Activation failed, Please try again later\",\"username\":\"\"}";
        }
        logger.error("success");
        return "{\"errmsg\":\"0\",\"errmsg_en\":\"0\",\"username\":\"" + uname + "\"}";
    }

    /**
     * 接口：获取新用户名功能
     *
     * @return
     */
    @RequestMapping(value = "/GetNewUserName")
    @ResponseBody
    public String GetNewUserName(HttpServletRequest request) {
        try {
            //判断传入的序列号是否为空
            String code = request.getParameter("code");
            if (StringUtils.isEmpty(code)) {
                return "{\"errmsg\":\"序列号不能为空\",\"errmsg\":\"SN is null\",\"username\":\"\"}";
            }

            //判断序列号是否存在
            TbSerialnumbers serial = new TbSerialnumbers();
            serial.setNumber(code.toUpperCase());
            serial = serialNumberService.selectOne(serial);
            if (serial == null) {
                return "{\"errmsg\":\"序列号不存在\",\"errmsg_en\":\"SN is not exist\",\"username\":\"\"}";
            }

            //获取可用的新用户名
            boolean cross = true;
            String uname = "";
            TbUseravailable useravailable = null;
            while (cross) {
                uname = getUserName();
                useravailable = new TbUseravailable();
                useravailable.setNickname(uname);
                useravailable = userAvailableService.selectOne(useravailable);
//				if (useravailable != null) {
//					return "{\"errmsg\":\"用户名已存在（测试版用户名不允许重复）\",\"errmsg_en\":\"Username is exist(test version)\",\"username\":\"\"}";
//				}
                if (useravailable == null) {
                    cross = false;
                }
            }

            //更新用户信息
            TbUseravailable ua = new TbUseravailable();
            ua.setNewnumber(code.toUpperCase());
            ua = userAvailableService.selectOne(ua);
            if (ua == null) {
                return "{\"errmsg\":\"原用户名不存在！\",\"errmsg_en\":\"Username is not exist\",\"username\":\"\"}";
            }
            ua.setNickname(uname);
            userAvailableService.updateByPrimaryKey(ua);

            return "{\"errmsg\":\"0\",\"errmsg_en\":\"0\",\"username\":\"" + uname + "\"}";
        } catch (Exception e) {
            logger.info("msg", e);
            return "{\"errmsg\":\"发生异常！\",\"errmsg_en\":\"Exception occurred!\",\"username\":\"\"}";
        }

    }

    /**
     * 接口：获取是否需要因为欠费等原因而锁定钢琴
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetIsLocked")
    @ResponseBody
    public String GetIsLocked(HttpServletRequest request) {
        String serialNumber = request.getParameter("SerialNumber");
        boolean isClientLock = "1".equals(request.getParameter("Lock")) ? true : false;

        if (StringUtils.isEmpty(serialNumber)) {
            return "1, Fail: SN is null locked, IsOverdue=";
        }

        try {
            TbSerialnumbers sn = new TbSerialnumbers();
            sn.setNumber(serialNumber.toUpperCase());
            sn = serialNumberService.selectOne(sn);
            if (sn == null) {
                return "1, Fail: SN is not exist locked, IsOverdue=";
            }

            Boolean isLocked = false;
            if (sn.getIslocked() == null) {
                isLocked = false;
            } else {
                isLocked = sn.getIslocked();
            }

            Boolean isOverdue = false;
            if (sn.getIsoverdue() == null) {
                isOverdue = false;
            } else {
                isOverdue = sn.getIsoverdue();
            }

            if (!isLocked && isOverdue) {//未上锁但欠费
                sn.setIslocked(isClientLock);
                serialNumberService.updateByPrimaryKey(sn);
            }

            if (isLocked) {//已上锁
                if (isOverdue) {//欠费
                    return "1, Fail: Is overdue locked, IsOverdue=True";
                } else {//未欠费
                    return "1, Fail: Is locked without overdue, IsOverdue=False";
                }
            }

            if (isOverdue) {
                return "0, OK, IsOverdue=True";
            } else {
                return "0, OK, IsOverdue=False";
            }

        } catch (Exception ex) {
            return "2, Fail: " + ex.getMessage() + ", IsOverdue=";
        }
    }

    /************************************* 方法部分 *************************************/
    /**
     * 方法作用：获取序列号
     *
     * @param sCount
     * @return 返回成功数量
     */
    public int getSerial(int sCount) throws Exception {
        int gCount = 0;
        for (int i = 0; i < sCount; i++) {
            String gid = generateID();// 主键ID
            String serialCode = generateSerial(16).toUpperCase();// 序列号
            TbSerialnumbers sn = new TbSerialnumbers();
            sn.setNumber(serialCode);
            sn = serialNumberService.selectOne(sn);
            if (sn == null) { // 序列号可用
                TbSerialnumbers addSn = new TbSerialnumbers();
                addSn.setId(gid);
                addSn.setNumber(serialCode);
                addSn.setAdddatetime(new Date());
                addSn.setIsused(false);
                addSn.setIsexport(false);
                serialNumberService.insert(addSn);
                gCount++;
            } else {
                i--;
            }
        }
        if (gCount == sCount) {
            return gCount;
        } else {
            return getSerial(sCount - gCount);
        }
    }

    /**
     * 方法作用：生成序列号
     */
    public static String generateSerial(int sCount) {
        Date date = new Date();
        char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
        }
        Random random = new Random(date.getTime());
        String string;
        string = "";
        for (int i = 0; i < 16; i++) {
            string += chars[random.nextInt(16)];
        }
        return string;
    }

    /**
     * 方法作用：补齐+分割 （将序列号补齐到16位并按照"-"进行分割）
     */
    public static String polishingAndSeperate(String code) {
        // String string = "a5e4db1f2a54d";
        String res = String.format("%16s", code);
        res = res.replaceAll("\\s", "*");
        // System.out.println(res);

        String[] strings = new String[4];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = res.substring(4 * i, 4 * (i + 1)).replaceAll("\\*", "");
            // System.out.println(strings[i]);
        }
        String result = "";
        for (int i = 0; i < strings.length; i++) {
            if (!StringUtils.isEmpty(strings[i])) {
                result += strings[i];
                if (i < strings.length - 1) {
                    result += "-";
                }
            }
        }
        return result;
    }

    /**
     * 方法作用：生成ID
     *
     * @return
     */
    public static String generateID() {
        String id = UUID.randomUUID().toString();
        String[] strings = id.split("-");
        id = "";
        strings[2] = generateSerial(4);
        for (int i = 0; i < 4; i++) {
//			id += strings[i] + "-";
            id += strings[i];
        }
        id += strings[4];
        return id;
    }

    /**
     * 方法作用：生成用户名
     *
     * @return
     */
    private static String getUserName() {
        String[] name1 = {"飞快", "迅捷", "敏捷", "矫健", "灵巧", "轻巧", "轻快", "轻盈", "轻柔", "玲珑", "伶俐", "灵动", "迅速", "灵活", "清浅", "矫捷", "利落", "利索", "麻利", "踟蹰",};
        String[] name2 = {"活泼", "聪明", "机智", "机灵", "阳光", "可爱", "俏皮", "憨厚", "幽默", "调皮", "随和", "大方", "认真", "宽容", "自信",
                "羞涩", "团结", "稳重", "热情", "正直 ", "优秀", "正义 ", "单纯 ", "勤俭", "朴素 ",};
        String[] name3 = {"小兔", "小鸟", "马驹", "猫咪", "小鹿", "蜗牛", "仓鼠", "青蛙", "小狗", "羊羔", "猎豹", "斑马", "海象", "刺猬", "河马",
                "狮子", "老虎", "猩猩", "企鹅", "袋鼠", "麝牛", "考拉", "蜥蜴", "大象",};

        Random random = new Random(new Date().getTime());
        String result = name1[random.nextInt(name1.length)] + "而" + name2[random.nextInt(name2.length)] + "的"
                + name3[random.nextInt(name3.length)];
        return result;
    }


}
