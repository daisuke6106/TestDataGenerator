package jp.co.dk.testdatagenerator;

import java.math.BigDecimal;


abstract class CountSpecify {
	
	/** 値 */
	protected String value;
	
	CountSpecify(String value) throws IllegalArgumentException {
		if (value == null) throw new IllegalArgumentException("can't create CountSpecifyType instance.");
		this.value = value;
	}
	
	/**
	 * 件数指定タイプを取得します。
	 * @return 件数指定タイプ
	 */
	abstract CountSpecifyType getCountSpecifyType();
	
	abstract long getCount(long maxCount);
}

class PacentageCountSpecify extends CountSpecify{
	
	/** 件数指定数値 */
	protected long count;
	
	PacentageCountSpecify(String value, long count) throws IllegalArgumentException {
		super(value);
		if (count < 1) throw new IllegalArgumentException("can't create CountSpecifyType instance.");
		this.count            = count;
	}
	
	@Override
	CountSpecifyType getCountSpecifyType(){
		return CountSpecifyType.PERCENTAGE_SPECIFIED;
	}

	@Override
	long getCount(long maxCount) {
		return new BigDecimal(count).multiply(new BigDecimal("0.01")).multiply(new BigDecimal(maxCount)).longValue();
	}
}

class AbsoluteCountSpecify extends CountSpecify{
	
	/** 件数指定数値 */
	protected long count;
	
	AbsoluteCountSpecify(String value, long count) throws IllegalArgumentException {
		super(value);
		if (count < 1) throw new IllegalArgumentException("can't create CountSpecifyType instance.");
		this.count            = count;
	}
	
	@Override
	CountSpecifyType getCountSpecifyType(){
		return CountSpecifyType.ABSOLUTE_SPECIFIED;
	}
	
	@Override
	long getCount(long maxCount) {
		return this.count;
	}
}

class NothingCountSpecify extends CountSpecify{
	
	NothingCountSpecify(String value) throws IllegalArgumentException {
		super(value);
	}
	
	@Override
	CountSpecifyType getCountSpecifyType(){
		return CountSpecifyType.NOTHING_SPECIFIED;
	}

	@Override
	long getCount(long maxCount) {
		return 0;
	}
}

/**
 * CountSpecifyTypeは、件数の指定方式を定義する定数クラスです。
 * 
 * @version 1.0
 * @author D.Kanno
 */
enum CountSpecifyType {
	/** 件数指定 */
	ABSOLUTE_SPECIFIED,
	
	/** パーセント指定 */
	PERCENTAGE_SPECIFIED,
	
	/** 指定なし */
	NOTHING_SPECIFIED,
}