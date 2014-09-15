package jp.co.dk.testdatagenerator.countspecify;

import jp.co.dk.testdatagenerator.function.Value;

public abstract class CountSpecify {
	
	/** 出力件数 */
	protected long outputCount;
	
	/** 値 */
	protected Value value;
	
	CountSpecify(){
		
	}
	
	CountSpecify(long outputCount, String value) throws IllegalArgumentException {
		if (outputCount < 0 || value == null) throw new IllegalArgumentException("can't create CountSpecifyType instance.");
		this.outputCount = outputCount;
		this.value = new Value(value);
	}
	
	public String getValue(long nowIndex) {
		return this.value.getValue(nowIndex);
	}
	
	@Override
	public String toString() {
		return this.value.toString();
	}
	
}

