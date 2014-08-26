package jp.co.dk.testdatagenerator.countspecify;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	AbsoluteCountSpecifyTest.class,
	CountSpecifyTest.class,
	NothingCountSpecifyTest.class,
	PacentageCountSpecifyTest.class
})
public class AllTest {}
 