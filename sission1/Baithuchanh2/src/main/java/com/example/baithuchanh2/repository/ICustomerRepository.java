package com.example.baithuchanh2.repository;

import com.example.baithuchanh2.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
}
