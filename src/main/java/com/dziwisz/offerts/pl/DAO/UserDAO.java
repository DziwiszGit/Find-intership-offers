package com.dziwisz.offerts.pl.DAO;

import com.dziwisz.offerts.pl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
