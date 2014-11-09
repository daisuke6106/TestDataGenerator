package jp.co.dk.testdatagenerator.function;

import java.text.NumberFormat;
import java.util.List;

public class Comma extends AbstractFunction {
	
	protected Value value;
	
	protected NumberFormat nfNum = NumberFormat.getNumberInstance();
	
	Comma(List<Value> arguments) throws IllegalArgumentException {
		super(arguments);
		if (arguments.size() != 1) throw new IllegalArgumentException(this.getName() + "の引数が不正です。");
		this.value = arguments.get(0);
	}
	
	@Override
	protected String getValue(long nowIndex) {
		return nfNum.format(Integer.parseInt(this.value.getValue(nowIndex)));
	}
	
	@Override
	protected String getName() {
		return "COMMA";
	}

}
