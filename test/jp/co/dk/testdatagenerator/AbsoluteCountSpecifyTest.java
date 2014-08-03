package jp.co.dk.testdatagenerator;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Test;

public class AbsoluteCountSpecifyTest extends TestCaseTemplate {

	@Test
	public void constractor() {
		try {
			String           value            = null;
			long             count            = 1L;
			CountSpecifyType countSpecifyType = CountSpecifyType.ABSOLUTE_SPECIFIED;
			AbsoluteCountSpecify sut = new AbsoluteCountSpecify(value, count);
			fail();
		} catch(IllegalArgumentException e) {
			assertThat(e.getMessage(), is("can't create CountSpecifyType instance."));
		}
		
		try {
			String           value            = "a";
			long             count            = 0L;
			CountSpecifyType countSpecifyType = CountSpecifyType.ABSOLUTE_SPECIFIED;
			AbsoluteCountSpecify sut = new AbsoluteCountSpecify(value, count);
			fail();
		} catch(IllegalArgumentException e) {
			assertThat(e.getMessage(), is("can't create CountSpecifyType instance."));
		}
		
		try {
			String           value            = "a";
			long             count            = 1L;
			CountSpecifyType countSpecifyType = CountSpecifyType.ABSOLUTE_SPECIFIED;
			AbsoluteCountSpecify sut = new AbsoluteCountSpecify(value, count);
			assertThat(sut.value                , is(value));
			assertThat(sut.count                , is(count));
			assertThat(sut.getCountSpecifyType(), is(countSpecifyType));
		} catch(IllegalArgumentException e) {
			fail(e);
		}
	}
}


