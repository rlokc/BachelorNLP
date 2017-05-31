package ru.rlokc.bachparse.consumer;

import java.util.Arrays;
import java.util.List;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

public class NlpNameFinder {
	TokenNameFinderModel model;
	NameFinderME parser;
	
	public NlpNameFinder(TokenNameFinderModel model) {
		this.model = model;
		this.parser = new NameFinderME(model);
	}
	
	public List<Span> parseString(List<String> s) {
		Span names[] = parser.find((String[]) s.toArray());
		return Arrays.asList(names);
	}
	
}
