# spring-cloud-function

### Technologies used
* Java 17
* Spring Boot 3.3.2
* AWS Lambda

Below are the Functions supported in this application:

* Adding a subscription
* Get a particular subscription
* List all subscriptions
* Delete a subscription

```
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
 ``` 

### Local Testing
* Add subscription

<img src="https://github.com/sakthiece08/spring-cloud-function/blob/master/src/main/resources/images/Add-subscription.png">

* Get a particular subscription

<img src="https://github.com/sakthiece08/spring-cloud-function/blob/master/src/main/resources/images/Get-subscription.png">

* List all subscriptions

<img src="https://github.com/sakthiece08/spring-cloud-function/blob/master/src/main/resources/images/ListAll.png">

* Delete subscription

<img src="https://github.com/sakthiece08/spring-cloud-function/blob/master/src/main/resources/images/Delete-subscription.png">


