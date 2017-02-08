package ru.rlokc.bachparse.service.modelprovider;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import ru.rlokc.bachparse.service.api.IModelProvider;

public class ServiceActivator implements BundleActivator {

	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		IModelProvider provider = new ModelProvider();
		context.registerService(IModelProvider.class.getName(), provider, null);
		System.out.println("Model provider registered");
	}

	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
