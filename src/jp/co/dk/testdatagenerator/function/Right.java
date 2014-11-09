package jp.co.dk.testdatagenerator.function;

import java.util.List;

public class Right extends AbstractFunction {
	
	protected Value value;
	
	protected Value count;
	
	Right(List<Value> arguments) throws IllegalArgumentException {
		super(arguments);
		if (arguments.size() != 2)    throw new IllegalArgumentException(this.getName() + "の引数が不正です。");
		if (arguments.get(0) == null) throw new IllegalArgumentException(this.getName() + "の引数が不正です。");
		if (arguments.get(1) == null) throw new IllegalArgumentException(this.getName() + "の引数が不正です。");
		this.value = arguments.get(0);
		this.count = arguments.get(1);
	}
	
	
	@Override
	public String getName() {
		return "RIGHT";
	}

	@Override
	protected String getValue(long nowIndex) throws IllegalArgumentException {
		String value    = this.value.getValue(nowIndex);
		String countStr = this.count.getValue(nowIndex);
		try {
			int count = Integer.parseInt(countStr);
			if (count<=0) throw new IllegalArgumentException("文字数指定に0以下が設定されています。:" + count);
			if (value.length() < count) return value;
			return value.substring(value.length() - count);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("文字数指定に数値以外の文字列が設定されています。:" + countStr);
		}
	}

}
