package com.ar.marketanalyzer.core.helpers;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Custom BufferedReader to override the readline() method.
 * <p>
 * This is done to replace multiple whitespace in the line being read by a comma.
 * It ignores single spaces.
 * 
 * @author Allan
 *
 */
public class WhitespaceToCSVReader extends BufferedReader{

	public WhitespaceToCSVReader(Reader in) {
		super(in);
	}

	/**
	 * This replaces the standard readline() with a custom one. The customization replaces
	 * 2 or more whitespaces with a comma, in effect converting a whitespace delimited file
	 * to a comma delimited file
	 * 
	 * @see BufferedReader#readLine()
	 */
	@Override
	public String readLine() throws IOException {
		String lBuf = super.readLine();
		
		if(StringUtils.isNotEmpty(lBuf)) {
			//lBuf = lBuf.replaceAll("\\s+", ",");
			lBuf = lBuf.replaceAll("[ \t]{2,}", ",");
		}
		
		return lBuf;
	}
}
