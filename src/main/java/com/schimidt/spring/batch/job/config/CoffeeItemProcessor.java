package com.schimidt.spring.batch.job.config;

import com.schimidt.spring.batch.domain.Coffee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class CoffeeItemProcessor implements ItemProcessor<Coffee, Coffee> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoffeeItemProcessor.class);

    @Override
    public Coffee process(final Coffee coffee) throws Exception {
        String brand = coffee.brand().toUpperCase();
        String origin = coffee.origin().toUpperCase();
        String characteristics = coffee.characteristics().toUpperCase();

        Coffee transformedCoffee = new Coffee(brand, origin, characteristics);
        LOGGER.info("Converting ( {} ) into ( {} )", coffee, transformedCoffee);

        return transformedCoffee;
    }
}
