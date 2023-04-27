package com.movies.accesskeyservice.service;

import com.movies.accesskeyservice.config.PropertyConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Service
@Slf4j
public class AccessKeyService {
    private final List<String> key;
    private final int[] requestCounter;
    private int pos = 0;

    public AccessKeyService(PropertyConfig config) {
        this.key = new ArrayList<>(config.getKeys());
        this.requestCounter = new int[this.key.size()];
        Arrays.fill(this.requestCounter, 500);
    }

    public String next() {
        checkArrayOutBounds();
        checkNumberOfRequest();
        log.info("Total number of request: {}", checkTotalNumberOfRequest());
        log.info("Requested access key: {}, containing request count = {}", key.get(pos), requestCounter[pos]);
        requestCounter[pos]--;
        return key.get(pos++);
    }

    private void checkArrayOutBounds() {
        if (pos > key.size() - 1) {
            pos = 0;
        }
    }

    private void checkNumberOfRequest() {
        if (requestCounter[pos] == 0) {
            pos++;
        }
    }

    private int checkTotalNumberOfRequest() {
       return IntStream.of(requestCounter).sum();
    }
}
