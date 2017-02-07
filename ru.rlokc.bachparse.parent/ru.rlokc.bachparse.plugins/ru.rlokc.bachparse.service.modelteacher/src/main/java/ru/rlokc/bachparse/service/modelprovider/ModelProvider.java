package ru.rlokc.bachparse.service.modelprovider;

import opennlp.tools.util.model.BaseModel;
import ru.rlokc.bachparse.service.api.IModelProvider;

public class ModelProvider implements IModelProvider{
	
	public BaseModel getModel(String modelType, String elementType, boolean forceRetrain){
		BaseModel model = ModelLoader.loadModel(modelType, elementType);
		if (model == null || forceRetrain) model = ModelTrainer.trainModel(modelType, elementType);
		return model;
	}
}
