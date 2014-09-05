package jp.co.dk.testdatagenerator.function;

public class Right extends AbstractFunction {
	
	Right(String... arguments) throws IllegalArgumentException {
		super(arguments);
		if (arguments == null || arguments.length != 2) {
			throw new IllegalArgumentException(this.getFunctionName() + "の引数が不足です。");
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


}
