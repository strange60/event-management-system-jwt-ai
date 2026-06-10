package com.debraj.eventmanagement.service;

import com.debraj.eventmanagement.dto.EventRequestDto;
import com.debraj.eventmanagement.dto.EventResponseDto;
import com.debraj.eventmanagement.entity.Event;
import com.debraj.eventmanagement.exception.ResourceNotFoundException;
import com.debraj.eventmanagement.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public EventResponseDto createEvent(EventRequestDto dto) {

        Event event = new Event();

        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setLocation(dto.getLocation());
        event.setEventDate(dto.getEventDate());
        event.setCapacity(dto.getCapacity());

        Event savedEvent = repository.save(event);

        EventResponseDto response = new EventResponseDto();

        response.setId(savedEvent.getId());
        response.setTitle(savedEvent.getTitle());
        response.setDescription(savedEvent.getDescription());
        response.setLocation(savedEvent.getLocation());
        response.setEventDate(savedEvent.getEventDate());
        response.setCapacity(savedEvent.getCapacity());

        return response;
    }


    public List<Event> getAllEvents() {
        return repository.findAll();
    }
    public List<Event> getAllEventsSorted(String field) {
        return repository.findAll(Sort.by(field));
    }
    public Page<Event> getEventsWithPagination(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return repository.findAll(pageable);
    }
    public Event getEventById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Event Not Found With Id : " + id));
    }
    public void deleteEvent(Long id){

        Event event = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Event Not Found With Id : " + id));

        repository.delete(event);
    }
    public List<Event> searchEvents(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }
    public Page<Event> getEventsWithPaginationAndSorting(
            int page,
            int size,
            String field) {

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(field)
                );

        return repository.findAll(pageable);
    }
    private EventResponseDto mapToDto(Event event) {

        EventResponseDto dto = new EventResponseDto();

        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setLocation(event.getLocation());
        dto.setEventDate(event.getEventDate());
        dto.setCapacity(event.getCapacity());

        return dto;
    }
}