package ru.rlokc.bachparse.service.modelprovider;

import java.io.File;
import java.nio.charset.Charset;

import opennlp.tools.tokenize.TokenSample;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.TokenSampleStream;
import opennlp.tools.tokenize.TokenizerFactory;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.util.model.BaseModel;

public class TokenizerTrainer {

	public static BaseModel train(File trainFile) {
		TokenizerModel model = null;
		try {
			Charset charset = Charset.forName("UTF-8");
			ObjectStream<String> lineStream = 
					new PlainTextByLineStream(new MarkableFileInputStreamFactory(trainFile), charset);
			ObjectStream<TokenSample> sampleStream = new TokenSampleStream(lineStream);
			TokenizerFactory factory = new TokenizerFactory("ru", null, true, null);
			model = TokenizerME.train(sampleStream, factory, TrainingParameters.defaultParams());
			sampleStream.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}	
}
