package org.pet.groupchat.service;

import org.pet.groupchat.dto.MessageDto;
import org.pet.groupchat.dto.Mapper;
import org.pet.groupchat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<MessageDto> findAll() {
        List<MessageDto> messages = messageRepository.findAll(Sort.by(Sort.Direction.ASC, "dateTime"))
                .stream()
                .map(Mapper::toMessageDto)
                .collect(Collectors.toList());
        return messages;
    }

}
