package jp.co.dk.testdatagenerator.function;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class RowTest {
	public static class インスタンスが生成できた場合 extends TestCaseTemplate{
		
		protected Row sut;
		
		@Before
		public void init() {
			this.sut = new Row();
		}
		
		@Test
		public void getName() {
			assertThat(this.sut.getName(), is("ROW"));
		}
		
		@Test
		public void getValue() {
			assertThat(this.sut.getValue(100L), is("100"));
		}
	}
}
