package jp.co.dk.testdatagenerator.countspecify;

import java.util.regex.Pattern;

public interface CountSpecifyFactory {
	
	CountSpecify createCountSpecify(long outputCount, Pattern pattern, String format);
	
}