package com.klsw.piano.service.database;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klsw.piano.service._BaseService;
import com.klsw.pianoCommon.api.mapper.DbBookMapper;
import com.klsw.pianoCommon.api.mapper.DbCountMapper;
import com.klsw.pianoCommon.api.model.DbBook;
import com.klsw.pianoCommon.api.model.DbCount;

@Service
public class BookService extends _BaseService<DbBook> {
	
	@Autowired
	private DbBookMapper bookMapper;
	
	@Autowired
	private DbCountMapper countMapper;
	
	/**
	 * 根据书名搜索对应的曲谱书
	 * @param bookName
	 * @return
	 */
	public List<DbBook> searchByBookName(String bookName) {
		return bookMapper.searchByBookName(bookName);
	}
	
	/**
	 * 根据分类名搜索对应的曲谱书
	 * @param bookName
	 * @return
	 */
	public List<DbBook> searchBySubjectName(String subjectName) {
		return bookMapper.searchBySubjectName(subjectName);
	}

	/**
	 * 根据曲谱书列表修改统计表中的统计次数
	 * @param bookList
	 */
	public void addSearchNum(List<DbBook> bookList) {
		DbCount count = null;
		
		for(DbBook book : bookList) {
			count = new DbCount();
			count.setName(book.getBookName());
			count.setLabel(book.getBookLabel());
			
			count = countMapper.selectOne(count);
			
			if(count == null) {
				count = new DbCount();
				count.setType(1);
				count.setName(book.getBookName());
				count.setCount(1);
				count.setAddTime(new Date());
				countMapper.insert(count);
			}
			
			count.setCount(count.getCount() + 1);
			count.setModifyTime(new Date());
			countMapper.updateByPrimaryKeySelective(count);
		}
	}
}
