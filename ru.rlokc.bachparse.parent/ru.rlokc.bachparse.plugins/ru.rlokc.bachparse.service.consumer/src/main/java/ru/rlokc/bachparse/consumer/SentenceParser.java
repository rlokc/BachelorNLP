package ru.rlokc.bachparse.consumer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Arrays;


import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class SentenceParser {
	
	SentenceModel model;
	SentenceDetectorME parser;
	
	public SentenceParser(SentenceModel model) {
		this.model = model;
		this.parser = new SentenceDetectorME(model);
	}
	
	public List<String> parseFile(File f){
		return parseString(this.buildStringFromFile(f));
	}
	
	public List<String> parseString(String s){		
		String sentences[] = parser.sentDetect(s);
		List<String> arr = Arrays.asList(sentences);
		return arr;
	}
	
	public String buildStringFromFile(File f){
		String res = "";
		try {
			InputStream is = new FileInputStream(f);
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));		
			String line = buf.readLine();
			StringBuilder sb = new StringBuilder();			
			while (line != null){
				sb.append(line).append(" ");
				line = buf.readLine();
			}
			res = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public void writeToFile(String filePath, List<String> toWrite){
		BufferedWriter outputWriter = null;
		try {
			outputWriter = new BufferedWriter(new FileWriter(filePath));
			for (String s : toWrite) {
				outputWriter.write(s);
				outputWriter.newLine();
			}
			System.out.println("Written the result into " + filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printParseResult(List<String> res) {
		for (String s : res) {
			System.out.println(s);
		}
	}
}
