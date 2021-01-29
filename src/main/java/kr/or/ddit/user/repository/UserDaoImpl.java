package kr.or.ddit.user.repository;

import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;

// bean id ="" class=""
//@Repository���� ���ٸ� ������ ���� ������ ������ �� �̸����� class �̸����� ù���ڸ� �ҹ��ڷ� ��
// ���ڿ��� ������ ���� �̸��� ���� �ȴ�
// UserDaoImpl ==> userDaoImpl


//  UserDao  / UserDaoImpl  ==> @Repository(name ="userDaoImpl")
//  UserDaoI / UserDao  ==> @Repository(name ="userDao")

@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Override
	public UserVo getUser(String userid) {
		//  ������ �����ͺ��̽����� ��ȸ�� �ؾ��ϳ� ���� �ʱ�ܰ��
		// ������ �Ϸ���� ����, ���� Ȯ���Ϸ��� �ϴ� ����� ������ �����̳ʿ� ������ ���߱� ����
		// new �����ڸ� ���� ������ vo��ü�� ��ȯ
		
		
		return new UserVo("brown", "����");
	}

}
