package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.service.UserService;

@ContextConfiguration(locations = {"classpath:/kr/or/ddit/ioc/component-scan.xml",	
									"classpath:/kr/or/ddit/config/spring/datasource-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ComponentScanTest {

	@Resource(name="userDao")
	private UserDao userDao;
	//@Repository 어노테이션을 적용한 userDaoImpl 스프링 빈이 정상적으로 컨테이너에 등록되었는지 확인
	
	@Resource(name="userService")
	private UserService userService;
	
	@Test
	public void userDaoImplSpringBeanTest() {
		assertNotNull(userDao);
		
		UserVo userVo = userDao.selectUser("cici");
		
		assertEquals("브라운이욧", userVo.getUsernm());
	}
	
	//userServiceImpl 스프링 빈이 정상적으로 컨테이너에 등록 되었는지 확인
	@Test
	public void userServiceImplSpringBeanTest() {
		assertNotNull(userService);
		
		UserVo userVo = userService.selectUser("cici");
		
		assertEquals("브라운이욧", userVo.getUsernm());
	}

}
