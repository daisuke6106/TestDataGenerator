package jp.co.dk.testdatagenerator.function;

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
				new Concatenate((String[])null);
				fail();
			} catch(IllegalArgumentException e) {
				assertThat(e.getMessage(), is("CONCATENATEの引数が不正です。"));
			}
		}
		
		@Test
		public void 引数が空の配列の場合() {
			try {
				new Concatenate(new String[0]);
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
			this.sut = new Concatenate("ABCDE","FGHIJ");
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
			this.sut = new Concatenate("ABCDE","FGHIJ","KLMNO");
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
