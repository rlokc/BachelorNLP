package ru.rlokc.bachparse.service.modelprovider;

import java.io.File;

import opennlp.tools.util.model.BaseModel;


public class ModelTrainer {
	private static String modelDir;
	private static String trainDir;
	
	public static BaseModel trainModel(String modelType, String elementType){
		BaseModel model = null;
		
		String trainFilePath = trainDir + elementType + ".train";
		File trainFile = new File(trainFilePath);
		
		switch (elementType) {
			case "SentenceDetectorME":
				model = SentDetectTrainer.train(trainFile);
				break;
			case "TokenizerME":
				model = TokenizerTrainer.train(trainFile);
				break;
		}
		return model;
	}
	
}
