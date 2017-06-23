package com.klsw.apiCommon.api.mapper;


import com.klsw.apiCommon.api.MyMapper;
import com.klsw.apiCommon.api.model.DbBook;

import java.util.List;

public interface DbBookMapper extends MyMapper<DbBook> {
	List<DbBook> searchByBookName(String bookName);
	
	List<DbBook> searchBySubjectName(String subjectName);
}