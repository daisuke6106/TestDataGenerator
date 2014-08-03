package jp.co.dk.testdatagenerator;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Test;

public class NothingCountSpecifyTest extends TestCaseTemplate {

	@Test
	public void constractor() {
		try {
			String           value            = null;
			CountSpecifyType countSpecifyType = CountSpecifyType.NOTHING_SPECIFIED;
			NothingCountSpecify sut = new NothingCountSpecify(value);
			fail();
		} catch(IllegalArgumentException e) {
			assertThat(e.getMessage(), is("can't create CountSpecifyType instance."));
		}
		
		try {
			String           value            = "a";
			CountSpecifyType countSpecifyType = CountSpecifyType.NOTHING_SPECIFIED;
			NothingCountSpecify sut = new NothingCountSpecify(value);
			assertThat(sut.value                , is(value));
			assertThat(sut.getCountSpecifyType(), is(countSpecifyType));
		} catch(IllegalArgumentException e) {
			fail(e);
		}
	}
}


