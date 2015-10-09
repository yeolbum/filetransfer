package pri.yb.filetransfer.common;

import java.io.File;
import java.io.FileFilter;

import pri.yb.filetransfer.constant.Const;

public class YbFileFilter implements FileFilter{
	
	private int filter_type;
	
	public YbFileFilter(int filter_type) {
		this.filter_type = filter_type;
	}

	@Override
	public boolean accept(File pathname) {
		
		if(filter_type == Const.FILE_FILTER.DIRECOTORY){
			return pathname.isDirectory();
		}else if(filter_type == Const.FILE_FILTER.FILE){
			return pathname.isFile();
		}else{
			return false;
		}
	}

}
