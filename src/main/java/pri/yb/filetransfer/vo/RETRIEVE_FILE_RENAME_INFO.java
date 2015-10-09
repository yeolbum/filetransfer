package pri.yb.filetransfer.vo;

public class RETRIEVE_FILE_RENAME_INFO {
	

	private String source_name;
	
	private String target_name;

	public String getSource_name() {
		return source_name;
	}

	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}

	public String getTarget_name() {
		return target_name;
	}

	public void setTarget_name(String target_name) {
		this.target_name = target_name;
	}

	@Override
	public String toString() {
		return "RETRIEVE_FILE_RENAME_INFO [source_name=" + source_name
				+ ", target_name=" + target_name + "]";
	}

}
