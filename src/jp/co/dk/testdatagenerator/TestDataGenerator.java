package jp.co.dk.testdatagenerator;

import java.math.BigDecimal;

/**
 * TestDataGeneratorは、テストに使用するためのデータを作成するツールです。<p/>
 * 生成する際の件数、文字コード、改行コード、作成先のディレクトリとファイル名、データ形式は指定のフォーマットを使用することで指定します。<br/>
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class TestDataGenerator {
	
	
	public static void main(final String[] args) {
		long a = 5; //%
		long b = 100; //count
		BigDecimal c = new BigDecimal(a).multiply(new BigDecimal("0.01")).multiply(new BigDecimal(b));
		System.out.print(c.longValue());
	}

}
