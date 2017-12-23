package com.fuppino.eventmanagement.entities.projections;

import java.time.Instant;
import java.time.ZonedDateTime;

import org.springframework.data.rest.core.config.Projection;

import com.fuppino.eventmanagement.entities.Event;

@Projection(name = "partial", types = { Event.class })
public interface PartialEventProjection {
	public String getName();

	public ZonedDateTime getStartTime();

	public ZonedDateTime getEndTime();
	
	public Instant getCreated();
}
