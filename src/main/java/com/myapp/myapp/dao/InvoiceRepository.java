package com.myapp.myapp.dao;

import com.myapp.myapp.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    public Page<Invoice> findByNameContains(String mc , Pageable pageable);
}
