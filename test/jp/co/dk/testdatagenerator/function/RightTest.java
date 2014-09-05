package jp.co.dk.testdatagenerator.function;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class RightTest {

	public static class コンストラクタ extends TestCaseTemplate{
		@Test
		public void 引数がNULLの場合() {
			try {
				new Right(null);
				fail();
			} catch(IllegalArgumentException e) {
				assertThat(e.getMessage(), is("RIGHTの引数が不足です。"));
			}
		}
		
		@Test
		public void 引数が空の配列の場合() {
			try {
				new Right(new String[0]);
				fail();
			} catch(IllegalArgumentException e) {
				assertThat(e.getMessage(), is("RIGHTの引数が不足です。"));
			}
		}
	}

}
