package jp.co.dk.testdatagenerator.function;

import java.util.regex.Pattern;

public interface FunctionFactory {
	
	AbstractFunction createFunction(long outputCount, Pattern pattern, String format);
	
}