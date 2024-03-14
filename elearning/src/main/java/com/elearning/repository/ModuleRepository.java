package com.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.elearning.entity.Module;;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {

}
