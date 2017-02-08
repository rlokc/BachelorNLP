package ru.rlokc.bachparse.service.modelprovider;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import opennlp.tools.util.model.BaseModel;


public class ModelTrainer {
	private static String modelDir = "/Users/rlokc/Dropbox/Education/7sem/Bachelor/models/";
	private static String trainDir = "/Users/rlokc/Dropbox/Education/7sem/Bachelor/training/";
	
	public static BaseModel trainModel(String modelType, String elementType){
		
		BaseModel model = null;
		
		String trainFilePath = trainDir + elementType + ".train";
		File trainFile = new File(trainFilePath);
		
		String modelFilePath = modelDir + elementType + ".bin";
		File modelFile = new File(modelFilePath);

		if (elementType == "SentenceDetectorME")
			model = SentDetectTrainer.train(trainFile);
		else if (elementType == "TokenizerME")
			model = TokenizerTrainer.train(trainFile);
		
		if (model != null) {
			OutputStream modelOut = null;
			try {
				modelOut = new BufferedOutputStream(new FileOutputStream(modelFile));
				model.serialize(modelOut);
				if (modelOut != null) 
					modelOut.close();
				System.out.println("Model saved");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return model;
	}
	
}
