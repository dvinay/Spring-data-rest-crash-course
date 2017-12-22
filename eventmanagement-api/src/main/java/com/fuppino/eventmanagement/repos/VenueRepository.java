package com.fuppino.eventmanagement.repos;

import org.springframework.data.repository.CrudRepository;

import com.fuppino.eventmanagement.entities.Venue;

public interface VenueRepository extends CrudRepository<Venue, Long> {
	
}