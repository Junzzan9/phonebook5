package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		sqlSession.insert("phonebook.personInsert", personVo);

		return 1;
	}

	public int personInsert2(String name, String hp, String company) {

		Map<String, Object> personMap = new HashMap<String, Object>();

		personMap.put("name", name);
		personMap.put("hp", hp);
		personMap.put("company", company);

		int count = sqlSession.insert("phonebook.personInsert2", personMap);

		return count;
	}

	public int personDelete(int personId) {

		sqlSession.delete("phonebook.personDelete", personId);

		return 1;
	}

	public PersonVo getPerson(int personId) {

		PersonVo personVo = sqlSession.selectOne("phonebook.selectPerson", personId);

		return personVo;
	}

	public Map<String, Object> getPerson2(int personId) {

		Map<String, Object> personMap = sqlSession.selectOne("phonebook.selectPerson2", personId);

		return personMap;
	}

	public int personUpdate(PersonVo personVo) {

		sqlSession.update("phonebook.personUdate", personVo);

		return 1;
	}
}
