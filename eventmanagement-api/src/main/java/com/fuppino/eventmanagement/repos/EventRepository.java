package com.fuppino.eventmanagement.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fuppino.eventmanagement.entities.Event;

public interface EventRepository extends PagingAndSortingRepository<Event, Long> {
	
}