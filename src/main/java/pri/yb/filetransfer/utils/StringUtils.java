package pri.yb.filetransfer.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class StringUtils {

	public static String getFileSize(long file_size) throws Exception{
		
		final long kbytes = 1024l;
		
		if(file_size == 0) return "-";
		
		String rtn_value = "";
		
		if(file_size > kbytes){ 
			rtn_value = numberToCommaNumber(( file_size / kbytes )) + "KB";
			
		}else{
			rtn_value = file_size + "B";
		}
		
		return rtn_value;
		
	}
	
	public static String numberToCommaNumber(long num) throws Exception{
		
		DecimalFormat df = new DecimalFormat();
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setGroupingSeparator(',');
		
		df.setDecimalFormatSymbols(dfs);
		
		return df.format(num);
	}

}
