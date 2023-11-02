package com.example.assign.confirmtoken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepo extends JpaRepository<ConfirmationToken, UUID> {

    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("""
            UPDATE ConfirmationToken c
            SET c.confirmedAt = ?2
            WHERE c.token = ?1
            """)
    void updateConfirmAt(String token, LocalDateTime confirmedAt);
}
