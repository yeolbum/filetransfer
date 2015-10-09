package pri.yb.filetransfer.vo;

import java.util.List;

public class RETRIVE_DIRECTORY_INFO {
	
	private String current_path;
	
	private String parent_path;
	
	private boolean root_directory;
	
	private List<RETRIEVE_FILES_INFO> files;
	
	

	public String getCurrent_path() {
		return current_path;
	}

	public void setCurrent_path(String current_path) {
		this.current_path = current_path;
	}

	public List<RETRIEVE_FILES_INFO> getFiles() {
		return files;
	}

	public void setFiles(List<RETRIEVE_FILES_INFO> files) {
		this.files = files;
	}

	public String getParent_path() {
		return parent_path;
	}

	public void setParent_path(String parent_path) {
		this.parent_path = parent_path;
	}

	public boolean isRoot_directory() {
		return root_directory;
	}

	public void setRoot_directory(boolean root_directory) {
		this.root_directory = root_directory;
	}
	
	
	
}
