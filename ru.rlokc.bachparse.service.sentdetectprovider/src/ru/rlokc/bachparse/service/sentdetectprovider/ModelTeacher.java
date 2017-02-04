package ru.rlokc.bachparse.service.sentdetectprovider;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceSample;
import opennlp.tools.sentdetect.SentenceSampleStream;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.sentdetect.SentenceDetectorME;

public class ModelTeacher {
	private String filename;
	private String modelFile = "model.bin";
	
	public ModelTeacher(){
		
	}
	
	public void train(String filename) throws Exception{
		this.filename = filename;
		File file = new File(filename);
		Charset charset = Charset.forName("UTF-8");
		ObjectStream<String> lineStream = 
				new PlainTextByLineStream(new MarkableFileInputStreamFactory(file), charset);
		ObjectStream<SentenceSample> sampleStream = new SentenceSampleStream(lineStream);
		
		SentenceModel model;
		
		try {
			model = SentenceDetectorME.train("en", sampleStream, true, null, TrainingParameters.defaultParams());
			
		} finally {
			sampleStream.close();
		}
		
		OutputStream modelOut = null;
		try {
			modelOut = new BufferedOutputStream(new FileOutputStream(modelFile));
			model.serialize(modelOut);
		} finally {
			if (modelOut != null) 
				modelOut.close();
		}
	}
}
