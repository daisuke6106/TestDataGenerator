package jp.co.dk.testdatagenerator;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Test;

public class RecordTest extends TestCaseTemplate{

	@Test
	public void constractor() {
		
		try {
			Record sut = new Record("a");
			assertThat(sut.columns.size(), is(1));
			assertThat(sut.columns.get(0).toString(), is("a"));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record("a,");
			assertThat(sut.columns.size(), is(2));
			assertThat(sut.columns.get(0).toString(), is("a"));
			assertThat(sut.columns.get(1).toString(), is(""));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record("a,b");
			assertThat(sut.columns.size(), is(2));
			assertThat(sut.columns.get(0).toString(), is("a"));
			assertThat(sut.columns.get(1).toString(), is("b"));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record("\"a\",\"b\"");
			assertThat(sut.columns.size(), is(2));
			assertThat(sut.columns.get(0).toString(), is("\"a\""));
			assertThat(sut.columns.get(1).toString(), is("\"b\""));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record("\"a\",\"\"");
			assertThat(sut.columns.size(), is(2));
			assertThat(sut.columns.get(0).toString(), is("\"a\""));
			assertThat(sut.columns.get(1).toString(), is("\"\""));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record("\"a\",");
			assertThat(sut.columns.size(), is(2));
			assertThat(sut.columns.get(0).toString(), is("\"a\""));
			assertThat(sut.columns.get(1).toString(), is(""));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record("[\"a\"]");
			assertThat(sut.columns.size(), is(1));
			assertThat(sut.columns.get(0).toString(), is("[\"a\"]"));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record("@a[\"a\"];@p[\"b\"]");
			assertThat(sut.columns.size(), is(1));
			assertThat(sut.columns.get(0).toString(), is("@a[\"a\"];@p[\"b\"]"));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record("@a[\"a\"=10];@a[\"b\"=10]");
			assertThat(sut.columns.size(), is(1));
			assertThat(sut.columns.get(0).toString(), is("@a[\"a\"=10];@a[\"b\"=10]"));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		try {
			Record sut = new Record("@a[\"a\"=10];@a[\"b\"=10],@p[\"a\"=10];@p[\"b\"=10]");
			assertThat(sut.columns.size(), is(2));
			assertThat(sut.columns.get(0).toString(), is("@a[\"a\"=10];@a[\"b\"=10]"));
			assertThat(sut.columns.get(1).toString(), is("@p[\"a\"=10];@p[\"b\"=10]"));
		} catch (IllegalAccessException e) {
			fail(e);
		}
		
		
	}

}
