package com.event.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.event.commands.EventForm;
import com.event.domain.Event;



@Component
public class EventToEventForm implements Converter<Event, EventForm> {
    @Override
    public EventForm convert(Event event) {
        EventForm eventForm = new EventForm();
        eventForm.setId(event.getId().toHexString());
        eventForm.setDescription(event.getDescription());
        eventForm.setPrice(event.getPrice());
        eventForm.setImageUrl(event.getImageUrl());
        return eventForm;
    }
}
