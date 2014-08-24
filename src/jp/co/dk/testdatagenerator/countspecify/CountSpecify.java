package jp.co.dk.testdatagenerator.countspecify;

public abstract class CountSpecify {
	
	/** 出力件数 */
	protected long outputCount;
	
	/** 値 */
	protected String value;
	
	CountSpecify(long outputCount, String value) throws IllegalArgumentException {
		if (outputCount < 0 || value == null) throw new IllegalArgumentException("can't create CountSpecifyType instance.");
		this.outputCount = outputCount;
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}

