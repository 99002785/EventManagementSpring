package com.event.services;

import java.util.List;

import com.event.commands.EventForm;
import com.event.domain.Event;



public interface EventService {

	List<Event> listAll();

    Event getById(String id);

    Event saveOrUpdate(Event event);

    void delete(String id);

    Event saveOrUpdateEventForm(EventForm eventForm);
	
}
