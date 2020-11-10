package cn.free.spring;

import cn.free.spring.model.Coffee;
import cn.free.spring.model.CoffeeOrder;
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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author gaowenjin
 * @date 2020/11/10
 * @description:
 */
@SpringBootApplication
@EnableJpaRepositories
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
    public void run(ApplicationArguments args) throws Exception {
        initOrders();
    }

    private void initOrders() {

        Coffee espresso = Coffee.builder().name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .build();

        coffeeRepository.save(espresso);
        log.info("Coffee：{}", espresso);

        Coffee latte = Coffee.builder().name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .build();

        coffeeRepository.save(latte);
        log.info("Coffee：{}", latte);

        CoffeeOrder coffeeOrder = CoffeeOrder.builder()
                .customer("Li Li")
                .items(Collections.singletonList(espresso))
                .state(0)
                .build();

        coffeeOrderRepository.save(coffeeOrder);
        log.info("Order: {}", coffeeOrder);

        coffeeOrder = CoffeeOrder.builder()
                .customer("Li Li")
                .items(Arrays.asList(espresso, latte))
                .state(0)
                .build();

        coffeeOrderRepository.save(coffeeOrder);
        log.info("Order：{}", coffeeOrder);
        log.info("Order count: {}", coffeeOrderRepository.count());


    }

}
