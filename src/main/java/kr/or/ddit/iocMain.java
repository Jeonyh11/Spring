package kr.or.ddit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.service.UserService;

public class iocMain {

	private static final Logger logger = LoggerFactory.getLogger(iocMain.class);
	public static void main(String[] args) {
		//1. ������ ���� ������ �̿��Ͽ� ������ �����̳ʸ� ����
			// ������ �����̳� Ÿ�� : ApplicationContext
		//2. ������ �����̳ʿ��� ������� �������� ��(��ü)�� ��û
			//DL(Dependency Lookup) : ������ �����̳ʿ��� ������ ���� ��û�ϴ� ����
		//3. ������ �����̤ÿ��� �����ǰ� �ִ� ���� �� ����� ������ Ȯ��
			
//		1��			
		ApplicationContext context 
			= new ClassPathXmlApplicationContext("classpath:/kr/or/ddit/ioc/ioc.xml");
		
		UserDao userDao = (UserDao) context.getBean("userDao");
		
		UserVo userVo = userDao.selectUser("brown");
		
		logger.debug("userVo : {}",userVo);
		
		//������ �����̳ʷ� ���� userService ������ ���� DL�� ���� ������ 
		// getUser �޼ҵ带 call, ��ȯ�� �� (userVo)�� logg�� ���� ���
		
		UserService userService = (UserService) context.getBean("userService");
	
		UserVo userVo2 = userService.selectUser("brown");
		
		logger.debug("userVo2 : {}", userVo2);
		
		for(String beanName : context.getBeanDefinitionNames()) {
			logger.debug("beanName  : {}",beanName);
		}
		
	}
}
