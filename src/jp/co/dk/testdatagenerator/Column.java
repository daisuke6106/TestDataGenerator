package jp.co.dk.testdatagenerator;

import java.util.List;

public class Column {
	
	/** インデックス番号 */
	protected int index; 
	
	/** 生成対象データの種類一覧 */
	protected List<CountSpecify> datas;
	
	Column(int index, List<CountSpecify> datas) throws IllegalArgumentException {
		if (index < 0 || datas == null || datas.size() == 0) throw new IllegalArgumentException("can't create Column instance.");
		this.index = index;
		this.datas = datas;
	}
}
