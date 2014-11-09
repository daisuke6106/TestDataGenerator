package jp.co.dk.testdatagenerator.function;

import java.util.ArrayList;
import java.util.List;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class ConcatenateTest {

	public static class コンストラクタ extends TestCaseTemplate{
		@Test
		public void 引数がNULLの場合() {
			try {
				new Concatenate((List<Value>)null);
				fail();
			} catch(IllegalArgumentException e) {
				assertThat(e.getMessage(), is("CONCATENATEの引数が不正です。"));
			}
		}
		
		@Test
		public void 引数が空の配列の場合() {
			try {
				List<Value> values = new ArrayList<Value>();
				new Concatenate(values);
				fail();
			} catch(IllegalArgumentException e) {
				assertThat(e.getMessage(), is("CONCATENATEの引数が不正です。"));
			}
		}
	}
	
	public static class インスタンスが生成できた場合且つ引数の文字列が２つ extends TestCaseTemplate{
		
		protected Concatenate sut;
		
		@Before
		public void init() {
			List<Value> values = new ArrayList<Value>();
			values.add(new Value("ABCDE"));
			values.add(new Value("FGHIJ"));
			this.sut = new Concatenate(values);
		}
		
		@Test
		public void getName() {
			assertThat(this.sut.getName(), is("CONCATENATE"));
		}
		
		@Test
		public void getValue() {
			assertThat(this.sut.getValue(100L), is("ABCDEFGHIJ"));
		}
	}
	
	public static class インスタンスが生成できた場合且つ引数の文字列が３つ extends TestCaseTemplate{
		
		protected Concatenate sut;
		
		@Before
		public void init() {
			List<Value> values = new ArrayList<Value>();
			values.add(new Value("ABCDE"));
			values.add(new Value("FGHIJ"));
			values.add(new Value("KLMNO"));
			this.sut = new Concatenate(values);
		}
		
		@Test
		public void getName() {
			assertThat(this.sut.getName(), is("CONCATENATE"));
		}
		
		@Test
		public void getValue() {
			assertThat(this.sut.getValue(100L), is("ABCDEFGHIJKLMNO"));
		}
	}
}
