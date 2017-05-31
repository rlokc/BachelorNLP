package ru.rlokc.bachparse.consumer;

import java.util.Arrays;
import java.util.List;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

public class NlpPOSTagger {
	POSModel model;
	POSTaggerME parser;
	
	public NlpPOSTagger(POSModel model) {
		this.model = model;
		this.parser = new POSTaggerME(model);
	}
	
	public List<String> parseString(List<String> s) {
		String parts[] = parser.tag((String[]) s.toArray());
		return Arrays.asList(parts);
	}
}
