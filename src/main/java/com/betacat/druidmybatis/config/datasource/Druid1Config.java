package com.betacat.druidmybatis.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = Druid1Config.MAPPER_PACKAGE, sqlSessionFactoryRef = Druid1Config.SESSION_FACTORY)
public class Druid1Config {

    static final String SESSION_FACTORY = "druid1SessionFactory";
    private static final String DATASOURCE_NAME = "druid1";

    //mapper类的包路径
    static final String MAPPER_PACKAGE = "com.betacat.druidmybatis.mapper.druid1";
    //自定义mapper的xml文件路径
    private static final String MAPPER_XML_PATH = "classpath*:mapper/druid1/*.xml";
    //数据源配置的前缀，必须与application.yml中配置的对应数据源的前缀一致
    private static final String DATASOURCE1_PREFIX = "spring.datasource.druid1";

    @Bean(name = DATASOURCE_NAME)
    @ConfigurationProperties(DATASOURCE1_PREFIX)
    public DataSource druid1(){
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory() {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(druid1());
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            //自定义mapper的xml文件地址，当通用mapper提供的默认功能无法满足我们的需求时，可以自己添加实现，与mybatis写mapper一样
            sessionFactoryBean.setMapperLocations(resolver.getResources(MAPPER_XML_PATH));
            return sessionFactoryBean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
