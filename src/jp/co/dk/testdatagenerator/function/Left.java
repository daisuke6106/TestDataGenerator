package jp.co.dk.testdatagenerator.function;

import java.util.List;

public class Left extends AbstractFunction {
	
	protected Value value;
	
	protected Value count;
	
	Left(List<Value> arguments) throws IllegalArgumentException {
		super(arguments);
		if (arguments.size() != 2) throw new IllegalArgumentException(this.getName() + "の引数が不正です。");
		this.value = arguments.get(0);
		this.count = arguments.get(1);
	}
	
	
	@Override
	public String getName() {
		return "LEFT";
	}

	@Override
	protected String getValue(long nowIndex) {
		String value = this.value.getValue(nowIndex);
		int    count = Integer.parseInt(this.count.getValue(nowIndex));
		if (value.length() < count) return value;
		return value.substring(0, count);
	}


}
