package jp.co.dk.testdatagenerator.countspecify;

import jp.co.dk.test.template.TestCaseTemplate;
import jp.co.dk.testdatagenerator.countspecify.CountSpecify;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class CountSpecifyPatternTest {
	
	public static class 件数指定 extends TestCaseTemplate{
		
		@Test
        public void 引数の出力件数が不正な場合() {
        	try {
        		new CountSpecify(-1L, "a") {

					@Override
					public String getName() {
						return "DummyName";
					}

					@Override
					public String getManualMessage(String linesep) {
						return "DummyManualMessage";
					}

					@Override
					public String getExample(String linesep) {
						return "DummyExample";
					}};
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("can't create CountSpecifyType instance."));
			}
        }
		
		@Test
        public void 引数の値がNULL() {
        	try {
        		new CountSpecify(0L, null) {

					@Override
					public String getName() {
						return "DummyName";
					}

					@Override
					public String getManualMessage(String linesep) {
						return "DummyManualMessage";
					}

					@Override
					public String getExample(String linesep) {
						return "DummyExample";
					}};
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("can't create CountSpecifyType instance."));
			}
        }
		
	}
}
