package kr.or.ddit.user.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("modify")
@Controller
public class ModifyController {
	
	private static final Logger logger = LoggerFactory.getLogger(ModifyController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(path="modi", method = {RequestMethod.GET})
	public String ModifyModi(String userid, Model model) {
		
		UserVo user = userService.selectUser(userid);
		
		model.addAttribute("user", user);
		
		return "user/modify";
	}
	
	@RequestMapping(path="modisend", method= {RequestMethod.POST})
	public String ModifySend(UserVo userVo, MultipartFile profile) throws ParseException {

		
		logger.debug("userVo : {} " , userVo);	
		
		int success = 0;
		
		if(profile.getSize() > 0) {
			String originalFilename = profile.getOriginalFilename();
			String filename = UUID.randomUUID().toString() + "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			
			userVo.setFilename(originalFilename);
			userVo.setRealfilename("d:\\upload\\" + filename);
			
			try {
				profile.transferTo(new File(userVo.getRealfilename()));
				success = userService.modifyUser(userVo);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		String userid = userVo.getUserid();
		
		try {
			success = userService.modifyUser(userVo);
		}catch(Exception e){		
			success = 0;
		}
		if(success == 1) {
			return "redirect:/user/detail?userid="+userid;			
		}else {
			return "redirect:/modify/modi";
		}
	}
	
	@RequestMapping(path ="delete", method= {RequestMethod.POST})
	public String DeleteUser(String userid) {
		
		int success = 0;
		
		
		try {
			success = userService.deleteUser(userid);
		}catch(Exception e) {
			success = -1;
		}
		
		if(success == 1) {
			return "redirect:/user/pagingUser";			
		}else {
			return "redirect:/user/detail?userid="+userid;
		}
		
	}

}
