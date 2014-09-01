package jp.co.dk.testdatagenerator.countspecify;

import jp.co.dk.testdatagenerator.HelpPrintable;

public abstract class CountSpecify implements HelpPrintable {
	
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
	
	@Override
	public String toString() {
		return this.value;
	}
	
}

