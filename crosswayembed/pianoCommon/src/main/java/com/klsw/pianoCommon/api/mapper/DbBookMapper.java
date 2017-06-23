package com.klsw.pianoCommon.api.mapper;

import java.util.List;

import com.klsw.pianoCommon.api.MyMapper;
import com.klsw.pianoCommon.api.model.DbBook;

public interface DbBookMapper extends MyMapper<DbBook> {
	List<DbBook> searchByBookName(String bookName);
	
	List<DbBook> searchBySubjectName(String subjectName);
}