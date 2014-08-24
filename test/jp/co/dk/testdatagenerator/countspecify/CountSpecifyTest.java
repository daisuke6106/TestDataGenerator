package jp.co.dk.testdatagenerator.countspecify;

import jp.co.dk.test.template.TestCaseTemplate;
import jp.co.dk.testdatagenerator.countspecify.CountSpecify;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class CountSpecifyTest {
	
	public static class コンストラクタ extends TestCaseTemplate{
		
		@Test
        public void 引数の出力件数が不正な場合() {
        	try {
        		new CountSpecify(-1L, "a") {};
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("can't create CountSpecifyType instance."));
			}
        }
		
		@Test
        public void 引数の値がNULL() {
        	try {
        		new CountSpecify(0L, null) {};
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("can't create CountSpecifyType instance."));
			}
        }
		
		@Test
        public void 引数が正常な場合インスタンスが生成できること() {
        	try {
        		CountSpecify sut = new CountSpecify(0L, "") {};
				assertThat(sut.outputCount, is(0L));
				assertThat(sut.value      , is(""));
			} catch (IllegalArgumentException e) {
				fail(e);
			}
        	try {
        		CountSpecify sut = new CountSpecify(1L, "a") {};
				assertThat(sut.outputCount, is(1L));
				assertThat(sut.value      , is("a"));
			} catch (IllegalArgumentException e) {
				fail(e);
			}
        }
	}
	
	
	public static class インスタンス生成に成功 extends TestCaseTemplate{
		
		protected CountSpecify sut ;
		
		@Before
		public void init() {
			this.sut = new CountSpecify(0L, "") {};
		}
		
		@Test
        public void getValue() {
			assertThat(this.sut.getValue(), is(""));
        }
	}

}
