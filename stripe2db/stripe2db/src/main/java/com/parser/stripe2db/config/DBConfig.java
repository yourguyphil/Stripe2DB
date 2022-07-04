package com.parser.stripe2db.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DBConfig {
    @Getter
    private String connectionString;

    public DBConfig (@Value("${sql.connection}") String connectionString){
        this.connectionString = connectionString;
        log.debug("Using Connection String: " + connectionString);
    }
}
