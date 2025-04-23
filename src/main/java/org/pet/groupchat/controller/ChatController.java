package org.pet.groupchat.controller;

import org.pet.groupchat.model.User;
import org.pet.groupchat.repository.UserRepository;
import org.pet.groupchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.HashMap;
import java.util.List;

@RestController
public class ChatController {

    private final UserService userService;

    @Autowired
    public ChatController(UserService service) {
        this.userService = service;
    }

    @GetMapping("/init")
    public HashMap<String, Boolean> init() {
        HashMap<String, Boolean> response = new HashMap<>();
        response.put("result", false);

        return response;
    }

    @PostMapping("/auth")
    public HashMap<String, Boolean> auth(@RequestParam String name) {
        HashMap<String, Boolean> response = new HashMap<>();

        userService.authUser(name);

        response.put("result", true);

        return response;
    }

    @PostMapping("/message")
    public Boolean sendMessage(@RequestParam String message) {
        return true;
    }

    @GetMapping("/message")
    public List<String> getMessagesList() {
        return null;
    }

    @GetMapping("/users")
    public HashMap<Long, String> getUsersList() {
        return null;
    }
}
