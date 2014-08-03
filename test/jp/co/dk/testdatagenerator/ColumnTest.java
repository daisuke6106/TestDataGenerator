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
			int                index = -1;
			List<CountSpecify> datas = new ArrayList<CountSpecify>();
			CountSpecify nothing = new NothingCountSpecify("a");
			Column sut = new Column(index, datas);
			fail();
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("can't create Column instance."));
		}
		
		try {
			int                index = 0;
			List<CountSpecify> datas = null;
			Column sut = new Column(index, datas);
			fail();
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("can't create Column instance."));
		}
		
		try {
			int                index = 0;
			List<CountSpecify> datas = new ArrayList<CountSpecify>();
			Column sut = new Column(index, datas);
			fail();
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("can't create Column instance."));
		}
		
		try {
			int                index = 0;
			List<CountSpecify> datas = new ArrayList<CountSpecify>();
			CountSpecify nothing = new NothingCountSpecify("a");
			datas.add(nothing);
			Column sut = new Column(index, datas);
			assertThat(sut.index, is(index));
			assertThat(sut.datas, is(datas));
		} catch (IllegalArgumentException e) {
			fail(e);
		}
	}

}
