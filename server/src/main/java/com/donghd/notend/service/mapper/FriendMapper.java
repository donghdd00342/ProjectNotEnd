package com.donghd.notend.service.mapper;

import com.donghd.notend.domain.*;
import com.donghd.notend.service.dto.FriendDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Friend and its DTO FriendDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface FriendMapper extends EntityMapper<FriendDTO, Friend> {

    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "friend.id", target = "friendId")
    @Mapping(source = "friend.firstName", target = "friendFirstName")
    @Mapping(source = "friend.lastName", target = "friendLastName")
    @Mapping(source = "friend.imageUrl", target = "friendImageUrl")
    @Mapping(source = "friend.login", target = "friendLogin")
    FriendDTO toDto(Friend friend);

    @Mapping(source = "ownerId", target = "owner")
    @Mapping(source = "friendId", target = "friend")
    Friend toEntity(FriendDTO friendDTO);

    default Friend fromId(Long id) {
        if (id == null) {
            return null;
        }
        Friend friend = new Friend();
        friend.setId(id);
        return friend;
    }
}
