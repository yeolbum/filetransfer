package pri.yb.filetransfer.service;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import pri.yb.filetransfer.common.YbFileFilter;
import pri.yb.filetransfer.constant.Const;
import pri.yb.filetransfer.utils.StringUtils;
import pri.yb.filetransfer.vo.RETRIEVE_FILES_INFO;
import pri.yb.filetransfer.vo.RETRIVE_DIRECTORY_INFO;

@Service
public class MainViewService {
	
	private String LAST_MODIFIED_PATTERN = "yyyy. MM. dd  a hh:mm:ss";

	public RETRIVE_DIRECTORY_INFO getFileInfoList(String directory_path) throws Exception{
		
		RETRIVE_DIRECTORY_INFO dir_info = new RETRIVE_DIRECTORY_INFO();
		
		List<RETRIEVE_FILES_INFO> files = new ArrayList<RETRIEVE_FILES_INFO>();
		
		files.addAll(getFileFilteredList(directory_path, Const.FILE_FILTER.DIRECOTORY));
		files.addAll(getFileFilteredList(directory_path, Const.FILE_FILTER.FILE));
		
		File curr_directory = new File(directory_path);
		
		System.out.println();
		
		dir_info.setRoot_directory(curr_directory.getParentFile() != null);
		dir_info.setCurrent_path(directory_path);
		dir_info.setParent_path(curr_directory.getParent());
		dir_info.setFiles(files);
		
		return dir_info;
	}
	
	private List<RETRIEVE_FILES_INFO> getFileFilteredList(String directory_path, int file_filter ) throws Exception{
		
		File file = new File(directory_path);
		
		List<RETRIEVE_FILES_INFO> filtered_files = new ArrayList<RETRIEVE_FILES_INFO>();
		
		FileFilter filter = new YbFileFilter(file_filter);
		
		for(File f : file.listFiles(filter)){
			
			if(f.canRead()){
			
				RETRIEVE_FILES_INFO file_info = new RETRIEVE_FILES_INFO();
				file_info.setFile_name(f.getName());
				file_info.setLast_modified_dt(DateFormatUtils.format(f.lastModified(), LAST_MODIFIED_PATTERN));
				file_info.setFile_size(StringUtils.getFileSize(f.length()));
				file_info.setIs_directory(f.isDirectory());
				file_info.setIs_file(f.isFile());
				
				filtered_files.add(file_info);
			}
			
		}
		
		return filtered_files;
	}

}
