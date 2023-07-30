package com.jafar.pockesoz.repository;

import com.jafar.pockesoz.entity.Pocket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PocketRepository extends JpaRepository<Pocket, String> {

    @Query(value = "SELECT * FROM m_pocket WHERE user_id = :userId",
            nativeQuery = true)
    List<Pocket> findAllByUser_Id(@Param("userId") String userId);
}
