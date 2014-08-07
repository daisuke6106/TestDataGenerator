package jp.co.dk.testdatagenerator;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Test;

public class RecordTest extends TestCaseTemplate{
	
	protected static String newLine = System.getProperty("line.separator");
	
	@Test
	public void constractor() {
		
		try {
			Record sut = new Record(1, "a");
			assertThat(sut.columns.size(), is(1));
			assertThat(sut.columns.get(0).toString(), is("a"));
			
			assertThat(sut.getValue(0), is("a" + newLine));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record(1,"a,");
			assertThat(sut.columns.size(), is(2));
			assertThat(sut.columns.get(0).toString(), is("a"));
			assertThat(sut.columns.get(1).toString(), is(""));
			
			assertThat(sut.getValue(0), is("a," + newLine));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record(1,"a,b");
			assertThat(sut.columns.size(), is(2));
			assertThat(sut.columns.get(0).toString(), is("a"));
			assertThat(sut.columns.get(1).toString(), is("b"));
			
			assertThat(sut.getValue(0), is("a,b" + newLine));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record(1,"\"a\",\"b\"");
			assertThat(sut.columns.size(), is(2));
			assertThat(sut.columns.get(0).toString(), is("\"a\""));
			assertThat(sut.columns.get(1).toString(), is("\"b\""));
			
			assertThat(sut.getValue(0), is("\"a\",\"b\"" + newLine));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record(1,"\"a\",\"\"");
			assertThat(sut.columns.size(), is(2));
			assertThat(sut.columns.get(0).toString(), is("\"a\""));
			assertThat(sut.columns.get(1).toString(), is("\"\""));
			
			assertThat(sut.getValue(0), is("\"a\",\"\"" + newLine));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record(1,"\"a\",");
			assertThat(sut.columns.size(), is(2));
			assertThat(sut.columns.get(0).toString(), is("\"a\""));
			assertThat(sut.columns.get(1).toString(), is(""));
			
			assertThat(sut.getValue(0), is("\"a\"," + newLine));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record(1,"[\"a\"]");
			assertThat(sut.columns.size(), is(1));
			assertThat(sut.columns.get(0).toString(), is("[\"a\"]"));
			
			assertThat(sut.getValue(0), is("[\"a\"]" + newLine));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record(1,"@a[\"a\"]");
			assertThat(sut.columns.size(), is(1));
			assertThat(sut.columns.get(0).toString(), is("@a[\"a\"]"));
			
			assertThat(sut.getValue(0), is("@a[\"a\"]" + newLine));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		try {
			Record sut = new Record(10,"@a[a=3]");
			assertThat(sut.columns.size(), is(1));
			assertThat(sut.columns.get(0).toString(), is("@a[a=3]"));
			
			assertThat(sut.getValue(0), is("a" + newLine));
			assertThat(sut.getValue(1), is("a" + newLine));
			assertThat(sut.getValue(2), is("a" + newLine));
			assertThat(sut.getValue(3), is(""  + newLine));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record(10,"@a[\"a\"=3]");
			assertThat(sut.columns.size(), is(1));
			assertThat(sut.columns.get(0).toString(), is("@a[\"a\"=3]"));
			
			assertThat(sut.getValue(0), is("\"a\"" + newLine));
			assertThat(sut.getValue(1), is("\"a\"" + newLine));
			assertThat(sut.getValue(2), is("\"a\"" + newLine));
			assertThat(sut.getValue(3), is(""  + newLine));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record(10,"@a[\"a\"=2];@a[\"b\"=3]");
			assertThat(sut.columns.size(), is(1));
			assertThat(sut.columns.get(0).toString(), is("@a[\"a\"=2];@a[\"b\"=3]"));
			
			assertThat(sut.getValue(0), is("\"a\"" + newLine));
			assertThat(sut.getValue(1), is("\"a\"" + newLine));
			assertThat(sut.getValue(2), is("\"b\"" + newLine));
			assertThat(sut.getValue(3), is("\"b\"" + newLine));
			assertThat(sut.getValue(4), is("\"b\"" + newLine));
			assertThat(sut.getValue(5), is(""      + newLine));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record(10,"@a[\"a\"=2];@a[\"b\"=3]");
			assertThat(sut.columns.size(), is(1));
			assertThat(sut.columns.get(0).toString(), is("@a[\"a\"=2];@a[\"b\"=3]"));
			
			assertThat(sut.getValue(0), is("\"a\"" + newLine));
			assertThat(sut.getValue(1), is("\"a\"" + newLine));
			assertThat(sut.getValue(2), is("\"b\"" + newLine));
			assertThat(sut.getValue(3), is("\"b\"" + newLine));
			assertThat(sut.getValue(4), is("\"b\"" + newLine));
			assertThat(sut.getValue(5), is(""      + newLine));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record(10,"@a[\"a\"=2];@a[\"b\"=3];\"c\"");
			assertThat(sut.columns.size(), is(1));
			assertThat(sut.columns.get(0).toString(), is("@a[\"a\"=2];@a[\"b\"=3];\"c\""));
			
			assertThat(sut.getValue(0), is("\"a\"" + newLine));
			assertThat(sut.getValue(1), is("\"a\"" + newLine));
			assertThat(sut.getValue(2), is("\"b\"" + newLine));
			assertThat(sut.getValue(3), is("\"b\"" + newLine));
			assertThat(sut.getValue(4), is("\"b\"" + newLine));
			assertThat(sut.getValue(5), is("\"c\"" + newLine));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record(10,"@p[\"a\"=20]");
			assertThat(sut.columns.size(), is(1));
			assertThat(sut.columns.get(0).toString(), is("@p[\"a\"=20]"));
			
			assertThat(sut.getValue(0), is("\"a\"" + newLine));
			assertThat(sut.getValue(1), is("\"a\"" + newLine));
			assertThat(sut.getValue(2), is("" + newLine));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record(10,"@p[\"a\"=20];@p[\"b\"=30];@p[\"c\"=40]");
			assertThat(sut.columns.size(), is(1));
			assertThat(sut.columns.get(0).toString(), is("@p[\"a\"=20];@p[\"b\"=30];@p[\"c\"=40]"));
			
			assertThat(sut.getValue(0),  is("\"a\"" + newLine));
			assertThat(sut.getValue(1),  is("\"a\"" + newLine));
			assertThat(sut.getValue(2),  is("\"b\"" + newLine));
			assertThat(sut.getValue(3),  is("\"b\"" + newLine));
			assertThat(sut.getValue(4),  is("\"b\"" + newLine));
			assertThat(sut.getValue(5),  is("\"c\"" + newLine));
			assertThat(sut.getValue(6),  is("\"c\"" + newLine));
			assertThat(sut.getValue(7),  is("\"c\"" + newLine));
			assertThat(sut.getValue(8),  is("\"c\"" + newLine));
			assertThat(sut.getValue(9),  is("" + newLine));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record(10,"@p[\"a\"=20];@p[\"b\"=30];@p[\"c\"=40];\"d\"");
			assertThat(sut.columns.size(), is(1));
			assertThat(sut.columns.get(0).toString(), is("@p[\"a\"=20];@p[\"b\"=30];@p[\"c\"=40];\"d\""));
			
			assertThat(sut.getValue(0),  is("\"a\"" + newLine));
			assertThat(sut.getValue(1),  is("\"a\"" + newLine));
			assertThat(sut.getValue(2),  is("\"b\"" + newLine));
			assertThat(sut.getValue(3),  is("\"b\"" + newLine));
			assertThat(sut.getValue(4),  is("\"b\"" + newLine));
			assertThat(sut.getValue(5),  is("\"c\"" + newLine));
			assertThat(sut.getValue(6),  is("\"c\"" + newLine));
			assertThat(sut.getValue(7),  is("\"c\"" + newLine));
			assertThat(sut.getValue(8),  is("\"c\"" + newLine));
			assertThat(sut.getValue(9),  is("\"d\"" + newLine));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record(10,"@a[\"a\"=3];@a[\"b\"=3];\"c\",@p[1=50];@p[2=50]");
			assertThat(sut.columns.size(), is(2));
			assertThat(sut.columns.get(0).toString(), is("@a[\"a\"=3];@a[\"b\"=3];\"c\""));
			assertThat(sut.columns.get(1).toString(), is("@p[1=50];@p[2=50]"));
			assertThat(sut.getValue(0),  is("\"a\",1" + newLine));
			assertThat(sut.getValue(1),  is("\"a\",1" + newLine));
			assertThat(sut.getValue(2),  is("\"a\",1" + newLine));
			assertThat(sut.getValue(3),  is("\"b\",1" + newLine));
			assertThat(sut.getValue(4),  is("\"b\",1" + newLine));
			assertThat(sut.getValue(5),  is("\"b\",2" + newLine));
			assertThat(sut.getValue(6),  is("\"c\",2" + newLine));
			assertThat(sut.getValue(7),  is("\"c\",2" + newLine));
			assertThat(sut.getValue(8),  is("\"c\",2" + newLine));
			assertThat(sut.getValue(9),  is("\"c\",2" + newLine));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record(10,"@r[\"a\";\"b\"]");
			assertThat(sut.columns.size(), is(1));
			assertThat(sut.columns.get(0).toString(), is("@r[\"a\";\"b\"]"));
			
			// ランダムアクセスするため、テスト失敗の場合が多い
//			assertThat(sut.getValue(0),  is("\"a\"" + newLine));
//			assertThat(sut.getValue(1),  is("\"b\"" + newLine));
			
		} catch (IllegalAccessException e) {
			fail(e);
		}
	}

}
