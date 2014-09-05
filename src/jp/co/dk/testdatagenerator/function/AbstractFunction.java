package jp.co.dk.testdatagenerator.function;

import java.util.Arrays;
import java.util.regex.Pattern;

import jp.co.dk.testdatagenerator.HelpPrintable;

public abstract class AbstractFunction implements HelpPrintable {
	
	protected String[] arguments;
	
	protected Pattern functionPattern;
	
	protected AbstractFunction function;
	
	AbstractFunction(String... arguments) throws IllegalArgumentException {
		this.arguments = arguments;
		StringBuilder sb = new StringBuilder('^').append(this.getFunctionName()).append("\\(.*\\)").append('$');
		this.functionPattern = Pattern.compile(sb.toString());
	}
	
	protected abstract String getFunctionName();
	
	public abstract String getValue();
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getFunctionName());
		if (this.arguments == null) {
			sb.append("[]");
		} else {
			sb.append(Arrays.asList(this.arguments).toString());
		}
		return sb.toString();
	}
}
