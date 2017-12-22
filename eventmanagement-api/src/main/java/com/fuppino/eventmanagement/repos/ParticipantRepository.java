package com.fuppino.eventmanagement.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fuppino.eventmanagement.entities.Participant;

public interface ParticipantRepository extends PagingAndSortingRepository<Participant, Long> {
	
}