package cn.free.spring.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    public void insert() {
        Arrays.asList("b", "c").forEach(bar -> {
            jdbcTemplate.update("INSERT INTO t_foo(bar) VALUES (?)", bar);
        });

        // 获取插入主键id
        Map<String, String> row = new HashMap<>();
        row.put("bar", "d");

        Number id = simpleJdbcInsert.executeAndReturnKey(row);
        log.info("id of d is {}", id.longValue());
    }

    public void listData() {
        List<String> list = jdbcTemplate.queryForList("SELECT bar FROM t_foo", String.class);
        list.forEach(l -> log.info("BAR {}", l));

        List<Foo> list2 = jdbcTemplate.query("SELECT * FROM t_foo", new RowMapper<Foo>() {
            @Override
            public Foo mapRow(ResultSet resultSet, int i) throws SQLException {
                return Foo.builder()
                        .id(resultSet.getLong(1))
                        .bar(resultSet.getString(2))
                        .build();
            }
        });

        list2.forEach(l -> log.info("FOO {}", l));

    }

}
