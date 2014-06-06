package com.ar.marketanalyzer.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.lang3.StringUtils;

public class WhitespaceToCSVReader extends BufferedReader{

	public WhitespaceToCSVReader(Reader in) {
		super(in);
	}

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
