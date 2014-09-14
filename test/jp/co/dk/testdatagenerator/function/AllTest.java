package jp.co.dk.testdatagenerator.function;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	AbstractFunctionTest.class,
	FunctionPatternTest.class,
	RightTest.class,
	ValueTest.class,
})
public class AllTest {}
 