package pri.yb.filetransfer.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pri.yb.filetransfer.service.FileActionService;
import pri.yb.filetransfer.vo.RETRIEVE_FILE_RENAME_INFO;

import com.google.gson.Gson;

@Controller
public class FileActionController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileActionController.class);
	
	@Inject
	private FileActionService fileActionService;

	@RequestMapping(value ="/renameFile.do", method = RequestMethod.POST)
	public  @ResponseBody boolean renameFile(@RequestBody String rename_file_json, ModelAndView mv
			) throws Exception{
		
		RETRIEVE_FILE_RENAME_INFO req = new Gson().fromJson(rename_file_json, RETRIEVE_FILE_RENAME_INFO.class);
		
		logger.info("renameFile info : {}", req.toString() );
		
		return fileActionService.renameFile(req);
	}
	
	@RequestMapping(value ="/deleteFile.do", method = RequestMethod.POST)
	public  @ResponseBody boolean deleteFile(HttpServletRequest request,  ModelAndView mv
			) throws Exception{
		
		String delete_file_name = request.getParameter("delete_file_name");
		
		logger.info("deleteFile : {}", delete_file_name );
		
		return fileActionService.deleteFile(delete_file_name);
	}
	
	@RequestMapping(value ="/downloadFile.do", method = RequestMethod.GET)
	public void downloadFile(@RequestParam(value="file_path", required=true) String file_path, HttpServletResponse response) throws Exception {
		
		logger.info("downloadFile : {}", file_path );
		 
		File file = new File(file_path);
		InputStream is = new FileInputStream(file);
		
		// 한글처리
		String download_filename = new String(file.getName().getBytes("UTF-8"), "ISO-8859-1");
 
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ download_filename + "\"");
		// Read from the file and write into the response
		OutputStream os = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = is.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}
		os.flush();
		os.close();
		is.close();
	}

}
