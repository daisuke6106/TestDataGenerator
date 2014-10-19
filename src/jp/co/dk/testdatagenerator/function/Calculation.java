package jp.co.dk.testdatagenerator.function;

import java.util.List;

public abstract class Calculation extends AbstractFunction {
	
	protected Value value1;
	
	protected Value value2;
	
	Calculation(List<Value> arguments) throws IllegalArgumentException {
		super(arguments);
		if (arguments.size() != 2) throw new IllegalArgumentException(this.getName() + "の引数が不正です。");
		this.value1 = arguments.get(0);
		this.value2 = arguments.get(1);
	}
	
	@Override
	protected String getValue(long nowIndex) {
		String value1Str  = this.value1.getValue(nowIndex);
		String value2Str  = this.value2.getValue(nowIndex);
		long   value1Long = 0L;
		long   value2Long = 0L;
		try {
			value1Long = Long.parseLong(value1Str);
			value2Long = Long.parseLong(value2Str);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(this.getName() + "の計算処理に失敗しました。値が数値でありません。値1=[" + value1Str + "] 値2=[" + value2Str + "]");
		}
		return Long.toString(this.calculat(value1Long, value2Long));
	}
	
	protected abstract long calculat(long value1, long value2);
}
