package com.tech.spring.boot.dbservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tech.spring.boot.dbservice.model.Quote;

public interface QuotesRepository extends /*JpaRepository*/CrudRepository<Quote, Integer>{

	List<Quote> findByUserName(String username);

}
