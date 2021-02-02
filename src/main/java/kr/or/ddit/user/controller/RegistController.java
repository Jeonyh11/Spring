package kr.or.ddit.user.controller;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("regist")
@Controller
public class RegistController {
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(path = "view", method= {RequestMethod.GET})
	public String registView() {
		
		return "user/regist";
	}
	
	
	@RequestMapping(path="save", method= {RequestMethod.POST})
	public String registSave(String userid, String usernm, String alias, String pass, String addr1,
								String addr2, String zipcode, MultipartFile profile) {
		
		String filename = profile.getOriginalFilename();
		
		if(profile.getSize() > 0) {
		try {
			profile.transferTo(new java.io.File("d:\\upload\\"+ profile.getOriginalFilename()));
			} catch(IllegalStateException | IOException e){		
				e.printStackTrace();
			}
		}
		
		UserVo userVo = new UserVo(userid, usernm, pass, new Date(), alias, addr1, addr2, zipcode,
				filename, null);
		
		int success = 0;
		
		try {
			success = userService.insertUser(userVo);
		}catch(Exception e) {
			success = 0;
		}
		if(success == 1) {
			return "redirect:/user/pagingUser";
		}else {   
			return "redirect:/user/regist";
		}
		
	}
}
