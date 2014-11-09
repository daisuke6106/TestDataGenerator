package jp.co.dk.testdatagenerator.function;

import java.util.ArrayList;
import java.util.List;

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
        		this.sut = new AbstractFunction((List<Value>)null){
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
        		this.sut = new AbstractFunction(new ArrayList<Value>()){
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
				List<Value> values = new ArrayList<Value>();
				values.add(new Value("aaa"));
        		this.sut = new AbstractFunction(values){
					@Override
					public String getName() {
						return "DummyName";
					}
					@Override
					protected String getValue(long nowCount) {
						return "DummyValue";
					}};
				assertThat(this.sut.arguments.size()           , is(1));
				assertThat(this.sut.arguments.get(0).toString(), is("aaa"));
				
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
				List<Value> values = new ArrayList<Value>();
				values.add(new Value("aaa"));
				values.add(new Value("bbb"));
				values.add(new Value("ccc"));
        		this.sut = new AbstractFunction(values){
					@Override
					public String getName() {
						return "DummyName";
					}
					@Override
					protected String getValue(long nowCount) {
						return "DummyValue";
					}};
				
				assertThat(this.sut.arguments.size(), is(3));
				assertThat(this.sut.arguments.get(0).toString(), is("aaa"));
				assertThat(this.sut.arguments.get(1).toString(), is("bbb"));
				assertThat(this.sut.arguments.get(2).toString(), is("ccc"));
				
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
