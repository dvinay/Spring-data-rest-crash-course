package com.fuppino.eventmanagement.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fuppino.eventmanagement.entities.Venue;

public interface VenueRepository extends PagingAndSortingRepository<Venue, Long> {
	
}