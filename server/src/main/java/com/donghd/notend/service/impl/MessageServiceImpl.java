package com.donghd.notend.service.impl;

import com.donghd.notend.service.MessageService;
import com.donghd.notend.service.UserService;
import com.donghd.notend.config.Constants;
import com.donghd.notend.domain.Message;
import com.donghd.notend.domain.User;
import com.donghd.notend.repository.MessageRepository;
import com.donghd.notend.service.dto.MessageDTO;
import com.donghd.notend.service.mapper.MessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Message.
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final MessageRepository messageRepository;

    private final MessageMapper messageMapper;

    private final UserService userService;

    public MessageServiceImpl(MessageRepository messageRepository, MessageMapper messageMapper, UserService userService) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        this.userService = userService;
    }

    /**
     * Save a message.
     *
     * @param messageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MessageDTO save(MessageDTO messageDTO) {
        log.debug("Request to save Message : {}", messageDTO);
        Message message = messageMapper.toEntity(messageDTO);
        message = messageRepository.save(message);
        return messageMapper.toDto(message);
    }

    /**
     * Get all the messages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MessageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Messages");
        Optional<User> userOpt = userService.getUserWithAuthorities();
        if (userOpt.isPresent()) {
            return messageRepository.findByToUser_Id(userOpt.get().getId(), pageable)
            .map(messageMapper::toDto);
        }
        return null;
    }

    @Override
    public Page<MessageDTO> findAllMsgUnread(Pageable pageable) {
        log.debug("Request to get all Messages Unread");
        Optional<User> userOpt = userService.getUserWithAuthorities();
        if (userOpt.isPresent()) {
            return messageRepository.findByToUser_IdAndStatus(userOpt.get().getId(), Constants.SENT, pageable)
            .map(messageMapper::toDto);
        }
        return null;
    }


    /**
     * Get one message by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MessageDTO> findOne(Long id) {
        log.debug("Request to get Message : {}", id);
        return messageRepository.findById(id)
            .map(messageMapper::toDto);
    }

    /**
     * Delete the message by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Message : {}", id);
        messageRepository.deleteById(id);
    }
}
