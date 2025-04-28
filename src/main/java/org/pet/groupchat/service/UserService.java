package org.pet.groupchat.service;

import org.pet.groupchat.dto.Mapper;
import org.pet.groupchat.dto.UserDto;
import org.pet.groupchat.model.Message;
import org.pet.groupchat.model.User;
import org.pet.groupchat.repository.MessageRepository;
import org.pet.groupchat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public UserService(UserRepository userRepository, MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public HashMap<String, Boolean> authUser(String name) {
        HashMap<String, Boolean> result = new HashMap<>();
        if (name.isEmpty()) {
            result.put("result", false);
            return result;
        }
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        User user = new User(sessionId, name);
        userRepository.save(user);
        result.put("result", true);
        return result;
    }

    public HashMap<String, Boolean> init() {
        HashMap<String, Boolean> result = new HashMap<>();
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        Optional<User> user = userRepository.findBySessionId(sessionId);
        result.put("result", user.isPresent());
        return result;
    }

    public HashMap<String, Boolean> sendMessage(User user, String message) {
        HashMap<String, Boolean> result = new HashMap<>();
        if (message.isEmpty()) {
            result.put("result", false);
            return result;
        }
        Message msg = new Message();
        msg.setUser(user);
        msg.setMessage(message);
        msg.setDateTime(LocalDateTime.now());
        messageRepository.save(msg);
        result.put("result", true);
        return result;
    }

    public List<UserDto> findAll() {
        List<UserDto> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .map(Mapper::toUserDto)
                .toList();
        return users;
    }
}
