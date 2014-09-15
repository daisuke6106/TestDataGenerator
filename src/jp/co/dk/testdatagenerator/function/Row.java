package jp.co.dk.testdatagenerator.function;

public class Row extends AbstractFunction {
	
	@Override
	public String getName() {
		return "ROW";
	}

	@Override
	protected String getValue(long nowIndex) {
		return Long.toString(nowIndex);
	}


}
