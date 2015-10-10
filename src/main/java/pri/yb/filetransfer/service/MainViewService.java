package pri.yb.filetransfer.service;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pri.yb.filetransfer.common.YbFileFilter;
import pri.yb.filetransfer.constant.Const;
import pri.yb.filetransfer.utils.StringUtils;
import pri.yb.filetransfer.vo.RETRIEVE_FILES_INFO;
import pri.yb.filetransfer.vo.RETRIVE_DIRECTORY_INFO;

@Service
public class MainViewService {
	
	private static final Logger logger = LoggerFactory.getLogger(MainViewService.class);
	
	private String LAST_MODIFIED_PATTERN = "yyyy. MM. dd  a hh:mm:ss";

	/**
	* <pre>
	* 1. 메소드명 : getFileInfoList
	* 2. 작성일 : 2015. 10. 11. 오전 2:47:54
	* 3. 설명 : 1. 화면에 출력되는 값들을 set
	* </pre>
	* @param directory_path
	* @return
	* @throws Exception
	*/
	public RETRIVE_DIRECTORY_INFO getFileInfoList(String directory_path) throws Exception{
		
		logger.info("directory Path : " + directory_path);
		
		RETRIVE_DIRECTORY_INFO dir_info = new RETRIVE_DIRECTORY_INFO();
		
		List<RETRIEVE_FILES_INFO> files = new ArrayList<RETRIEVE_FILES_INFO>();
		
		files.addAll(getFileFilteredList(directory_path, Const.FILE_FILTER.DIRECOTORY));
		files.addAll(getFileFilteredList(directory_path, Const.FILE_FILTER.FILE));
		
		File curr_directory = new File(directory_path);
		
		dir_info.setRoot_directory(curr_directory.getParentFile() != null);
		dir_info.setCurrent_path(directory_path);
		dir_info.setParent_path(org.apache.commons.lang.StringUtils.replace(curr_directory.getParent(), "\\", "/"));
		dir_info.setFiles(files);
		
		return dir_info;
	}
	
	/**
	* <pre>
	* 1. 메소드명 : getFileFilteredList
	* 2. 작성일 : 2015. 10. 11. 오전 2:46:32
	* 3. 설명 : 테이블에 출력되는 값들을 set
	* </pre>
	* @param directory_path
	* @param file_filter
	* @return
	* @throws Exception
	*/
	private List<RETRIEVE_FILES_INFO> getFileFilteredList(String directory_path, int file_filter ) throws Exception{
		
		File file = new File(directory_path);
		
		List<RETRIEVE_FILES_INFO> filtered_files = new ArrayList<RETRIEVE_FILES_INFO>();
		
		FileFilter filter = new YbFileFilter(file_filter);
		
		for(File f : file.listFiles(filter)){
			
			RETRIEVE_FILES_INFO file_info = new RETRIEVE_FILES_INFO();
			file_info.setFile_name(f.getName());
			file_info.setLast_modified_dt(DateFormatUtils.format(f.lastModified(), LAST_MODIFIED_PATTERN));
			file_info.setFile_size(StringUtils.getFileSize(f.length()));
			file_info.setIs_directory(f.isDirectory());
			file_info.setIs_file(f.isFile());
			
			filtered_files.add(file_info);
			
		}
		
		return filtered_files;
	}

}
