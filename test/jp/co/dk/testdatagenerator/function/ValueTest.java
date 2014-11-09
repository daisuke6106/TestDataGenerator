package jp.co.dk.testdatagenerator.function;

import jp.co.dk.test.template.TestCaseTemplate;
import jp.co.dk.testdatagenerator.function.Value;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class ValueTest {
	
	public static class コンストラクタ extends TestCaseTemplate {
		
		@Test
		public void 引数がNULLの場合() {
			try {
				new Value(null);
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("can't create Value instance. data is not set."));
			}
		}
		
	}
	
	public static class 関数を保持しない且つ変数を保持しない値である場合 extends TestCaseTemplate {
		
		protected Value sut;
		
		@Before
		public void コンストラクタ() {
			try {
				this.sut = new Value("value");
				assertThat(this.sut.data, is("value"));
			} catch (IllegalArgumentException e) {
				fail(e);
			}
		}
		
		@Test
		public void getValue() {
			assertThat(this.sut.getValue(100L), is("value"));
		}
	}
	
}
