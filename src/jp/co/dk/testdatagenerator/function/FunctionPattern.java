package jp.co.dk.testdatagenerator.function;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.co.dk.testdatagenerator.HelpPrintable;

public enum FunctionPattern implements HelpPrintable {
	
	/** 右から指定文字数を切り出す。 */
	RIGHT("^RIGHT\\((.*?),([0-9]+)\\)$",
		"RIGHT",
		"指定の文字を右から指定の文字数だけ切り取ります。",
		"RIGHT(ABCDE,3)",
		new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(String[] arguments) {
			return new Right(arguments);
		}
	}),
	
	;
	protected Pattern pattern;
	
	protected FunctionFactory functionFactory;
	
	protected String name;
	
	protected String manualMessage;
	
	protected String example;
	
	FunctionPattern(String pattern, String name, String manualMessage, String example, FunctionFactory functionFactory) {
		this.pattern         = Pattern.compile(pattern);
		this.name            = name;
		this.manualMessage   = manualMessage;
		this.example         = example;
		this.functionFactory = functionFactory;
	}
	
	public AbstractFunction match(String format) {
		if (format == null) return null;
		Matcher matcher = this.pattern.matcher(format);
		if (!matcher.find()) return null;
		String[] arguments = new String[matcher.groupCount()];
		for (int i=0; i<arguments.length; i++) {
			arguments[i] = matcher.group(i+1);
		}
		return this.functionFactory.createFunction(arguments);
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
