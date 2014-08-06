package jp.co.dk.testdatagenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class Record {
	
	/** フォーマット */
	protected String format;
	
	/** フォーマット正規表現 */
	protected final Pattern formatPattern = Pattern.compile(",(?=(([^\"]*\"){2})*[^\"]*$)");  
    
	/** カラム一覧 */
	protected List<Column> columns = new ArrayList<Column>();
	
	Record(String format) throws IllegalAccessException {
		if (format == null || format.equals("")) throw new IllegalAccessException("can't create Record instance. format is not set.");
		this.format = format;
		List<String> columnStrings = Arrays.asList(formatPattern.split(format, -1));
		for (int index=0; index<columnStrings.size(); index++) {
			this.columns.add(createColumn(index, columnStrings.get(index)));
		}
	}
	
	protected Column createColumn(int index, String columnString) {
		return new Column(index, columnString);
	}
	
	String getCsvRecord() {
		StringBuilder csvRecord = new StringBuilder();
		int size = columns.size();
		for (int i=0; i<size; i++) {
			csvRecord.append(columns.get(i).getCsvColumn());
			if (i!=size-1) csvRecord.append(',');
		}
		return csvRecord.toString();
	}
}

class OrderRecord extends Record {
	
	protected long count;
	
	OrderRecord(long count, String format) throws IllegalAccessException {
		super(format);
		this.count = count;
	}
	
	
	
}