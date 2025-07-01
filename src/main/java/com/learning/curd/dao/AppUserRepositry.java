package com.learning.curd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.curd.dto.AppUserDTO;

@Repository
public interface AppUserRepositry extends JpaRepository<AppUserDTO, Integer>{

}
