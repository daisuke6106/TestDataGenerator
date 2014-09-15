package jp.co.dk.testdatagenerator.function;

import java.util.Arrays;

public abstract class AbstractFunction {
	
	protected String[] arguments;
	
	AbstractFunction() {
		
	}
	
	AbstractFunction(String... arguments) throws IllegalArgumentException {
		if (arguments == null || arguments.length == 0) throw new IllegalArgumentException(this.getName() + "の引数が不正です。");
		this.arguments = arguments;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getName());
		if (this.arguments == null) {
			sb.append("[]");
		} else {
			sb.append(Arrays.asList(this.arguments).toString());
		}
		return sb.toString();
	}
	protected abstract String getName();
	protected abstract String getValue(long nowCount);
}
