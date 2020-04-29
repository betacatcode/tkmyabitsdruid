package com.betacat.druidmybatis;

import com.betacat.druidmybatis.domain.User;
import com.betacat.druidmybatis.mapper.druid1.Druid1Mapper;
import com.betacat.druidmybatis.mapper.druid2.Druid2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TestLineRunner implements CommandLineRunner {

    @Autowired
    Druid1Mapper druid1Mapper;

    @Autowired
    Druid2Mapper druid2Mapper;

    @Override
    public void run(String... args) throws Exception {
        List<User> users = druid1Mapper.selectAll();
        for (User user : users) {
            druid2Mapper.insert(user);
        }
    }
}
