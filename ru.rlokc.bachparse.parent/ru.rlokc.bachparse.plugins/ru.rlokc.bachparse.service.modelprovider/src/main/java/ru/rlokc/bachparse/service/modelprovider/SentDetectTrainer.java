package ru.rlokc.bachparse.service.modelprovider;

import java.io.File;
import java.nio.charset.Charset;

import opennlp.tools.sentdetect.SentenceDetectorFactory;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceSample;
import opennlp.tools.sentdetect.SentenceSampleStream;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.util.model.BaseModel;

public class SentDetectTrainer {
	
	public static BaseModel train(File trainFile) {
		SentenceModel model = null;
		try {
			Charset charset = Charset.forName("UTF-8");
			ObjectStream<SentenceSample> sampleStream = new OpenCorporaSentenceSampleStream(trainFile);
			SentenceDetectorFactory factory = new SentenceDetectorFactory("ru", true, null, null);
			model = SentenceDetectorME.train("en", sampleStream, factory, TrainingParameters.defaultParams());
			sampleStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
}
