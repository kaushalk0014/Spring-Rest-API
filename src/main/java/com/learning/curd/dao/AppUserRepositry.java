package com.learning.curd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.curd.entiry.AppUser;

@Repository
public interface AppUserRepositry extends JpaRepository<AppUser, Integer>{

}
