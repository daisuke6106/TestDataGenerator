package jp.co.dk.testdatagenerator.countspecify;


import jp.co.dk.test.template.TestCaseTemplate;
import jp.co.dk.testdatagenerator.countspecify.AbsoluteCountSpecify;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class AbsoluteCountSpecifyTest {

	public static class コンストラクタ extends TestCaseTemplate{
		
		@Test
        public void 引数の出力件数が不正な場合() {
        	try {
        		new AbsoluteCountSpecify(100L, "a", -1L);
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("件数指定に0以下が設定されています。:" + "a"));
			}
        	try {
        		new AbsoluteCountSpecify(100L, "a", 0L);
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("件数指定に0以下が設定されています。:" + "a"));
			}
        	try {
        		new AbsoluteCountSpecify(100L, "a", 101L);
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("件数指定に出力件数以上の件数が設定されています。:" + "a"));
			}
        }
		
		@Test
        public void 正常にインスタンスが生成できた場合() {
			for (long i=1; i<=100; i++) {
	        	try {
	        		AbsoluteCountSpecify sut = new AbsoluteCountSpecify(100L, "a", i);
					assertThat(sut.origin_count,  is(i));
					assertThat(sut.use_count,   is(0L));
				} catch (IllegalArgumentException e) {
					fail(e);
				}
			}
        }
	}
	
	public static class 使用回数が上限に達していない場合 extends TestCaseTemplate{
		
		protected AbsoluteCountSpecify sut ;
		
		@Before
		public void init() {
			sut = new AbsoluteCountSpecify(100L, "a", 1);
		}
		
		@Test
		public void getValue() {
			assertThat(sut.use_count,  is(0L));
			assertThat(sut.getValue(0L), is("a"));
			assertThat(sut.use_count,  is(1L));
		}
		
		@Test
		public void getName() {
			assertThat(this.sut.getName(), is("件数指定"));
		}
		
		@Test
		public void getManualMessage() {
			assertThat(
				this.sut.getManualMessage(this.getLineSeparator()), 
				is("指定の件数だけ指定の値を出力する場合、値=件数の形で指定してください。" + this.getLineSeparator())
			);
		}
		
		@Test
		public void getExample() {
			assertThat(
				this.sut.getExample(this.getLineSeparator()), 
				is(
					"aaa=10" + this.getLineSeparator() +
					"\"bbb\"=10" + this.getLineSeparator() +
					"aaa=10;bbb=10" + this.getLineSeparator()
				)
			);
		}
	}
	
	public static class 使用回数が上限に達している場合 extends TestCaseTemplate{
		
		protected AbsoluteCountSpecify sut ;
		
		@Before
		public void init() {
			sut = new AbsoluteCountSpecify(100L, "a", 1);
			assertThat(sut.getValue(0L), is("a"));
		}
		
		@Test
		public void getValue() {
			assertThat(sut.use_count,  is(1L));
			assertThat(sut.getValue(0L), nullValue());
			assertThat(sut.use_count,  is(2L));
		}
		
		@Test
		public void getName() {
			assertThat(this.sut.getName(), is("件数指定"));
		}
		
		@Test
		public void getManualMessage() {
			assertThat(
				this.sut.getManualMessage(this.getLineSeparator()), 
				is("指定の件数だけ指定の値を出力する場合、値=件数の形で指定してください。" + this.getLineSeparator())
			);
		}
		
		@Test
		public void getExample() {
			assertThat(
				this.sut.getExample(this.getLineSeparator()), 
				is(
					"aaa=10" + this.getLineSeparator() +
					"\"bbb\"=10" + this.getLineSeparator() +
					"aaa=10;bbb=10" + this.getLineSeparator()
				)
			);
		}
	}
}
