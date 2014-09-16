package jp.co.dk.testdatagenerator.function;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class AbstractFunctionTest {
	
	public static class コンストラクタ extends TestCaseTemplate {
		
		protected AbstractFunction sut;
		
		@Test
		public void 引数がnullの場合() {
			try {
        		this.sut = new AbstractFunction((String[])null){
					@Override
					public String getName() {
						return "DummyName";
					}
					@Override
					protected String getValue(long nowCount) {
						return "DummyValue";
					}};
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("DummyNameの引数が不正です。"));
			}
		}
		
		@Test
		public void 引数の配列が空の場合() {
			try {
        		this.sut = new AbstractFunction(new String[0]){
					@Override
					public String getName() {
						return "DummyName";
					}
					@Override
					protected String getValue(long nowCount) {
						return "DummyValue";
					}};
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("DummyNameの引数が不正です。"));
			}
		}
	}
	
	public static class 引数が空な場合 extends TestCaseTemplate{
		
		protected AbstractFunction sut;
		
		@Before
		public void 引数のデータがnullの場合例外が発生すること() {
			try {
        		this.sut = new AbstractFunction(){
					@Override
					public String getName() {
						return "DummyName";
					}
					@Override
					protected String getValue(long nowCount) {
						return "DummyValue";
					}};
				assertThat(this.sut.arguments, nullValue());
			} catch (IllegalArgumentException e) {
				fail(e);
			}
		}
		
		@Test
		public void testToString() {
			assertThat(this.sut.toString(), is("DummyName[]"));
		}
	}
	
	public static class 引数が単数な場合 extends TestCaseTemplate {
		
		protected AbstractFunction sut;
		
		@Before
		public void 引数のデータがnullの場合例外が発生すること() {
			try {
        		this.sut = new AbstractFunction("aaa"){
					@Override
					public String getName() {
						return "DummyName";
					}
					@Override
					protected String getValue(long nowCount) {
						return "DummyValue";
					}};
				assertThat(this.sut.arguments.length, is(1));
				assertThat(this.sut.arguments[0]    , is("aaa"));
				
			} catch (IllegalArgumentException e) {
				fail(e);
			}
		}
		
		@Test
		public void testToString() {
			assertThat(this.sut.toString(), is("DummyName[aaa]"));
		}
	}
	
	public static class 引数が複数な場合 extends TestCaseTemplate {
		
		protected AbstractFunction sut;
		
		@Before
		public void 引数のデータがnullの場合例外が発生すること() {
			try {
        		this.sut = new AbstractFunction("aaa", "bbb", "ccc"){
					@Override
					public String getName() {
						return "DummyName";
					}
					@Override
					protected String getValue(long nowCount) {
						return "DummyValue";
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
			assertThat(this.sut.toString(), is("DummyName[aaa, bbb, ccc]"));
		}
	}
}
