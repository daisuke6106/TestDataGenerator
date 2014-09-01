package jp.co.dk.testdatagenerator.countspecify;

class NothingCountSpecify extends CountSpecify{
	
	NothingCountSpecify(long outputCount, String value) throws IllegalArgumentException {
		super(outputCount, value);
	}
	
	@Override
	public String getName() {
		return "絶対値指定";
	}

	@Override
	public String getManualMessage(String linesep) {
		StringBuilder sb = new StringBuilder();
		sb.append("値を設定する場合、値のみ設定してください。").append(linesep);
		return sb.toString();
	}

	@Override
	public String getExample(String linesep) {
		StringBuilder sb = new StringBuilder();
		sb.append("aaa").append(linesep);
		sb.append("\"bbb\"").append(linesep);
		return sb.toString();
	}
}