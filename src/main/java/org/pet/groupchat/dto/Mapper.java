package org.pet.groupchat.dto;

import org.pet.groupchat.model.Message;
import org.pet.groupchat.model.User;

public class Mapper {
    public static MessageDto toMessageDto(Message message) {
        return new MessageDto(
                message.getMessage(),
                message.getDateTime().toString(),
                message.getUser().getName()
        );
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getName());
    }
}
