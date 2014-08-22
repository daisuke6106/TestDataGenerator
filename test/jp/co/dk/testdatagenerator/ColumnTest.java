package jp.co.dk.testdatagenerator;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class ColumnTest{
	
	public static class コンストラクタ extends TestCaseTemplate{
		 
        protected Column sut ;
        
        @Test
        public void 出力件数が不正() {
        	try {
				Column sut = new Column(-1L, 0, "a");
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("can't create Column instance."));
			}
        }
        
        @Test
        public void インデックス番号が不正() {
    		try {
				Column sut = new Column(0, -1, "a");
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("can't create Column instance."));
			}
        }
        
        @Test
        public void カラムデータが不正() {
			try {
				Column sut = new Column(100, 0, null);
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("can't create Column instance."));
			}
        }
    }
	
	public static class ランダム設定なし extends TestCaseTemplate{
		 
        protected Column sut ;
        
        @Test
        public void test() {
        	try {
				Column sut = new Column(-1L, 0, "a");
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("can't create Column instance."));
			}
        }
 
    }
	
//	@Test
//	public void constractor() {
//		
//		
//		try {
//			Column sut = new Column(100, 0, null);
//			fail();
//		} catch (IllegalArgumentException e) {
//			assertThat(e.getMessage(), is("can't create Column instance."));
//		}
//		
//		
//		try {
//			Column sut = new Column(100, 0, "");
//			assertThat(sut.dataList.size(), is(1));
//			assertThat(sut.dataList.get(0) instanceof NothingCountSpecify, is(true));
//			assertThat(((NothingCountSpecify)sut.dataList.get(0)).value , is(""));
//		} catch (IllegalArgumentException e) {
//			fail(e);
//		}
//		
//		try {
//			Column sut = new Column(100, 0, "a");
//			assertThat(sut.dataList.size(), is(1));
//			assertThat(sut.dataList.get(0) instanceof NothingCountSpecify, is(true));
//			assertThat(((NothingCountSpecify)sut.dataList.get(0)).value , is("a"));
//		} catch (IllegalArgumentException e) {
//			fail(e);
//		}
//		
//		try {
//			Column sut = new Column(100, 0, "\"\"");
//			assertThat(sut.dataList.size(), is(1));
//			assertThat(sut.dataList.get(0) instanceof NothingCountSpecify, is(true));
//			assertThat(((NothingCountSpecify)sut.dataList.get(0)).value , is("\"\""));
//		} catch (IllegalArgumentException e) {
//			fail(e);
//		}
//		
//		try {
//			Column sut = new Column(100, 0, "\"a\"");
//			assertThat(sut.dataList.size(), is(1));
//			assertThat(sut.dataList.get(0) instanceof NothingCountSpecify, is(true));
//			assertThat(((NothingCountSpecify)sut.dataList.get(0)).value , is("\"a\""));
//		} catch (IllegalArgumentException e) {
//			fail(e);
//		}
//		
//		try {
//			Column sut = new Column(100, 0, "a;b");
//			assertThat(sut.dataList.size(), is(2));
//			assertThat(sut.dataList.get(0) instanceof NothingCountSpecify, is(true));
//			assertThat(((NothingCountSpecify)sut.dataList.get(0)).value , is("a"));
//			assertThat(sut.dataList.get(1) instanceof NothingCountSpecify, is(true));
//			assertThat(((NothingCountSpecify)sut.dataList.get(1)).value , is("b"));
//		} catch (IllegalArgumentException e) {
//			fail(e);
//		}
//		
//		try {
//			Column sut = new Column(100, 0, "a;");
//			assertThat(sut.dataList.size(), is(2));
//			assertThat(sut.dataList.get(0) instanceof NothingCountSpecify, is(true));
//			assertThat(((NothingCountSpecify)sut.dataList.get(0)).value , is("a"));
//			assertThat(sut.dataList.get(1) instanceof NothingCountSpecify, is(true));
//			assertThat(((NothingCountSpecify)sut.dataList.get(1)).value , is(""));
//		} catch (IllegalArgumentException e) {
//			fail(e);
//		}
//		
//		try {
//			Column sut = new Column(100, 0, "\"a\";\"\"");
//			assertThat(sut.dataList.size(), is(2));
//			assertThat(sut.dataList.get(0) instanceof NothingCountSpecify, is(true));
//			assertThat(((NothingCountSpecify)sut.dataList.get(0)).value , is("\"a\""));
//			assertThat(sut.dataList.get(1) instanceof NothingCountSpecify, is(true));
//			assertThat(((NothingCountSpecify)sut.dataList.get(1)).value , is("\"\""));
//		} catch (IllegalArgumentException e) {
//			fail(e);
//		}
//		
//		try {
//			Column sut = new Column(100, 0, "@a[aaaa=10]");
//			assertThat(sut.dataList.size(), is(1));
//			assertThat(sut.dataList.get(0) instanceof AbsoluteCountSpecify, is(true));
//			assertThat(((AbsoluteCountSpecify)sut.dataList.get(0)).value , is("aaaa"));
//			assertThat(((AbsoluteCountSpecify)sut.dataList.get(0)).origin_count , is(10L));
//		} catch (IllegalArgumentException e) {
//			fail(e);
//		}
//		
//		try {
//			Column sut = new Column(100, 0, "@a[\"aaaa\"=10]");
//			assertThat(sut.dataList.size(), is(1));
//			assertThat(sut.dataList.get(0) instanceof AbsoluteCountSpecify, is(true));
//			assertThat(((AbsoluteCountSpecify)sut.dataList.get(0)).value , is("\"aaaa\""));
//			assertThat(((AbsoluteCountSpecify)sut.dataList.get(0)).origin_count , is(10L));
//		} catch (IllegalArgumentException e) {
//			fail(e);
//		}
//		try {
//			Column sut = new Column(100, 0, "@a[\"aaaa\"=10];@a[\"bbbb\"=20]");
//			assertThat(sut.dataList.size(), is(2));
//			assertThat(sut.dataList.get(0) instanceof AbsoluteCountSpecify, is(true));
//			assertThat(((AbsoluteCountSpecify)sut.dataList.get(0)).value , is("\"aaaa\""));
//			assertThat(((AbsoluteCountSpecify)sut.dataList.get(0)).origin_count , is(10L));
//			assertThat(sut.dataList.get(1) instanceof AbsoluteCountSpecify, is(true));
//			assertThat(((AbsoluteCountSpecify)sut.dataList.get(1)).value , is("\"bbbb\""));
//			assertThat(((AbsoluteCountSpecify)sut.dataList.get(1)).origin_count , is(20L));
//		} catch (IllegalArgumentException e) {
//			fail(e);
//		}
//		
//		try {
//			Column sut = new Column(100, 0, "@p[\"aaaa\"=10]");
//			assertThat(sut.dataList.size(), is(1));
//			assertThat(sut.dataList.get(0) instanceof PacentageCountSpecify, is(true));
//			assertThat(((PacentageCountSpecify)sut.dataList.get(0)).value , is("\"aaaa\""));
//			assertThat(((PacentageCountSpecify)sut.dataList.get(0)).percentage , is(10L));
//		} catch (IllegalArgumentException e) {
//			fail(e);
//		}
//		try {
//			Column sut = new Column(100, 0, "@p[\"aaaa\"=10];@p[\"bbbb\"=20]");
//			assertThat(sut.dataList.size(), is(2));
//			assertThat(sut.dataList.get(0) instanceof PacentageCountSpecify, is(true));
//			assertThat(((PacentageCountSpecify)sut.dataList.get(0)).value , is("\"aaaa\""));
//			assertThat(((PacentageCountSpecify)sut.dataList.get(0)).percentage , is(10L));
//			assertThat(sut.dataList.get(1) instanceof PacentageCountSpecify, is(true));
//			assertThat(((PacentageCountSpecify)sut.dataList.get(1)).value , is("\"bbbb\""));
//			assertThat(((PacentageCountSpecify)sut.dataList.get(1)).percentage , is(20L));
//		} catch (IllegalArgumentException e) {
//			fail(e);
//		}
//		
//		try {
//			Column sut = new Column(100, 0, "@a[\"aaaa\"=10];@p[\"bbbb\"=20];cccc");
//			assertThat(sut.dataList.size(), is(3));
//			assertThat(sut.dataList.get(0) instanceof AbsoluteCountSpecify, is(true));
//			assertThat(((AbsoluteCountSpecify)sut.dataList.get(0)).value , is("\"aaaa\""));
//			assertThat(((AbsoluteCountSpecify)sut.dataList.get(0)).origin_count , is(10L));
//			assertThat(sut.dataList.get(1) instanceof PacentageCountSpecify, is(true));
//			assertThat(((PacentageCountSpecify)sut.dataList.get(1)).value , is("\"bbbb\""));
//			assertThat(((PacentageCountSpecify)sut.dataList.get(1)).percentage , is(20L));
//			assertThat(sut.dataList.get(2) instanceof NothingCountSpecify, is(true));
//			assertThat(((NothingCountSpecify)sut.dataList.get(2)).value , is("cccc"));
//			
//		} catch (IllegalArgumentException e) {
//			fail(e);
//		}
//	}

}
