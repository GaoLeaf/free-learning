package cn.free.spring.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

/**
 * @author gaowenjin
 * @date 2020/11/2
 * @description:
 */
@SpringBootApplication
@Slf4j
public class JdbcDemoApplication implements CommandLineRunner {

    @Autowired
    private FooDao fooDao;

    @Autowired
    private BatchFooDao batchFooDao;

    public static void main(String[] args) {
        SpringApplication.run(JdbcDemoApplication.class, args);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public SimpleJdbcInsert simpleJdbcInsert(JdbcTemplate jdbcTemplate) {
        return new SimpleJdbcInsert(jdbcTemplate).withTableName("t_foo").usingGeneratedKeyColumns("ID"); // 指定表主键
    }

    @Override
    public void run(String... args) throws Exception {

        fooDao.insert();
        batchFooDao.batchInsert();
        fooDao.listData();

    }
}
