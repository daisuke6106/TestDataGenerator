package jp.co.dk.testdatagenerator;

/**
 * TestDataGeneratorは、テストに使用するためのデータを作成するツールです。<p/>
 * 生成する際の件数、文字コード、改行コード、作成先のディレクトリとファイル名、データ形式は指定のフォーマットを使用することで指定します。<br/>
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class TestDataGenerator {
	
	
	public static void main(final String[] args) {
		System.out.println(args.length);
		System.out.println(args[0]);
		System.out.println(args[1]);
	}

}
