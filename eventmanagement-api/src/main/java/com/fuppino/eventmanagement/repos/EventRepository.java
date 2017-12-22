package com.fuppino.eventmanagement.repos;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.fuppino.eventmanagement.entities.Event;
import java.lang.String;
import java.util.List;

public interface EventRepository extends PagingAndSortingRepository<Event, Long> {
	
	List<Event> findByName(@Param("name") String name);
	
}