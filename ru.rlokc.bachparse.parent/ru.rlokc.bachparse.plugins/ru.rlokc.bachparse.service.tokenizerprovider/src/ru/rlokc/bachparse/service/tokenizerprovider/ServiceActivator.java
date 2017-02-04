package ru.rlokc.bachparse.service.tokenizerprovider;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import ru.rlokc.bachparse.service.api.INlpService;
import ru.rlokc.bachparse.service.api.INlpServiceConsumer;
import ru.rlokc.bachparse.service.api.Event;

public class ServiceActivator implements BundleActivator {

	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		INlpService service = new NlpServiceImpl();
		context.registerService(INlpService.class.getName(), service, null);
		System.out.println("NLP service registered");
		service.addEvent(null);
	}

	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
