package jp.co.dk.testdatagenerator;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Test;

public class FormatTest extends TestCaseTemplate{

	@Test
	public void constractor() {
		
		Format sut = new Format("\"a\"");
		List<Column> columns = sut.getColumnList();
		assertThat(columns.size(), is(1));
		assertThat(columns.size(), is(1));
		
		Format sut = new Format("\"a\",\"1\"");
		List<Column> columns = sut.getColumnList();
		assertThat(columns.size(), is(2));
		
	}

}
