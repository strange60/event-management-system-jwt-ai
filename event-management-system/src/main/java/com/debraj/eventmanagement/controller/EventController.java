package com.debraj.eventmanagement.controller;

import com.debraj.eventmanagement.entity.Event;
import com.debraj.eventmanagement.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {
        return ResponseEntity.ok(service.createEvent(event));
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return service.getAllEvents();
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

}