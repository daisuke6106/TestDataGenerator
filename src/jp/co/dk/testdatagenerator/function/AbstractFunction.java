package jp.co.dk.testdatagenerator.function;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractFunction {
	
	protected List<Value> arguments;
	
	AbstractFunction() {
		
	}
	
	AbstractFunction(List<Value> arguments) throws IllegalArgumentException {
		if (arguments == null || arguments.size() == 0) throw new IllegalArgumentException(this.getName() + "の引数が不正です。");
		this.arguments = arguments;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getName());
		if (this.arguments == null) {
			sb.append("[]");
		} else {
			sb.append(this.arguments.toString());
		}
		return sb.toString();
	}
	protected abstract String getName();
	protected abstract String getValue(long nowCount) throws IllegalArgumentException;
}
