package kr.or.ddit.login;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import kr.or.ddit.test.config.WebTestConfig;

public class LoginControllerTest extends WebTestConfig{
	
	@Test
	public void veiwTest() throws Exception {
		//localhost/login/view + enter ==> GET ¹æ½Ä
		mockMvc.perform(get("/login/view"))
								.andExpect(status().isOk())
								.andExpect(view().name("login"));
	}
	
	@Test
	public void processSucessTest() throws Exception {
		mockMvc.perform(post("/login/process")
				.param("userid","cici")
				.param("pass", "brownPass")
				.param("price", "1000"))
				.andExpect(view().name("main"))
				.andDo(print());
	}
	
	@Test
	public void processFailTest() throws Exception {
		mockMvc.perform(post("/login/process")
				.param("userid","cici")
				.param("pass", "brown")
				.param("price", "1000"))
				.andExpect(view().name("redirect:/login/view"))
				.andDo(print());
	}
	


	
}
