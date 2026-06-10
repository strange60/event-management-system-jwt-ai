package com.debraj.eventmanagement.controller;

import com.debraj.eventmanagement.dto.EventRequestDto;
import com.debraj.eventmanagement.dto.EventResponseDto;
import com.debraj.eventmanagement.entity.Event;
import com.debraj.eventmanagement.service.EventService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@SecurityRequirement(name = "bearerAuth")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EventResponseDto> createEvent(
           @Valid @RequestBody EventRequestDto dto) {

        return ResponseEntity.ok(
                service.createEvent(dto)
        );
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return service.getAllEvents();
    }
    @GetMapping("/sorted")
    public List<Event> getSortedEvents(@RequestParam String field) {
        return service.getAllEventsSorted(field);
    }
    @GetMapping("/paged")
    public Page<Event> getEventsWithPagination(
            @RequestParam int page,
            @RequestParam int size) {

        return service.getEventsWithPagination(
                page,
                size
        );
    }
    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return service.getEventById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id){
        service.deleteEvent(id);
        return "Event Deleted Successfully";
    }
    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id,
                             @RequestBody Event updatedEvent) {

        Event event = service.getEventById(id);

        event.setTitle(updatedEvent.getTitle());
        event.setDescription(updatedEvent.getDescription());
        event.setLocation(updatedEvent.getLocation());
        event.setEventDate(updatedEvent.getEventDate());
        event.setCapacity(updatedEvent.getCapacity());

        return service.createEvent(event);
    }
    @GetMapping("/search")
    public List<Event> searchEvents(
            @RequestParam String title) {

        return service.searchEvents(title);
    }
    @GetMapping("/paged-sorted")
    public Page<Event> getEventsWithPaginationAndSorting(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String field) {

        return service.getEventsWithPaginationAndSorting(
                page,
                size,
                field
        );
    }


}