package com.donghd.notend.service.impl;

import com.donghd.notend.service.FriendService;
import com.donghd.notend.config.Constants;
import com.donghd.notend.domain.Friend;
import com.donghd.notend.repository.FriendRepository;
import com.donghd.notend.service.dto.FriendDTO;
import com.donghd.notend.service.mapper.FriendMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Friend.
 */
@Service
@Transactional
public class FriendServiceImpl implements FriendService {

    private final Logger log = LoggerFactory.getLogger(FriendServiceImpl.class);

    private final FriendRepository friendRepository;

    private final FriendMapper friendMapper;

    public FriendServiceImpl(FriendRepository friendRepository, FriendMapper friendMapper) {
        this.friendRepository = friendRepository;
        this.friendMapper = friendMapper;
    }

    /**
     * Save a friend.
     *
     * @param friendDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FriendDTO save(FriendDTO friendDTO) {
        log.debug("Request to save Friend : {}", friendDTO);
        Friend friend = friendMapper.toEntity(friendDTO);
        friend = friendRepository.save(friend);
        return friendMapper.toDto(friend);
    }

    /**
     * Get all the friends.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FriendDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Friends");
        return friendRepository.findAll(pageable)
            .map(friendMapper::toDto);
    }

    @Override
    public Page<FriendDTO> findAllByOwner(Pageable pageable) {
        log.debug("Request to get all Friends By Owner");
        return friendRepository.findAllByOwnerIsCurrentUser(pageable)
            .map(friendMapper::toDto);
    }

    @Override
    public Page<FriendDTO> findAllAskFriend(Pageable pageable) {
        log.debug("Request to get all By Friend (Ask Friends)");
        return friendRepository.findAllByFriendIsCurrentUser(pageable)
            .map(friendMapper::toDto);
    }


    /**
     * Get one friend by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FriendDTO> findOne(Long id) {
        log.debug("Request to get Friend : {}", id);
        return friendRepository.findById(id)
            .map(friendMapper::toDto);
    }

    @Override
    public Optional<FriendDTO> findByFriendId(Long friendId) {
        log.debug("Request to find By Friend Id : {}", friendId);
        return friendRepository.findByFriend_Id(friendId)
            .map(friendMapper::toDto);
    }

    @Override
    public Optional<FriendDTO> findByIdsMacth(Long friendId, Long ownerId) {
        log.debug("Request to find By Ids Macth : friendId = {}, ownerId = {}", friendId, ownerId);
        return friendRepository.findByFriend_IdAndOwner_Id(friendId, ownerId)
            .map(friendMapper::toDto);
    }

    /**
     * Delete the friend by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Friend : {}", id);
        friendRepository.deleteById(id);
    }

    @Override
    public Integer checkFriendStatus(Long myId, Long friendId) {
        Optional<Friend> statusOpt = friendRepository.findByFriend_IdAndOwner_Id(friendId, myId);
        if (statusOpt.isPresent()) {
            return statusOpt.get().getStatus();
        } else {
            return Constants.FRIEND_STATUS_NONE;
        }
    }
}
