package ru.rlokc.bachparse.service.modelprovider;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import opennlp.tools.cmdline.TerminateToolException;
import opennlp.tools.cmdline.sentdetect.SentenceEvaluationErrorListener;
import opennlp.tools.sentdetect.SentenceDetectorEvaluationMonitor;
import opennlp.tools.sentdetect.SentenceDetectorEvaluator;
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
		trainFile = new File ("/home/rlokc/Dropbox/Education/7sem/Bachelor/annot.opcorpora.xml");
		File evalFile = new File ("/home/rlokc/Dropbox/Education/7sem/Bachelor/training/SentenceDetectorME.train");
		try {
			Charset charset = Charset.forName("UTF-8");
			ObjectStream<SentenceSample> sampleStream = new OpenCorporaSentenceSampleStream(trainFile);
			//Закомментированные строки для тестирования плейнтекста, раскомментированные над ними -- для опенкорпоры
//			ObjectStream<String> lineStream = new PlainTextByLineStream(new MarkableFileInputStreamFactory(trainFile), charset);
//			ObjectStream<SentenceSample> sampleStream = new SentenceSampleStream(lineStream);
			SentenceDetectorFactory factory = new SentenceDetectorFactory("ru", true, null, null);
			TrainingParameters param = TrainingParameters.defaultParams(); //n=10, cutoff=??
			//Either of: NAIVEBAYES, PERCEPTRON, MAXENT
			param.put(TrainingParameters.ALGORITHM_PARAM, "PERCEPTRON");
			model = SentenceDetectorME.train("en", sampleStream, factory, param);
			
			//Eval
			SentenceDetectorEvaluationMonitor errorListener = new SentenceEvaluationErrorListener();
		    SentenceDetectorEvaluator evaluator = new SentenceDetectorEvaluator(
		        new SentenceDetectorME(model), errorListener);

		    System.out.print("Evaluating ... ");
		    try {
		    	ObjectStream<String> lstream = new PlainTextByLineStream(new MarkableFileInputStreamFactory(evalFile), charset);
		    	ObjectStream<SentenceSample> stream = new SentenceSampleStream(lstream);
		    	//Строка внизу для плейнтекста, сверху -- для опенкорпоры
//		    	ObjectStream<SentenceSample> stream = new OpenCorporaSentenceSampleStream(evalFile);
		      evaluator.evaluate(stream);
			  System.err.println("done");
			  System.out.println();
			  System.out.println(evaluator.getFMeasure());
		    }    
		    catch (IOException e) {
		        throw new TerminateToolException(-1, "IO error while reading training data or indexing data: " +
		                e.getMessage(), e);
		          }
		} catch (Exception e) {
			e.printStackTrace();
		}    
		return model;
	}
}
