package kr.or.ddit.user.repository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;

// bean id ="" class=""
//@Repository에서 별다른 설정을 하지 않으면 스프링 빈 이름으로 class 이름에서 첫글자를 소문자로 한
// 문자열이 스프링 빈의 이름을 설정 된다
// UserDaoImpl ==> userDaoImpl


//  UserDao  / UserDaoImpl  ==> @Repository(name ="userDaoImpl")
//  UserDaoI / UserDao  ==> @Repository(name ="userDao")

@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public UserVo selectUser(String userid) {
			
		return template.selectOne("users.selectUser", userid);
		
	}

	@Override
	public List<UserVo> selectAllUser() {
		List<UserVo> userList = template.selectList("users.selectAllUser");
		return userList;
	}

	@Override
	public List<UserVo> selectPagingUser(PageVo vo) {
		List<UserVo> userList = template.selectList("users.selectPagingUser", vo);
		return userList;
	}

	@Override
	public int selectAllUserCnt() {
		int cnt = template.selectOne("users.selectAllUserCnt");
		return cnt;
	}

	@Override
	public int modifyUser(UserVo userVo) {
		int updateCnt = template.update("users.modifyUser", userVo);
		
		
//		if(updateCnt == 1) {
//			template.commit();
//		} else {
//			template.rollback();
//		}
		return updateCnt;
	}

	@Override
	public int insertUser(UserVo userVo) {
		int cnt = template.insert("users.insertUser", userVo);
		
		return cnt;
	}

	@Override
	public int deleteUser(String userid) {
		int cnt = template.delete("users.deleteUser", userid);
		
	
		return cnt;
	}

}
