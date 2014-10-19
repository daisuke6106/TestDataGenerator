package jp.co.dk.testdatagenerator.function;

import java.util.List;

public class FunctionValue extends Value {
	
	protected AbstractFunction function;
	
	public FunctionValue(String name, List<Value> arguments) {
		super(name);
		for (FunctionPattern pattern : FunctionPattern.values()) {
			this.function = pattern.match(name, arguments);
			if (this.function != null) return;
		}
		throw new IllegalArgumentException("該当する関数が存在しません。");
	}
	
	@Override
	public String getValue(long nowIndex) {
		return this.function.getValue(nowIndex);
	}
	
}
