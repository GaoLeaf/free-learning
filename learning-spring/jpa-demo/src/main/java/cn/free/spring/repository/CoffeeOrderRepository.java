package cn.free.spring.repository;

import cn.free.spring.model.CoffeeOrder;

import java.util.List;

/**
 * @author gaowenjin
 * @date 2020/11/10
 * @description:
 */
public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {

    /**
     * 查询顾客已下订单（根据id排序）
     * @param customer 顾客姓名
     * @return
     */
    List<CoffeeOrder> findByCustomerOrderById(String customer);

    /**
     * 查询咖啡已下订单
     * @param name 咖啡名称
     * @return
     */
    List<CoffeeOrder> findByItems_Name(String name);

}
