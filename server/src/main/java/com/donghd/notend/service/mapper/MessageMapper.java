package com.donghd.notend.service.mapper;

import com.donghd.notend.domain.*;
import com.donghd.notend.service.dto.MessageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Message and its DTO MessageDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface MessageMapper extends EntityMapper<MessageDTO, Message> {

    @Mapping(source = "fromUser.id", target = "fromUserId")
    @Mapping(source = "toUser.id", target = "toUserId")
    @Mapping(source = "fromUser.login", target = "fromUserLogin")
    @Mapping(source = "fromUser.firstName", target = "fromUserFirstName")
    @Mapping(source = "fromUser.lastName", target = "fromUserLastName")
    @Mapping(source = "fromUser.imageUrl", target = "fromUserImageUrl")
    MessageDTO toDto(Message message);

    @Mapping(source = "fromUserId", target = "fromUser")
    @Mapping(source = "toUserId", target = "toUser")
    Message toEntity(MessageDTO messageDTO);

    default Message fromId(Long id) {
        if (id == null) {
            return null;
        }
        Message message = new Message();
        message.setId(id);
        return message;
    }
}
