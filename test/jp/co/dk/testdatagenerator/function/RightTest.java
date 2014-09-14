package jp.co.dk.testdatagenerator.function;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class RightTest {

	public static class コンストラクタ extends TestCaseTemplate{
		@Test
		public void 引数がNULLの場合() {
			try {
				new Right(null);
				fail();
			} catch(IllegalArgumentException e) {
				assertThat(e.getMessage(), is("RIGHTの引数が不正です。"));
			}
		}
		
		@Test
		public void 引数が空の配列の場合() {
			try {
				new Right(new String[0]);
				fail();
			} catch(IllegalArgumentException e) {
				assertThat(e.getMessage(), is("RIGHTの引数が不正です。"));
			}
		}
		
		@Test
		public void 引数の個数が不正な場合() {
			try {
				new Right("aaa");
				fail();
			} catch(IllegalArgumentException e) {
				assertThat(e.getMessage(), is("RIGHTの引数が不正です。"));
			}
			
			try {
				new Right("aaa", "bbb", "ccc");
				fail();
			} catch(IllegalArgumentException e) {
				assertThat(e.getMessage(), is("RIGHTの引数が不正です。"));
			}
		}
		

		@Test
		public void 第一引数がNULLの場合() {
			try {
				new Right(null, "123");
				fail();
			} catch(IllegalArgumentException e) {
				assertThat(e.getMessage(), is("RIGHTの引数が不正です。"));
			}
		}
		

		@Test
		public void 第二引数がNULLの場合() {
			try {
				new Right("aaa", null);
				fail();
			} catch(IllegalArgumentException e) {
				assertThat(e.getMessage(), is("RIGHTの引数が不正です。"));
			}
		}
		
		@Test
		public void 第二引数が数値でない場合() {
			try {
				new Right("aaa", "bbb");
				fail();
			} catch(IllegalArgumentException e) {
				assertThat(e.getMessage(), is("RIGHTの引数が不正です。"));
			}
		}
		
		@Test
		public void 第二引数が負値の場合() {
			try {
				new Right("aaa", "0");
				fail();
			} catch(IllegalArgumentException e) {
				assertThat(e.getMessage(), is("RIGHTの引数が不正です。"));
			}
		}
	}
	
	public static class インスタンスが生成できた場合且つ切り出す文字数が指定文字の文字数以下の場合 extends TestCaseTemplate{
		
		protected Right sut;
		
		@Before
		public void init() {
			this.sut = new Right("ABCDE","1");
			assertThat(this.sut.value.toString(), is("ABCDE"));
			assertThat(this.sut.count           , is(1));
		}
		
		@Test
		public void getName() {
			assertThat(this.sut.getName(), is("RIGHT"));
		}
		
		@Test
		public void getManualMessage() {
			assertThat(this.sut.getManualMessage(this.getLineSeparator()), is("指定の文字を右から指定の文字数だけ切り取ります。"));
		}
		
		@Test
		public void getExample() {
			assertThat(this.sut.getExample(this.getLineSeparator()), is("RIGHT(ABCDE,3)"));
		}
		
		@Test
		public void getValue() {
			assertThat(this.sut.getValue(100L), is("E"));
		}
	}
	
	public static class インスタンスが生成できた場合且つ切り出す文字数が指定文字の文字数以上の場合 extends TestCaseTemplate{
		
		protected Right sut;
		
		@Before
		public void init() {
			this.sut = new Right("ABCDE","10");
			assertThat(this.sut.value.toString(), is("ABCDE"));
			assertThat(this.sut.count           , is(10));
		}
		
		@Test
		public void getName() {
			assertThat(this.sut.getName(), is("RIGHT"));
		}
		
		@Test
		public void getManualMessage() {
			assertThat(this.sut.getManualMessage(this.getLineSeparator()), is("指定の文字を右から指定の文字数だけ切り取ります。"));
		}
		
		@Test
		public void getExample() {
			assertThat(this.sut.getExample(this.getLineSeparator()), is("RIGHT(ABCDE,3)"));
		}
		
		@Test
		public void getValue() {
			assertThat(this.sut.getValue(100L), is("ABCDE"));
		}
	}
	
	public static class インスタンスが生成できた場合且つ切り出す文字数が指定文字の文字数と一致の場合 extends TestCaseTemplate{
		
		protected Right sut;
		
		@Before
		public void init() {
			this.sut = new Right("ABCDE","5");
			assertThat(this.sut.value.toString(), is("ABCDE"));
			assertThat(this.sut.count           , is(5));
		}
		
		@Test
		public void getName() {
			assertThat(this.sut.getName(), is("RIGHT"));
		}
		
		@Test
		public void getManualMessage() {
			assertThat(this.sut.getManualMessage(this.getLineSeparator()), is("指定の文字を右から指定の文字数だけ切り取ります。"));
		}
		
		@Test
		public void getExample() {
			assertThat(this.sut.getExample(this.getLineSeparator()), is("RIGHT(ABCDE,3)"));
		}
		
		@Test
		public void getValue() {
			assertThat(this.sut.getValue(100L), is("ABCDE"));
		}
	}
}
