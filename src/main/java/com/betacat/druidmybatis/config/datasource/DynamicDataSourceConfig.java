package com.betacat.druidmybatis.config.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//多数据源配置类
@Configuration
@Component
public class DynamicDataSourceConfig {

    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("druid1") DataSource druid1, @Qualifier("druid2") DataSource druid2) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("druid1",druid1);
        targetDataSources.put("druid2", druid2);
        return new DynamicDataSource(druid1, targetDataSources);
    }
}
