package ru.rlokc.bachparse.service.modelprovider;

import java.io.File;
import java.lang.reflect.Constructor;

import opennlp.tools.util.model.BaseModel;
import opennlp.tools.sentdetect.SentenceModel;

public class ModelLoader {
	//TODO: find a way to store this thing
	private static String modelDir = "%папка_моделей%";
	
	public static BaseModel loadModel(String modelType, String elementType){
		String modelPath = modelDir + elementType + ".bin";
		File f = new File(modelPath);
		BaseModel model = null;
		if (f.exists()){
			try {
				Class<?> modelClass = Class.forName(modelType);//modelType);
				Constructor<?> cons = modelClass.getConstructor(File.class);
				model = (BaseModel) cons.newInstance(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return model;
	}
}
