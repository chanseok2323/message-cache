package com.message.repository;

import com.message.domain.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {
    Code findByNo(Long no);
    Code findByMessageKey(String key);

}
