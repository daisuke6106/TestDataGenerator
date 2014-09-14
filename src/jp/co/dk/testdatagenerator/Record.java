package jp.co.dk.testdatagenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


class Record implements HelpPrintable {
	
	/** 出力件数 */
	protected long count;
	
	/** フォーマット */
	protected String format;
	
	/** フォーマット正規表現 */
	protected final Pattern formatPattern = Pattern.compile(",(?=(([^\"]*\"){2})*[^\"]*$)");  
    
	/** カラム一覧 */
	protected List<Column> columns = new ArrayList<Column>();
	
	/** 改行コード */
	protected static String newLine = System.getProperty("line.separator");
	
	Record(long outputCount, String format) throws IllegalAccessException {
		if (outputCount < 1) throw new IllegalAccessException("can't create Record instance. outputCount is invalid.");
		if (format == null)  throw new IllegalAccessException("can't create Record instance. format is not set.");
		this.count  = outputCount;
		this.format = format;
		List<String> columnStrings = Arrays.asList(formatPattern.split(format, -1));
		for (int index=0; index<columnStrings.size(); index++) {
			this.columns.add(new Column(this.count, index, columnStrings.get(index)));
		}
	}
	
	String getValue(long nowIndex) {
		StringBuilder value = new StringBuilder();
		int size = columns.size();
		for (int i=0; i<size; i++) {
			value.append(columns.get(i).getValue(nowIndex));
			if (i!=size-1) {
				value.append(',');
			} else {
				value.append(Record.newLine);
			}
		}
		return value.toString();
	}

	@Override
	public String getName() {
		return "レコードフォーマット";
	}

	@Override
	public String getManualMessage(String linesep) {
		StringBuilder sb = new StringBuilder();
		sb.append("出力するレコード１行あたりのフォーマットを指定する。").append(linesep);
		sb.append("カラムごとにカンマ区切りで各カラムのフォーマットを定義する。").append(linesep);
		sb.append(linesep);
		sb.append("レコードフォーマットの指定方法は以下の通り。").append(linesep);
		sb.append("カラムフォーマット,カラムフォーマット,カラムフォーマット,...").append(linesep);
		return sb.toString();
	}

	@Override
	public String getExample(String linesep) {
		StringBuilder sb = new StringBuilder();
		sb.append("\"aaa\",1234,2001/01/01").append(" と設定した場合、以下のようなレコードが出力される。").append(linesep);
		sb.append("\"aaa\",1234,2001/01/01").append(linesep);
		return null;
	}
}
