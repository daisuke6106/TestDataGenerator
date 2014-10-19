package jp.co.dk.testdatagenerator.function;

public class Value {
	
	protected String data;
	
	public Value(String data) throws IllegalArgumentException {
		if (data == null) throw new IllegalArgumentException("can't create Value instance. data is not set.");
		this.data = data;
	}
	
	public String getValue(long nowIndex) {
		return this.data;
	}
	
	@Override
	public String toString() {
		return this.data;
	}
}
