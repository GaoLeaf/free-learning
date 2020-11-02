package cn.free.spring.jdbc;

import lombok.Builder;
import lombok.Data;

/**
 * @author gaowenjin
 * @date 2020/11/2
 * @description:
 */
@Data
@Builder
public class Foo {

    private Long id;
    private String bar;

}
