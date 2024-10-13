package org.gfg.expenseTracker.repository;


import org.gfg.expenseTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public  interface  UserRepository extends JpaRepository<User, Integer> {
   //Native Query
    @Query(value = "select u from user u where user.email =:email", nativeQuery = true)//runs on sql
    public User findByEmailAddress(String email);
    //Jpql Query
    @Query(value = "select u from User u where u.email = :email")//runs on hibernate
    public User findByEmailAddressJPQL(String email);



//    public User findByEmail(String email);
//
// public User findByNameLike(String pattern);
//
// public User findByCreatedAtGreaterThanAndLike(Date CreateAt, String name);
User findByEmail(String email);


 User findByNameLike(String pattern);

 User findByCreatedAtGreaterThanAndNameLike(Date createdAt, String name);




 
}
