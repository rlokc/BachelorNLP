package ru.rlokc.bachparse.service.tokenizerprovider;

import java.util.ArrayList;

import ru.rlokc.bachparse.service.api.Event;
import ru.rlokc.bachparse.service.api.INlpService;
import ru.rlokc.bachparse.service.api.INlpServiceConsumer;

public class NlpServiceImpl implements INlpService{
	
	private ArrayList<INlpServiceConsumer> consumerList = new ArrayList<INlpServiceConsumer>();
	
	public void start(){
		System.out.println("Service started");
	}
	
	public void addConsumer(INlpServiceConsumer cons){
		consumerList.add(cons);
	}
	
	public void removeConsumer(INlpServiceConsumer cons){
		consumerList.remove(cons);
	}
	
	public void addEvent(Event e){
		System.out.println("SERVICE: Event added");
		ModelTeacher teacher = new ModelTeacher();
		try {
			teacher.train("/home/rlokc/Dropbox/Education/7sem/Bachelor/trees.txt");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void removeEvent(Event e){
		System.out.println("SERVICE: Event deleted");
	}
}
