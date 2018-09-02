package com.donghd.notend.repository;

import com.donghd.notend.domain.Friend;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Friend entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("select friend from Friend friend where friend.owner.login = ?#{principal.username}")
    List<Friend> findByOwnerIsCurrentUser();

    @Query("select friend from Friend friend where friend.friend.login = ?#{principal.username}")
    List<Friend> findByFriendIsCurrentUser();

}
