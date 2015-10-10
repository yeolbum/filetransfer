package pri.yb.filetransfer.service;

import java.io.File;

import org.springframework.stereotype.Service;

import pri.yb.filetransfer.vo.RETRIEVE_FILE_RENAME_INFO;

@Service
public class FileActionService {
	
	public boolean renameFile(RETRIEVE_FILE_RENAME_INFO req) throws Exception{
		
		File source_file = new File(req.getSource_name());
		
		return source_file.renameTo(new File(source_file.getParent()+"/"+req.getTarget_name()));
	}
	
	public boolean deleteFile(String delete_file_name) throws Exception{
		
		File source_file = new File(delete_file_name);
		
		return source_file.delete();
	}


}
