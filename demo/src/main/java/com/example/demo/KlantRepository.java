package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import java.util.*;

public interface KlantRepository extends JpaRepository<Klant,Long>{
//	@Query("SELECT k FROM Klant k WHERE LOWER(k.name) = LOWER(:name)")
	// public List<Klant> findByName(@Param("name")String name);
}
