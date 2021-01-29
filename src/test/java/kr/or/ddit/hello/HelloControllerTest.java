package kr.or.ddit.hello;

import static org.junit.Assert.*;

import javax.annotation.Resource;

/*
 *  java - gui swing, awt, java fx 
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(locations = {"classpath:/kr/or/ddit/config/spring/application-context.xml",
									"classpath:/kr/or/ddit/config/spring/root-context.xml"})

@WebAppConfiguration       //������ ȯ���� Web ����� application Context�� ����
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloControllerTest {
	
	//helloController ���� ���ҽ��� �޾Ҵµ� �װ����� ������ @Resource(name="userService")�� userDao �ΰ��� �ʿ��ؼ�
	// @ContextConfiguration(locations = �� �ΰ� ������Ѵ� ����� xml�� �������� �ҷ��ͼ� ����� ���� �� �ְ��Ѵ�.
//	@Resource(name="helloController")
//	private HelloController helloController;
		
	//������ �� �߿� ���� ������ Ÿ���� ������ ���� �����Ѵ�
	//���� ������ Ÿ���� ������ ���� ������ ���� ��� @Qulifier ������̼��� ����
	// Ư�� ������ ���� �̸��� ��Ī�� �� �ִ�.
	//     ==>Resource ������̼� �ϳ��� ������� ���� ����
	
	@Autowired      //TYPE
	private HelloController helloController;
	
	@Test
	public void helloControllerTest() {
		assertNotNull(helloController);
	}

}
