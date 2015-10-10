package pri.yb.filetransfer.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pri.yb.filetransfer.service.MainViewService;

@Controller
public class MainViewController {
	
	@Inject
	private MainViewService mainViewService;
	
	private static final Logger logger = LoggerFactory.getLogger(MainViewController.class);
	
	@RequestMapping(value = "/movePath.do", method = RequestMethod.GET)
	public ModelAndView movePath(HttpServletRequest request, ModelAndView mv) throws Exception{
		
		String path = request.getParameter("path");
		path = path == null ? "C:/" : path;		// default C드라이브
		
		logger.info("movePath : {}", path);
		
		mv.addObject("dir_info", mainViewService.getFileInfoList(path) );
		mv.setViewName("file_explore");
		
		return mv;
	}
	
	
	
}
