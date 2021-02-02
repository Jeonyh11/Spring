package kr.or.ddit.user.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;

public class UserDaoTest extends ModelTestConfig{
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

	@Resource(name = "userDao")
	private UserDao userDao;
	
//	@Resource(name = "userDao")
//	private userDao userDao;
	
	@Test
	public void getUserTest() {
		/***Given***/
		String userid = "cony";

		/***When***/
		UserVo userVo = userDao.selectUser(userid);

		/***Then***/
		assertEquals("内聪", userVo.getUsernm());
		
	}
	
//	@Test
//	public void getUserTest2() {
//		/***Given***/
//		String userid = "brown";
//
//		/***When***/
//		UserVo userVo = userDao.selectUser(userid);
//		
//		/***Then***/
//		assertEquals("宏扼款", userVo.getUsernm());
//	}
	
	@Test
	public void selectAllUserTest() {
		/***Given***/
		

		/***When***/
		List<UserVo> userVo = userDao.selectAllUser();
		
		/***Then***/
		
		assertNotNull(userVo);
	}
	
	@Test
	public void selectPagingUser() {
		/***Given***/
		 int page = 1;
		 int pageSize = 5;
		PageVo vo = new PageVo(page, pageSize);

		/***When***/
		List<UserVo> userVo = userDao.selectPagingUser(vo );
		
		/***Then***/
		
		assertNotNull(userVo);
	}
	
	@Test
	public void selectAllUserCntTest() {
		/***Given***/
		

		/***When***/
		int userCnt = userDao.selectAllUserCnt();
		
		/***Then***/
		
		assertNotNull(userCnt);
	}
	
	@Test
	public void modifyUserTest() {
		/***Given***/
		UserVo userVo = new UserVo("cici","宏扼款捞具","brownPass",new Date(), "dsf","磅"
									,"林家2","12","fsd","fdsf"); 

		/***When***/
		int userCnt = userDao.modifyUser(userVo);
		
		/***Then***/
		
		assertEquals(1,userCnt);
	}
	
	@Test
	public void insertUserTest() {
		/***Given***/
		UserVo userVo = new UserVo("browns","宏扼款捞具","brownPasss",new Date(), "dsf","磅"
									,"林家2","12","fsd","fdsf"); 

		/***When***/
		int cnt = userDao.insertUser(userVo);
		
		/***Then***/
		
		assertEquals(1,cnt);
	}

	@Test
	public void deleteUserTest() {
		/***Given***/
		String userid = "browns";

		/***When***/
		int cnt = userDao.deleteUser(userid);
		
		/***Then***/
		
		assertEquals(1, cnt);
	}
}
