package kr.or.ddit.hello;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.user.service.UserService;

@RequestMapping("hello")
@Controller
public class HelloController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Resource(name="userService")
	private UserService userService;
	
	@ModelAttribute(name="rangers")
	public List<String> rangers(){
		List<String> list = new ArrayList<String>();
			list.add("brown");
			list.add("sally");
			list.add("james");
			list.add("cony");
			list.add("moon");
		
	return list;
	}
	
	// localhost/hello/view
	@RequestMapping("view")
	public String view(Model model, @ModelAttribute(name = "rangers") List<String> rangers,
											HttpServletRequest request) {		
		logger.debug("HelloController.view() : {} ",userService.selectUser("cony"));
		logger.debug("ragers ; {}",rangers);
		
		//request.setAttribute("userVo", userService.getUser("brown"));
		model.addAttribute("userVo", userService.selectUser("cony"));
			return "hello";
	}
	
	//hello/path/subpath
	@RequestMapping("path/{subpath}")
	public String pathVariable(@PathVariable("subpath") String subpath, Model model,
							@RequestHeader(name = "User-Agent") String userAgent) {
		logger.debug("userAgent : {}",userAgent);
		model.addAttribute("subpath", subpath);
		return "hello";
	}
}
