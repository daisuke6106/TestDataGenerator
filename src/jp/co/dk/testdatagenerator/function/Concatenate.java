package jp.co.dk.testdatagenerator.function;

import java.util.List;

public class Concatenate extends AbstractFunction {
	
	Concatenate(List<Value> arguments) throws IllegalArgumentException {
		super(arguments);
		if (arguments == null || arguments.size() == 0) throw new IllegalArgumentException(this.getName() + "の引数が不正です。");
	}
	
	@Override
	public String getName() {
		return "CONCATENATE";
	}

	@Override
	protected String getValue(long nowIndex) {
		StringBuilder sb = new StringBuilder();
		for (Value argument : this.arguments) sb.append(argument.getValue(nowIndex)); 
		return sb.toString();
	}
}
