package ru.rlokc.bachparse.service.modelprovider;

import java.io.File;

import opennlp.tools.tokenize.TokenSample;
import opennlp.tools.util.ObjectStream;

public class OpenCorporaTokenSampleStream extends XmlOpener implements ObjectStream<TokenSample>{
	
	public OpenCorporaTokenSampleStream(String filePath) {
		super(filePath);
	}
	
	public OpenCorporaTokenSampleStream(File file) {
		super(file);
	}
	
	public TokenSample read() {
		return null;
	}
}
