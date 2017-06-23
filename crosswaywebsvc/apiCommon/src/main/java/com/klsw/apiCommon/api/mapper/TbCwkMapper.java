package com.klsw.apiCommon.api.mapper;

import com.klsw.apiCommon.api.MyMapper;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.apiCommon.api.model.TeacherParam;
import com.klsw.apiCommon.api.model.WKStudent;
import com.klsw.apiCommon.api.model.WKTeacher;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TbCwkMapper extends MyMapper<TbCwk> {

    Integer selectId(@Param("username") String username, @Param("type") String type);

    List<WKTeacher> selectSignedTeacher(@Param("studentId") Integer studentId, @Param("pianoGrade") Integer pianoGrade);

    List<WKTeacher> selectNotSignedTeacher(TeacherParam teacherParam);

    List<HashMap<String, Object>> selectStudent(@Param("teacherId") Integer teacherId);

    WKStudent studentSelf(@Param("id") Integer Id);

    List<HashMap<String, Object>> selectTeacher(@Param("studentId") Integer studentId);

    WKTeacher teacherSelf(@Param("id") Integer Id);

    List<Map<String, Object>> selectTeacherListByliveroomInfo(@Param("teachGrade") String teachGrade, @Param("level") Integer level);

    HashMap<String, Object> getUserInfo(@Param("userId") Integer userId);
    
    List<Map<String, Object>> teacherList(@Param("signType")Integer signType, 
    									  @Param("studentId")Integer studentId, 
    									  @Param("teachGrade")String teachGrade, 
    									  @Param("serviceType")Integer serviceType, 
    									  @Param("level")Integer level);
    
    List<WKStudent> studentList();
}