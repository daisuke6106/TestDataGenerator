package jp.co.dk.testdatagenerator.countspecify;


import jp.co.dk.test.template.TestCaseTemplate;
import jp.co.dk.testdatagenerator.countspecify.PacentageCountSpecify;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class PacentageCountSpecifyTest {

	public static class コンストラクタ extends TestCaseTemplate{
		
		@Test
        public void 引数の出力件数が不正な場合() {
        	try {
        		new PacentageCountSpecify(1L, "a", -1L);
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("パーセンテージ指定に0以下が設定されています。:" + "a"));
			}
        	try {
        		new PacentageCountSpecify(1L, "a", 0L);
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("パーセンテージ指定に0以下が設定されています。:" + "a"));
			}
        }
		
		@Test
        public void 引数のパーセンテージが100以上の場合() {
        	try {
        		new PacentageCountSpecify(100L, "a", 101L);
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("件数指定に出力件数以上のパーセンテージが設定されています。:" + "a"));
			}
        }
		
		@Test
        public void 正常にインスタンスが生成できた場合() {
			for (long i=1; i<=100; i++) {
	        	try {
	        		PacentageCountSpecify sut = new PacentageCountSpecify(100L, "a", i);
					assertThat(sut.percentage,  is(i));
					assertThat(sut.usage_count, is(i));
					assertThat(sut.use_count,   is(0L));
				} catch (IllegalArgumentException e) {
					fail(e);
				}
			}
			
			try {
        		PacentageCountSpecify sut = new PacentageCountSpecify(200L, "a", 50L);
				assertThat(sut.percentage,  is(50L));
				assertThat(sut.usage_count, is(100L));
				assertThat(sut.use_count,   is(0L));
			} catch (IllegalArgumentException e) {
				fail(e);
			}
			
			try {
        		PacentageCountSpecify sut = new PacentageCountSpecify(50L, "a", 33L);
				assertThat(sut.percentage,  is(33L));
				assertThat(sut.usage_count, is(17L));
				assertThat(sut.use_count,   is(0L));
			} catch (IllegalArgumentException e) {
				fail(e);
			}
        }
	}
	
	public static class 使用回数が上限に達していない場合 extends TestCaseTemplate{
		
		protected PacentageCountSpecify sut ;
		
		@Before
		public void init() {
			sut = new PacentageCountSpecify(100L, "a", 1);
		}
		
		@Test
		public void getValue() {
			assertThat(sut.use_count,  is(0L));
			assertThat(sut.getValue(), is("a"));
			assertThat(sut.use_count,  is(1L));
		}
	}
	
	public static class 使用回数が上限に達している場合 extends TestCaseTemplate{
		
		protected PacentageCountSpecify sut ;
		
		@Before
		public void init() {
			sut = new PacentageCountSpecify(100L, "a", 1);
			assertThat(sut.getValue(), is("a"));
		}
		
		@Test
		public void getValue() {
			assertThat(sut.use_count,  is(1L));
			assertThat(sut.getValue(), nullValue());
			assertThat(sut.use_count,  is(2L));
		}
	}
}
