package com.donghd.notend.repository;

import com.donghd.notend.domain.Message;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Message entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select message from Message message where message.fromUser.login = ?#{principal.username}")
    List<Message> findByFromUserIsCurrentUser();

    @Query("select message from Message message where message.toUser.login = ?#{principal.username}")
    List<Message> findByToUserIsCurrentUser();

}
