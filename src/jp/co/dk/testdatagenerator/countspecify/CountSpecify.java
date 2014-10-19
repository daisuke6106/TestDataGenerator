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
		for (char chara : value.toCharArray()) formatQue.offer(new Character(chara));
		List<Value> values = this.createValues(formatQue);
		if (values.size() > 1) throw new IllegalArgumentException("関数は複数定義できません。");
		this.value = values.get(0);
	}
	
	protected List<Value> createValues(Deque<Character> formatQue) throws IllegalArgumentException {
		List<Value>   result           = new ArrayList<Value>();
		StringBuilder value            = new StringBuilder();
		boolean       isEscaped        = false;
		boolean       isStartFunction  = false;
		boolean       isFinishFunction = false;
		
		List<Value> childValues = new ArrayList<Value>();
		
		while(formatQue.peek() != null) {
			char chara = formatQue.poll().charValue();
			if (chara == '(') {
				isStartFunction = true;
				childValues = createValues(formatQue);
				
			} else if (chara == ')') {
				isFinishFunction = true;
				
				if (isStartFunction == false)formatQue.offerFirst(new Character(')'));
				
				if (value.length() == 0 && isStartFunction == false && isFinishFunction == false) result.add(new Value(""));
				if (value.length() == 0 && isStartFunction == true  && isFinishFunction == false) throw new IllegalArgumentException("関数名が設定されてません。");
				if (value.length() == 0 && isStartFunction == false && isFinishFunction == true ) result.add(new Value(""));
				if (value.length() == 0 && isStartFunction == true  && isFinishFunction == true ) throw new IllegalArgumentException("関数名が設定されてません。");
				
				if (value.length() != 0 && isStartFunction == false && isFinishFunction == false) result.add(new Value(value.toString()));
				if (value.length() != 0 && isStartFunction == true  && isFinishFunction == false) throw new IllegalArgumentException("関数がクローズされてません。");
				if (value.length() != 0 && isStartFunction == false && isFinishFunction == true ) throw new IllegalArgumentException("関数が開始されてません。");
				if (value.length() != 0 && isStartFunction == true  && isFinishFunction == true ) result.add(new FunctionValue(value.toString(), childValues));
				
				return result;
				
			} else if (chara == ';'){
//				
//				if (value.length() == 0 && isStartFunction == false && isFinishFunction == false) result.add(new Value(""));
//				if (value.length() == 0 && isStartFunction == true  && isFinishFunction == false) throw new IllegalArgumentException("関数名が設定されてません。");
//				if (value.length() == 0 && isStartFunction == false && isFinishFunction == true ) throw new IllegalArgumentException("関数名が設定されてません。");
//				if (value.length() == 0 && isStartFunction == true  && isFinishFunction == true ) throw new IllegalArgumentException("関数名が設定されてません。");
//				
//				if (value.length() != 0 && isStartFunction == false && isFinishFunction == false) result.add(new Value(value.toString()));
//				if (value.length() != 0 && isStartFunction == true  && isFinishFunction == false) throw new IllegalArgumentException("関数がクローズされてません。");
//				if (value.length() != 0 && isStartFunction == false && isFinishFunction == true ) throw new IllegalArgumentException("関数が開始されてません。");
//				if (value.length() != 0 && isStartFunction == true  && isFinishFunction == true ) result.add(new FunctionValue(value.toString(), childValues));
//				
				isStartFunction  = false;
				isFinishFunction = false;
				
				value            = new StringBuilder();
				childValues      = new ArrayList<Value>();
				
				
			} else {
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

