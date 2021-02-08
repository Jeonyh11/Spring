package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;


//스프링 환경에서 junit 코드를 실행 ==> junit 코드도 스프링 빈으로 등록

public class UserServiceTest extends ModelTestConfig{
	
	@Resource(name = "userService")
	private UserService userService;
	
	
	@Before
	public void setUp() { 
		UserVo userVo= new UserVo("ddit_n","테스트","brownPass",new Date(), "테스트","테스트"
				,"주소2","12","fsd.png","fdsf");
		
		userService.insertUser(userVo);
		
		userService.deleteUser("ddit_n");
	}

	
	@Test
	public void getUserTest2() {
		/***Given***/
		String userid = "coco";

		/***When***/
		UserVo userVo = userService.selectUser(userid);
		
		/***Then***/
		assertEquals("coco", userVo.getUsernm());
	}
	
	@Test
	public void selectAllUserTest() {
		/***Given***/
		

		/***When***/
		List<UserVo> userList = userService.selectAllUser();
		
		/***Then***/
		assertNotNull(userList);

}
	
	@Test
	public void selectPagingTest() {
		/***Given***/
		PageVo vo = new PageVo(1,5);

		/***When***/
		Map<String, Object> paging = userService.selectPagingUser(vo);
		List<UserVo> userList = (List<UserVo>) paging.get("userList");
		int userCnt = (int) paging.get("userCnt");
		
		/***Then***/
		assertEquals(5,userList.size());
		assertNotNull(userCnt);
	}
	
	
	@Test
	public void modifyUserTest() {
		/***Given***/
		UserVo userVo = new UserVo("cici","브라운이욧","brownPass",new Date(), "dsf","곰"
				,"주소2","12","fsd","fdsf");

		/***When***/
		int modi = userService.modifyUser(userVo);

		/***Then***/
		assertEquals(1, modi);
	}
	
	@Test
	public void insertUserTest() {
		/***Given***/
		UserVo userVo = new UserVo("chacha","브라운이욧","chacha",new Date(), "dsf","곰"
				,"주소2","12","fsd","fdsf");

		/***When***/
		int ins = userService.insertUser(userVo);

		/***Then***/
		assertEquals(1, ins);
	}
	
	@Test
	public void deleteUserTest() {
		/***Given***/
		String userid = "chacha";

		/***When***/
		int dele = userService.deleteUser(userid);

		/***Then***/
		assertEquals(1, dele);
	}
	
	

}
