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
				assertThat(this.sut.function, nullValue());
			} catch (IllegalArgumentException e) {
				fail(e);
			}
		}
		
		@Test
		public void getValue() {
			assertThat(this.sut.getValue(100L), is("value"));
		}
	}
	
	public static class 関数を保持しない且つ変数を保持する値である場合 extends TestCaseTemplate {
		
		protected Value sut;
		
		@Before
		public void コンストラクタ() {
			try {
				this.sut = new Value("value@{count}");
				assertThat(this.sut.data, is("value@{count}"));
				assertThat(this.sut.function, nullValue());
			} catch (IllegalArgumentException e) {
				fail(e);
			}
		}
		
		@Test
		public void getValue() {
			assertThat(this.sut.getValue(100L), is("value100"));
		}
	}
	
	public static class 関数を保持する且つ変数を保持しない値である場合 extends TestCaseTemplate {
		
		protected Value sut;
		
		@Before
		public void コンストラクタ() {
			try {
				this.sut = new Value("RIGHT(abcde;1)");
				assertThat(this.sut.data, is("RIGHT(abcde;1)"));
				assertThat((this.sut.function instanceof Right), is(true));
			} catch (IllegalArgumentException e) {
				fail(e);
			}
		}
		
		@Test
		public void getValue() {
			assertThat(this.sut.getValue(100L), is("e"));
		}
	}
	
	public static class 関数をネストして保持する且つ変数を保持しない値である場合 extends TestCaseTemplate {
		
		protected Value sut;
		
		@Before
		public void コンストラクタ() {
			try {
				this.sut = new Value("RIGHT(RIGHT(あいうえおかきくけこ;5);1)");
				assertThat(this.sut.data, is("RIGHT(RIGHT(あいうえおかきくけこ;5);1)"));
				assertThat((this.sut.function instanceof Right), is(true));
			} catch (IllegalArgumentException e) {
				fail(e);
			}
		}
		
		@Test
		public void getValue() {
			assertThat(this.sut.getValue(100L), is("こ"));
		}
	}
}
