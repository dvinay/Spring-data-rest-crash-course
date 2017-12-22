package com.fuppino.eventmanagement.repos;

import org.springframework.data.repository.CrudRepository;

import com.fuppino.eventmanagement.entities.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
	
}