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
			assertThat(this.sut.match("RIGHT(aaa;bbb)")  , nullValue());
			assertThat(this.sut.match("RIGHT(aaa;bbb;ccc)")  , nullValue());
			assertThat((this.sut.match("RIGHT(aaa;123)") instanceof Right) , is(true));
		}
	}
	
	public static class LEFT extends TestCaseTemplate {
		
		protected FunctionPattern sut = FunctionPattern.LEFT;
		
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
			assertThat(this.sut.match("LEFT()")  , nullValue());
			assertThat(this.sut.match("LEFT(aaa)")  , nullValue());
			assertThat(this.sut.match("LEFT(aaa;bbb)")  , nullValue());
			assertThat(this.sut.match("LEFT(aaa;bbb;ccc)")  , nullValue());
			assertThat((this.sut.match("LEFT(aaa;123)") instanceof Left) , is(true));
		}
	}

	public static class ROW extends TestCaseTemplate {
	
		protected FunctionPattern sut = FunctionPattern.ROW;
		
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
			assertThat((this.sut.match("ROW()") instanceof Row) , is(true));
			assertThat(this.sut.match("ROW(aaa)")  , nullValue());
			assertThat(this.sut.match("ROW(aaa;bbb)")  , nullValue());
			assertThat(this.sut.match("ROW(aaa;bbb;ccc)")  , nullValue());
			assertThat(this.sut.match("ROW(aaa;123)") , nullValue());
		}
	}
	
	public static class CONCATENATE extends TestCaseTemplate {
		
		protected FunctionPattern sut = FunctionPattern.CONCATENATE;
		
		@Test
		public void コンストラクタ() {
			assertThat(this.sut.pattern        , notNullValue());
			assertThat(this.sut.functionFactory, notNullValue());
		}
		
		@Test
		public void match() {
			assertThat(this.sut.match(null)                                                , nullValue());
			assertThat(this.sut.match("")                                                  , nullValue());
			assertThat(this.sut.match("aaa")                                               , nullValue());
			assertThat(this.sut.match("CONCATENATE()")                                     , nullValue());
			assertThat(this.sut.match("CONCATENATE(aaa)")                                  , nullValue());
			assertThat((this.sut.match("CONCATENATE(aaa;bbb)")instanceof Concatenate)      , is(true));
			assertThat((this.sut.match("CONCATENATE(aaa;bbb;ccc)")instanceof Concatenate)  , is(true));
			assertThat((this.sut.match("CONCATENATE(aaa;123)")instanceof Concatenate)      , is(true));
		}
	}
}
