package ru.rlokc.bachparse.service.api;

public interface INlpServiceConsumer {
	public void onEventAdded(Event e);
	public void onEventRemoved(Event e);
}
