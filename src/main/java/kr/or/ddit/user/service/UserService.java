package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageVo;

import kr.or.ddit.user.model.UserVo;

public interface UserService {
	// userid에 해당하는 사용자 한명의 정보 조회
	UserVo selectUser(String userid);
	
	// 전체 사용자 정보 조회
	List<UserVo> selectAllUser();
	
	// 페이지에 맞는 사용자 정보 조회
	Map<String, Object> selectPagingUser(PageVo vo);
	
	
	
	// 사용자 정보 수정
	int modifyUser(UserVo userVo);
	
	// 사용자 등록
	int insertUser(UserVo userVo);

	// 사용자 삭제
	int deleteUser(String userid);
}
