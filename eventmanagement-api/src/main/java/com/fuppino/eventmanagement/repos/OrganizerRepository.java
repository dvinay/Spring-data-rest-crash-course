package com.fuppino.eventmanagement.repos;

import org.springframework.data.repository.CrudRepository;

import com.fuppino.eventmanagement.entities.Organizer;

public interface OrganizerRepository extends CrudRepository<Organizer, Long> {
	
}