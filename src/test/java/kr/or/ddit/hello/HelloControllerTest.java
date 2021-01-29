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

@WebAppConfiguration       //스프링 환경을 Web 기반의 application Context로 생성
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloControllerTest {
	
	//helloController 으로 리소스를 받았는데 그곳으로 가보면 @Resource(name="userService")와 userDao 두개가 필요해서
	// @ContextConfiguration(locations = 를 두개 적어야한다 기능을 xml에 적어논곳을 불러와서 기능이 사용될 수 있게한다.
//	@Resource(name="helloController")
//	private HelloController helloController;
		
	//스프링 빈 중에 대입 가능한 타입의 스프링 빈을 주입한다
	//만약 동일한 타입의 스프링 빈이 여러개 있을 경우 @Qulifier 어노테이션을 통해
	// 특정 스프링 빈의 이름을 지칭할 수 있다.
	//     ==>Resource 어노테이션 하나를 사용했을 때와 동일
	
	@Autowired      //TYPE
	private HelloController helloController;
	
	@Test
	public void helloControllerTest() {
		assertNotNull(helloController);
	}

}
