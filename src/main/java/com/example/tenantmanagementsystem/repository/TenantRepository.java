package com.example.tenantmanagementsystem.repository;

import com.example.tenantmanagementsystem.model.Tenant;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface TenantRepository extends JpaRepository<Tenant, Long> {

    Optional<Tenant> findTenantById(Long id);


    void deleteTenantById(Long id);

}
