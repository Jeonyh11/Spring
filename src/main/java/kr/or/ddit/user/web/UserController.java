package kr.or.ddit.user.web;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@RequestMapping("allUserTiles")
	public String allUserTiles(Model model) {
		model.addAttribute("userList", userService.selectAllUser());
		
		return "tiles.user.allUser";
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
	
	@RequestMapping(path="detailTiles", method= {RequestMethod.GET})
	public String DetailViewTiles(String userid, Model model) {
		
		UserVo user  = userService.selectUser(userid);
		
		model.addAttribute("user", user);
		
		return "tiles.user.detail";
	}
	
	@RequestMapping("excelDownload")
	public String excelDownload(Model model) {
		List<String> header = new ArrayList<String>();
		header.add("사용자아이디");
		header.add("사용자이름");
		header.add("사용자별명");
		
		model.addAttribute("header", header);
		model.addAttribute("data", userService.selectAllUser());
		
		return "userExcelDownloadView";
	}
	
	@RequestMapping("pagingUserTiles")
	public String pagingUserTiles(@RequestParam(defaultValue = "1") int page,
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
		
		return "tiles.user.pagingUser";
	}
	
	//사용자 리스트가 없는 상태의 화면만 응답으로 생성
	@RequestMapping("pagingUserAjaxView")
	public String pagingUserAjaxView() {
		
		return "tiles.user.pagingUserAjax";
	}
	
	
	@RequestMapping("pagingUserAjax")
	public String pagingUserAjax(@RequestParam(defaultValue = "1") int page,
									@RequestParam(defaultValue = "5")int pageSize,
										Model model) {
		
		
		PageVo pageVo = new PageVo(page, pageSize); 
		
	
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		
		//문자열로 만들어주는 jsonView 쪽
		return "jsonView";
	}
	
	@RequestMapping("pagingUserAjaxHtml")
	public String pagingUserAjaxHtml(@RequestParam(defaultValue = "1") int page,
									@RequestParam(defaultValue = "5")int pageSize,
										Model model) {
		
		
		PageVo pageVo = new PageVo(page, pageSize); 
		
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		
		
		//문자열로 만들어주는 jsonView 쪽
		return "user/pagingUserAjaxHtml";
		
		//pagingUserAjaxHtml => /WEB-INF/views/user/pagingUserAjaxHtml.jsp
	}
	
	
	@RequestMapping("profile")
	public void profile(HttpServletResponse resp, String userid, HttpServletRequest req) {
		resp.setContentType("image");
		//userid 파라미터를 이용하여
		//userService 객체를 통해 사용자의 사진 파일 이름을 획득
		//파일 입출력을 통해 사진을 읽어들여 resp객체의 outputStream으로 응답 생성
	
		
		UserVo userVo = userService.selectUser(userid);
		
		String path = "";
		String filename ="";
		if(userVo.getRealfilename().equals("")) {
			path = req.getServletContext().getRealPath("/image/unknown.png");
			filename = "unknown.png";
		}
		else {
			 path = userVo.getRealfilename();
			 filename = userVo.getFilename();
		}
		logger.debug("path :{}",path);
		
		try {
			
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();
			byte[] buff = new byte[512];
			
			while(fis.read(buff) != -1) {
				sos.write(buff);
			}
			fis.close();
			sos.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("profileDownload")
	public void ProfileDownlaod(HttpServletResponse resp, String userid) {
		
		UserVo userVo = userService.selectUser(userid);
		
		resp.setHeader("Content-Disposition","attachment; filename=" +userVo.getFilename());
		
		String path = userVo.getRealfilename();
		
		try {
			
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();
			byte[] buff = new byte[512];
			
			while(fis.read(buff) != -1) {
				sos.write(buff);
			}
			fis.close();
			sos.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
