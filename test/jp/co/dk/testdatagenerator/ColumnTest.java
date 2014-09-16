package jp.co.dk.testdatagenerator;

import java.util.ArrayList;
import java.util.List;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.core.AnyOf.*;


@RunWith(Enclosed.class)
public class ColumnTest{
	
	public static class コンストラクタ extends TestCaseTemplate{
		 
        protected Column sut ;
        
        @Test
        public void 出力件数が不正() {
        	try {
				Column sut = new Column(-1L, 0, "a");
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("can't create Column instance."));
			}
        }
        
        @Test
        public void インデックス番号が不正() {
    		try {
				Column sut = new Column(0, -1, "a");
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("can't create Column instance."));
			}
        }
        
        @Test
        public void カラムデータが不正() {
			try {
				Column sut = new Column(100, 0, null);
				fail();
			} catch (IllegalArgumentException e) {
				assertThat(e.getMessage(), is("can't create Column instance."));
			}
        }
        
    }
	
	public static class ランダム設定なしー値の末尾にパイプなし extends TestCaseTemplate{
		 
        protected Column sut ;
        
        @Before
        public void init() {
        	try {
				this.sut = new Column(100L, 0, "aaa|bbb|ccc");
				assertThat(this.sut.outputCount, is(100L));
				assertThat(this.sut.index      , is(0));
				assertThat(this.sut.origin_datas      , is("aaa|bbb|ccc"));
				assertThat(this.sut.isRandom   , is(false));
				assertThat(this.sut.dataList.size(), is(3));
				assertThat(this.sut.dataList.get(0).getValue(0L), is("aaa"));
				assertThat(this.sut.dataList.get(1).getValue(0L), is("bbb"));
				assertThat(this.sut.dataList.get(2).getValue(0L), is("ccc"));
			} catch (IllegalArgumentException e) {
				fail(e);
			}
        }
        
        @Test
        public void getValue() {
    		assertThat(this.sut.getValue(0), is("aaa"));
    		assertThat(this.sut.getValue(1), is("aaa"));
    		assertThat(this.sut.getValue(2), is("aaa"));
        }
        
        @Test
        public void testToString() {
        	assertThat(this.sut.toString(), is("aaa|bbb|ccc"));
        }
    }
	
	public static class ランダム設定なしー値の末尾にパイプあり extends TestCaseTemplate{
		 
        protected Column sut ;
        
        @Before
        public void init() {
        	try {
				this.sut = new Column(100L, 0, "aaa|bbb|ccc|");
				assertThat(this.sut.outputCount, is(100L));
				assertThat(this.sut.index      , is(0));
				assertThat(this.sut.origin_datas      , is("aaa|bbb|ccc|"));
				assertThat(this.sut.isRandom   , is(false));
				assertThat(this.sut.dataList.size(), is(4));
				assertThat(this.sut.dataList.get(0).getValue(0L), is("aaa"));
				assertThat(this.sut.dataList.get(1).getValue(0L), is("bbb"));
				assertThat(this.sut.dataList.get(2).getValue(0L), is("ccc"));
				assertThat(this.sut.dataList.get(3).getValue(0L), is(""));
			} catch (IllegalArgumentException e) {
				fail(e);
			}
        }
        
        @Test
        public void getValue() {
    		assertThat(this.sut.getValue(0), is("aaa"));
    		assertThat(this.sut.getValue(1), is("aaa"));
    		assertThat(this.sut.getValue(2), is("aaa"));
    		assertThat(this.sut.getValue(3), is("aaa"));
        }
        
        @Test
        public void testToString() {
        	assertThat(this.sut.toString(), is("aaa|bbb|ccc|"));
        }
    }
	
	public static class ランダム設定ありー値の末尾にパイプなし extends TestCaseTemplate{
		 
        protected Column sut ;
        
        @Before
        public void init() {
        	try {
				this.sut = new Column(100L, 0, "@r[aaa|bbb|ccc]");
				assertThat(this.sut.outputCount, is(100L));
				assertThat(this.sut.index      , is(0));
				assertThat(this.sut.origin_datas      , is("@r[aaa|bbb|ccc]"));
				assertThat(this.sut.isRandom   , is(true));
				assertThat(this.sut.dataList.size(), is(3));
				assertThat(this.sut.dataList.get(0).getValue(0L), is("aaa"));
				assertThat(this.sut.dataList.get(1).getValue(0L), is("bbb"));
				assertThat(this.sut.dataList.get(2).getValue(0L), is("ccc"));
			} catch (IllegalArgumentException e) {
				fail(e);
			}
        }
        
        @Test
        public void getValue() {
        	List<String> resultList = new ArrayList<String>();
        	resultList.add("aaa");
        	resultList.add("bbb");
        	resultList.add("ccc");
    		assertThat(resultList.contains(this.sut.getValue(0)), is(true));
    		assertThat(resultList.contains(this.sut.getValue(1)), is(true));
    		assertThat(resultList.contains(this.sut.getValue(2)), is(true));
        }
        
        @Test
        public void testToString() {
        	assertThat(this.sut.toString(), is("@r[aaa|bbb|ccc]"));
        }
    }
	
	public static class ランダム設定ありー値の末尾にパイプあり extends TestCaseTemplate{
		 
        protected Column sut ;
        
        @Before
        public void init() {
        	try {
				this.sut = new Column(100L, 0, "@r[aaa|bbb|ccc|]");
				assertThat(this.sut.outputCount, is(100L));
				assertThat(this.sut.index      , is(0));
				assertThat(this.sut.origin_datas      , is("@r[aaa|bbb|ccc|]"));
				assertThat(this.sut.isRandom   , is(true));
				assertThat(this.sut.dataList.size(), is(4));
				assertThat(this.sut.dataList.get(0).getValue(0L), is("aaa"));
				assertThat(this.sut.dataList.get(1).getValue(0L), is("bbb"));
				assertThat(this.sut.dataList.get(2).getValue(0L), is("ccc"));
				assertThat(this.sut.dataList.get(3).getValue(0L), is(""));
			} catch (IllegalArgumentException e) {
				fail(e);
			}
        }
        
        @Test
        public void getValue() {
        	List<String> resultList = new ArrayList<String>();
        	resultList.add("aaa");
        	resultList.add("bbb");
        	resultList.add("ccc");
        	resultList.add("");
        	
    		assertThat(resultList.contains(this.sut.getValue(0L)), is(true));
    		assertThat(resultList.contains(this.sut.getValue(1L)), is(true));
    		assertThat(resultList.contains(this.sut.getValue(2L)), is(true));
    		assertThat(resultList.contains(this.sut.getValue(3L)), is(true));
        }
        
        @Test
        public void testToString() {
        	assertThat(this.sut.toString(), is("@r[aaa|bbb|ccc|]"));
        }
    }

}
