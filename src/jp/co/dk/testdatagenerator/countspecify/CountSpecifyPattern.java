package jp.co.dk.testdatagenerator.countspecify;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.co.dk.testdatagenerator.HelpPrintable;

public enum CountSpecifyPattern implements HelpPrintable {
	/** 件数指定 */
	ABSOLUTE_SPECIFIED(
		"^(.*?)=([0-9]+)$", 
		"件数指定",
		"指定の件数だけ指定の値を出力する場合、値=件数の形で指定してください。",
		"aaa=10${NL}\"bbb\"=10${NL}aaa=10;bbb=10",
		new CountSpecifyFactory(){
			@Override
			public CountSpecify createCountSpecify(long outputCount, Pattern pattern, String format) {
				Matcher matcher = pattern.matcher(format);
				matcher.find();
				String data        = matcher.group(1);
				String countString = matcher.group(2);
				long   count       = Long.parseLong(countString);
				return new AbsoluteCountSpecify(outputCount, data, count);
			}
		}
	),
	
	/** パーセント指定 */
	PERCENTAGE_SPECIFIED("^(.*?)=([0-9]+)%$", 
		"パーセンテージ指定",
		"指定の値を指定の割合で出力する場合、値=数値%の形で指定してください。",
		"aaa=10%${NL}\"bbb\"=10%${NL}aaa=10%;bbb=10%",
		new CountSpecifyFactory(){
			@Override
			public CountSpecify createCountSpecify(long outputCount, Pattern pattern, String format) {
				Matcher matcher = pattern.matcher(format);
				matcher.find();
				String data        = matcher.group(1);
				String countString = matcher.group(2);
				long   count       = Long.parseLong(countString);
				return new PacentageCountSpecify(outputCount, data, count);
			}
		}
	),
	
	/** 指定なし */
	NOTHING_SPECIFIED("^.*$", 
		"件数指定なし",
		"件数を何も指定しない場合、その値のみを記述する形式で指定してください",
		"aaa${NL}\"bbb\"{NL}aaa;bbb",
		new CountSpecifyFactory(){
			@Override
			public CountSpecify createCountSpecify(long outputCount, Pattern pattern, String format) {
				return new NothingCountSpecify(outputCount, format);
			}
		}
	),
	
	;
	protected Pattern pattern;
	
	protected CountSpecifyFactory countSpecifyFactory;
	
	protected String name;
	
	protected String manualMessage;
	
	protected String example;
	
	CountSpecifyPattern(String pattern, String name, String manualMessage, String example, CountSpecifyFactory countSpecifyFactory) {
		this.pattern             = Pattern.compile(pattern);
		this.name                = name;
		this.manualMessage       = manualMessage;
		this.example             = example;
		this.countSpecifyFactory = countSpecifyFactory;
	}
	
	public boolean match(String format) {
		Matcher matcher = this.pattern.matcher(format);
		return matcher.find();
	}
	
	public CountSpecify getCountSpecify(long outputCount, String format) {
		return this.countSpecifyFactory.createCountSpecify(outputCount, this.pattern, format);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getManualMessage(String linesep) {
		return this.manualMessage;
	}

	@Override
	public String getExample(String linesep) {
		return this.example;
	}
}
