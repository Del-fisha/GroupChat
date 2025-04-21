package org.pet.groupchat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String name;
    private String sessionId;

    public UserDto(String name, String sessionId) {
        this.name = name;
        this.sessionId = sessionId;
    }

}
