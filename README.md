# spring-cloud-function

### Technologies used
* Java 17
* Spring Boot 3.3.2
* AWS Lambda

####  Maven Dependency for Spring Cloud Function
```
 <dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-function-web</artifactId>
 </dependency>
```

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
```
curl -H "Content-Type: text/plain" localhost:8080/create -d "EmailSubscription"
curl -H "Content-Type: text/plain" localhost:8080/create -d "MobileSubscription"
curl -H "Content-Type: text/plain" localhost:8080/create -d "ManualSubscription"

```

* Get a particular subscription

```
curl -H "Content-Type: text/plain" localhost:8080/getSubs -d "EmailSubscription"
{"id":1,"name":"EmailSubscription"}
```

* Delete subscription
```
curl -H "Content-Type: text/plain" localhost:8080/clear  -d "ManualSubscription"
```
* List all subscriptions

```
curl -H "Content-Type: text/plain" localhost:8080/findAll                       
[{"id":1,"name":"EmailSubscription"},{"id":2,"name":"MobileSubscription"}]%
```

## Adding AWS Adapter dependency

```
<dependency>
     <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-function-adapter-aws</artifactId>
 </dependency>
```

## Deploying on AWS Lambda
Please follow the standard deployment steps as outlined [here](https://tanzu.vmware.com/developer/guides/serverless-spring/) 

Important point to note is the **_Function URL_** which is used to access the Function from any client.

## Testing the Function on AWS Lambda using Postman
We need to pass the corresponding function name in the **_request header_** as shown below: 
```
curl --location 'https://qu3u7kpwtd2jcvoypedenjjyoi0bfcnb.lambda-url.us-east-1.on.aws/' \
--header 'spring.cloud.function.definition: create' \
--header 'Content-Type: text/plain' \
--data 'sampleSubcription-A'
```
Please take a look at the Postman collection embedded as part of this repository.

## References
* [Spring docs - Spring Cloud Function ](https://docs.spring.io/spring-cloud-function/docs/current/reference/html/aws.html#_getting_started)
* [VMware developer guide](https://tanzu.vmware.com/developer/guides/serverless-spring/)