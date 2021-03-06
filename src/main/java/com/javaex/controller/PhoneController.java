package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {
	// [field]
	@Autowired
	private PhoneDao personDao;
	// [construct]

	// [g/s method]

	// [general method]

	// List
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("[PhoneController.list]");

		// PhoneDao pDao = new PhoneDao();

		List<PersonVo> pList = personDao.getPersonList();

		System.out.println(pList);

		// model에 담기 (박스에담기)
		model.addAttribute("pList", pList); // controller --> dispatcherservlet 전달

		return "list"; // DispatcherServlet --> list.jsp로 foward
	}

	// WriteForm
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("[PhoneController.wForm]");

		return "writeForm";
	}

	// WRITE -- ModelAttribte 사용
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo pVo) {
		System.out.println("[PhoneController.write]");

		// @ModelAttribute --> new PersonVo [필드,파라미터 이름이 같으면 자동으로 생성]
		// 디폴트생성자+g/s 로 생성

		System.out.println(pVo);

		// Dao 생성(메소드이용)
		// PhoneDao pDao = new PhoneDao();
		personDao.personInsert(pVo);

		// view -->리다이렉트
		return "redirect:/list";
	}

	// WRITE2 -- 파라미터를 개별로 받을때(-->맵에 저장)
	@RequestMapping(value="/write2", method= {RequestMethod.GET,RequestMethod.POST})
	public String write2(@RequestParam("name") String name,
						 @RequestParam("hp") String hp,
						 @RequestParam("company") String company) {
		System.out.println("[PhoneController.write2]");
		
		int count = personDao.personInsert2(name,hp,company);
		
		return "redirect:/list";
	}

	// 삭제
	@RequestMapping(value = "delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int personId) {
		System.out.println("[PhoneController.Delete]");

		// Dao 생성(메소드이용)
		// PhoneDao pDao = new PhoneDao();
		personDao.personDelete(personId);

		// view -->리다이렉트
		return "redirect:/list";

	}

	// 수정폼
	@RequestMapping(value = "updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(Model model, @RequestParam("no") int personId) {
		System.out.println("[PhoneController.uForm]");

		// PhoneDao pDao = new PhoneDao();
		PersonVo personVo = personDao.getPerson(personId);

		model.addAttribute("personVo", personVo);

		return "updateForm";
	}
	
	// 수정폼2 (Map)
		@RequestMapping(value = "updateForm2", method = { RequestMethod.GET, RequestMethod.POST })
		public String updateForm2(Model model,@RequestParam("no") int personId) {
			System.out.println("[PhoneController.uForm]");

			// PhoneDao pDao = new PhoneDao();
			Map<String, Object> personMap = personDao.getPerson2(personId);

			model.addAttribute("pMap",personMap);
			
			return "updateForm2";
		}

	// 수정
	@RequestMapping(value = "update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(@ModelAttribute PersonVo pVo) {
		System.out.println("[PhoneController.Update]");

		// PhoneDao pDao = new PhoneDao();
		personDao.personUpdate(pVo);

		return "redirect:/list";
	}

	// 그림,코드

	/*********************************
	 * Test
	 ****************************************/
	@RequestMapping(value = "/board/read/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(@PathVariable("no") int boardNo) {
		System.out.println("[PhoneController.read]");
		System.out.println("PathVariable.Test.read");

		// localhost:8088/phoneboo3/board/read/1
		// localhost:8088/phoneboo3/board/read/2
		// localhost:8088/phoneboo3/board/read/3

		System.out.println(boardNo);

		return "";
	}

	@RequestMapping(value = "/test")
	public String test() {
		System.out.println("test");

		return "/WEB-INF/views/test.jsp";
	}

}
