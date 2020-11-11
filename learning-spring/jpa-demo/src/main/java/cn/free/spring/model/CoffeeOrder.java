package cn.free.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;

/**
 * @author gaowenjin
 * @date 2020/11/10
 * @description:
 */
@Entity
@Table(name = "T_ORDER")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeOrder extends BaseEntity<Long> {

    private String customer;

    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    @OrderBy("id")
    private List<Coffee> items;

    @Column(nullable = false)
    private  OrderState state;
}
