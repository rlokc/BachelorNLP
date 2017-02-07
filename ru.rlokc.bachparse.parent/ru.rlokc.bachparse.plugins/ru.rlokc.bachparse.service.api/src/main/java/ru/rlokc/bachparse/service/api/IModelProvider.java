package ru.rlokc.bachparse.service.api;

import opennlp.tools.util.model.BaseModel;

public interface IModelProvider {
	public BaseModel getModel(String modelType, String elementType, boolean forceRetrain);
}
