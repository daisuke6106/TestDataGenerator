package jp.co.dk.testdatagenerator.function;

import java.util.regex.Pattern;

public interface FunctionFactory {
	
	AbstractFunction createFunction(String[] arguments);
	
}