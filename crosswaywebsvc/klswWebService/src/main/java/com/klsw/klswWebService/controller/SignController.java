package com.klsw.klswWebService.controller;

import com.klsw.apiCommon.api.model.Ret;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.apiCommon.api.model.TbCwkbean;
import com.klsw.apiCommon.api.model.TbCwkhomeworksign;
import com.klsw.klswWebService.service.HistorySignService;
import com.klsw.klswWebService.service.HomeworkSignService;
import com.klsw.klswWebService.service.TbCwkBeanService;
import com.klsw.klswWebService.service.TbCwkService;
import com.klsw.klswWebService.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liukun on 2016/12/23.
 */
@Controller
@RequestMapping(value = "/sign")
public class SignController {

    private static final Logger log = LoggerFactory.getLogger(SignController.class);

    @Autowired
    private HistorySignService historySignService;

    @Autowired
    private TbCwkService tbCwkService;

    @Autowired
    private HomeworkSignService homeworkSignService;

    @Autowired
    private TbCwkBeanService tbCwkBeanService;

    /**
     * 进行签约
     *
     * @param studentId 学生ID
     * @param teacherId 老师ID
     * @param signtype  签约类型
     * @param params    参数
     * @return 签约结果
     * @throws Exception
     */
    @RequestMapping(value = "sign", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Ret sign(@RequestParam("studentId") Integer studentId,
                    @RequestParam("teacherId") Integer teacherId,
                    @RequestParam("signtype") Integer signtype,
                    @RequestParam Map<String, String> params) throws Exception {
        try {
            //查询签约双方（教师、学生）信息
            TbCwk teacher = tbCwkService.selectByPrimaryKey(teacherId);
            TbCwk student = tbCwkService.selectByPrimaryKey(studentId);
            
            //检查签约信息（用户是否存在、类型是否匹配）
            if (teacher == null || student == null || !"stu".equals(student.getType()) || !"tea".equals(teacher.getType())) {
                return Ret.warn("签约信息有误");
            }
            
            /**********  判断签约类型并做相应处理     **********/
            if (signtype == 1) {//试用类型
            	//查询该学生已使用过的试用次数
                TbCwkhomeworksign sign = new TbCwkhomeworksign();
                sign.setCwkstudentid(studentId);
                sign.setType(1);
                List<TbCwkhomeworksign> list = homeworkSignService.select(sign);
                
                //如果试用次数用完，则不能再和任意老师进行试用
                if (list.size() >= Constants.TRY_USE_COUNT) {
                    return Ret.warn("您的试用次数已用完");
                }
                
                //查询该学生和该老师有无试用记录
                sign.setCwkteacherid(teacherId);
                list = homeworkSignService.select(sign);
                
                //如果已经试用过该老师，则不能再次试用
                if (list.size() > 0) {
                    return Ret.warn("您已试用过该老师");
                }
                
                //获取老师试用单价
                Float price = teacher.getPrice() == null ? 0 : teacher.getPrice();
                Double totalPrice = price * 1.0;
                
                //判断学生威豆余额是否够用
                if (student.getCwkbeancount() < price) {
                    return Ret.warn("您的威豆余额不足,请及时充值");
                }
                
                //设置签约参数并插入签约记录
                sign = new TbCwkhomeworksign();
                sign.setType(1);
                sign.setCwkteacherid(teacherId);
                sign.setCwkstudentid(studentId);
                sign.setAddtime(new Date());
                sign.setOneprice(price);
                sign.setTotalprice(totalPrice);
                sign.setUsedcount(0);//设置已使用次数
                sign.setIsfirstsign((byte) 0);//设置为首签
                sign.setTimesperday(1);//设置每日提交次数，试用规定为1次
                sign.setIsoverdue(false);
                sign.setEndtime(new Date());
                
                //如果添加不成功
                if (homeworkSignService.insertSelective(sign) != 1) {
                    return Ret.error("试用签约失败");
                }
                
                //插入一条签约历史记录
                historySignService.insertByHomeworkSign(sign);
                
                //更新学生剩余威豆数
                int studentRemainBeanCount = (int) (student.getCwkbeancount() - totalPrice);
                student.setCwkbeancount(studentRemainBeanCount);
                if (tbCwkService.updateByPrimaryKey(student) != 1) {
                    return Ret.error("试用签约失败");
                }
                
                //更新教师剩余威豆数
                int teacherRemainBeanCount = (int) (teacher.getCwkbeancount() + totalPrice);
                teacher.setCwkbeancount(teacherRemainBeanCount);
                if (tbCwkService.updateByPrimaryKey(teacher) != 1) {
                    return Ret.error("试用签约失败");
                }
                
                //插入一条威豆收支明细
                //学生明细
                TbCwkbean tbCwkbean = new TbCwkbean();
                tbCwkbean.setAddtime(new Date());
                tbCwkbean.setChangenumber((int) (double) totalPrice);
                tbCwkbean.setCwkid(studentId);
                tbCwkbean.setChangetype(0);
                tbCwkbean.setRemainbean(studentRemainBeanCount);
                tbCwkbean.setDescribtion("签约扣除");
                tbCwkBeanService.insertUseGeneratedKeys(tbCwkbean);
                //老师明细
                tbCwkbean = new TbCwkbean();
                tbCwkbean.setDescribtion("签约获取");
                tbCwkbean.setChangenumber((int) (double) totalPrice);
                tbCwkbean.setAddtime(new Date());
                tbCwkbean.setChangetype(1);
                tbCwkbean.setCwkid(teacherId);
                tbCwkbean.setRemainbean(teacherRemainBeanCount);
                tbCwkBeanService.insertUseGeneratedKeys(tbCwkbean);
                return Ret.success("S", "试用签约成功", null);
                
            } else if (signtype == 0) { //包月类型
                //获取签约月数和每日提交次数，并判断是否为数字
                String months = params.get("months");
                String timesperday = params.get("timesperday");
                if (!StringUtils.isNumeric(months) || !StringUtils.isNumeric(timesperday)) {
                    return Ret.warn("签约信息有误");
                }
                //将月数和每日提交次数进行类型转换
                Integer monthsInt = Integer.parseInt(months);
                Integer timesperdayInt = Integer.parseInt(timesperday);
                
                //获取包月签约单价
                Float price = teacher.getPricewithmonth();
                
                //查询该学生和该老师有无包月记录
                TbCwkhomeworksign sign = new TbCwkhomeworksign();
                sign.setType(0);
                sign.setCwkstudentid(studentId);
                sign.setCwkteacherid(teacherId);
                List<TbCwkhomeworksign> list = homeworkSignService.select(sign);
                boolean isfirstSign = true;
                if (list.size() > 0) {//如果有过签约记录，则需要进一步判断是首签还是续签
                    for (TbCwkhomeworksign entity : list) {
                        if (entity.getEndtime().after(new Date())) {//如果有未到期的签约记录
                            return Ret.warn("您已签约该老师");
                        }
                        //续签处理,在该到期签约上进行更新
                        sign = entity;
                    }
                    //设置为续签
                    isfirstSign = false;
                }
                
                //计算签约总价并判断学生威豆余额是否够用
                Double totalPrice = SignController.getTotalPrice(monthsInt, timesperdayInt, teacher.getPricewithmonth());
                if (student.getCwkbeancount() < totalPrice) {
                    return Ret.warn("您的威豆不足,请及时充值");
                }
                
                //设置签约参数
                sign.setType(0);
                sign.setMonths(monthsInt);
                sign.setCwkteacherid(teacherId);
                sign.setCwkstudentid(studentId);
                sign.setAddtime(new Date());
                sign.setOneprice(price);
                sign.setTotalprice(totalPrice);
                sign.setTimesperday(timesperdayInt);
                sign.setUsedcount(0);
                //设置到期时间
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH, monthsInt);
                Date endtime = calendar.getTime();
                sign.setEndtime(endtime);
                sign.setTimestoday(timesperdayInt);
                sign.setIsoverdue(false);
                int a;
                if (isfirstSign) {
                    sign.setIsfirstsign((byte) 0);
                    a = homeworkSignService.insertSelective(sign);
                } else {
                    sign.setIsfirstsign((byte) 1);
                    a = homeworkSignService.updateByPrimaryKey(sign);
                }

                //插入一条签约历史记录
                historySignService.insertByHomeworkSign(sign);

                //如果添加不成功
                if (a != 1) {
                    return Ret.error("签约失败");
                }
                
                //更新学生剩余威豆
                int studentRemainBeanCount = (int) (student.getCwkbeancount() - totalPrice);
                student.setCwkbeancount(studentRemainBeanCount);
                if (tbCwkService.updateByPrimaryKey(student) != 1) {
                    return Ret.error("签约失败");
                }
                
                //更新教师剩余威豆
                int teacherRemainBeanCount = (int) (teacher.getCwkbeancount() + totalPrice);
                teacher.setCwkbeancount(teacherRemainBeanCount);
                if (tbCwkService.updateByPrimaryKey(teacher) != 1) {
                    return Ret.error("签约失败");
                }
                
                //插入威豆交易明细
                //学生明细
                TbCwkbean tbCwkbean = new TbCwkbean();
                tbCwkbean.setAddtime(new Date());
                tbCwkbean.setChangenumber((int) (double) totalPrice);
                tbCwkbean.setCwkid(studentId);
                tbCwkbean.setChangetype(0);
                tbCwkbean.setRemainbean(studentRemainBeanCount);
                tbCwkbean.setDescribtion("签约扣除");
                tbCwkBeanService.insertUseGeneratedKeys(tbCwkbean);
                //老师明细
                tbCwkbean = new TbCwkbean();
                tbCwkbean.setDescribtion("签约获取");
                tbCwkbean.setChangenumber((int) (double) totalPrice);
                tbCwkbean.setAddtime(new Date());
                tbCwkbean.setChangetype(1);
                tbCwkbean.setCwkid(teacherId);
                tbCwkbean.setRemainbean(teacherRemainBeanCount);
                tbCwkBeanService.insertUseGeneratedKeys(tbCwkbean);
                
                return Ret.success("S", "签约成功", null);
            }
        } catch (Exception e) {
            log.error("msg",e);
            e.printStackTrace();
            throw e;
        }
        return Ret.error("签约失败");
    }


//    @RequestMapping(value = "signedList")
//    @ResponseBody
//    public Ret signList(@RequestParam Map<String, String> params) {
//        List<TbCwkhomeworksign> signedList = null;
//        PageHelper.startPage(1, 10);
//        try {
//            Integer cwkId = tbCwkService.selectId(params.get("username"), params.get("type"));
//            signedList = homeworkSignService.signedList(cwkId);
//            PageInfo<TbCwkhomeworksign> pageInfo = new PageInfo<>(signedList);
//            return Ret.success(pageInfo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Ret.error("查询签约列表失败");
//        }
//
//    }


    public static double getTotalPrice(int months,int timesPerDay,float priceWithMonth) {
    	double totalPrice = 0;
    	totalPrice = months * (double)priceWithMonth;
    	
    	return totalPrice;
    }
}
