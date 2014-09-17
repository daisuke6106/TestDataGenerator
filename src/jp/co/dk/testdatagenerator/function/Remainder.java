package jp.co.dk.testdatagenerator.function;

public class Remainder extends Calculation {
	
	Remainder(String... arguments) throws IllegalArgumentException {
		super(arguments);
	}
	
	@Override
	protected long calculat(long value1, long value2) {
		return value1 % value2;
	}

	@Override
	protected String getName() {
		return "REMAINDER";
	}

}
