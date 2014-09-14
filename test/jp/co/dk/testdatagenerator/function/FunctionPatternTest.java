package jp.co.dk.testdatagenerator.function;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class FunctionPatternTest {
	
	public static class RIGHT extends TestCaseTemplate {
		
		protected FunctionPattern sut = FunctionPattern.RIGHT;
		
		@Test
		public void コンストラクタ() {
			assertThat(this.sut.pattern        , notNullValue());
			assertThat(this.sut.functionFactory, notNullValue());
		}
		
		@Test
		public void match() {
			assertThat(this.sut.match(null), nullValue());
			assertThat(this.sut.match("")  , nullValue());
			assertThat(this.sut.match("aaa")  , nullValue());
			assertThat(this.sut.match("RIGHT()")  , nullValue());
			assertThat(this.sut.match("RIGHT(aaa)")  , nullValue());
			assertThat(this.sut.match("RIGHT(aaa,bbb)")  , nullValue());
			assertThat(this.sut.match("RIGHT(aaa,bbb,ccc)")  , nullValue());
			assertThat((this.sut.match("RIGHT(aaa,123)") instanceof Right) , is(true));
		}
	}
	
	

}
