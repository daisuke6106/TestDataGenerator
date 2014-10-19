package jp.co.dk.testdatagenerator.function;

import java.util.List;

public class Division extends Calculation {
	
	Division(List<Value> arguments) throws IllegalArgumentException {
		super(arguments);
	}
	
	@Override
	protected long calculat(long value1, long value2) {
		return value1 / value2;
	}

	@Override
	protected String getName() {
		return "DIVISION";
	}

}
