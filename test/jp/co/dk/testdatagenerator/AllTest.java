package jp.co.dk.testdatagenerator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	ColumnTest.class,
	Record.class,
})
public class AllTest {}
 