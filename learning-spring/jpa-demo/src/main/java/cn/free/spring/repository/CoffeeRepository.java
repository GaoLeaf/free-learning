package cn.free.spring.repository;

import cn.free.spring.model.Coffee;
import org.springframework.data.repository.CrudRepository;

/**
 * @author gaowenjin
 * @date 2020/11/10
 * @description:
 */
public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
}
