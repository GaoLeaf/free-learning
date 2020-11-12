package cn.free.spring.mybatis.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.util.Date;

/**
 * @author gaowenjin
 * @date 2020/11/12
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {

    private Long id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;
}
