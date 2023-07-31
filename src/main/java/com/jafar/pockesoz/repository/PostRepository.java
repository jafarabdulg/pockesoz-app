package com.jafar.pockesoz.repository;

import com.jafar.pockesoz.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {

    @Query(value = "SELECT * FROM m_post WHERE user_id = :userId", nativeQuery = true)
    List<Post> findAllByUser_Id(@Param("userId") String userId);
}
