package jp.co.dk.testdatagenerator.function;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum FunctionPattern {
	/** 件数指定 */
	RIGHT("^RIGHT\\((.*?),([0-9]+)\\)$", new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(Pattern pattern, String format) {
			Matcher matcher = pattern.matcher(format);
			matcher.find();
			String value  = matcher.group(1);
			int    length = Integer.parseInt(matcher.group(2));
			return new Right(value, length);
		}
	}),
	
	;
	private Pattern pattern;
	
	private FunctionFactory functionFactory;
	
	FunctionPattern(String pattern, FunctionFactory functionFactory) {
		this.pattern         = Pattern.compile(pattern);
		this.functionFactory = functionFactory;
	}
	
	public boolean match(String format) {
		Matcher matcher = this.pattern.matcher(format);
		return matcher.find();
	}
	
	public AbstractFunction getFunction(String format) {
		return this.functionFactory.createFunction(this.pattern, format);
	}
}
