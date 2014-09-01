package jp.co.dk.testdatagenerator.function;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class AbstractFunctionTest {

	public static class 引数がnullな場合 extends TestCaseTemplate{
		
		protected AbstractFunction sut;
		
		@Before
		public void 引数のデータがnullの場合例外が発生すること() {
			try {
        		this.sut = new AbstractFunction(null){
					@Override
					protected String getFunctionName() {
						return "DUMMYFUNCTION";
					}
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
				
				assertThat(this.sut.arguments, nullValue());
				
			} catch (IllegalArgumentException e) {
				fail(e);
			}
		}
		
		@Test
		public void testToString() {
			assertThat(this.sut.toString(), is("DUMMYFUNCTION[]"));
		}
	}
	
	public static class 引数が単数な場合 extends TestCaseTemplate {
		
		protected AbstractFunction sut;
		
		@Before
		public void 引数のデータがnullの場合例外が発生すること() {
			try {
        		this.sut = new AbstractFunction("aaa"){
					@Override
					protected String getFunctionName() {
						return "DUMMYFUNCTION";
					}
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
				
				assertThat(this.sut.arguments.length, is(1));
				assertThat(this.sut.arguments[0]    , is("aaa"));
				
			} catch (IllegalArgumentException e) {
				fail(e);
			}
		}
		
		@Test
		public void testToString() {
			assertThat(this.sut.toString(), is("DUMMYFUNCTION[aaa]"));
		}
	}
	
	public static class 引数が複数な場合 extends TestCaseTemplate {
		
		protected AbstractFunction sut;
		
		@Before
		public void 引数のデータがnullの場合例外が発生すること() {
			try {
        		this.sut = new AbstractFunction("aaa", "bbb", "ccc"){
					@Override
					protected String getFunctionName() {
						return "DUMMYFUNCTION";
					}
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
				
				assertThat(this.sut.arguments.length, is(3));
				assertThat(this.sut.arguments[0]    , is("aaa"));
				assertThat(this.sut.arguments[1]    , is("bbb"));
				assertThat(this.sut.arguments[2]    , is("ccc"));
				
			} catch (IllegalArgumentException e) {
				fail(e);
			}
		}
		
		@Test
		public void testToString() {
			assertThat(this.sut.toString(), is("DUMMYFUNCTION[aaa, bbb, ccc]"));
		}
	}
}
