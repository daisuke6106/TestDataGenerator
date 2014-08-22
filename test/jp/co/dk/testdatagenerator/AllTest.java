package jp.co.dk.testdatagenerator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	ColumnTest.class,
	NothingCountSpecifyTest.class,
	PacentageCountSpecifyTest.class,
})
public class AllTest {}
 