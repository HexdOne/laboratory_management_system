package com.spring.laboratory.Config.Dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.beans.PropertyVetoException;

@Configuration
@MapperScan("com.spring.laboratory.Mapper")
public class DataSourceConfiguration {
    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Bean(name = "dataSource")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(jdbcDriver);//驱动
        dataSource.setJdbcUrl(jdbcUrl);//IP
        dataSource.setUser(jdbcUsername);//账号
        dataSource.setPassword(jdbcPassword);//密码
        //关闭连接后不自动commit
        dataSource.setAutoCommitOnClose(false);
        return dataSource;
    }
}
