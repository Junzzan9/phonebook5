package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {

	@Autowired
	private SqlSession sqlSession;

	// 전체리스트가져오기
	public List<PersonVo> getPersonList() {

		// DB에 요청해서 데이터가져오기

		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList");
		System.out.println(personList);

		return personList;
	}

	public int personInsert(PersonVo personVo) {

		sqlSession.insert("phonebook.personInsert",personVo);

		return 1;
	}
}
