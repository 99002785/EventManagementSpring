package com.event.converters;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import com.event.commands.EventForm;
import com.event.domain.Event;


@Component
public class EventFormToEvent implements Converter<EventForm, Event> {

    @Override
    public Event convert(EventForm eventForm) {
        Event event = new Event();
        if (eventForm.getId() != null  && !StringUtils.isEmpty(eventForm.getId())) {
            event.setId(new ObjectId(eventForm.getId()));
        }
        event.setDescription(eventForm.getDescription());
        event.setPrice(eventForm.getPrice());
        event.setImageUrl(eventForm.getImageUrl());
        return event;
    }

	
}