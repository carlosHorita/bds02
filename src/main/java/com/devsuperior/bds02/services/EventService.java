package com.devsuperior.bds02.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class EventService {

	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Transactional
	public EventDTO update(EventDTO dto, Long id) {
		try {
			Event entity = eventRepository.getOne(id);
			entity.setDate(dto.getDate());
			entity.setName(dto.getName());
			entity.setUrl(dto.getUrl());
			entity.setCity(cityRepository.getOne(dto.getCityId()));
			entity = eventRepository.save(entity);
			return new EventDTO(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id); 
		}


	}
	
}
