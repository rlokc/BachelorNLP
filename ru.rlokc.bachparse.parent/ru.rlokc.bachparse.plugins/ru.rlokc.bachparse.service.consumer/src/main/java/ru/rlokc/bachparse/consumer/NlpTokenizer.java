package ru.rlokc.bachparse.consumer;

import java.util.Arrays;
import java.util.List;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class NlpTokenizer {
	TokenizerModel model;
	TokenizerME parser;
	
	public NlpTokenizer(TokenizerModel model) {
		this.model = model;
		this.parser = new TokenizerME(model);
	}
	
	public List<String> parseString(String s) {
		String tokens[] = parser.tokenize(s);
		List<String> arr = Arrays.asList(tokens);
		return arr;
	}
}
