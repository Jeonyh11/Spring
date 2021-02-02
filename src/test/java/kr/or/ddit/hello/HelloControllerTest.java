package kr.or.ddit.hello;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.annotation.Resource;

import org.junit.Before;

/*
 *  java - gui swing, awt, java fx 
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.config.WebTestConfig;
import kr.or.ddit.user.model.UserVo;

@ContextConfiguration(locations = {"classpath:/kr/or/ddit/config/spring/application-context.xml",
									"classpath:/kr/or/ddit/config/spring/root-context.xml"})

@WebAppConfiguration       //������ ȯ���� Web ����� application Context�� ����
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloControllerTest extends WebTestConfig{
	
	//helloController ���� ���ҽ��� �޾Ҵµ� �װ����� ������ @Resource(name="userService")�� userDao �ΰ��� �ʿ��ؼ�
	// @ContextConfiguration(locations = �� �ΰ� ������Ѵ� ����� xml�� �������� �ҷ��ͼ� ����� ���� �� �ְ��Ѵ�.
//	@Resource(name="helloController")
//	private HelloController helloController;
		
	//������ �� �߿� ���� ������ Ÿ���� ������ ���� �����Ѵ�
	//���� ������ Ÿ���� ������ ���� ������ ���� ��� @Qulifier ������̼��� ����
	// Ư�� ������ ���� �̸��� ��Ī�� �� �ִ�.
	//     ==>Resource ������̼� �ϳ��� ������� ���� ����
	
	//  DispatcherServlet ==> MockMvc ������ ���ؼ�
	//						  WebApplicationContext ��ü�� �ʿ�
    //					  ==> ������ �����ӿ�ũ���� ioc �����̳� ������ �ڵ����� ����
	
	
	
	//localhost/hello/view
	@Test
	public void viewTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/hello/view"))
							.andExpect(status().isOk())
							.andExpect(view().name("hello"))
							.andExpect(model().attributeExists("userVo"))
							.andDo(print())
							.andReturn();
		
		ModelAndView mav =  mvcResult.getModelAndView();
		
		assertEquals("hello", mav.getViewName());
		UserVo userVo = (UserVo) mav.getModel().get("userVo");
		assertEquals("�ڴ�", userVo.getUsernm());
		//��밪�� �Ѷ������� �ϼ����״�.
	}
	
	@Test
	public void pathVariableTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/hello/path/brown"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("subpath"))
				.andDo(print())
				.andReturn();
	}
	
	
	

}
