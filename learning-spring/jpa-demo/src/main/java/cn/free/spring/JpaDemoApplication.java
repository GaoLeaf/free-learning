package cn.free.spring;

import cn.free.spring.model.Coffee;
import cn.free.spring.model.CoffeeOrder;
import cn.free.spring.model.OrderState;
import cn.free.spring.repository.CoffeeOrderRepository;
import cn.free.spring.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gaowenjin
 * @date 2020/11/10
 * @description:
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
public class JpaDemoApplication implements ApplicationRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        initOrders();
        findOrders();
    }

    // 插入数据
    private void initOrders() {

        // 新增咖啡 espresso
        Coffee espresso = Coffee.builder().name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .build();

        coffeeRepository.save(espresso);
        log.info("Coffee：{}", espresso);

        // 新增咖啡 latte
        Coffee latte = Coffee.builder().name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .build();

        coffeeRepository.save(latte);
        log.info("Coffee：{}", latte);

        // 顾客Li Li 下单
        CoffeeOrder coffeeOrder = CoffeeOrder.builder()
                .customer("Li Li")
                .items(Collections.singletonList(espresso))
                .state(OrderState.INIT)
                .build();

        coffeeOrderRepository.save(coffeeOrder);
        log.info("Order: {}", coffeeOrder);

        // 顾客Li Li 再下一单
        coffeeOrder = CoffeeOrder.builder()
                .customer("Li Li")
                .items(Arrays.asList(espresso, latte))
                .state(OrderState.INIT)
                .build();

        coffeeOrderRepository.save(coffeeOrder);
        log.info("Order：{}", coffeeOrder);

        // 订单总数
        log.info("Order count: {}", coffeeOrderRepository.count());

    }

    // 查找数据
    private void findOrders() {

        // 查询所有咖啡
        coffeeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .forEach(c -> log.info("Loading Coffee {}", c));

        // 查询顾客已下订单
        coffeeOrderRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "id"))
                .forEach(o -> log.info("顾客{}订单{}", o.getCustomer(), o.getId()));

        List<CoffeeOrder> orders = coffeeOrderRepository.findTop3ByOrderByUpdateTimeDescIdAsc();
        log.info("findTop3ByOrderByUpdateTimeDescIdAsc：{}", getJoinedOrderId(orders));

        // 顾客Li Li 已下订单
        orders = coffeeOrderRepository.findByCustomerOrderById("Li Li");
        log.info("findByCustomerOrderById：{}", getJoinedOrderId(orders));

        // 不开启事务会因为没Session而报LazyInitializationException
        log.info("---------------分割线 start--------------");
        orders.forEach(o -> {
            log.info("customer {} ,orderId -> {}", o.getCustomer(), o.getId());
            o.getItems().forEach(i -> log.info("item {}", i));
        });
        log.info("---------------分割线 end--------------");

        // 含有咖啡latte的订单
        orders = coffeeOrderRepository.findByItems_Name("latte");
        log.info("findByItems_Name：{}", getJoinedOrderId(orders));
    }

    private String getJoinedOrderId(List<CoffeeOrder> orders) {
        return orders.stream().map(o -> o.getId().toString()).collect(Collectors.joining(","));
    }

}
