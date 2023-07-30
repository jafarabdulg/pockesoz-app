package com.jafar.pockesoz.repository;

import com.jafar.pockesoz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    @Query(value = "SELECT * FROM m_user WHERE email = :email AND is_active = true",
                    nativeQuery = true)
    Optional<User> findUserByEmail(@Param("email") String email);
}
