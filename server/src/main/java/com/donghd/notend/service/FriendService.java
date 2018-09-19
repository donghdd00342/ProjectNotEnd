package com.donghd.notend.service;

import com.donghd.notend.service.dto.FriendDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Friend.
 */
public interface FriendService {

    /**
     * Save a friend.
     *
     * @param friendDTO the entity to save
     * @return the persisted entity
     */
    FriendDTO save(FriendDTO friendDTO);

    /**
     * Get all the friends.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FriendDTO> findAll(Pageable pageable);

    Page<FriendDTO> findAllByOwner(Pageable pageable);

    Page<FriendDTO> findAllAskFriend(Pageable pageable);

    /**
     * Get the "id" friend.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FriendDTO> findOne(Long id);

    Optional<FriendDTO> findByFriendId(Long friendId);

    Optional<FriendDTO> findByIdsMacth(Long friendId, Long ownerId);

    /**
     * Delete the "id" friend.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    Integer checkFriendStatus(Long myId, Long friendId);
}
