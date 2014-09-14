package jp.co.dk.testdatagenerator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	ColumnTest.class,
	RecordTest.class,
	jp.co.dk.testdatagenerator.countspecify.AllTest.class,
	jp.co.dk.testdatagenerator.function.AllTest.class,
})
public class AllTest {}
 