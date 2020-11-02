package cn.free.spring.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author gaowenjin
 * @date 2020/11/2
 * @description:
 */
@Repository
@Slf4j
public class FooDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert() {
        Arrays.asList("b", "c").forEach(bar -> {
            jdbcTemplate.update("INSERT INTO t_foo(bar) VALUES (?)", bar);
        });
    }

    public void listData() {
        List<String> list = jdbcTemplate.queryForList("SELECT bar FROM t_foo", String.class);
        list.forEach(l -> log.info("BAR {}", l));

        List<Foo> list2 = jdbcTemplate.query("SELECT * FROM t_foo", new RowMapper<Foo>() {
            @Override
            public Foo mapRow(ResultSet resultSet, int i) throws SQLException {
                return Foo.builder().id(resultSet.getLong(1)).bar(resultSet.getString(2)).build();
            }
        });

        list2.forEach(l -> log.info("FOO {}", l));

    }

}
