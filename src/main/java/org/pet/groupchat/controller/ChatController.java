package org.pet.groupchat.controller;

import org.pet.groupchat.dto.MessageDto;
import org.pet.groupchat.dto.UserDto;
import org.pet.groupchat.model.User;
import org.pet.groupchat.repository.UserRepository;
import org.pet.groupchat.service.MessageService;
import org.pet.groupchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class ChatController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final MessageService messageService;

    @Autowired
    public ChatController(UserService service, UserRepository repository, MessageService messageService) {
        this.userService = service;
        this.userRepository = repository;
        this.messageService = messageService;
    }

    @GetMapping("/init")
    public HashMap<String, Boolean> init() {
        HashMap<String, Boolean> response = userService.init();
        return response;
    }

    @PostMapping("/auth")
    public HashMap<String, Boolean> auth(@RequestParam String name) {
        HashMap<String, Boolean> response = userService.authUser(name);
        return response;
    }

    @PostMapping("/message")
    public HashMap<String, Boolean> sendMessage(@RequestParam String message) {
        String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
        Optional<User> user = userRepository.findBySessionId(sessionId);
        HashMap<String, Boolean> result = userService.sendMessage(user.orElseThrow(), message);
        return result;
    }

    @GetMapping("/message")
    public List<MessageDto> getMessagesList() {
        return messageService.findAll();
    }

    @GetMapping("/users")
    public List<UserDto> getUsersList() {
        return userService.findAll();
    }
}
