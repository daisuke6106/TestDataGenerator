package jp.co.dk.testdatagenerator.countspecify;

import java.math.BigDecimal;

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
	public String getValue() {
		++use_count;
		if (this.use_count>this.usage_count) return null;
		return super.getValue();
	}
	
}