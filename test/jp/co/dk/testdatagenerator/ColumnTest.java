package jp.co.dk.testdatagenerator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Test;

public class ColumnTest extends TestCaseTemplate {

	@Test
	public void constractor() {
		
		try {
			Column sut = new Column(-1, "a");
			fail();
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("can't create Column instance."));
		}
		
		try {
			Column sut = new Column(0, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("can't create Column instance."));
		}
		
		
		try {
			Column sut = new Column(0, "");
			assertThat(sut.dataList.size(), is(1));
			assertThat(sut.dataList.get(0) instanceof NothingCountSpecify, is(true));
			assertThat(((NothingCountSpecify)sut.dataList.get(0)).value , is(""));
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		try {
			Column sut = new Column(0, "a");
			assertThat(sut.dataList.size(), is(1));
			assertThat(sut.dataList.get(0) instanceof NothingCountSpecify, is(true));
			assertThat(((NothingCountSpecify)sut.dataList.get(0)).value , is("a"));
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		try {
			Column sut = new Column(0, "\"\"");
			assertThat(sut.dataList.size(), is(1));
			assertThat(sut.dataList.get(0) instanceof NothingCountSpecify, is(true));
			assertThat(((NothingCountSpecify)sut.dataList.get(0)).value , is("\"\""));
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		try {
			Column sut = new Column(0, "\"a\"");
			assertThat(sut.dataList.size(), is(1));
			assertThat(sut.dataList.get(0) instanceof NothingCountSpecify, is(true));
			assertThat(((NothingCountSpecify)sut.dataList.get(0)).value , is("\"a\""));
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		try {
			Column sut = new Column(0, "a;b");
			assertThat(sut.dataList.size(), is(2));
			assertThat(sut.dataList.get(0) instanceof NothingCountSpecify, is(true));
			assertThat(((NothingCountSpecify)sut.dataList.get(0)).value , is("a"));
			assertThat(sut.dataList.get(1) instanceof NothingCountSpecify, is(true));
			assertThat(((NothingCountSpecify)sut.dataList.get(1)).value , is("b"));
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		try {
			Column sut = new Column(0, "a;");
			assertThat(sut.dataList.size(), is(2));
			assertThat(sut.dataList.get(0) instanceof NothingCountSpecify, is(true));
			assertThat(((NothingCountSpecify)sut.dataList.get(0)).value , is("a"));
			assertThat(sut.dataList.get(1) instanceof NothingCountSpecify, is(true));
			assertThat(((NothingCountSpecify)sut.dataList.get(1)).value , is(""));
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		try {
			Column sut = new Column(0, "\"a\";\"\"");
			assertThat(sut.dataList.size(), is(2));
			assertThat(sut.dataList.get(0) instanceof NothingCountSpecify, is(true));
			assertThat(((NothingCountSpecify)sut.dataList.get(0)).value , is("\"a\""));
			assertThat(sut.dataList.get(1) instanceof NothingCountSpecify, is(true));
			assertThat(((NothingCountSpecify)sut.dataList.get(1)).value , is("\"\""));
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		try {
			Column sut = new Column(0, "@a[aaaa=10]");
			assertThat(sut.dataList.size(), is(1));
			assertThat(sut.dataList.get(0) instanceof AbsoluteCountSpecify, is(true));
			assertThat(((AbsoluteCountSpecify)sut.dataList.get(0)).value , is("aaaa"));
			assertThat(((AbsoluteCountSpecify)sut.dataList.get(0)).count , is(10L));
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		try {
			Column sut = new Column(0, "@a[\"aaaa\"=10]");
			assertThat(sut.dataList.size(), is(1));
			assertThat(sut.dataList.get(0) instanceof AbsoluteCountSpecify, is(true));
			assertThat(((AbsoluteCountSpecify)sut.dataList.get(0)).value , is("\"aaaa\""));
			assertThat(((AbsoluteCountSpecify)sut.dataList.get(0)).count , is(10L));
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		try {
			Column sut = new Column(0, "@a[\"aaaa\"=10];@a[\"bbbb\"=20]");
			assertThat(sut.dataList.size(), is(2));
			assertThat(sut.dataList.get(0) instanceof AbsoluteCountSpecify, is(true));
			assertThat(((AbsoluteCountSpecify)sut.dataList.get(0)).value , is("\"aaaa\""));
			assertThat(((AbsoluteCountSpecify)sut.dataList.get(0)).count , is(10L));
			assertThat(sut.dataList.get(1) instanceof AbsoluteCountSpecify, is(true));
			assertThat(((AbsoluteCountSpecify)sut.dataList.get(1)).value , is("\"bbbb\""));
			assertThat(((AbsoluteCountSpecify)sut.dataList.get(1)).count , is(20L));
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		try {
			Column sut = new Column(0, "@p[\"aaaa\"=10]");
			assertThat(sut.dataList.size(), is(1));
			assertThat(sut.dataList.get(0) instanceof PacentageCountSpecify, is(true));
			assertThat(((PacentageCountSpecify)sut.dataList.get(0)).value , is("\"aaaa\""));
			assertThat(((PacentageCountSpecify)sut.dataList.get(0)).count , is(10L));
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		try {
			Column sut = new Column(0, "@p[\"aaaa\"=10];@p[\"bbbb\"=20]");
			assertThat(sut.dataList.size(), is(2));
			assertThat(sut.dataList.get(0) instanceof PacentageCountSpecify, is(true));
			assertThat(((PacentageCountSpecify)sut.dataList.get(0)).value , is("\"aaaa\""));
			assertThat(((PacentageCountSpecify)sut.dataList.get(0)).count , is(10L));
			assertThat(sut.dataList.get(1) instanceof PacentageCountSpecify, is(true));
			assertThat(((PacentageCountSpecify)sut.dataList.get(1)).value , is("\"bbbb\""));
			assertThat(((PacentageCountSpecify)sut.dataList.get(1)).count , is(20L));
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		try {
			Column sut = new Column(0, "@a[\"aaaa\"=10];@p[\"bbbb\"=20];cccc");
			assertThat(sut.dataList.size(), is(3));
			assertThat(sut.dataList.get(0) instanceof AbsoluteCountSpecify, is(true));
			assertThat(((AbsoluteCountSpecify)sut.dataList.get(0)).value , is("\"aaaa\""));
			assertThat(((AbsoluteCountSpecify)sut.dataList.get(0)).count , is(10L));
			assertThat(sut.dataList.get(1) instanceof PacentageCountSpecify, is(true));
			assertThat(((PacentageCountSpecify)sut.dataList.get(1)).value , is("\"bbbb\""));
			assertThat(((PacentageCountSpecify)sut.dataList.get(1)).count , is(20L));
			assertThat(sut.dataList.get(2) instanceof NothingCountSpecify, is(true));
			assertThat(((NothingCountSpecify)sut.dataList.get(2)).value , is("cccc"));
			
		} catch (IllegalArgumentException e) {
			fail(e);
		}
	}

}
