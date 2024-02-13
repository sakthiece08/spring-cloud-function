package com.teqmonic.serverless.function;

import com.teqmonic.serverless.model.Subscription;
import com.teqmonic.serverless.service.SubscriptionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.print.DocFlavor;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class SubscriptionFunction {

    private final SubscriptionService service;

    public SubscriptionFunction(SubscriptionService service) {
        this.service = service;
    }

    @Bean
    public Supplier<List<Subscription>> findAll() {
        return service::getSubscriptionList; // () -> service.getSubscriptionList();
    }

    @Bean
    public Consumer<String> create() {
        return service::addSubscription; // (name, email) -> service.addSubscription(name);
    }

    @Bean
    public Function<String, Optional<Subscription>> getSubs() {
        return service::getSubscription;
    }

    @Bean
    public Consumer<String> clear() {
        return service::deleteSubscription; // name -> service.deleteSubscription(name)
    }

}
