package jp.co.dk.testdatagenerator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * TestDataGeneratorは、テストに使用するためのデータを作成するツールです。<p/>
 * 生成する際の件数、文字コード、改行コード、作成先のディレクトリとファイル名、データ形式は指定のフォーマットを使用することで指定します。<br/>
 * 
 * @version 1.0
 * @author D.Kanno
 */
public class TestDataGenerator {
	
	
	public static void main(final String[] args) {
		// 生成数
		long count = 0L;
		// フォーマット
		Record format = null;
		// 出力ファイル名
		File outputFile = null;
		// 文字コード
		String encode = null;
		
		try {
			switch (args.length) {
				case 3:
					count      = Long.parseLong(args[0]);
					format     = new Record(count, args[1]);
					outputFile = new File(args[2]);
					encode     = "UTF-8";
					break;
				case 4:
					count      = Long.parseLong(args[0]);
					format     = new Record(count, args[1]);
					outputFile = new File(args[2]);
					encode     = args[3];
					break;
				default:
					System.out.println("usage : tdgen 件数 フォーマット 出力ファイル [文字コード]");
					System.exit(1);
			}
		} catch (NumberFormatException e) {
			System.out.println("出力件数に不正な値が設定されています。:" + args[0]);
			System.exit(1);
		} catch (IllegalAccessException e) {
			System.out.println("フォーマットの解析に失敗しました。:" + e.getMessage());
			System.exit(1);
		}
		
		try {
			if (!outputFile.exists() && !outputFile.createNewFile()) {
				System.out.println("ファイル生成に失敗しました。:" + outputFile.toString());
				System.exit(1);
			}
		} catch (IOException e) {
			System.out.println("ファイル生成に失敗しました。:" + outputFile.toString());
			System.exit(1);
		}
		
		try {
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));			
			for (long i=0; i<count; i++) {
				String value = format.getValue(i);
				outputStream.write(value.getBytes(encode));
				if (i==1000) outputStream.flush();
			}
			outputStream.flush();
			outputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("ファイル出力処理に失敗しました。。:" + e.getMessage());
			System.exit(1);
		} catch (UnsupportedEncodingException e) {
			System.out.println("ファイル出力処理に失敗しました。。:" + e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			System.out.println("ファイル出力処理に失敗しました。。:" + e.getMessage());
			System.exit(1);
		}
	}

}
