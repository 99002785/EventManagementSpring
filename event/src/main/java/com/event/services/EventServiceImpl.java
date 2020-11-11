package com.event.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.commands.EventForm;
import com.event.converters.EventFormToEvent;
import com.event.domain.Event;
import com.event.repositories.EventRepository;

@Service
public class EventServiceImpl implements EventService{
	
	 private EventRepository eventRepository;
	    private EventFormToEvent eventFormToEvent;

	    @Autowired
	    public EventServiceImpl(EventRepository eventRepository, EventFormToEvent eventFormToEvent) {
	        this.eventRepository = eventRepository;
	        this.eventFormToEvent = eventFormToEvent;
	    }


	    @Override
	    public List<Event> listAll() {
	        List<Event> events = new ArrayList<>();
	        eventRepository.findAll().forEach(events::add); //fun with Java 8
	        return events;
	    }

	    @Override
	    public Event getById(String id) {
	        return eventRepository.findById(id).orElse(null);
	    }

	    @Override
	    public Event saveOrUpdate(Event event) {
	        eventRepository.save(event);
	        return event;
	    }

	    @Override
	    public void delete(String id) {
	        eventRepository.deleteById(id);
	    }

	    @Override
	    public Event saveOrUpdateEventForm(EventForm eventForm) {
	        Event savedEvent = saveOrUpdate(eventFormToEvent.convert(eventForm));

	        System.out.println("Saved Event Id: " + savedEvent.getId());
	        return savedEvent;
	    }


		
}
