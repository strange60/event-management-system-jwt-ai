package com.debraj.eventmanagement.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AiController {

    @PostMapping("/generate-description")
    public Map<String, String> generateDescription(
            @RequestBody Map<String, String> request) {

        String title = request.get("title");

        String description =
                "Join our " + title +
                        " event to learn industry-relevant concepts, practical implementation, and hands-on experience.";

        Map<String, String> response =
                new HashMap<>();

        response.put(
                "description",
                description);

        return response;
    }
}