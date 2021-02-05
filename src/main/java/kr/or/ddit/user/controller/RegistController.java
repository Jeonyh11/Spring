package kr.or.ddit.user.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.validator.UserVoValidator;

@RequestMapping("regist")
@Controller
public class RegistController {
	private static final Logger logger = LoggerFactory.getLogger(RegistController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(path = "view", method= {RequestMethod.GET})
	public String registView() {
		
		return "user/regist";
	}
	
	@RequestMapping(path = "viewLANG", method= {RequestMethod.GET})
	public String registLANGView() {
		
		return "tiles.user.regist";
	}
	
	//BindingResult 객체는 command 객체 바로 뒤에 인자로 기술해야한다.
	@RequestMapping(path="save", method= {RequestMethod.POST})
	public String registSave(@Valid UserVo userVo, BindingResult result, MultipartFile profile) {
		
		//new UserVoValidator().validate(userVo, result);
		
		if(result.hasErrors()) {
			logger.debug("result has error");
			return"user/regist";
		}
		
		if(profile.getSize() > 0) {		
			String originalFilename = profile.getOriginalFilename();
			String filename = UUID.randomUUID().toString() + "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			
			userVo.setFilename(originalFilename);
			userVo.setRealfilename("d:\\upload\\" + filename);
			userVo.setReg_dt(new Date());
		try {
			profile.transferTo(new File("d:\\upload\\"+ profile.getOriginalFilename()));
			} catch(IllegalStateException | IOException e){		
				e.printStackTrace();
			}
		}

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
