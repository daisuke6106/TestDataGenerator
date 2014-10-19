package jp.co.dk.testdatagenerator.function;

import java.util.List;

public interface FunctionFactory {
	
	AbstractFunction createFunction(List<Value> arguments);
	
}