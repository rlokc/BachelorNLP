package ru.rlokc.bachparse.service.modelprovider;

import java.io.File;

import opennlp.tools.namefind.NameSample;
import opennlp.tools.util.ObjectStream;

public class OpenCorporaNameSampleStream extends XmlOpener implements ObjectStream<NameSample>{
	
	public OpenCorporaNameSampleStream(String filePath) {
		super(filePath);
	}
	
	public OpenCorporaNameSampleStream(File file) {
		super(file);
	}
	
	public NameSample read() {
		return null;
	}
}
