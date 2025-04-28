package org.pet.groupchat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageDto {

    private String text;
    private String date;
    private String userName;

}
