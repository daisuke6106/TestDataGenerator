package jp.co.dk.testdatagenerator.function;


public class Value {
	
	protected String data;
	
	protected AbstractFunction function;
	
	public Value(String data) {
		if (data == null) throw new IllegalArgumentException("can't create Value instance. data is not set.");
		this.data = data;
	}
	
	public String getValue(long nowIndex) {
		if (this.function != null) {
			return this.function.getValue(nowIndex);
		} else {
			return this.data.replace("@{count}", Long.toString(nowIndex));
		}
	}
	
	@Override
	public String toString() {
		return this.data;
	}
}
