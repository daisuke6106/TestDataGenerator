package jp.co.dk.testdatagenerator;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class RecordTest extends TestCaseTemplate{
	
	protected static String newLine = System.getProperty("line.separator");
	
	public static class コンストラクタ extends TestCaseTemplate {
		
		@Test
		public void 出力件数が不正() {
			try {
				new Record(0, "a");
				fail();
			} catch (IllegalAccessException e) {
				assertThat(e.getMessage(), is("can't create Record instance. outputCount is invalid."));
			}
		}
		
		@Test
		public void フォーマットが不正() {
			try {
				new Record(1, null);
				fail();
			} catch (IllegalAccessException e) {
				assertThat(e.getMessage(), is("can't create Record instance. format is not set."));
			}
		}
		
	}
	
	public static class フォーマットが１カラム且つ空文字 extends TestCaseTemplate {
		
		protected Record sut;
		
		@Before
		public void init() {
			try {
				this.sut = new Record(1, "");
				assertThat(this.sut.count, is(1L));
				assertThat(this.sut.format, is(""));
				assertThat(this.sut.columns.size(), is(1));
				assertThat(this.sut.columns.get(0).toString(), is(""));
				assertThat(this.sut.newLine, is(RecordTest.newLine));
				
			} catch (IllegalAccessException e) {
				fail(e);
			}
		}
		
		@Test
		public void getValue() {
			assertThat(sut.getValue(0), is("" + newLine));
		}
	}
	
	public static class フォーマットが１カラム且つ単一文字 extends TestCaseTemplate {
		
		protected Record sut;
		
		@Before
		public void init() {
			try {
				this.sut = new Record(1, "a");
				assertThat(this.sut.count, is(1L));
				assertThat(this.sut.format, is("a"));
				assertThat(this.sut.columns.size(), is(1));
				assertThat(this.sut.columns.get(0).toString(), is("a"));
				assertThat(this.sut.newLine, is(RecordTest.newLine));
				
			} catch (IllegalAccessException e) {
				fail(e);
			}
		}
		
		@Test
		public void getValue() {
			assertThat(sut.getValue(0), is("a" + newLine));
		}
	}
	
	public static class フォーマットが複数カラム且つ全て空文字 extends TestCaseTemplate {
		
		protected Record sut;
		
		@Before
		public void init() {
			try {
				this.sut = new Record(1, ",");
				assertThat(this.sut.count, is(1L));
				assertThat(this.sut.format, is(","));
				assertThat(this.sut.columns.size(), is(2));
				assertThat(this.sut.columns.get(0).toString(), is(""));
				assertThat(this.sut.columns.get(1).toString(), is(""));
				assertThat(this.sut.newLine, is(RecordTest.newLine));
				
			} catch (IllegalAccessException e) {
				fail(e);
			}
		}
		
		@Test
		public void getValue() {
			assertThat(sut.getValue(0), is("" + "," + "" + newLine));
		}
	}
	
	public static class フォーマットが複数カラム且つ単一文字と空文字 extends TestCaseTemplate {
		
		protected Record sut;
		
		@Before
		public void init() {
			try {
				this.sut = new Record(1, "a,");
				assertThat(this.sut.count, is(1L));
				assertThat(this.sut.format, is("a,"));
				assertThat(this.sut.columns.size(), is(2));
				assertThat(this.sut.columns.get(0).toString(), is("a"));
				assertThat(this.sut.columns.get(1).toString(), is(""));
				assertThat(this.sut.newLine, is(RecordTest.newLine));
				
			} catch (IllegalAccessException e) {
				fail(e);
			}
		}
		
		@Test
		public void getValue() {
			assertThat(sut.getValue(0), is("a" + "," + "" + newLine));
		}
	}
	
	
	public static class フォーマットが複数カラム且つ単一文字 extends TestCaseTemplate {
		
		protected Record sut;
		
		@Before
		public void init() {
			try {
				this.sut = new Record(1, "a,b");
				assertThat(this.sut.count, is(1L));
				assertThat(this.sut.format, is("a,b"));
				assertThat(this.sut.columns.size(), is(2));
				assertThat(this.sut.columns.get(0).toString(), is("a"));
				assertThat(this.sut.columns.get(1).toString(), is("b"));
				assertThat(this.sut.newLine, is(RecordTest.newLine));
				
			} catch (IllegalAccessException e) {
				fail(e);
			}
		}
		
		@Test
		public void getValue() {
			assertThat(sut.getValue(0), is("a" + "," + "b" + newLine));
		}
	}
	
	public static class フォーマットが複数カラム且つ複数文字 extends TestCaseTemplate {
		
		protected Record sut;
		
		@Before
		public void init() {
			try {
				this.sut = new Record(1, "\"a\",\"b\"");
				assertThat(this.sut.count, is(1L));
				assertThat(this.sut.format, is("\"a\",\"b\""));
				assertThat(this.sut.columns.size(), is(2));
				assertThat(this.sut.columns.get(0).toString(), is("\"a\""));
				assertThat(this.sut.columns.get(1).toString(), is("\"b\""));
				assertThat(this.sut.newLine, is(RecordTest.newLine));
				
			} catch (IllegalAccessException e) {
				fail(e);
			}
		}
		
		@Test
		public void getValue() {
			assertThat(sut.getValue(0), is("\"a\"" + "," + "\"b\"" + newLine));
		}
	}
	
	public static class フォーマットが単数カラム且つ件数固定関数指定 extends TestCaseTemplate {
		
		protected Record sut;
		
		@Before
		public void init() {
			try {
				this.sut = new Record(10,"a=3");
				assertThat(this.sut.count, is(10L));
				assertThat(this.sut.format, is("a=3"));
				assertThat(this.sut.columns.size(), is(1));
				assertThat(this.sut.columns.get(0).toString(), is("a=3"));
				assertThat(this.sut.newLine, is(RecordTest.newLine));
				
			} catch (IllegalAccessException e) {
				fail(e);
			}
		}
		
		@Test
		public void getValue() {
			assertThat(sut.getValue(0), is("a" + RecordTest.newLine));
			assertThat(sut.getValue(1), is("a" + RecordTest.newLine));
			assertThat(sut.getValue(2), is("a" + RecordTest.newLine));
			assertThat(sut.getValue(3), is(""  + RecordTest.newLine));
		}
	}
	
	public static class フォーマットが複数カラム且つ件数固定関数指定 extends TestCaseTemplate {
		
		protected Record sut;
		
		@Before
		public void init() {
			try {
				this.sut = new Record(10,"\"a\"=3,\"b\"=2");
				assertThat(this.sut.count, is(10L));
				assertThat(this.sut.format, is("\"a\"=3,\"b\"=2"));
				assertThat(this.sut.columns.size(), is(2));
				assertThat(this.sut.columns.get(0).toString(), is("\"a\"=3"));
				assertThat(this.sut.columns.get(1).toString(), is("\"b\"=2"));
				assertThat(this.sut.newLine, is(RecordTest.newLine));
				
			} catch (IllegalAccessException e) {
				fail(e);
			}
		}
		
		@Test
		public void getValue() {
			assertThat(sut.getValue(0), is("\"a\",\"b\"" + RecordTest.newLine));
			assertThat(sut.getValue(1), is("\"a\",\"b\"" + RecordTest.newLine));
			assertThat(sut.getValue(2), is("\"a\"," + RecordTest.newLine));
			assertThat(sut.getValue(3), is(","  + RecordTest.newLine));
		}
	}
	
	public static class フォーマットが複数カラム且つ件数固定関数指定デフォルト指定あり extends TestCaseTemplate {
		
		protected Record sut;
		
		@Before
		public void init() {
			try {
				this.sut = new Record(10,"\"a\"=3,\"b\"=2,\"c\"");
				assertThat(this.sut.count, is(10L));
				assertThat(this.sut.format, is("\"a\"=3,\"b\"=2,\"c\""));
				assertThat(sut.columns.size(), is(3));
				assertThat(sut.columns.get(0).toString(), is("\"a\"=3"));
				assertThat(sut.columns.get(1).toString(), is("\"b\"=2"));
				assertThat(sut.columns.get(2).toString(), is("\"c\""));
				
			} catch (IllegalAccessException e) {
				fail(e);
			}
		}
		
		@Test
		public void getValue() {
			assertThat(sut.getValue(0), is("\"a\",\"b\",\"c\"" + RecordTest.newLine));
			assertThat(sut.getValue(1), is("\"a\",\"b\",\"c\"" + RecordTest.newLine));
			assertThat(sut.getValue(2), is("\"a\",,\"c\"" + RecordTest.newLine));
			assertThat(sut.getValue(3), is(",,\"c\""  + RecordTest.newLine));
		}
	}
	
	public static class フォーマットが単数カラム且つ件数パーセンテージ関数指定 extends TestCaseTemplate {
		
		protected Record sut;
		
		@Before
		public void init() {
			try {
				this.sut = new Record(5,"a=20%");
				assertThat(this.sut.count, is(5L));
				assertThat(this.sut.format, is("a=20%"));
				assertThat(this.sut.columns.size(), is(1));
				assertThat(this.sut.columns.get(0).toString(), is("a=20%"));
				assertThat(this.sut.newLine, is(RecordTest.newLine));
				
			} catch (IllegalAccessException e) {
				fail(e);
			}
		}
		
		@Test
		public void getValue() {
			assertThat(sut.getValue(0), is("a" + RecordTest.newLine));
			assertThat(sut.getValue(1), is(""  + RecordTest.newLine));
			assertThat(sut.getValue(2), is(""  + RecordTest.newLine));
			assertThat(sut.getValue(3), is(""  + RecordTest.newLine));
			assertThat(sut.getValue(4), is(""  + RecordTest.newLine));
		}
	}
	
	public static class フォーマットが複数カラム且つ件数パーセンテージ関数指定 extends TestCaseTemplate {
		
		protected Record sut;
		
		@Before
		public void init() {
			try {
				this.sut = new Record(5,"\"a\"=80%,\"b\"=50%");
				assertThat(this.sut.count, is(5L));
				assertThat(this.sut.format, is("\"a\"=80%,\"b\"=50%"));
				assertThat(this.sut.columns.size(), is(2));
				assertThat(this.sut.columns.get(0).toString(), is("\"a\"=80%"));
				assertThat(this.sut.columns.get(1).toString(), is("\"b\"=50%"));
				assertThat(this.sut.newLine, is(RecordTest.newLine));
				
			} catch (IllegalAccessException e) {
				fail(e);
			}
		}
		
		@Test
		public void getValue() {
			assertThat(sut.getValue(0), is("\"a\",\"b\"" + RecordTest.newLine));
			assertThat(sut.getValue(1), is("\"a\",\"b\"" + RecordTest.newLine));
			assertThat(sut.getValue(2), is("\"a\",\"b\"" + RecordTest.newLine));
			assertThat(sut.getValue(3), is("\"a\","  + RecordTest.newLine));
			assertThat(sut.getValue(4), is(","  + RecordTest.newLine));
		}
	}
	
	public static class フォーマットが複数カラム且つ件数パーセンテージ関数指定デフォルト指定あり extends TestCaseTemplate {
		
		protected Record sut;
		
		@Before
		public void init() {
			try {
				this.sut = new Record(5,"\"a\"=80%,\"b\"=50%,\"c\"");
				assertThat(this.sut.count, is(5L));
				assertThat(this.sut.format, is("\"a\"=80%,\"b\"=50%,\"c\""));
				assertThat(sut.columns.size(), is(3));
				assertThat(sut.columns.get(0).toString(), is("\"a\"=80%"));
				assertThat(sut.columns.get(1).toString(), is("\"b\"=50%"));
				assertThat(sut.columns.get(2).toString(), is("\"c\""));
				
			} catch (IllegalAccessException e) {
				fail(e);
			}
		}
		
		@Test
		public void getValue() {
			assertThat(sut.getValue(0), is("\"a\",\"b\",\"c\"" + RecordTest.newLine));
			assertThat(sut.getValue(1), is("\"a\",\"b\",\"c\"" + RecordTest.newLine));
			assertThat(sut.getValue(2), is("\"a\",\"b\",\"c\"" + RecordTest.newLine));
			assertThat(sut.getValue(3), is("\"a\",,\"c\""  + RecordTest.newLine));
			assertThat(sut.getValue(3), is(",,\"c\""  + RecordTest.newLine));
		}
	}
}
