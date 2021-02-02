package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageVo;

import kr.or.ddit.user.model.UserVo;

public interface UserService {
	// userid�� �ش��ϴ� ����� �Ѹ��� ���� ��ȸ
	UserVo selectUser(String userid);
	
	// ��ü ����� ���� ��ȸ
	List<UserVo> selectAllUser();
	
	// �������� �´� ����� ���� ��ȸ
	Map<String, Object> selectPagingUser(PageVo vo);
	
	
	
	// ����� ���� ����
	int modifyUser(UserVo userVo);
	
	// ����� ���
	int insertUser(UserVo userVo);

	// ����� ����
	int deleteUser(String userid);
}
