package ru.rlokc.bachparse.consumer;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import opennlp.tools.postag.POSModel;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.model.BaseModel;
import ru.rlokc.bachparse.service.api.Event;
import ru.rlokc.bachparse.service.api.IModelProvider;
import ru.rlokc.bachparse.service.api.INlpService;
import ru.rlokc.bachparse.service.api.INlpServiceConsumer;
import ru.rlokc.bachparse.service.api.TaskType;


public class NlpConsumerImpl implements INlpServiceConsumer{
	
	protected ArrayList<INlpService> serviceList = new ArrayList<INlpService>();
	
	public void addService(INlpService serv){
		serviceList.add(serv);
	}
	
	public void removeService(INlpService serv){
		serviceList.remove(serv);
	}
	
	public void onEventAdded(Event e){
		System.out.println("Consumer: Event added");
		BundleContext context = FrameworkUtil.getBundle(NlpConsumerImpl.class).getBundleContext();
		ServiceReference<?> reference = context.getServiceReference(IModelProvider.class.getName());
		IModelProvider provider = (IModelProvider) context.getService(reference);
		
		BaseModel model;
		List<?> result;
		
		switch (e.task) {
			case SENTENCE:
				model = provider.getModel("opennlp.tools.sentdetect.SentenceModel", "SentenceDetectorME", false);
				NlpSentenceDetector sentdetector = new NlpSentenceDetector((SentenceModel)  model);
				result = sentdetector.parseString(e.text);
				break;
			case TOKEN:
				result = tokenize(provider, e.text);
				break;
			case POS:
				model = provider.getModel("opennlp.tools.sentdetect.POSModel", "POSTaggerME", true);
				NlpPOSTagger postagger = new NlpPOSTagger((POSModel) model);
				result = postagger.parseString(tokenize(provider, e.text));
				break;
			case NAME:
				model = provider.getModel("opennlp.tools.sentdetect.TokenNameFinderModel", "NameFinderME", true);
				NlpNameFinder namefinder = new NlpNameFinder( (TokenNameFinderModel) model);
				result = namefinder.parseString(tokenize(provider, e.text));
				break;

		}
	}
	
	/*
	 * Since both PartOfSpeech and NameFinder require a string to be tokenized first
	 * it is moved to a separate method
	 */
	private List<String> tokenize(IModelProvider provider, String s) {
		BaseModel model = provider.getModel("opennlp.tools.tokenize.TokenizerModel", "TokenizerME", false);
		NlpTokenizer tokenizer = new NlpTokenizer( (TokenizerModel) model);
		return tokenizer.parseString(s);
	}
	
	public void onEventRemoved(Event e){
		System.out.println("Consumer: Event deleted");
	}
	
}
