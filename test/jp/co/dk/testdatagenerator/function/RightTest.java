package jp.co.dk.testdatagenerator.function;

import java.util.ArrayList;
import java.util.List;

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
				new Right((List<Value>)null);
				fail();
			} catch(IllegalArgumentException e) {
				assertThat(e.getMessage(), is("RIGHTの引数が不正です。"));
			}
		}
		
		@Test
		public void 引数が空の配列の場合() {
			try {
				new Right(new ArrayList<Value>());
				fail();
			} catch(IllegalArgumentException e) {
				assertThat(e.getMessage(), is("RIGHTの引数が不正です。"));
			}
		}
		
		@Test
		public void 引数の個数が不正な場合() {
			try {
				List<Value> values = new ArrayList<Value>();
				values.add(new Value("aaa"));
				new Right(values);
				fail();
			} catch(IllegalArgumentException e) {
				assertThat(e.getMessage(), is("RIGHTの引数が不正です。"));
			}
			
			try {
				List<Value> values = new ArrayList<Value>();
				values.add(new Value("aaa"));
				values.add(new Value("bbb"));
				values.add(new Value("ccc"));
				new Right(values);
				fail();
			} catch(IllegalArgumentException e) {
				assertThat(e.getMessage(), is("RIGHTの引数が不正です。"));
			}
		}
		

		@Test
		public void 第一引数がNULLの場合() {
			try {
				List<Value> values = new ArrayList<Value>();
				values.add(null);
				values.add(new Value("123"));
				new Right(values);
				fail();
			} catch(IllegalArgumentException e) {
				assertThat(e.getMessage(), is("RIGHTの引数が不正です。"));
			}
		}
		

		@Test
		public void 第二引数がNULLの場合() {
			try {
				List<Value> values = new ArrayList<Value>();
				values.add(new Value("aaa"));
				values.add(null);
				new Right(values);
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
			List<Value> values = new ArrayList<Value>();
			values.add(new Value("ABCDE"));
			values.add(new Value("1"));
			this.sut = new Right(values);
			assertThat(this.sut.value.toString(), is("ABCDE"));
			assertThat(this.sut.count.toString(), is("1"));
		}
		
		@Test
		public void getName() {
			assertThat(this.sut.getName(), is("RIGHT"));
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
			List<Value> values = new ArrayList<Value>();
			values.add(new Value("ABCDE"));
			values.add(new Value("10"));
			this.sut = new Right(values);
			assertThat(this.sut.value.toString(), is("ABCDE"));
			assertThat(this.sut.count.toString(), is("10"));
		}
		
		@Test
		public void getName() {
			assertThat(this.sut.getName(), is("RIGHT"));
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
			List<Value> values = new ArrayList<Value>();
			values.add(new Value("ABCDE"));
			values.add(new Value("5"));
			this.sut = new Right(values);
			assertThat(this.sut.value.toString(), is("ABCDE"));
			assertThat(this.sut.count.toString(), is("5"));
		}
		
		@Test
		public void getName() {
			assertThat(this.sut.getName(), is("RIGHT"));
		}
		
		@Test
		public void getValue() {
			assertThat(this.sut.getValue(100L), is("ABCDE"));
		}
	}
	
	public static class インスタンスが生成できた場合且つ切り出す文字数に負値が設定されていた場合 extends TestCaseTemplate{
		
		protected Right sut;
		
		@Before
		public void init() {
			List<Value> values = new ArrayList<Value>();
			values.add(new Value("ABCDE"));
			values.add(new Value("0"));
			this.sut = new Right(values);
			assertThat(this.sut.value.toString(), is("ABCDE"));
			assertThat(this.sut.count.toString(), is("0"));
		}
		
		@Test
		public void getName() {
			assertThat(this.sut.getName(), is("RIGHT"));
		}
		
		@Test
		public void getValue() {
			try {
				this.sut.getValue(100L);
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("文字数指定に0以下が設定されています。:0"));
			}
		}
	}

	public static class インスタンスが生成できた場合且つ切り出す文字数に数値以外が設定されていた場合 extends TestCaseTemplate{
		
		protected Right sut;
		
		@Before
		public void init() {
			List<Value> values = new ArrayList<Value>();
			values.add(new Value("ABCDE"));
			values.add(new Value("aaa"));
			this.sut = new Right(values);
			assertThat(this.sut.value.toString(), is("ABCDE"));
			assertThat(this.sut.count.toString(), is("aaa"));
		}
		
		@Test
		public void getName() {
			assertThat(this.sut.getName(), is("RIGHT"));
		}
		
		@Test
		public void getValue() {
			try {
				this.sut.getValue(100L);
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("文字数指定に数値以外の文字列が設定されています。:aaa"));
			}
		}
	}
}
