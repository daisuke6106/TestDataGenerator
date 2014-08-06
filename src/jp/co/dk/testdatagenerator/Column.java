package jp.co.dk.testdatagenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Column {
	
	/** インデックス番号 */
	protected int index; 
	
	protected String datas;
	
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
	 * @param index このカラムのインデックス番号
	 * @param datas このカラムのデータ内容
	 * @throws IllegalArgumentException 引数が不正な場合
	 */
	Column(int index, String datas) throws IllegalArgumentException {
		if (index < 0 || datas == null) throw new IllegalArgumentException("can't create Column instance.");
		this.index = index;
		this.datas = datas;
		String[] patterns = this.datas.split(";");
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
					CountSpecify countSpecify = countSpecifyPattern.getCountSpecify(pattern);
					this.dataList.add(countSpecify);
					break;
				}
			}
		}
	}
	
	List<CountHolder> getEarchCount(long count) throws IllegalArgumentException{
		List<CountHolder> countHolders = new ArrayList<CountHolder>();
		long   summary     = 0;
		long[] earchCounts = new long[this.dataList.size()];
		for (int i=0; i<earchCounts.length; i++) {
			CountSpecify countSpecify = this.dataList.get(i);
			long earchCount = countSpecify.getCount(count);
			countHolders.add(new CountHolder(earchCount, countSpecify));
			summary = summary + earchCount;
		}
		if (count == summary) {
			return countHolders;
		} else if (count < summary) {
			throw new IllegalArgumentException("指定されたカラムの件数では、生成件数を上回る為、作成できません。: " + this.toString());
		} else if (count > summary) {
			int countNothing = 0;
			for (CountHolder countHolder : countHolders) if (countHolder.count == 0) countNothing++;
			if (countNothing == 0) {
				new IllegalArgumentException("指定されたカラムの件数では、生成件数を下回る為、作成できません。: " + this.toString());
			} else if (countNothing == 1) {
				for (CountHolder countHolder : countHolders) if (countHolder.count == 0) countHolder.count = count - summary;
			} else if (countNothing > 1 ) {
				new IllegalArgumentException("件数指定なしは２つ以上設定することはできません。: " + this.toString());
			}
		}
		return countHolders;
	}
	
	public String getCsvColumn() {
		return null;
	}
	
	@Override
	public String toString() {
		return this.datas;
	}
}


class CountHolder {
	
	long count;
	
	CountSpecify countSpecify;
	
	CountHolder(long count, CountSpecify countSpecify) {
		this.count = count;
		this.countSpecify = countSpecify;
	}
	
	void setCount(long count) {
		this.count = count;
	}
}

enum CountSpecifyPattern {
	
	/** 件数指定 */
	ABSOLUTE_SPECIFIED("^@a\\[(.*?)=([0-9]+)\\]$", new CountSpecifyFactory(){
		@Override
		public CountSpecify createCountSpecify(Pattern pattern, String format) {
			Matcher matcher = pattern.matcher(format);
			matcher.find();
			String data        = matcher.group(1);
			String countString = matcher.group(2);
			long   count       = Long.parseLong(countString);
			return new AbsoluteCountSpecify(data, count);
		}
	}),
	
	/** パーセント指定 */
	PERCENTAGE_SPECIFIED("^@p\\[(.*?)=([0-9]+)\\]$", new CountSpecifyFactory(){
		@Override
		public CountSpecify createCountSpecify(Pattern pattern, String format) {
			Matcher matcher = pattern.matcher(format);
			matcher.find();
			String data        = matcher.group(1);
			String countString = matcher.group(2);
			long   count       = Long.parseLong(countString);
			return new PacentageCountSpecify(data, count);
		}
	}),
	
	/** 指定なし */
	NOTHING_SPECIFIED("^.*$", new CountSpecifyFactory(){
		@Override
		public CountSpecify createCountSpecify(Pattern pattern, String format) {
			return new NothingCountSpecify(format);
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
	
	public CountSpecify getCountSpecify(String format) {
		return this.countSpecifyFactory.createCountSpecify(this.pattern, format);
	}
	
}

interface CountSpecifyFactory {
	
	CountSpecify createCountSpecify(Pattern pattern, String format);
	
}