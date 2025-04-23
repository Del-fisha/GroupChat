package org.pet.groupchat.service;

import org.pet.groupchat.model.User;
import org.pet.groupchat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository) {
        userRepository = repository;
    }

    public void authUser(String name) {
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        User user = new User(sessionId, name);
        userRepository.save(user);
    }
}
