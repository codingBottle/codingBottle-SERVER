package com.codingbottle.dbendpagination.global.util;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class InitDB {

    @Transactional
    @PostConstruct
    public void init() {

    }
}
