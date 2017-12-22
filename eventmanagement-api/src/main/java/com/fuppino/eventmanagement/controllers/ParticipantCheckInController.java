package com.fuppino.eventmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fuppino.eventmanagement.entities.Participant;
import com.fuppino.eventmanagement.repos.ParticipantRepository;

@RepositoryRestController
@RequestMapping("/events")
public class ParticipantCheckInController {
	
	@Autowired
	private ParticipantRepository participantRepository;
	
	@PostMapping("/checkin/{id}")
	public ResponseEntity<PersistentEntityResource> checkIn(@PathVariable("id") Long id , PersistentEntityResourceAssembler assembler) {
		
		Participant participant = participantRepository.findOne(id);
		if(participant != null) {
			if(participant.getCheckedIn()) {
				throw new ResourceNotFoundException();
			}
			participant.setCheckedIn(true);
			participantRepository.save(participant);
		}
		return ResponseEntity.ok(assembler.toResource(participant));
	}
}
