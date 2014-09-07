package jp.co.dk.testdatagenerator.countspecify;

import jp.co.dk.test.template.TestCaseTemplate;
import jp.co.dk.testdatagenerator.countspecify.NothingCountSpecify;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class NothingCountSpecifyTest {
	
	public static class コンストラクタ extends TestCaseTemplate{
		
		@Test
        public void 引数の出力件数が不正な場合() {
        	try {
        		new NothingCountSpecify(-1L, "a");
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("can't create CountSpecifyType instance."));
			}
        }
		
		@Test
        public void 引数の値がNULL() {
        	try {
        		new NothingCountSpecify(0L, null);
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("can't create CountSpecifyType instance."));
			}
        }
		
	}
	
	
	public static class 値が空文字の状態 extends TestCaseTemplate{
		
		protected NothingCountSpecify sut ;
		
		@Before
		public void init() {
			this.sut = new NothingCountSpecify(0L, "");
			assertThat(this.sut.outputCount     , is(0L));
			assertThat(this.sut.value.toString(), is(""));
		}
		
		@Test
        public void getValue() {
			assertThat(this.sut.getValue(0L), is(""));
        }
		
		@Test
		public void testToString() {
			assertThat(this.sut.toString(), is(""));
		}
	}
	
	
	public static class 値が１文字以上の文字列の状態 extends TestCaseTemplate{
		
		protected NothingCountSpecify sut ;
		
		@Before
		public void init() {
			this.sut = new NothingCountSpecify(0L, "a");
			assertThat(this.sut.outputCount     , is(0L));
			assertThat(this.sut.value.toString(), is("a"));
		}
		
		@Test
        public void getValue() {
			assertThat(this.sut.getValue(0L), is("a"));
        }
		
		@Test
		public void testToString() {
			assertThat(this.sut.toString(), is("a"));
		}
	}
}
