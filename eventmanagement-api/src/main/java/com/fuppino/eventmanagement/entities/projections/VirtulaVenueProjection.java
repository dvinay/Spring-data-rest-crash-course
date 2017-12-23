package com.fuppino.eventmanagement.entities.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.fuppino.eventmanagement.entities.Venue;

@Projection(name = "virtual", types = { Venue.class })
public interface VirtulaVenueProjection {

	@Value("#{target.streetAddress} #{target.streetAddress2}")
	public String getCompleteStreetAddress();

}
