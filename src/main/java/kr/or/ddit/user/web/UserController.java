package kr.or.ddit.user.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("user")
@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource(name = "userService")
	private UserService userService;
	
	
	@RequestMapping("allUser")
	public String allUser(Model model) {
		model.addAttribute("userList", userService.selectAllUser());
		
		return "user/allUser";
	}
	
	@RequestMapping("pagingUser")
	public String pagingUser(@RequestParam(defaultValue = "1") int page,
							@RequestParam(defaultValue = "5")int pageSize,
							Model model) {
		logger.debug("page : {}, pageSize : {} ", page, pageSize);
		
		PageVo pageVo = new PageVo(page, pageSize); 
		
		Map<String, Object> map = userService.selectPagingUser(pageVo);
		
		int userCnt = (int) map.get("userCnt");
		
		int pagination = (int) Math.ceil((double)userCnt/pageSize);
		
		model.addAttribute("userList", map.get("userList"));		
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageVo", pageVo);
		
		return "user/pagingUser";
	}
//	@RequestMapping("pagingUser")
//	public String pagingUser(PageVo pageVo) {
//		logger.debug("pageVo : {}",pageVo);
//	return "";
//	}
	
	@RequestMapping(path="detail", method= {RequestMethod.GET})
	public String DetailView(String userid, Model model) {
		
		UserVo user  = userService.selectUser(userid);
		
		model.addAttribute("user", user);
		
		return "user/detail";
	}
}
