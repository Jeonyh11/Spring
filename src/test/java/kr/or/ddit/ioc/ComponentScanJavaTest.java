package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.config.ComponentScanJavaConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.service.UserService;

@ContextConfiguration(classes = ComponentScanJavaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ComponentScanJavaTest {

	@Resource(name="userDao")
	private UserDao userDao;
	//@Repository ������̼��� ������ userDaoImpl ������ ���� ���������� �����̳ʿ� ��ϵǾ����� Ȯ��
	
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	@Test
	public void userDaoImplSpringBeanTest() {
		assertNotNull(userDao);
		
		UserVo userVo = userDao.getUser("brown");
		
		assertEquals("����", userVo.getUsernm());
	}
	
	//userServiceImpl ������ ���� ���������� �����̳ʿ� ��� �Ǿ����� Ȯ��
	@Test
	public void userServiceImplSpringBeanTest() {
		assertNotNull(userService);
		
		UserVo userVo = userService.getUser("brown");
		
		assertEquals("����", userVo.getUsernm());
	}

}
