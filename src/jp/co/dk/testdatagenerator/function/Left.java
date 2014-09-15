package jp.co.dk.testdatagenerator.function;

public class Left extends AbstractFunction {
	
	protected Value value;
	
	protected int   count;
	
	Left(String... arguments) throws IllegalArgumentException {
		super(arguments);
		if (arguments.length != 2) throw new IllegalArgumentException(this.getName() + "の引数が不正です。");
		String value = arguments[0];
		int count = 0;
		try {
			count = Integer.parseInt(arguments[1]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(this.getName() + "の引数が不正です。");
		}
		
		if (value == null || count <= 0) throw new IllegalArgumentException(this.getName() + "の引数が不正です。");
		this.value = new Value(value);
		this.count = count;
	}
	
	
	@Override
	public String getName() {
		return "LEFT";
	}

	@Override
	protected String getValue(long nowIndex) {
		String value = this.value.getValue(nowIndex);
		if (value.length() < this.count) return value;
		return value.substring(0, this.count);
	}


}
