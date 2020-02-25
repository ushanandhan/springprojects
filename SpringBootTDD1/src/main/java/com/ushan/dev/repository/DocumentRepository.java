package com.ushan.dev.repository;

import com.ushan.dev.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document,Long> {
    Document findByDocName(String fileName);
}
