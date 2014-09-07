package jp.co.dk.testdatagenerator.function;

public class Right extends AbstractFunction {
	
	Right(String value, int count) throws IllegalArgumentException {
		super(value);
		if (value == null || count <= 0) {
			throw new IllegalArgumentException(this.getFunctionName() + "の引数が不正です。");
		}
	}
	
	@Override
	protected String getFunctionName() {
		return "RIGHT";
	}
	
	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getManualMessage(String linesep) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getExample(String linesep) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getValue(long nowCount) {
		// TODO Auto-generated method stub
		return null;
	}


}
