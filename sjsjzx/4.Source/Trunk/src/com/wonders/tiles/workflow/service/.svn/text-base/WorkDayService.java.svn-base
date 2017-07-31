package com.wonders.tiles.workflow.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;



import com.wonders.utils.PropertyUtils;

public class WorkDayService {
	
	private static WorkDayService instance = new WorkDayService();

	public static WorkDayService getInstance() {
		if (instance == null)
			instance = new WorkDayService();
 
		return instance;
	}
	
	private WorkDayService() {
	}
	
	// 应用环境目录
	private static String appPath = PropertyUtils.getProperty("app.path");

	private static String txtSavePath = appPath + PropertyUtils.getProperty("workday.mapping.path");

	// 星期
	private static String weeksStr[] = new String[] { "一", "二", "三", "四", "五","六", "日" };

	public void defaultHoliday(){
		
	}
	/**
	 * 构造文件名：文件路径+年份+businessDay.txt
	 */
	public static String creatFileName() {
		// 获得系统当前的时间
		Date nowDate = new Date(System.currentTimeMillis());
		// 获得当前的年份
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String tempDateYear = sdf.format(nowDate);

		String fileName = txtSavePath + tempDateYear + "workDay.txt";
		return fileName;
	}
	

	/**
	 * 将工作日期写入TXT文档，以“年份+businessDay”命名
	 * @param businessDay
	 * @throws IOException
	 */
	public void outputFile(String businessDay) throws IOException {
		// 文件名称以“年份+businessDay.txt”命名
		String fileName = (String) creatFileName();
		// 写文件
		FileOutputStream fos = new FileOutputStream(fileName);

		if (null != businessDay) {
			String[] dayInfo = businessDay.split(",");
			if (null != dayInfo && dayInfo.length > 0) {
				for (int i = 0; i < dayInfo.length; i++) {
					fos.write((dayInfo[i] + ",\r\n").getBytes());
				}
			}
		}
		fos.close();
	}
	/**
	 * 按照年月查询当月的日期
	 * @param year
	 * @param month
	 * @return
	 * @throws IOException
	 */
	public Map queryDateByMonth(String year, String month) throws IOException {
		Map returnMap = new HashMap();
		// 构造返回的工作日List
		List businessDayList = new ArrayList();
		// 构造返回的节假日List
		List holidayList = new ArrayList();
		// 读取文件
		String readTxtFile = readTxtFile();
		// 根据文件内容构造数组
		String dateStr[] = readTxtFile.split(",");
		// 定义2个临时的变量，供循环使用
		String tempStr;
		String tempCheck[];
		if (null != dateStr) {
			for (int i = 0; i < dateStr.length - 1; i++) {
				tempStr = dateStr[i];
				tempCheck = tempStr.split("/");
				// 拼接需要查询的月份
				String queryDate = year + "-" + month;
				// System.out.println(tempCheck[0].trim().substring(0,7));
				if ((queryDate).equals(tempCheck[0].trim().substring(0, 7))) {
					if ("00".equals(tempCheck[2])) {
						holidayList.add(tempCheck[0].trim() + "/"+ tempCheck[1]);
					} else {
						businessDayList.add(tempCheck[0].trim() + "/"+ tempCheck[1]);
					}
				}
			}
		}
		returnMap.put("holidayList", holidayList);
		returnMap.put("businessDayList", businessDayList);
		return returnMap;
	}
	
