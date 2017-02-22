package ru.rlokc.bachparse.consumer;

import java.util.List;
import java.io.File;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import opennlp.tools.sentdetect.SentenceModel;


import ru.rlokc.bachparse.service.api.IModelProvider;

public class ConsumerActivator implements BundleActivator{
	
	private BundleContext context;
	private IModelProvider provider;
	
	public void start(BundleContext context) {
		this.context = context;
		ServiceReference<?> reference = context.getServiceReference(IModelProvider.class.getName());
		provider = (IModelProvider) context.getService(reference);
		System.out.println("Consumer launched, got a provider");
		
		SentenceModel model = (SentenceModel) provider.getModel("opennlp.tools.sentdetect.SentenceModel", "SentenceDetectorME", false);
		SentenceParser parser = new SentenceParser(model);
		
		ClassLoader classLoader = getClass().getClassLoader();
		//TODO: Load from resources maybe?
		File testFile = new File("/Users/rlokc/Programming/Bachelor/git/BachelorNLP/ru.rlokc.bachparse.parent/ru.rlokc.bachparse.plugins/ru.rlokc.bachparse.service.consumer/src/main/resources/test.txt");
		String parsedFile = "/Users/rlokc/Programming/Bachelor/git/BachelorNLP/ru.rlokc.bachparse.parent/ru.rlokc.bachparse.plugins/ru.rlokc.bachparse.service.consumer/src/main/resources/res.txt";
		
		List<String> parseResult = parser.parseFile(testFile);
		parser.writeToFile(parsedFile, parseResult);
	}
	
	public void stop(BundleContext context) {
		System.out.println("Consumer stopped");
	}
}
