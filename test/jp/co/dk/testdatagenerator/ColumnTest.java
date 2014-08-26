package jp.co.dk.testdatagenerator;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;



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
	
	public static class ランダム設定なしー値の末尾にセミコロンなし extends TestCaseTemplate{
		 
        protected Column sut ;
        
        @Before
        public void init() {
        	try {
				this.sut = new Column(100L, 0, "aaa;bbb;ccc");
				assertThat(this.sut.outputCount, is(100L));
				assertThat(this.sut.index      , is(0));
				assertThat(this.sut.origin_datas      , is("aaa;bbb;ccc"));
				assertThat(this.sut.isRandom   , is(false));
				assertThat(this.sut.dataList.size(), is(3));
				assertThat(this.sut.dataList.get(0).getValue(), is("aaa"));
				assertThat(this.sut.dataList.get(1).getValue(), is("bbb"));
				assertThat(this.sut.dataList.get(2).getValue(), is("ccc"));
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
        	assertThat(this.sut.toString(), is("aaa;bbb;ccc"));
        }
    }
	
	public static class ランダム設定なしー値の末尾にセミコロンあり extends TestCaseTemplate{
		 
        protected Column sut ;
        
        @Before
        public void init() {
        	try {
				this.sut = new Column(100L, 0, "aaa;bbb;ccc;");
				assertThat(this.sut.outputCount, is(100L));
				assertThat(this.sut.index      , is(0));
				assertThat(this.sut.origin_datas      , is("aaa;bbb;ccc;"));
				assertThat(this.sut.isRandom   , is(false));
				assertThat(this.sut.dataList.size(), is(4));
				assertThat(this.sut.dataList.get(0).getValue(), is("aaa"));
				assertThat(this.sut.dataList.get(1).getValue(), is("bbb"));
				assertThat(this.sut.dataList.get(2).getValue(), is("ccc"));
				assertThat(this.sut.dataList.get(3).getValue(), is(""));
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
        	assertThat(this.sut.toString(), is("aaa;bbb;ccc;"));
        }
    }
	
	public static class ランダム設定ありー値の末尾にセミコロンなし extends TestCaseTemplate{
		 
        protected Column sut ;
        
        @Before
        public void init() {
        	try {
				this.sut = new Column(100L, 0, "@r[aaa;bbb;ccc]");
				assertThat(this.sut.outputCount, is(100L));
				assertThat(this.sut.index      , is(0));
				assertThat(this.sut.origin_datas      , is("@r[aaa;bbb;ccc]"));
				assertThat(this.sut.isRandom   , is(true));
				assertThat(this.sut.dataList.size(), is(3));
				assertThat(this.sut.dataList.get(0).getValue(), is("aaa"));
				assertThat(this.sut.dataList.get(1).getValue(), is("bbb"));
				assertThat(this.sut.dataList.get(2).getValue(), is("ccc"));
			} catch (IllegalArgumentException e) {
				fail(e);
			}
        }
        
        @Test
        public void getValue() {
    		assertThat(this.sut.getValue(0), anyOf(
    			is("aaa"),
    			is("bbb"),
    			is("ccc")
    		));
    		assertThat(this.sut.getValue(1), anyOf(
    			is("aaa"),
    			is("bbb"),
    			is("ccc")
        	));
    		assertThat(this.sut.getValue(2), anyOf(
				is("aaa"),
				is("bbb"),
				is("ccc")
	    	));
        		
        }
        
        @Test
        public void testToString() {
        	assertThat(this.sut.toString(), is("@r[aaa;bbb;ccc]"));
        }
    }
	
	public static class ランダム設定ありー値の末尾にセミコロンあり extends TestCaseTemplate{
		 
        protected Column sut ;
        
        @Before
        public void init() {
        	try {
				this.sut = new Column(100L, 0, "@r[aaa;bbb;ccc;]");
				assertThat(this.sut.outputCount, is(100L));
				assertThat(this.sut.index      , is(0));
				assertThat(this.sut.origin_datas      , is("@r[aaa;bbb;ccc;]"));
				assertThat(this.sut.isRandom   , is(true));
				assertThat(this.sut.dataList.size(), is(4));
				assertThat(this.sut.dataList.get(0).getValue(), is("aaa"));
				assertThat(this.sut.dataList.get(1).getValue(), is("bbb"));
				assertThat(this.sut.dataList.get(2).getValue(), is("ccc"));
				assertThat(this.sut.dataList.get(3).getValue(), is(""));
			} catch (IllegalArgumentException e) {
				fail(e);
			}
        }
        
        @Test
        public void getValue() {
    		assertThat(this.sut.getValue(0), anyOf(
    			is("aaa"),
    			is("bbb"),
    			is("ccc"),
    			is("")
    		));
    		assertThat(this.sut.getValue(1), anyOf(
    			is("aaa"),
    			is("bbb"),
    			is("ccc"),
    			is("")
        	));
    		assertThat(this.sut.getValue(2), anyOf(
				is("aaa"),
				is("bbb"),
				is("ccc"),
				is("")
	    	));
    		assertThat(this.sut.getValue(3), anyOf(
				is("aaa"),
				is("bbb"),
				is("ccc"),
				is("")
	    	));
        }
        
        @Test
        public void testToString() {
        	assertThat(this.sut.toString(), is("@r[aaa;bbb;ccc;]"));
        }
    }

}
