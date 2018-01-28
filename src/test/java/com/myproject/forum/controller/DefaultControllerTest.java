package com.myproject.forum.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultControllerTest {

    @Autowired
    private DefaultController defaultControllerTest;

    @Test
    public void testDefaultController_isNotNull() throws Exception {
        assertThat(defaultControllerTest).isNotNull();
    }


}
