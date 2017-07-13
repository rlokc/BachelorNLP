package ru.rlokc.bachparse.service.modelprovider;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import opennlp.tools.util.model.BaseModel;


public class ModelTrainer {
	private static String modelDir = "/home/rlokc/Dropbox/Education/7sem/Bachelor/";
	private static String trainDir = "/home/rlokc/Dropbox/Education/7sem/Bachelor/training/";
	
	public static BaseModel trainModel(String modelType, String elementType){
		
		BaseModel model = null;
		
		String trainFilePath = trainDir + "SentenceDetectorME.train";
		File trainFile = new File(trainFilePath);
		
		String modelFilePath = modelDir + elementType + ".bin";
		File modelFile = new File(modelFilePath);

		//TODO: Maybe reimplement it into a lookup table
		if (elementType == "SentenceDetectorME")
			model = SentDetectTrainer.train(trainFile);
		else if (elementType == "TokenizerME")
			model = TokenizerTrainer.train(trainFile);
		else if (elementType == "POSTaggerME")
			model = POSTaggerTrainer.train(trainFile);
		else if (elementType == "NameFinderME")
			model = POSTaggerTrainer.train(trainFile);
		else
			System.out.println("No trainer for " + elementType); //TODO: Remake it into exception throwing
		
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
