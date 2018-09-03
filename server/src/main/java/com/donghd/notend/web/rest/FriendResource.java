package com.donghd.notend.web.rest;

import com.donghd.notend.config.Constants;
import com.donghd.notend.service.FriendService;
import com.donghd.notend.web.rest.errors.BadRequestAlertException;
import com.donghd.notend.web.rest.errors.SomethingWentWrongException;
import com.donghd.notend.web.rest.util.HeaderUtil;
import com.donghd.notend.web.rest.util.PaginationUtil;
import com.donghd.notend.service.dto.FriendDTO;
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
 * REST controller for managing Friend.
 */
@RestController
@RequestMapping("/api")
public class FriendResource {

    private final Logger log = LoggerFactory.getLogger(FriendResource.class);

    private static final String ENTITY_NAME = "friend";

    private final FriendService friendService;

    public FriendResource(FriendService friendService) {
        this.friendService = friendService;
    }

    /**
     * POST  /friends : Create a new friend.
     *
     * @param friendDTO the friendDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new friendDTO, or with status 400 (Bad Request) if the friend has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/friends")
    public ResponseEntity<FriendDTO> askFriend(@Valid @RequestBody FriendDTO friendDTO) throws URISyntaxException {
        log.debug("REST request to save Friend : {}", friendDTO);
        if (friendDTO.getId() != null) {
            throw new BadRequestAlertException("A new friend cannot already have an ID", ENTITY_NAME, "idexists");
        }

        // populator friendDTO
        friendDTO.setRelationshipDate(Instant.now());
        friendDTO.setStatus(Constants.PENDING);

        FriendDTO result = friendService.save(friendDTO);
        return ResponseEntity.created(new URI("/api/friends/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /friends : Updates an existing friend.
     *
     * @param friendDTO the friendDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated friendDTO,
     * or with status 400 (Bad Request) if the friendDTO is not valid,
     * or with status 500 (Internal Server Error) if the friendDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/friends")
    public ResponseEntity<FriendDTO> acceptFriend(@Valid @RequestBody FriendDTO friendDTO) throws URISyntaxException {
        log.debug("REST request to update Friend : {}", friendDTO);
        if (friendDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        // process accept
        Optional<FriendDTO> friendOpt = friendService.findOne(friendDTO.getId());
        if (friendOpt.isPresent()) {
            FriendDTO friendDb = friendOpt.get();
            friendDb.setStatus(Constants.ACCEPT);
            
            FriendDTO result = friendService.save(friendDTO);
            friendService.save(swapUser(friendDTO));

            return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, friendDTO.getId().toString()))
                .body(result);
        } else {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
    }

    private FriendDTO swapUser(FriendDTO friendDTO) {
        Long temp = friendDTO.getOwnerId();
        friendDTO.setOwnerId(friendDTO.getFriendId());
        friendDTO.setFriendId(temp);
        return friendDTO;
    }

    /**
     * GET  /friends : get all the friends.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of friends in body
     */
    @GetMapping("/friends")
    public ResponseEntity<List<FriendDTO>> getAllFriends(Pageable pageable) {
        log.debug("REST request to get a page of Friends");
        Page<FriendDTO> page = friendService.findAllByOwner(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/friends");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /friends/request : get all the ask friend.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of friends in body
     */
    @GetMapping("/friends/request")
    public ResponseEntity<List<FriendDTO>> getAllRequestFriend(Pageable pageable) {
        log.debug("REST request to get a page of Ask Friend");
        Page<FriendDTO> page = friendService.findAllAskFriend(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/friends");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * DELETE  /friends/:id : delete the "id" friend.
     *
     * @param id the id of the friendDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/friends/{id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable Long id) {
        log.debug("REST request to delete Friend : {}", id);
        if (id == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        // process delete
        Optional<FriendDTO> friendOpt = friendService.findOne(id);
        if (friendOpt.isPresent()) {
            FriendDTO friendDb = friendOpt.get();
            Optional<FriendDTO> friendDbMatch = friendService.findByIdsMacth(friendDb.getOwnerId(), friendDb.getFriendId());

            if (!friendDbMatch.isPresent()) {
                throw new SomethingWentWrongException("Ids not match", ENTITY_NAME, "Ids not match");
            }

            friendService.delete(id);
            friendService.delete(friendDbMatch.get().getId());

            return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
        } else {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "id not found");
        }
    }
}
