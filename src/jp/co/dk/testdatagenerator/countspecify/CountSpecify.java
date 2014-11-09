package jp.co.dk.testdatagenerator.countspecify;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import jp.co.dk.testdatagenerator.function.FunctionValue;
import jp.co.dk.testdatagenerator.function.Value;

public abstract class CountSpecify {
	
	/** 出力件数 */
	protected long outputCount;
	
	/** 値 */
	protected Value value;
	
	CountSpecify(long outputCount, String value) throws IllegalArgumentException {
		if (outputCount < 0 || value == null) throw new IllegalArgumentException("can't create CountSpecifyType instance.");
		this.outputCount = outputCount;
		Deque<Character> formatQue = new LinkedList<Character>();
		char[] valueArray = value.toCharArray();
		for (int i=0; i<valueArray.length; i++) formatQue.offer(new Character(i, valueArray[i]));
		List<Value> values = this.createValues(formatQue, false);
		if (values.size() > 1) throw new IllegalArgumentException("関数は複数定義できません。");
		this.value = values.get(0);
	}
	
//	Values createValues(Deque<Character> formatQue) throws Exception {
//		StringBuilder sb = new StringBuilder();
//		List<Values> values = new ArrayList<Values>();
//		boolean isStart  = false;
//		boolean isFinish = false;
//		while(formatQue.peek()!=null) {
//			Character characterObj = formatQue.poll();
//			int  index = characterObj.getIndex();
//			char chara = characterObj.getCharacter();
//			switch (chara) {
//				case '(':
//					isStart = true;
//					values.add(createValues(formatQue));
//					break;
//				case ')':
//					isFinish = true;
//					if (isStart == false)formatQue.offerFirst(characterObj);
//					return new Values(sb.toString(), values);
//				case ';':
//					break;
//				default:
//					sb.append(chara);
//			}
//		}
//		throw new Exception("aaa");
//	}
	
	protected List<Value> createValues(Deque<Character> formatQue, boolean isNest) throws IllegalArgumentException {
		List<Value>   result           = new ArrayList<Value>();
		StringBuilder value            = new StringBuilder();
		boolean       isEscaped        = false;
		boolean       isStartFunction  = false;
		boolean       isFinishFunction = false;
		
		List<Value> childValues = new ArrayList<Value>();
		
		while(formatQue.peek() != null) {
			Character characterObj = formatQue.poll();
			int  index = characterObj.index;
			char chara = characterObj.getCharacter();
			switch (chara) {
				case '(': 
					isStartFunction = true;
					childValues = createValues(formatQue, true);
					break;
					
				case ')' :
					isFinishFunction = true;
					
					if (isNest) {
						if (value.length() == 0) {
							if (isStartFunction == false && isFinishFunction == true ) {
								formatQue.offerFirst(characterObj);
								result.add(new Value(""));
							}
							if (isStartFunction == true  && isFinishFunction == true ) {
								throw new IllegalArgumentException("関数名が設定されてません。");
							}
						} else {
							if (isStartFunction == false && isFinishFunction == true ) {
								formatQue.offerFirst(characterObj);
								result.add(new Value(value.toString()));
								
							}
							if (isStartFunction == true  && isFinishFunction == true ) {
								result.add(new FunctionValue(value.toString(), childValues));
							}
						}
					} else {
						if (value.length() == 0) {
							if (isStartFunction == false && isFinishFunction == true ) {
								formatQue.offerFirst(characterObj);
								result.add(new Value(""));
							}
							if (isStartFunction == true  && isFinishFunction == true ) {
								throw new IllegalArgumentException("関数名が設定されてません。");
							}
						} else {
							if (isStartFunction == false && isFinishFunction == true ) {
								//formatQue.offerFirst(characterObj);
								//result.add(new Value(value.toString()));
								throw new IllegalArgumentException("関数が正しく終了してません。");
							}
							if (isStartFunction == true  && isFinishFunction == true ) {
								result.add(new FunctionValue(value.toString(), childValues));
							}
						}
					}
					
					
					return result;
					
//				case ';':
//					if (value.length() == 0 && isStartFunction == false && isFinishFunction == false) result.add(new Value(""));
//					if (value.length() == 0 && isStartFunction == true  && isFinishFunction == false) throw new IllegalArgumentException("関数名が設定されてません。");
//					if (value.length() == 0 && isStartFunction == false && isFinishFunction == true ) throw new IllegalArgumentException("関数名が設定されてません。");
//					if (value.length() == 0 && isStartFunction == true  && isFinishFunction == true ) throw new IllegalArgumentException("関数名が設定されてません。");
//
//					if (value.length() != 0 && isStartFunction == false && isFinishFunction == false) result.add(new Value(value.toString()));
//					if (value.length() != 0 && isStartFunction == true  && isFinishFunction == false) result.add(childValues.get(0));
//					if (value.length() != 0 && isStartFunction == false && isFinishFunction == true ) throw new IllegalArgumentException("関数が開始されてません。");
//					if (value.length() != 0 && isStartFunction == true  && isFinishFunction == true ) result.add(new FunctionValue(value.toString(), childValues));
//					
//					isStartFunction  = false;
//					isFinishFunction = false;
//					value            = new StringBuilder();
//					childValues      = new ArrayList<Value>();
//					break;
				default:
					value.append(chara);
			}
		}
		
		if (value.length() == 0 && isStartFunction == false && isFinishFunction == false) result.add(new Value(""));
		if (value.length() == 0 && isStartFunction == true  && isFinishFunction == false) throw new IllegalArgumentException("関数名が設定されてません。");
		if (value.length() == 0 && isStartFunction == false && isFinishFunction == true ) throw new IllegalArgumentException("関数名が設定されてません。");
		if (value.length() == 0 && isStartFunction == true  && isFinishFunction == true ) throw new IllegalArgumentException("関数名が設定されてません。");
		
		if (value.length() != 0 && isStartFunction == false && isFinishFunction == false) result.add(new Value(value.toString()));
		if (value.length() != 0 && isStartFunction == true  && isFinishFunction == false) throw new IllegalArgumentException("関数がクローズされてません。");
		if (value.length() != 0 && isStartFunction == false && isFinishFunction == true ) throw new IllegalArgumentException("関数が開始されてません。");
		if (value.length() != 0 && isStartFunction == true  && isFinishFunction == true ) result.add(new FunctionValue(value.toString(), childValues));
		
		return result;
	}

	public String getValue(long nowIndex) {
		return this.value.getValue(nowIndex);
	}
	
	@Override
	public String toString() {
		return this.value.toString();
	}
	
}

class Character {
	
	protected int index;
	
	protected char character;
	
	public Character(int index, char character){
		this.index = index;
		this.character = character;
	}
	
	int getIndex() {
		return this.index;
	}
	
	char getCharacter() {
		return this.character;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.character);
	}
}
