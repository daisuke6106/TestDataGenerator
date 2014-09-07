package jp.co.dk.testdatagenerator.countspecify;

class AbsoluteCountSpecify extends CountSpecify{
	
	/** 件数指定数値 */
	protected long origin_count;
	
	/** 使用回数 */
	protected long use_count = 0L;
	
	AbsoluteCountSpecify(long outputCount, String value, long count) throws IllegalArgumentException {
		super(outputCount, value);
		if (count < 1) throw new IllegalArgumentException("件数指定に0以下が設定されています。:" + value);
		if (count > outputCount) throw new IllegalArgumentException("件数指定に出力件数以上の件数が設定されています。:" + value); 
		this.origin_count = count;
	}
	
	@Override
	public String getValue(long nowIndex) {
		++use_count;
		if (this.use_count>this.origin_count) return null;
		return super.getValue(nowIndex);
	}

	@Override
	public String getName() {
		return "件数指定";
	}

	@Override
	public String getManualMessage(String linesep) {
		StringBuilder sb = new StringBuilder();
		sb.append("指定の件数だけ指定の値を出力する場合、値=件数の形で指定してください。").append(linesep);
		return sb.toString();
	}

	@Override
	public String getExample(String linesep) {
		StringBuilder sb = new StringBuilder();
		sb.append("aaa=10").append(linesep);
		sb.append("\"bbb\"=10").append(linesep);
		sb.append("aaa=10;bbb=10").append(linesep);
		return sb.toString();
	}
	
}

