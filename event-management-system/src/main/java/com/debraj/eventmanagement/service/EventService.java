package com.debraj.eventmanagement.service;

import com.debraj.eventmanagement.entity.Event;
import com.debraj.eventmanagement.exception.ResourceNotFoundException;
import com.debraj.eventmanagement.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public Event createEvent(Event event) {
        return repository.save(event);
    }

    public List<Event> getAllEvents() {
        return repository.findAll();
    }
    public Event getEventById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Event Not Found With Id : " + id));
    }
    public void deleteEvent(Long id){
        repository.deleteById(id);
    }

}