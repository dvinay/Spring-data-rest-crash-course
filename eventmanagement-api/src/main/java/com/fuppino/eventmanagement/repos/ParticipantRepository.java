package com.fuppino.eventmanagement.repos;

import org.springframework.data.repository.CrudRepository;

import com.fuppino.eventmanagement.entities.Participant;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {
	
}