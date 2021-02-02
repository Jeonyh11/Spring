package kr.or.ddit.user.repository;

import java.util.List;

import kr.or.ddit.common.model.PageVo;

import kr.or.ddit.user.model.UserVo;

public interface UserDao {
	
	// ��ü ����� ���� ��ȸ
		List<UserVo> selectAllUser();
		
		// userid�� �ش��ϴ� ����� �Ѹ��� ���� ��ȸ
		UserVo selectUser(String userid);
		
		// �������� �´� ����� ���� ��ȸ
		List<UserVo> selectPagingUser(PageVo vo);
		
		// ����� ��ü �� ��ȸ
		int selectAllUserCnt();
		
		// ����� ���� ����
		int modifyUser(UserVo userVo);
		
		// ����� ���
		int insertUser(UserVo userVo);

		// ����� ����
		int deleteUser(String userid);
}
