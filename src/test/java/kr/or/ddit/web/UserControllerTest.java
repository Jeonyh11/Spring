package kr.or.ddit.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.WebTestConfig;

public class UserControllerTest extends WebTestConfig{

	@Test
	public void pagingTest() throws Exception {
		mockMvc.perform(get("/user/pagingUser"))
				.andExpect(status().isOk())
				.andExpect(view().name("user/pagingUser"))
				.andExpect(model().attributeExists("userList"))
				.andExpect(model().attributeExists("pageVo"))
				.andExpect(model().attributeExists("pagination"))
				.andDo(print());
	}
	
	@Test
	public void test() {
		PageVo pageVo = new PageVo();
		System.out.println("pageVo.getPage() : "+ pageVo.getPage());
		
		int page = 0;
		System.out.println(page);
	}
	
	
	

}
