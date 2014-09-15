package jp.co.dk.testdatagenerator.function;

public interface FunctionFactory {
	
	AbstractFunction createFunction(String[] arguments);
	
}