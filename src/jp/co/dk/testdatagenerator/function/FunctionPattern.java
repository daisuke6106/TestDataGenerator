package jp.co.dk.testdatagenerator.function;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum FunctionPattern {
	
	/** 右から指定文字数を切り出す。 */
	RIGHT("RIGHT",
		"指定の文字を右から指定の文字数だけ切り取ります。",
		"RIGHT(ABCDE;3)",
		new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(List<Value> arguments) {
			return new Right(arguments);
		}
	}),
	
	/** 左から指定文字数を切り出す。 */
	LEFT("LEFT",
		"指定の文字を左から指定の文字数だけ切り取ります。",
		"LEFT(ABCDE;3)",
		new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(List<Value> arguments) {
			return new Left(arguments);
		}
	}),
	
	/** 現在の行番号を返します。 */
	ROW("ROW",
		"現在の行番号を返します。",
		"ROW()",
		new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(List<Value> arguments) {
			return new Row();
		}
	}),
	
	/** 文字列を結合する。 */
	CONCATENATE("CONCATENATE",
		"文字列を結合する。",
		"CONCATENATE(aaa;bbb)",
		new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(List<Value> arguments) {
			return new Concatenate(arguments);
		}
	}),
	
	/** 加算した結果を返します。 */
	ADD("ADD",
		"加算した結果を返します。",
		"ADD(10;20)",
		new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(List<Value> arguments) {
			return new Addition(arguments);
		}
	}),
	
	/** 減算した結果を返します。 */
	SUBTRACT("SUBTRACT",
		"減算した結果を返します。",
		"SUBTRACT(10;20)",
		new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(List<Value> arguments) {
			return new Subtraction(arguments);
		}
	}),
	
	/** 乗算した結果を返します。 */
	MULTIPLY("MULTIPLY",
		"乗算した結果を返します。",
		"MULTIPLY(10;20)",
		new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(List<Value> arguments) {
			return new Multiplication(arguments);
		}
	}),
	
	/** 除算した結果を返します。 */
	DIVIDE("DIVIDE",
		"除算した結果を返します。",
		"DIVIDE(10;20)",
		new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(List<Value> arguments) {
			return new Division(arguments);
		}
	}),
	
	/** 剰余した結果を返します。 */
	REMAIND("REMAIND",
		"剰余した結果を返します。",
		"REMAIND(10;20)",
		new FunctionFactory(){
		@Override
		public AbstractFunction createFunction(List<Value> arguments) {
			return new Remainder(arguments);
		}
	}),
	
	;
	
	protected FunctionFactory functionFactory;
	
	protected String name;
	
	protected String manualMessage;
	
	protected String example;
	
	FunctionPattern(String name, String manualMessage, String example, FunctionFactory functionFactory) {
		this.name            = name;
		this.manualMessage   = manualMessage;
		this.example         = example;
		this.functionFactory = functionFactory;
	}
	
	public AbstractFunction match(String name, List<Value> arguments) {
		if (name == null || arguments == null) ;
		if (this.name.equals(name)) return this.functionFactory.createFunction(arguments);
		return null;
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

