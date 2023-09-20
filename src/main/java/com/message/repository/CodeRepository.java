package com.message.repository;

import com.message.domain.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {
    Code findByNo(Long no);
    Code findDistinctByMessageKey(String key);

    List<Code> findByLanguage(String language);

}
