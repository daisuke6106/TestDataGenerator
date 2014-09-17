package jp.co.dk.testdatagenerator.function;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum FunctionPattern {
	
	/** 右から指定文字数を切り出す。 */
	RIGHT("^RIGHT\\((.*?);([0-9]+)\\)$",
		"RIGHT",
		"指定の文字を右から指定の文字数だけ切り取ります。",
		"RIGHT(ABCDE;3)",
		new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(String[] arguments) {
			return new Right(arguments);
		}
	}),
	
	/** 左から指定文字数を切り出す。 */
	LEFT("^LEFT\\((.*?);([0-9]+)\\)$",
		"LEFT",
		"指定の文字を左から指定の文字数だけ切り取ります。",
		"LEFT(ABCDE;3)",
		new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(String[] arguments) {
			return new Left(arguments);
		}
	}),
	
	/** 現在の行番号を返します。 */
	ROW("^ROW\\(\\)$",
		"ROW",
		"現在の行番号を返します。",
		"ROW()",
		new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(String[] arguments) {
			return new Row();
		}
	}),
	
	/** 文字列を結合する。 */
	CONCATENATE("^CONCATENATE\\((.*?);(.*?)\\)$",
		"CONCATENATE",
		"文字列を結合する。",
		"CONCATENATE(aaa;bbb)",
		new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(String[] arguments) {
			return new Concatenate(arguments);
		}
	}),
	
	/** 加算した結果を返します。 */
	ADD("^ADD\\((.*?);(.*?)\\)$",
		"ADD",
		"加算した結果を返します。",
		"ADD(10;20)",
		new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(String[] arguments) {
			return new Addition(arguments);
		}
	}),
	
	/** 減算した結果を返します。 */
	SUBTRACT("^SUBTRACT\\((.*?);(.*?)\\)$",
		"SUBTRACT",
		"減算した結果を返します。",
		"SUBTRACT(10;20)",
		new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(String[] arguments) {
			return new Subtraction(arguments);
		}
	}),
	
	/** 乗算した結果を返します。 */
	MULTIPLY("^MULTIPLY\\((.*?);(.*?)\\)$",
		"MULTIPLY",
		"乗算した結果を返します。",
		"MULTIPLY(10;20)",
		new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(String[] arguments) {
			return new Multiplication(arguments);
		}
	}),
	
	/** 除算した結果を返します。 */
	DIVIDE("^DIVIDE\\((.*?);(.*?)\\)$",
		"DIVIDE",
		"除算した結果を返します。",
		"DIVIDE(10;20)",
		new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(String[] arguments) {
			return new Division(arguments);
		}
	}),
	
	/** 剰余した結果を返します。 */
	REMAIND("^REMAIND\\((.*?);(.*?)\\)$",
		"REMAIND",
		"剰余した結果を返します。",
		"REMAIND(10;20)",
		new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(String[] arguments) {
			return new Remainder(arguments);
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
//		String[] arguments = pattern.split(format, -1);
		return this.functionFactory.createFunction(arguments);
	}
	
	public String getName() {
		return this.name;
	}

	public String getManualMessage(String linesep) {
		return this.manualMessage;
	}

	public String getExample(String linesep) {
		return this.example;
	}
	
}
