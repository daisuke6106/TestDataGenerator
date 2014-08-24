package jp.co.dk.testdatagenerator.countspecify;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CountSpecifyPattern {
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
			return new PacentageCountSpecify(outputCount, data, count);
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
