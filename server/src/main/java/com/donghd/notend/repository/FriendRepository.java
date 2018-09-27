package com.donghd.notend.repository;

import com.donghd.notend.domain.Friend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Friend entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("select friend from Friend friend where friend.owner.login = ?#{principal.username}")
    List<Friend> findByOwnerIsCurrentUser();

    @Query("select friend from Friend friend where friend.owner.login = ?#{principal.username}")
    Page<Friend> findAllByOwnerIsCurrentUser(Pageable pageable);

    @Query("select friend from Friend friend where friend.owner.login = ?#{principal.username} and friend.status = 12")
    Page<Friend> findAllByOwnerIsCurrentUserAndStatusFriended(Pageable pageable);

    @Query("select friend from Friend friend where friend.friend.login = ?#{principal.username}")
    List<Friend> findByFriendIsCurrentUser();

    @Query("select friend from Friend friend where friend.friend.login = ?#{principal.username}")
    Page<Friend> findAllByFriendIsCurrentUser(Pageable pageable);

    Optional<Friend> findByFriend_Id(Long friendId);
    Optional<Friend> findByFriend_IdAndOwner_Id(Long friendId, Long ownerId);
    
    long countByStatus(Integer statusInt);

}
