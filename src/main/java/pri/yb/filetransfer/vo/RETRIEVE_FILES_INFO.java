package pri.yb.filetransfer.vo;

public class RETRIEVE_FILES_INFO {
	
	private String file_name;
	
	private String last_modified_dt;
	
	private String file_size;
	
	private boolean is_directory;
	
	private boolean is_file;

	
	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getLast_modified_dt() {
		return last_modified_dt;
	}

	public void setLast_modified_dt(String last_modified_dt) {
		this.last_modified_dt = last_modified_dt;
	}

	public String getFile_size() {
		return file_size;
	}

	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}

	public boolean isIs_directory() {
		return is_directory;
	}

	public void setIs_directory(boolean is_directory) {
		this.is_directory = is_directory;
	}

	public boolean isIs_file() {
		return is_file;
	}

	public void setIs_file(boolean is_file) {
		this.is_file = is_file;
	}

}
