package cn.free.spring.repository;

import cn.free.spring.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

/**
 * @author gaowenjin
 * @date 2020/11/10
 * @description:
 */
public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder, Long> {
}
