package com.donghd.notend.web.rest;

import com.donghd.notend.config.Constants;
import com.donghd.notend.service.MessageService;
import com.donghd.notend.web.rest.errors.BadRequestAlertException;
import com.donghd.notend.web.rest.util.HeaderUtil;
import com.donghd.notend.web.rest.util.PaginationUtil;
import com.donghd.notend.service.dto.MessageDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Message.
 */
@RestController
@RequestMapping("/api")
public class MessageResource {

    private final Logger log = LoggerFactory.getLogger(MessageResource.class);

    private static final String ENTITY_NAME = "message";

    private final MessageService messageService;

    public MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * POST  /messages : Create a new message.
     *
     * @param messageDTO the messageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new messageDTO, or with status 400 (Bad Request) if the message has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/messages")
    public ResponseEntity<MessageDTO> createMessage(@Valid @RequestBody MessageDTO messageDTO) throws URISyntaxException {
        log.debug("REST request to save Message : {}", messageDTO);
        if (messageDTO.getId() != null) {
            throw new BadRequestAlertException("A new message cannot already have an ID", ENTITY_NAME, "idexists");
        }
        messageDTO.setStatus(Constants.SENT);
        messageDTO.setSendingDateTime(Instant.now());

        MessageDTO result = messageService.save(messageDTO);
        return ResponseEntity.created(new URI("/api/messages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /messages : Updates an existing message.
     *
     * @param messageDTO the messageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated messageDTO,
     * or with status 400 (Bad Request) if the messageDTO is not valid,
     * or with status 500 (Internal Server Error) if the messageDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/messages/{id}")
    public ResponseEntity<MessageDTO> receivedMessage(@PathVariable Long id) throws URISyntaxException {
        log.debug("REST request to update Message : {}", id);

        Optional<MessageDTO> result = messageService.findOne(id);
        if (result.isPresent()) {
            MessageDTO messageDTO = result.get();
            messageDTO.setStatus(Constants.RECEIVED);
            
            messageService.save(messageDTO);
        } else {
            throw new BadRequestAlertException("Message not found", ENTITY_NAME, "msgNotFound");
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, id.toString()))
            .body(result.get());
    }

    /**
     * GET  /messages : get all the messages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of messages in body
     */
    @GetMapping("/messages")
    public ResponseEntity<List<MessageDTO>> getAllMessages(Pageable pageable) {
        log.debug("REST request to get a page of Messages");
        Page<MessageDTO> page = messageService.findAll(pageable);
        if (page != null) {
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/messages");
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        } else {
            throw new BadRequestAlertException("Authentication fail", ENTITY_NAME, "authFail");
        }
    }

    @GetMapping("/messages/unread")
    public ResponseEntity<List<MessageDTO>> getAllMessagesUnread(Pageable pageable) {
        log.debug("REST request to get a page of Messages Unread");
        Page<MessageDTO> page = messageService.findAllMsgUnread(pageable);
        if (page != null) {
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/messages/unread");
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        } else {
            throw new BadRequestAlertException("Authentication fail", ENTITY_NAME, "authFail");
        }
    }

    /**
     * GET  /messages/:id : get the "id" message.
     *
     * @param id the id of the messageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the messageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/messages/{id}")
    public ResponseEntity<MessageDTO> getMessage(@PathVariable Long id) {
        log.debug("REST request to get Message : {}", id);
        Optional<MessageDTO> messageDTO = messageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(messageDTO);
    }

    /**
     * DELETE  /messages/:id : delete the "id" message.
     *
     * @param id the id of the messageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        log.debug("REST request to delete Message : {}", id);
        messageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
