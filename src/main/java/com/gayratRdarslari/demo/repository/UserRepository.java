package com.gayratRdarslari.demo.repository;

import com.gayratRdarslari.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName (String userName);

}
