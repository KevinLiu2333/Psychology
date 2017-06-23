package com.klsw.klswWebService.controller;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.klsw.apiCommon.api.model.*;
import com.klsw.klswWebService.service.*;
import com.klsw.klswWebService.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: UserInquiryController
 *
 * @author LiuKun
 * @Description: 用户信息查询相关
 * @date 2016年9月7日
 */
@Controller
public class UserInquiryController extends _BaseController {

    @Autowired
    private HistorySignService historySignService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TbCwkService tbCwkService;

    @Autowired
    private TbLiveRoomService liveRoomService;

    @Resource
    private TbLiveAppointService appointService;

    /**
     * @param @return
     * @return Ret
     * @throws
     * @Description: 接口：所有教师列表查询
     * @author LiuKun
     * @date 2016年8月26日
     */
    @RequestMapping(value = "/teacherList")
    @ResponseBody
    public Ret teacherList(@RequestParam("pageNum") Integer pageNum) {
        TbCwk tbCwk = new TbCwk();
        tbCwk.setType("tea");
        try {
            PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
            List<TbCwk> tbCwkList = tbCwkService.select(tbCwk);
            List<WKTeacher> wkTeachers = Lists.newArrayList();
            WKTeacher wkTeacher = null;
            for (TbCwk cwk : tbCwkList) {
                TbCwkteacher tbCwkteacher = new TbCwkteacher();
                tbCwkteacher.setCwkid(cwk.getId());
                tbCwkteacher = teacherService.selectOne(tbCwkteacher);
                if (tbCwkteacher == null) {
                    tbCwkteacher = new TbCwkteacher();
                    tbCwkteacher.setCwkid(cwk.getId());
                    teacherService.insert(tbCwkteacher);
                }
                wkTeacher = new WKTeacher(tbCwk, tbCwkteacher);
                wkTeachers.add(wkTeacher);
            }
            PageInfo<WKTeacher> pageInfo = new PageInfo<>(wkTeachers);
            return Ret.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("查询教师列表失败");
        }

    }


    /**
     * @param pageNum 页数
     * @param @return
     * @return Ret
     * @throws
     * @Description: 学生列表查询
     * @author LiuKun
     * @date 2016年8月26日
     */

    @RequestMapping(value = "/studentList")
    @ResponseBody

    public Ret studentList(@RequestParam("pageNum") Integer pageNum) {
        TbCwk tbCwk = new TbCwk();
        tbCwk.setType("stu");
        try {
            PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
            List<TbCwk> tbCwkList = tbCwkService.select(tbCwk);
            List<WKStudent> wkStudents = Lists.newArrayList();
            WKStudent wkStudent = null;
            for (TbCwk cwk : tbCwkList) {
                TbCwkstudent tbCwkstudent = new TbCwkstudent();
                tbCwkstudent.setCwkid(cwk.getId());
                tbCwkstudent = studentService.selectOne(tbCwkstudent);
                if (tbCwkstudent == null) {
                    tbCwkstudent = new TbCwkstudent();
                    tbCwkstudent.setCwkid(cwk.getId());
                    studentService.insert(tbCwkstudent);
                }
                wkStudent = new WKStudent(tbCwk, tbCwkstudent);
                wkStudents.add(wkStudent);
            }
            PageInfo<WKStudent> pageInfo = new PageInfo<WKStudent>(wkStudents);
            return Ret.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("查询学生列表失败");
        }

    }

