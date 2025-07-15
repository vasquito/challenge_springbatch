package com.accenture.challenge.sales.dao;


import com.accenture.challenge.sales.dao.domains.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales,Long> {
}