	/**
	 * 预读工作日文件
	 * @return
	 * @throws IOException
	 */
	public  String readTxtFile() throws IOException {
		// 获得文件名（路径）
		String filename = (String) creatFileName();
        //判断文件是否存在,不存在则生成文件
		//checkByFilePath(filename);
		// 声明2个变量
		String readStr = "";
		String read;
		try {
			FileReader fileread = new FileReader(filename);
			BufferedReader bufread = new BufferedReader(fileread);
			while ((read = bufread.readLine()) != null) {
				readStr = readStr + read + "\r\n";
			}
			bufread.close();
			fileread.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return readStr;
	}
	/**
	 * 获得页面的日期数组，整理
	 * @param businessDayList
	 * @param holidayList
	 * @return
	 */
	public Map tidyDate(List businessDayList, List holidayList) {
		Map modifyDate = new HashMap();
		String tempStr = "";
		String tempDate = "";
		if (null != holidayList && holidayList.size() > 0) {
			for (int i = 0; i < holidayList.size(); i++) {
				tempStr = (String) holidayList.get(i);
				tempDate = tempStr.concat("/00");
				modifyDate.put(tempStr.split("/")[0], tempDate);
			}
		}
		if (null != businessDayList && businessDayList.size() > 0) {
			for (int j = 0; j < businessDayList.size(); j++) {
				tempStr = (String) businessDayList.get(j);
				tempDate = tempStr.concat("/01");
				modifyDate.put(tempStr.split("/")[0], tempDate);
			}
		}
		return modifyDate;
	}
	/**
	 * 根据查询的List替换文件内容
	 * 
	 * @param businessDayList
	 * @param holidayList
	 */
	public void replaceTxtByList(List businessDayList, List holidayList) {
		// 获得文件名
		String filename = (String) creatFileName();
		//判断文件是否存在，不存在则生成文件
		File file = new File(filename);
		//替换工作日，假期
		replaceDate(file,businessDayList, "01");
		replaceDate(file,holidayList, "00");
	}
	
	/**
	 * 根据已知的日期，查询 N 个工作日后的日期
	 * @param dateStr 日期格式必须为yyyy-mm-dd
	 * @param count
	 * @return
	 */
	public String queryDate(String dateStr, int count) {
		String temp = "";
		String returnStr="";
		int num=0;
		boolean start = false;
		//中间间隔天数,从周末开始
		int internal=0;
		try {
			// 获得文件名
			String filename = (String) creatFileName();
			File file = new File(filename);
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			String[] record = new String[100000];
			int index = 0;
			// 保存该行前面的内容
			for (int j = 1; (temp = br.readLine()) != null
					&& !temp.equals(returnStr); j++) {
				//处理开始日期为周末的情况
				if(dateStr.equals(temp.split("/")[0]) && "00,".equals(temp.split("/")[2])){
					internal = 1;
					continue;
				}
				if(internal > 0 && "00,".equals(temp.split("/")[2])){
					internal = internal+1;
					continue;
				} 
				
				if(dateStr.equals(temp.split("/")[0]) || internal > 0 && "01,".equals(temp.split("/")[2])){
					start =true;
					internal = 0;
					if(count < 0){
						returnStr = record[index+count];
						break;
					}
				}
				//向后
				if(start){
					if("01,".equals(temp.split("/")[2])){
						num++;
					}
					if(num==count){
						returnStr = temp.split("/")[0];
						break;
					}
				}
				//向前
				if(count < 0){
					if("01,".equals(temp.split("/")[2])){
					record[index]=temp.split("/")[0];
					index++;
					}
				}
			}

			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnStr;
	}

	/**
	 * 根据已知的日期，查询 N 个工作日后的日期
	 * @param dateStr 日期格式必须为yyyy-mm-dd
	 * @param count
	 * @return
	 */
	public Date queryDate(Date curDate, int count) {
		String nextDateStr = this.queryDate(date2String(curDate), count);
		return string2Date(nextDateStr,"yyyy-MM-dd");
	}

	/**
	 * 将日期转换成字符格式
	 * 
	 * @param date
	 *            java.util.Date类型
	 * @param format
	 *            如果为null或""，默认为DATE格式
	 * @return 无法成功转换则返回null
	 */
	public static String date2String(java.util.Date date) {
		String result = null;
		if (date == null) {
			return result;
		}
		try {
			result = DateFormatUtils.format(date, "yyyy-MM-dd");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}
	/**
	 * 将字符串转换成日期格式

	 * 
	 * 
	 * @param str
	 *            需要转换的字符串

	 * 
	 * @param format
	 *            相应的转换格式，如果参数为Blank则表示按常规的三种格式转换

	 * 
	 * @return 如果不能正常转换则返回null
	 */
	public static java.util.Date string2Date(String str, String format) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		Date result = null;
		String[] formats = new String[3];
		formats[0] = "yyyy-MM-dd";
		formats[1] = "yyyy-MM-dd HH:mm:ss";
		formats[2] = "HH:mm:ss";
		
		try {
			result = org.apache.commons.lang.time.DateUtils.parseDate(str, formats);
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return result;
	}
	/**
	 * 根据已知的日期，查询 N 个工作日后的日期
	 * @param dateStr 日期格式必须为yyyy-mm-dd
	 * @param count
	 * @return
	 */
	public int queryDateCount(Date startDate, Date endDate) {
		return queryDate(date2String(startDate), date2String(endDate));
	}
	
	/**
	 * 根据已知的日期，查询 N 个工作日后的日期
	 * @param dateStr 日期格式必须为yyyy-mm-dd
	 * @param count
	 * @return
	 */
	public int queryDate(String startDateStr, String endDateStr) {
		String temp = "";
		String returnStr="";
		int num=0;
		boolean start = false;
		try {
			// 获得文件名
			String filename = (String) creatFileName();
			File file = new File(filename);
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			// 保存该行前面的内容
			for (int j = 1; (temp = br.readLine()) != null
					&& !temp.equals(returnStr); j++) {
				if(startDateStr.equals(temp.split("/")[0])){
					start =true;
				}
				if(start){
					if(endDateStr.equals(temp.split("/")[0])){
						returnStr = temp.split("/")[0];
						start =false;
					}else{
						System.out.println(temp);
						if("01,".equals(temp.split("/")[2])){
							num++;
						}
					}
				}
			}

			br.close();
			isr.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	/**
	 * 将List中的日期标识替换为mark
	 * @param dayList
	 * @param mark
	 */
	public void replaceDate(File file ,List dayList, String mark) {
		String temp = "";
		String tempMark = "/" + mark + ",";
		int count = 0;
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer buf = new StringBuffer();
			String oldStr = "";
			boolean hasReplaceStr = false;
			if (null != dayList && dayList.size() > 0) {
				// 保存该行前面的内容
				for (int j = 1; (temp = br.readLine()) != null
						&& !temp.equals(oldStr); j++) {
					count++;
					for (int i = 0; i < dayList.size(); i++) {
						oldStr = (String) dayList.get(i);
						if ((temp.split("/")[0] + "/" + temp.split("/")[1])
								.equals(oldStr)) {
							buf = buf.append(oldStr + tempMark);
							buf = buf.append(System
									.getProperty("line.separator"));
							hasReplaceStr = true;
						}
					}
					if (hasReplaceStr == false) {
						buf = buf.append(temp);
						buf = buf.append(System.getProperty("line.separator"));
					}
					hasReplaceStr = false;
				}
				// 保存该行后面的内容
				while ((temp = br.readLine()) != null) {
					buf = buf.append(System.getProperty("line.separator"));
					buf = buf.append(temp);
				}
			}
			br.close();
			FileOutputStream fos = new FileOutputStream(file);
			PrintWriter pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();
			pw.close();
			fis.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}}