    /**
     * @param userId 用户id
     * @return 用户信息
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Ret userInfo(@RequestParam("userId") Integer userId) {
        try {
            HashMap<String, Object> userInfo = tbCwkService.userInfo(userId);
            if (userInfo.get("ID") == null) {
                return Ret.warn("信息有误");
            }
            if (userInfo.get("type").equals("tea")) {
                //教师信息
                TbCwkteacher teacher = teacherService.selectByUserId(userId);
                userInfo.put("teacherInfo", teacher);
                TbLiveroom liveroom = new TbLiveroom();
                liveroom.setAnchorid(userId);
                userInfo.put("liveRoomCount", liveRoomService.selectCount(liveroom));
            } else if (userInfo.get("type").equals("stu")) {
                TbCwkstudent student = studentService.selectByUserId(userId);
                userInfo.put("studentInfo", student);
                TbLiveappoint liveappoint = new TbLiveappoint();
                liveappoint.setUserid(userId);
                userInfo.put("liveRoomCount", appointService.selectCount(liveappoint));
            }
            return Ret.success(userInfo);
        } catch (Exception e) {
            logger.error("msg", e);
            return Ret.error("查询失败");
        }
    }


    /**
     * @param teacherId 教师id
     * @return Ret
     * @author LiuKun
     * @date 2016年9月18日
     */
    @RequestMapping(value = "/teacherInfo")
    @ResponseBody
    public Ret teacherInfo(@RequestParam("teacherId") Integer teacherId) {
        try {
            TbCwk tbCwk = tbCwkService.selectByPrimaryKey(teacherId);
            if (tbCwk == null || !"tea".equals(tbCwk.getType())) {
                return Ret.warn("用户信息有误");
            }
            TbCwkteacher tbCwkteacher = new TbCwkteacher();
            tbCwkteacher.setCwkid(tbCwk.getId());
            tbCwkteacher = teacherService.selectOne(tbCwkteacher);
            if (tbCwkteacher == null) {
                return Ret.warn("获取用户信息有误");
            }
            WKTeacher teacher = new WKTeacher(tbCwk, tbCwkteacher);
            return Ret.success(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("获取用户信息失败");
        }
    }

    /**
     * 教师获取学生信息
     *
     * @param studentId
     * @return
     */
    @RequestMapping(value = "/studentInfo")
    @ResponseBody
    public Ret studentInfo(@RequestParam("studentId") Integer studentId) {
        try {
            TbCwk tbCwk = tbCwkService.selectByPrimaryKey(studentId);
            if (tbCwk == null || !"stu".equals(tbCwk.getType())) {
                return Ret.warn("用户信息有误");
            }
            TbCwkstudent tbCwkstudent = new TbCwkstudent();
            tbCwkstudent.setCwkid(tbCwk.getId());
            tbCwkstudent = studentService.selectOne(tbCwkstudent);
            if (tbCwkstudent == null) {
                return Ret.warn("获取用户信息有误");
            }
            WKStudent student = new WKStudent(tbCwk, tbCwkstudent);
            return Ret.success(student);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Ret.error("获取用户信息失败");
        }
    }

    /**
     * @param @param  orgId
     * @param @return
     * @return Ret
     * @throws
     * @Description: 获取机构信息
     * @author LiuKun
     * @date 2016年9月19日
     */
    @RequestMapping(value = "/orgInfo")
    @ResponseBody
    public Ret orgInfo(@RequestParam("orgId") Integer orgId) {

        try {
            TbCwk org = tbCwkService.selectByPrimaryKey(orgId);
            if (org == null || !"org".equals(org.getType())) {
                return Ret.warn("机构信息有误");
            }

            return Ret.success(org);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("获取机构信息失败");
        }

    }

    /**
     * @param studentId
     * @param teachGrade
     * @param university
     * @param minPrice
     * @param maxPrice
     * @param region
     * @return
     * @Describtion 接口：已签约老师列表
     * @author HKJ
     */

    @RequestMapping(value = "/selectSignedTeacher")
    @ResponseBody
    public Ret selectTeacher(@RequestParam("studentId") Integer studentId,
                             HttpServletRequest request) {

        try {
            TbCwkstudent cwkstudent = new TbCwkstudent();
            cwkstudent.setCwkid(studentId);
            cwkstudent = studentService.selectOne(cwkstudent);
            List<WKTeacher> teachers = tbCwkService.selectSignedTeacher(studentId, (int) cwkstudent.getPianoGrade());
            return Ret.success(teachers);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("msg", e);
            return Ret.error("异常");
        }
    }

    /**
     * @param studentId  用户id
     * @param teachGrade
     * @param university
     * @param minPrice
     * @param maxPrice
     * @param region
     * @return
     * @Describtion 接口：未签约老师列表
     * @author HKJ
     */
    @RequestMapping(value = "/selectNotSignedTeacher")
    @ResponseBody
    public Ret selectNotSignedTeacher(@RequestParam("studentId") Integer studentId,
                                      @RequestParam("pageNum") Integer pageNum,
                                      HttpServletRequest request) {
        String orderBy = request.getParameter("orderBy");
        String teachGrade = request.getParameter("teachGrade");
        String university = request.getParameter("university");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        String region = request.getParameter("region");

        try {
            TeacherParam teacherParam = new TeacherParam();
            teacherParam.setTeachGrade(StringUtils.isEmpty(teachGrade) ? null : teachGrade);
            teacherParam.setMinPrice(StringUtils.isEmpty(minPrice) ? 0 : Float.parseFloat(minPrice));
            teacherParam.setMaxPrice(StringUtils.isEmpty(maxPrice) ? Constants.MAX_PRICE : Float.parseFloat(maxPrice));
            teacherParam.setUniversity(StringUtils.isEmpty(university) ? null : university);
            teacherParam.setStudentId(studentId);
            teacherParam.setRegion(StringUtils.isEmpty(region) ? null : region);
            teacherParam.setPianoGrade(studentService.selectPianoGrade(studentId));

            PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
            if (StringUtils.isEmpty(orderBy)) {
                PageHelper.orderBy("average_score desc");
            } else if (orderBy.equals("high")) {
                PageHelper.orderBy("priceWithMonth DESC");
            } else if (orderBy.equals("low")) {
                PageHelper.orderBy("priceWithMonth ASC");
            }

            List<WKTeacher> teachers = tbCwkService.selectNotSignedTeacher(teacherParam);
            PageInfo<WKTeacher> pageInfo = new PageInfo<WKTeacher>(teachers);
            return Ret.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("异常");
        }
    }

    /**
     * 接口:查询签约相关信息
     *
     * @param pageNum
     * @param request
     * @return
     */
    @RequestMapping(value = "/signedList")
    @ResponseBody
    public Ret selectStudent(@RequestParam("pageNum") Integer pageNum,
                             @RequestParam("username") String username,
                             @RequestParam("branch") Integer branch,
                             HttpServletRequest request) {
        TbCwk tbCwk = tbCwkService.findByName(username);

        try {
            PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
            PageHelper.orderBy("AddTime DESC");
            List<HashMap<String, Object>> resultMap = null;
            if (branch == 0 && "tea".equals(tbCwk.getType())) {
                resultMap = tbCwkService.selectStudent(tbCwk.getId());
            } else if (branch == 0 && "stu".equals(tbCwk.getType())) {
                resultMap = tbCwkService.selectTeacher(tbCwk.getId());
            } else {
                resultMap = historySignService.selectSignedList(tbCwk.getId());
            }
            PageInfo<HashMap<String, Object>> pageInfo = new PageInfo<HashMap<String, Object>>(resultMap);
            return Ret.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("异常");
        }
    }
    
    
    /**
     * @Describe 查询教师列表接口
     * 
     * @param request
     * @param signType			签约类型	null：不查询签约	0：查询未签约	1：查询签约
     * @param studentId			学生Id
     * @param teachGrade		授课级别
     * @param serviceType		服务类型	null：不查询服务	0：批改服务	1：直播服务
     * @param level				课程等级	1：初级	2：中级	3：高级
     * @param pageNum			当前页码
     * @param pageSize			每一页的数量
     * 
     * @author HKJ
     * @return
     */
    @RequestMapping(value = "/teacher/list")
    @ResponseBody
    public Ret getTeacherList(HttpServletRequest request) {
    	//接收参数
    	String signTypeStr = request.getParameter("signType");
    	String studentIdStr = request.getParameter("studentId");
    	String teachGradeStr = request.getParameter("teachGrade");
    	String serviceTypeStr = request.getParameter("serviceType");
    	String levelStr = request.getParameter("level");
    	String pageNumStr = request.getParameter("pageNum");
    	String pageSizeStr = request.getParameter("pageSize");
    	
    	//处理参数
    	Integer signType = StringUtils.isEmpty(signTypeStr) ? null : Integer.valueOf(signTypeStr);
    	Integer studentId = StringUtils.isEmpty(studentIdStr) ? null : Integer.valueOf(studentIdStr);
    	String teachGrade = StringUtils.isEmpty(teachGradeStr) ? null : teachGradeStr;
    	Integer serviceType = StringUtils.isEmpty(serviceTypeStr) ? null : Integer.valueOf(serviceTypeStr);
    	Integer level = StringUtils.isEmpty(levelStr) ? null : Integer.valueOf(levelStr);
    	Integer pageNum = StringUtils.isEmpty(pageNumStr) ? 1 : Integer.valueOf(pageNumStr);
    	Integer pageSize = StringUtils.isEmpty(pageSizeStr) ? Constants.PAGE_SIZE : Integer.valueOf(pageSizeStr);
    	
    	try {
			PageHelper.startPage(pageNum, pageSize);
			List<Map<String, Object>> teacherList = tbCwkService.teacherList(signType, studentId, teachGrade, serviceType, level);
    		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(teacherList);
			
    		return Ret.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("msg", e);
			return Ret.error("查询教师列表异常，请稍后重试");
		}
    	
    	
    }
}



