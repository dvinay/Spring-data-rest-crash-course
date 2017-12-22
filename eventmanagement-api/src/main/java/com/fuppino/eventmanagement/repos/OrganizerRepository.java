package com.fuppino.eventmanagement.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fuppino.eventmanagement.entities.Organizer;

public interface OrganizerRepository extends PagingAndSortingRepository<Organizer, Long> {
	
}