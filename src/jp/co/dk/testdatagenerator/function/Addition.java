package jp.co.dk.testdatagenerator.function;

import java.util.List;

public class Addition extends Calculation {
	
	Addition(List<Value> arguments) throws IllegalArgumentException {
		super(arguments);
	}
	
	@Override
	protected long calculat(long value1, long value2) {
		return value1 + value2;
	}

	@Override
	protected String getName() {
		return "ADD";
	}

}
