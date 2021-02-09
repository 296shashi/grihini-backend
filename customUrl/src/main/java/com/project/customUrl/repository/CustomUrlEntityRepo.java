package com.project.customUrl.repository;

import com.project.customUrl.entity.CustomUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CustomUrlEntityRepo extends JpaRepository<CustomUrlEntity, Long> {

    //@Transactional
    //@Modifying(flushAutomatically = true)
    @Query("from CustomUrlEntity ce where ce.shortUrl = :shortUrl")
    CustomUrlEntity findByShortUrl(@Param("shortUrl")String shortUrl);
}
