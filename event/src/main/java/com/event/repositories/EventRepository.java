package com.event.repositories;

import org.springframework.data.repository.CrudRepository;

import com.event.domain.Event;


public interface EventRepository extends CrudRepository<Event, String> {
}