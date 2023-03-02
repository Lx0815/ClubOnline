package com.sgqn.clubonline.dao.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserClubRoleMidMapperTest {

    @Autowired
    private UserClubRoleMidMapper userClubRoleMidMapper;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void insertByUserIdAndRoleId() {
        System.out.println(userClubRoleMidMapper.insertByUserIdAndRoleId(1, 1));
    }
}