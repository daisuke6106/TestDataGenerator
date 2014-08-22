package jp.co.dk.testdatagenerator;

import java.math.BigDecimal;


abstract class CountSpecify {
	
	/** 出力件数 */
	protected long outputCount;
	
	/** 値 */
	protected String value;
	
	CountSpecify(long outputCount, String value) throws IllegalArgumentException {
		if (outputCount < 0 || value == null) throw new IllegalArgumentException("can't create CountSpecifyType instance.");
		this.outputCount = outputCount;
		this.value = value;
	}
	
	String getValue() {
		return this.value;
	}
	
}

class PacentageCountSpecify extends CountSpecify{
	
	/** パーセンテージ数値 */
	protected long percentage;
	
	/** 件数指定数値 */
	protected long usage_count;
	
	/** 使用回数 */
	protected long use_count = 0L;
	
	PacentageCountSpecify(long outputCount, String value, long percentage) throws IllegalArgumentException {
		super(outputCount, value);
		if (percentage < 1) throw new IllegalArgumentException("パーセンテージ指定に0以下が設定されています。:" + value);
		this.percentage = percentage;
		this.usage_count = new BigDecimal(percentage).multiply(new BigDecimal("0.01")).multiply(new BigDecimal(outputCount)).setScale(0, BigDecimal.ROUND_UP).longValue();
		if (this.usage_count > this.outputCount) throw new IllegalArgumentException("件数指定に出力件数以上のパーセンテージが設定されています。:" + value);
	}
	
	@Override
	String getValue() {
		++use_count;
		if (this.use_count>this.usage_count) return null;
		return super.getValue();
	}
	
}

class AbsoluteCountSpecify extends CountSpecify{
	
	/** 件数指定数値 */
	protected long origin_count;
	
	/** 使用回数 */
	protected long use_count = 0L;
	
	AbsoluteCountSpecify(long outputCount, String value, long count) throws IllegalArgumentException {
		super(outputCount, value);
		if (count < 1) throw new IllegalArgumentException("件数指定に0以下が設定されています。:" + value);
		if (count > outputCount) throw new IllegalArgumentException("件数指定に出力件数以上の件数が設定されています。:" + value); 
		this.origin_count = count;
	}
	
	@Override
	String getValue() {
		++use_count;
		if (this.use_count>this.origin_count) return null;
		return super.getValue();
	}
	
}

class NothingCountSpecify extends CountSpecify{
	
	NothingCountSpecify(long outputCount, String value) throws IllegalArgumentException {
		super(outputCount, value);
	}
}