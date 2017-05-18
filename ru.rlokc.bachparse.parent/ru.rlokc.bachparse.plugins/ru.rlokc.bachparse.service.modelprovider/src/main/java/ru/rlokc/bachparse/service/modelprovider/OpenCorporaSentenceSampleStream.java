package ru.rlokc.bachparse.service.modelprovider;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import opennlp.tools.sentdetect.SentenceSample;
import opennlp.tools.sentdetect.SentenceSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.Span;

public class OpenCorporaSentenceSampleStream implements ObjectStream<SentenceSample>{

	private XMLStreamReader streamReader;

	public OpenCorporaSentenceSampleStream(String filePath) {
		this(new File(filePath));
	}
	
	public OpenCorporaSentenceSampleStream(File file) {
		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		try {
			streamReader = xmlif.createXMLStreamReader(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SentenceSample read() {

	    StringBuilder sentencesString = new StringBuilder();
	    List<Span> sentenceSpans = new LinkedList<>();

		int begin = 0;
		int end = 0;
		boolean isParsingSentence = false;
		try {
			while (streamReader.hasNext()) {
				streamReader.next();			
				
				int eventType = streamReader.getEventType();
				switch (eventType) {
					case XMLStreamReader.START_ELEMENT:
						String type = streamReader.getLocalName();
						if (type == "source") {
							isParsingSentence = true;
							begin = sentencesString.length();
						}
						break;
					case XMLStreamReader.CHARACTERS:
						if (isParsingSentence) {
							String sentence = streamReader.getText();
							sentence = sentence.trim();
							sentencesString.append(sentence);
						}
						break;
					case XMLStreamReader.END_ELEMENT:
						if (isParsingSentence) {
							isParsingSentence = false;
							end = sentencesString.length();
							sentenceSpans.add(new Span(begin, end));
							sentencesString.append(' ');
						}
						break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	    if (sentenceSpans.size() > 0) {
	    	return new SentenceSample(sentencesString.toString(),
	          sentenceSpans.toArray(new Span[sentenceSpans.size()]));
	    }
	    
	    return null;
	}
}
