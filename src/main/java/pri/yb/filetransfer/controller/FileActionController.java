package pri.yb.filetransfer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pri.yb.filetransfer.vo.RETRIEVE_FILE_RENAME_INFO;

import com.google.gson.Gson;

@Controller
public class FileActionController {

	@RequestMapping(value ="/renameFile.do", method = RequestMethod.POST)
	public  ModelAndView renameFile(@RequestBody String rename_file_json, ModelAndView mv
			) throws Exception{
		
		RETRIEVE_FILE_RENAME_INFO req = new Gson().fromJson(rename_file_json, RETRIEVE_FILE_RENAME_INFO.class);
		
		System.out.println(req.toString());
		
		return mv;
	}

}
