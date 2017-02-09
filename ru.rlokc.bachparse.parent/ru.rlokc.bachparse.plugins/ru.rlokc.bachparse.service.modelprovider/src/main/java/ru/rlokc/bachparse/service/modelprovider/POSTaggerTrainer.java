package ru.rlokc.bachparse.service.modelprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerFactory;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.postag.WordTagSampleStream;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.util.model.BaseModel;

public class POSTaggerTrainer {
	
	public static BaseModel train(File trainFile) {
		POSModel model = null;
		try {
			MarkableFileInputStreamFactory dataIn = new MarkableFileInputStreamFactory(trainFile);
			ObjectStream<String> lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
			ObjectStream<POSSample> sampleStream = new WordTagSampleStream(lineStream);
			POSTaggerFactory factory = new POSTaggerFactory(null, null);			
			model = POSTaggerME.train("en", sampleStream, TrainingParameters.defaultParams(), factory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
}
