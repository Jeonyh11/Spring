package kr.or.ddit.ioc.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.ioc.CollectionBean;
import kr.or.ddit.ioc.DbConfig;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.repository.UserDaoImpl;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.user.service.UserServiceImpl;

//������ �����ӿ�ũ���� �ش� �ڹ� ������
//������ ���� �������� �˷��ش�
@ImportResource("classpath:/kr/or/ddit/config/spring/datasource-context.xml")
@PropertySource(value = {"classpath:/kr/or/ddit/config/db/dbinfo.properties"})
@Configuration
public class IocJavaConfig {
	
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;

	//�޼ҵ� : ������ ������ ���� ��ü�� ��ȯ�ϴ� �޼ҵ带 ����
	//        �޼ҵ忡 @Bean ������̼��� ����
	//		  @Bean ������̼ǿ� ���ٸ� �ɼ��� �������� ������ ������ ������ ���� �̸���
	//        �޼ҵ� �̸����� ����ȴ�  (@Bean ������̼��� name �Ӽ��� ���� ������ �� �̸� ���� ����)
	
	@Bean
	public UserDao userDao() {
		return new UserDaoImpl();
	}
	
	@Bean
	public UserServiceImpl userService() {
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserDao(userDao());
		
		return userService;
	}
	
	@Bean
	public UserServiceImpl userServiceCons() {
		return new UserServiceImpl(userDao());
	}
	
	/*<!--  prototype : �ش� ���� dl, di�� ������ �Ź� ���Ӱ� ���� ��ü�� ��ȯ -->
	<bean id="userServicePrototype" class="kr.or.ddit.user.service.UserServiceImpl" 
		  scope="prototype">
		<property name="userDao" ref="userDao"/>
	</bean>*/
	
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	//@Scope("prototype")
	@Bean
	public UserServiceImpl userServicePrototype() {
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserDao(userDao());
		return userService;
	}
	

	/*<bean id="collectionBean" class="kr.or.ddit.ioc.CollectionBean">
	<property name="list">
		<list>
			<value>brown</value>
			<value>sally</value>
			<value>cony</value>
		</list>			
	</property> */
	@Bean
	public CollectionBean collectionBean() {
		CollectionBean collectionBean = new CollectionBean();
		List<String> list = new ArrayList<String>();
		list.add("brown");
		list.add("sally");
		list.add("cony");
		
		collectionBean.setList(list);
		return collectionBean;
	}
	
	/*
	<bean id="dbConfig" class="kr.or.ddit.ioc.DbConfig">
	
		<property name="driverClassName" value="${jdbc.driverClassName}"/>
		<property name="url" value="${jdbc.url}"/>
		<property name="username" value="${jdbc.username}"/>
		<property name="password" value="${jdbc.password}"/>
	</bean>
	*/
	
	@Bean
	public DbConfig dbConfig() {
		DbConfig dbConfig = new DbConfig();
		dbConfig.setUrl(url);
		dbConfig.setDriverClassName(driverClassName);
		dbConfig.setUsername(username);
		dbConfig.setPassword(password);
		
		return dbConfig;
	}
}











