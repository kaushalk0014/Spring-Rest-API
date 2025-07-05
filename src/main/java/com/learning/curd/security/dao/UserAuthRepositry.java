package com.learning.curd.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.curd.entiry.AuthRequest;

@Repository
public interface UserAuthRepositry extends JpaRepository<AuthRequest, String>{

}
