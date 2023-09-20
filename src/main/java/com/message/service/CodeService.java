package com.message.service;

import com.message.domain.Code;
import com.message.dto.CodeDto;
import com.message.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CodeService {
    private final CodeRepository codeRepository;

    @Cacheable(key = "#key", value = "code", unless = "#result == null")
    public Code findByKey(String key) {
        log.info("call CodeService.findByKey");
        return codeRepository.findDistinctByMessageKey(key);
    }

    public List<Code> findByLanguage(String language) {
        log.info("call CodeService.findByLanguage");
        return codeRepository.findByLanguage(language);
    }

    @Transactional
    public void insert(Code code) {
        codeRepository.save(code);
    }

    //TODO 만약 messagekey 값이 update 치면, 캐시에 변경 전 messageKey 값이 남겨질 수 있기 때문에 @CacheEvic 으로 삭제가 필요할거 같음
    @CacheEvict(key = "#codeDto.beforeMessageKey", value = "code")
    @CachePut(key = "#codeDto.messageKey", value = "code")
    @Transactional
    public Code update(CodeDto codeDto) {
        log.info("messageKey = {}", codeDto.getMessageKey());
        Code code = codeRepository.findByNo(codeDto.getNo());
        Code modify = code.modify(codeDto.getMessageKey(), codeDto.getMessage());
        return codeRepository.save(modify);
    }

    @CacheEvict(value = "code", allEntries = true)
    @Scheduled(fixedDelay = 3)
    public void evictCache() {
        log.info("=== evict cache ===");
    }
}
