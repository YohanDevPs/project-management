package com.api.management.repositorys;

import com.api.management.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query(value = "SELECT * FROM User g WHERE g.name = ?1 AND g.password = ?2", nativeQuery = true)
//    public User getUserByEmailAndPassword(String name, String password);


//    public User findUserByNameAndPassword(String email, String password);
}
