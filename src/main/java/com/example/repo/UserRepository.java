package com.example.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import com.example.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>, QuerydslPredicateExecutor<User> {

    User findByFirstNameIgnoreCase(String firstName);

    List<User> findByLastNameIgnoreCase(String lastName);

    List<User> findByFirstNameAndLastNameAllIgnoreCase(String firstName, String lastName);

    

    List<User> findAll();


    @Query("UPDATE User SET lastLogin = NOW() WHERE userId = ?1")
    void updateLastLogin(String userId);
 

    /**
     * This method is similar to method {@code getUserPermissions}
     */
    @Query(value = "select permission from permission p join role_permissions rp on p.permission_id = rp.permission_id join role r on rp.role_id = r.role_id join user_roles ur on r.role_id = ur.role_id join user u on ur.user_id = u.user_id where u.user_id = ?1",
            nativeQuery = true)
    List<String> getPermissionsForUser(String userId);

}
