package com.learning.curd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.curd.entiry.CustomUserDetails;

@Repository
public interface UserRepositry extends JpaRepository<CustomUserDetails, String>{

}
