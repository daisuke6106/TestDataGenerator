package jp.co.dk.testdatagenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Column {
	
	/** 出力件数 */
	protected long outputCount;
	
	/** インデックス番号 */
	protected int index; 
	
	/** データ内容 */
	protected String datas;
	
	/** ランダムパターン */
	protected static Pattern randomPattern = Pattern.compile("^@r\\[(.*?)\\]$");
	
	/** 値取り出し時にランダムにするか */
	protected boolean isRandom = false;
	
	/** 生成対象データの種類一覧 */
	protected List<CountSpecify> dataList = new ArrayList<CountSpecify>();
	
	/**
	 * コンストラクタ<p/>
	 * このカラムのインデックス番号と、カラムのデータ内容を元にカラム情報を作成する。<br/>
	 * カラムのデータ内容は以下のように記述します。
	 * 
	 * ・単一のデータ=a
	 * ・複数のデータ=a;b
	 * ・複数のデータで件数指定=@a[a=50];@a[b=25]
	 * ・複数のデータで割合指定=@p[a=50];@[b=25]
	 * 
	 * @param outputCount 出力件数
	 * @param index このカラムのインデックス番号
	 * @param datas このカラムのデータ内容
	 * @throws IllegalArgumentException 引数が不正な場合
	 */
	Column(long outputCount, int index, String datas) throws IllegalArgumentException {
		if (outputCount <0 || index < 0 || datas == null) throw new IllegalArgumentException("can't create Column instance.");
		this.outputCount = outputCount;
		this.index = index;
		this.datas = datas;
		
		String copyData = datas;
		
		Matcher randomMatcher = randomPattern.matcher(this.datas);
		if (randomMatcher.find()) {
			this.isRandom = true;
			copyData = randomMatcher.group(1);
		}
		
		String[] patterns = copyData.split(";");
		if (this.datas.endsWith(";")) {
			String[] patternN = new String[patterns.length+1];
			for (int i=0; i<patterns.length; i++) patternN[i] = patterns[i];
			patternN[patternN.length-1] = "";
			patterns = patternN;
		}
		for (int i=0; i<patterns.length; i++) {
			String pattern = patterns[i];
			for (CountSpecifyPattern countSpecifyPattern : CountSpecifyPattern.values()) {
				if (countSpecifyPattern.match(pattern)) {
					CountSpecify countSpecify = countSpecifyPattern.getCountSpecify(this.outputCount, pattern);
					this.dataList.add(countSpecify);
					break;
				}
			}
		}
	}
	
	String getValue(long nowIndex) {
		if (this.isRandom) Collections.shuffle(this.dataList);
		for (CountSpecify countSpecify : this.dataList) {
			String value = countSpecify.getValue();
			if (value != null) return value;
		}
		return "";
	}
	
	@Override
	public String toString() {
		return this.datas;
	}
}

enum CountSpecifyPattern {
	
	/** 件数指定 */
	ABSOLUTE_SPECIFIED("^@a\\[(.*?)=([0-9]+)\\]$", new CountSpecifyFactory(){
		@Override
		public CountSpecify createCountSpecify(long outputCount, Pattern pattern, String format) {
			Matcher matcher = pattern.matcher(format);
			matcher.find();
			String data        = matcher.group(1);
			String countString = matcher.group(2);
			long   count       = Long.parseLong(countString);
			return new AbsoluteCountSpecify(outputCount, data, count);
		}
	}),
	
	/** パーセント指定 */
	PERCENTAGE_SPECIFIED("^@p\\[(.*?)=([0-9]+)\\]$", new CountSpecifyFactory(){
		@Override
		public CountSpecify createCountSpecify(long outputCount, Pattern pattern, String format) {
			Matcher matcher = pattern.matcher(format);
			matcher.find();
			String data        = matcher.group(1);
			String countString = matcher.group(2);
			long   count       = Long.parseLong(countString);
			return new PacentageCountSpecifyTest(outputCount, data, count);
		}
	}),
	
	/** 指定なし */
	NOTHING_SPECIFIED("^.*$", new CountSpecifyFactory(){
		@Override
		public CountSpecify createCountSpecify(long outputCount, Pattern pattern, String format) {
			return new NothingCountSpecify(outputCount, format);
		}
	}),
	
	;
	private Pattern pattern;
	
	private CountSpecifyFactory countSpecifyFactory;
	
	CountSpecifyPattern(String pattern, CountSpecifyFactory countSpecifyFactory) {
		this.pattern             = Pattern.compile(pattern);
		this.countSpecifyFactory = countSpecifyFactory;
	}
	
	public boolean match(String format) {
		Matcher matcher = this.pattern.matcher(format);
		return matcher.find();
	}
	
	public CountSpecify getCountSpecify(long outputCount, String format) {
		return this.countSpecifyFactory.createCountSpecify(outputCount, this.pattern, format);
	}
	
}

interface CountSpecifyFactory {
	
	CountSpecify createCountSpecify(long outputCount, Pattern pattern, String format);
	
}