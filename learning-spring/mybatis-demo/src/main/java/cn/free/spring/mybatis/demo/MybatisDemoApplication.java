package cn.free.spring.mybatis.demo;

import cn.free.spring.mybatis.demo.mapper.CoffeeMapper;
import cn.free.spring.mybatis.demo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gaowenjin
 * @date 2020/11/12
 * @description:
 */
@SpringBootApplication
@Slf4j
@MapperScan("cn.free.spring.mybatis.demo.mapper")
public class MybatisDemoApplication implements ApplicationRunner {

    @Autowired
    private CoffeeMapper coffeeMapper;

    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Coffee coffee = Coffee.builder().name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        int count = coffeeMapper.save(coffee);
        log.info("save {} coffee {}", count, coffee);

        coffee = Coffee.builder().name("lattee")
                .price(Money.of(CurrencyUnit.of("CNY"), 25.0))
                .build();
        count = coffeeMapper.save(coffee);
        log.info("save {} coffee {}", count, coffee);

        coffee = coffeeMapper.findById(coffee.getId());
        log.info("Find coffee {}", coffee);

    }
}
