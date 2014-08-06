package jp.co.dk.testdatagenerator;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Test;

public class CountSpecifyPatternTest extends TestCaseTemplate{
	
	@Test
	public void ABSOLUTE_SPECIFIED_Test() {
		// 空文字の場合
		assertThat(CountSpecifyPattern.ABSOLUTE_SPECIFIED.match(""), is(false));
		// 文字が入っている場合
		assertThat(CountSpecifyPattern.ABSOLUTE_SPECIFIED.match("a"), is(false));
		// 文字が入っている場合（複数）
		assertThat(CountSpecifyPattern.ABSOLUTE_SPECIFIED.match("あいうえお"), is(false));
		// 指定のフォーマットに近い形で定義されているが一部欠落している場合
		assertThat(CountSpecifyPattern.ABSOLUTE_SPECIFIED.match("a[aaa=10]"), is(false));
		assertThat(CountSpecifyPattern.ABSOLUTE_SPECIFIED.match("@[aaa=10]"), is(false));
		assertThat(CountSpecifyPattern.ABSOLUTE_SPECIFIED.match("@aaaa=10]"), is(false));
		assertThat(CountSpecifyPattern.ABSOLUTE_SPECIFIED.match("@a[aaa10]"), is(false));
		assertThat(CountSpecifyPattern.ABSOLUTE_SPECIFIED.match("@a[aaa=]"), is(false));
		assertThat(CountSpecifyPattern.ABSOLUTE_SPECIFIED.match("@a[aaa=10"), is(false));
		
		// 正常なフォーマットで定義されていた場合
		assertThat(CountSpecifyPattern.ABSOLUTE_SPECIFIED.match("@a[aaa=10]"), is(true));
		CountSpecify countSpecify1 = CountSpecifyPattern.ABSOLUTE_SPECIFIED.getCountSpecify("@a[aaa=10]");
		assertThat((countSpecify1 instanceof AbsoluteCountSpecify), is(true));
		assertThat((((AbsoluteCountSpecify)countSpecify1).value), is("aaa"));
		assertThat((((AbsoluteCountSpecify)countSpecify1).count), is(10L));
		
		assertThat(CountSpecifyPattern.ABSOLUTE_SPECIFIED.match("@a[=10]"), is(true));
		CountSpecify countSpecify2 = CountSpecifyPattern.ABSOLUTE_SPECIFIED.getCountSpecify("@a[=10]");
		assertThat((countSpecify2 instanceof AbsoluteCountSpecify), is(true));
		assertThat((((AbsoluteCountSpecify)countSpecify2).value), is(""));
		assertThat((((AbsoluteCountSpecify)countSpecify2).count), is(10L));

		assertThat(CountSpecifyPattern.ABSOLUTE_SPECIFIED.match("@a[\"aaa\"=10]"), is(true));
		CountSpecify countSpecify3 = CountSpecifyPattern.ABSOLUTE_SPECIFIED.getCountSpecify("@a[\"aaa\"=10]");
		assertThat((countSpecify3 instanceof AbsoluteCountSpecify), is(true));
		assertThat((((AbsoluteCountSpecify)countSpecify3).value), is("\"aaa\""));
		assertThat((((AbsoluteCountSpecify)countSpecify3).count), is(10L));
		
	}
	
	@Test
	public void PERCENTAGE_SPECIFIED_Test() {
		// 空文字の場合
		assertThat(CountSpecifyPattern.PERCENTAGE_SPECIFIED.match(""), is(false));
		// 文字が入っている場合
		assertThat(CountSpecifyPattern.PERCENTAGE_SPECIFIED.match("p"), is(false));
		// 文字が入っている場合（複数）
		assertThat(CountSpecifyPattern.PERCENTAGE_SPECIFIED.match("あいうえお"), is(false));
		// 指定のフォーマットに近い形で定義されているが一部欠落している場合
		assertThat(CountSpecifyPattern.PERCENTAGE_SPECIFIED.match("p[ppp=10]"), is(false));
		assertThat(CountSpecifyPattern.PERCENTAGE_SPECIFIED.match("@[ppp=10]"), is(false));
		assertThat(CountSpecifyPattern.PERCENTAGE_SPECIFIED.match("@pppp=10]"), is(false));
		assertThat(CountSpecifyPattern.PERCENTAGE_SPECIFIED.match("@p[ppp10]"), is(false));
		assertThat(CountSpecifyPattern.PERCENTAGE_SPECIFIED.match("@p[ppp=]"), is(false));
		assertThat(CountSpecifyPattern.PERCENTAGE_SPECIFIED.match("@p[ppp=10"), is(false));
		
		// 正常なフォーマットで定義されていた場合
		assertThat(CountSpecifyPattern.PERCENTAGE_SPECIFIED.match("@p[ppp=10]"), is(true));
		CountSpecify countSpecify1 = CountSpecifyPattern.PERCENTAGE_SPECIFIED.getCountSpecify("@p[ppp=10]");
		assertThat((countSpecify1 instanceof PacentageCountSpecify), is(true));
		assertThat((((PacentageCountSpecify)countSpecify1).value), is("ppp"));
		assertThat((((PacentageCountSpecify)countSpecify1).count), is(10L));
		
		assertThat(CountSpecifyPattern.PERCENTAGE_SPECIFIED.match("@p[=10]"), is(true));
		CountSpecify countSpecify2 = CountSpecifyPattern.PERCENTAGE_SPECIFIED.getCountSpecify("@p[=10]");
		assertThat((countSpecify2 instanceof PacentageCountSpecify), is(true));
		assertThat((((PacentageCountSpecify)countSpecify2).value), is(""));
		assertThat((((PacentageCountSpecify)countSpecify2).count), is(10L));
		
		assertThat(CountSpecifyPattern.PERCENTAGE_SPECIFIED.match("@p[\"ppp\"=10]"), is(true));
		CountSpecify countSpecify3 = CountSpecifyPattern.PERCENTAGE_SPECIFIED.getCountSpecify("@p[\"ppp\"=10]");
		assertThat((countSpecify3 instanceof PacentageCountSpecify), is(true));
		assertThat((((PacentageCountSpecify)countSpecify3).value), is("\"ppp\""));
		assertThat((((PacentageCountSpecify)countSpecify3).count), is(10L));
		
	}
	
	@Test
	public void NOTHING_SPECIFIED_Test() {
		// 空文字の場合
		assertThat(CountSpecifyPattern.NOTHING_SPECIFIED.match(""), is(true));
		assertThat(
				CountSpecifyPattern.NOTHING_SPECIFIED.getCountSpecify("") instanceof NothingCountSpecify
			, is(true)
		);
		
		// 文字が入っている場合
		assertThat(CountSpecifyPattern.NOTHING_SPECIFIED.match("a"), is(true));
		assertThat(
				CountSpecifyPattern.NOTHING_SPECIFIED.getCountSpecify("a") instanceof NothingCountSpecify
			, is(true)
		);
		
		// 文字が入っている場合（複数）
		assertThat(CountSpecifyPattern.NOTHING_SPECIFIED.match("あいうえお"), is(true));
		assertThat(
				CountSpecifyPattern.NOTHING_SPECIFIED.getCountSpecify("あいうえお") instanceof NothingCountSpecify
			, is(true)
		);
	}

}
