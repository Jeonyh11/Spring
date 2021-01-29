package kr.or.ddit.user.repository;

import kr.or.ddit.user.model.UserVo;

public interface UserDao {
	
	UserVo getUser(String userid);
}
