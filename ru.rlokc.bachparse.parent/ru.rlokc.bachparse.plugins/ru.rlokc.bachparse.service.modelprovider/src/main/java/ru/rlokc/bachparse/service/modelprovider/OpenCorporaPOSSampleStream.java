package ru.rlokc.bachparse.service.modelprovider;

import java.io.File;

import opennlp.tools.postag.POSSample;
import opennlp.tools.util.ObjectStream;

public class OpenCorporaPOSSampleStream extends XmlOpener implements ObjectStream<POSSample> {
	
	public OpenCorporaPOSSampleStream(String filePath) {
		super(filePath);
	}
	
	public OpenCorporaPOSSampleStream(File file) {
		super(file);
	}
	
	public POSSample read() {
		return null;
	}
}
