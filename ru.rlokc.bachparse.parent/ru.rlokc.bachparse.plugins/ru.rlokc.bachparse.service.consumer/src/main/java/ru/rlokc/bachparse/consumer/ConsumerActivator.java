package ru.rlokc.bachparse.consumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import opennlp.tools.sentdetect.*;

import ru.rlokc.bachparse.service.api.IModelProvider;

public class ConsumerActivator implements BundleActivator{
	
	private BundleContext context;
	private IModelProvider provider;
	
	public void start(BundleContext context) {
		this.context = context;
		ServiceReference<?> reference = context.getServiceReference(IModelProvider.class.getName());
		provider = (IModelProvider) context.getService(reference);
		System.out.println("Consumer launched, got a provider");
		provider.getModel("SentenceModel", "SentenceDetectorME", false);
	}
	
	public void stop(BundleContext context) {
		System.out.println("Consumer stopped");
	}
}
