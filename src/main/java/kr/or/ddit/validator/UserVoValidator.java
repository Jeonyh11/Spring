package kr.or.ddit.validator;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.or.ddit.user.model.UserVo;

public class UserVoValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserVo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//���������� ���
		//������ �ǴܵǴ� ��Ȳ�� üũ�Ͽ� errors�� �߰�
		
		UserVo userVo = (UserVo) target;
		
		//userid ���̰� 5���� �̻�(5���� ���)
		if(userVo.getUserid().length() < 5) {
			errors.rejectValue("userid", "length");
		}
	}



}
