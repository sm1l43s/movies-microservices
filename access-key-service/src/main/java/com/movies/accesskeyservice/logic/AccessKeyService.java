package com.movies.accesskeyservice.logic;

import com.movies.accesskeyservice.config.PropertyConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AccessKeyService {

    private final List<String> key;
    private int pos = 0;

    public AccessKeyService(PropertyConfig config) {
        this.key = new ArrayList<>(config.getKeys());
    }

    public String next() {
        log.info("Requested access key: {}", key.get(pos));
        if (pos > key.size() - 1) {
            pos = 0;
        }
        return key.get(pos++);
    }
}
