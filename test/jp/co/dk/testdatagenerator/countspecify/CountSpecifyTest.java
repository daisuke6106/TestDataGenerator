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
        public void 引数の値がNULLの場合() {
        	try {
        		new CountSpecify(0L, null) {};
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("can't create CountSpecifyType instance."));
			}
        }
		
		@Test
		public void 不正な引数が定義されている場合() {
			try {
				new CountSpecify(10L, "ADD(1)") {};
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("ADDの引数が不正です。"));
			}
		}
		
		@Test
		public void 不正な引数が定義されている場合＿() {
			try {
				new CountSpecify(10L, "ADD(1))") {};
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("ADDの引数が不正です。"));
			}
		}
		
		@Test
		public void 関数が正しく定義されていない場合＿０１() {
			try {
				new CountSpecify(10L, "ADD(") {};
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("関数がクローズされてません。"));
			}
		}
		
		@Test
		public void 関数が正しく定義されていない場合＿０２() {
			try {
				new CountSpecify(10L, "ADD)") {};
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("関数が正しく終了してません。"));
			}
		}
		
		@Test
		public void 関数が正しく定義されていない場合＿０３() {
			try {
				new CountSpecify(10L, "ADD(test") {};
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("関数がクローズされてません。"));
			}
		}
		
		@Test
		public void 関数が正しく定義されていない場合＿０４() {
			try {
				new CountSpecify(10L, "ADDtest)") {};
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("関数が正しく終了してません。"));
			}
		}
		
		@Test
		public void 関数が正しく定義されていない場合＿０５() {
			try {
				new CountSpecify(10L, "COMMA(ROW()") {};
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("関数がクローズされてません。"));
			}
		}
		
		@Test
		public void 関数が正しく定義されていない場合＿０６() {
			try {
				new CountSpecify(10L, "COMMA(ROW))") {};
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("関数がクローズされてません。"));
			}
		}
	}
	
	
	public static class 値が空文字の状態 extends TestCaseTemplate{
		
		protected CountSpecify sut ;
		
		@Before
		public void init() {
			this.sut = new CountSpecify(10L, "") {};
			assertThat(this.sut.outputCount     , is(10L));
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
		
		protected CountSpecify sut ;
		
		@Before
		public void init() {
			this.sut = new CountSpecify(10L, "a") {};
			assertThat(this.sut.outputCount     , is(10L));
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
	
	public static class 関数がひとつ定義されている場合_引数なし extends TestCaseTemplate{
		
		protected CountSpecify sut ;
		
		@Before
		public void init() {
			this.sut = new CountSpecify(10L, "ROW()") {};
			assertThat(this.sut.outputCount     , is(10L));
			assertThat(this.sut.value.toString(), is("ROW"));
		}
		
		@Test
        public void getValue() {
			assertThat(this.sut.getValue(10L), is("10"));
        }
		
		@Test
		public void testToString() {
			assertThat(this.sut.toString(), is("ROW"));
		}
	}
	
	public static class 関数がひとつ定義されている場合_単引数 extends TestCaseTemplate{
		
		protected CountSpecify sut ;
		
		@Before
		public void init() {
			this.sut = new CountSpecify(10L, "COMMA(1234)") {};
			assertThat(this.sut.outputCount     , is(10L));
			assertThat(this.sut.value.toString(), is("COMMA"));
		}
		
		@Test
        public void getValue() {
			assertThat(this.sut.getValue(10L), is("1,234"));
        }
		
		@Test
		public void testToString() {
			assertThat(this.sut.toString(), is("COMMA"));
		}
	}
	
	public static class 関数がネストで定義されている場合 extends TestCaseTemplate{
		
		protected CountSpecify sut ;
		
		@Before
		public void init() {
			this.sut = new CountSpecify(10L, "COMMA(ROW())") {};
			assertThat(this.sut.outputCount     , is(10L));
			assertThat(this.sut.value.toString(), is("COMMA"));
		}
		
		@Test
        public void getValue() {
			assertThat(this.sut.getValue(1234L), is("1,234"));
        }
		
		@Test
		public void testToString() {
			assertThat(this.sut.toString(), is("COMMA"));
		}
	}
	
//	public static class 関数がひとつ定義されている場合_引数あり_複数 extends TestCaseTemplate{
//		
//		protected CountSpecify sut ;
//		
//		@Before
//		public void init() {
//			this.sut = new CountSpecify(10L, "ADD(1;1)") {};
//			assertThat(this.sut.outputCount     , is(10L));
//			assertThat(this.sut.value.toString(), is("ADD"));
//		}
//		
//		@Test
//        public void getValue() {
//			assertThat(this.sut.getValue(0L), is("2"));
//        }
//		
//		@Test
//		public void testToString() {
//			assertThat(this.sut.toString(), is("ADD"));
//		}
//	}
	
//	public static class 関数がひとつ定義されている場合_引数あり且つ関数_単数 extends TestCaseTemplate{
//		
//		protected CountSpecify sut ;
//		
//		@Before
//		public void init() {
//			this.sut = new CountSpecify(10L, "ADD(ADD(1;1);1)") {};
//			assertThat(this.sut.outputCount     , is(10L));
//			assertThat(this.sut.value.toString(), is("ADD"));
//		}
//		
//		@Test
//        public void getValue() {
//			assertThat(this.sut.getValue(0L), is("3"));
//        }
//		
//		@Test
//		public void testToString() {
//			assertThat(this.sut.toString(), is("ADD"));
//		}
//	}
}
