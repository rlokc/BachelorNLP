package ru.rlokc.bachparse.consumer;

import java.util.ArrayList;

import ru.rlokc.bachparse.service.api.Event;
import ru.rlokc.bachparse.service.api.INlpService;
import ru.rlokc.bachparse.service.api.INlpServiceConsumer;


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
	}
	
	public void onEventRemoved(Event e){
		System.out.println("Consumer: Event deleted");
	}
	
}
