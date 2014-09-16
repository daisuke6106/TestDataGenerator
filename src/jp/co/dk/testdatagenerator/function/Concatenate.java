package jp.co.dk.testdatagenerator.function;

public class Concatenate extends AbstractFunction {
	
	Concatenate(String... arguments) throws IllegalArgumentException {
		super(arguments);
		if (arguments == null || arguments.length == 0) throw new IllegalArgumentException(this.getName() + "の引数が不正です。");
	}
	
	@Override
	public String getName() {
		return "CONCATENATE";
	}

	@Override
	protected String getValue(long nowIndex) {
		StringBuilder sb = new StringBuilder();
		for (String argument : this.arguments) sb.append(argument); 
		return sb.toString();
	}
}
