

1 MongoProperties 类 
2.1 MongoAutoConfiguration 类
2.2 src/main/resources/META-INF 目录中添加一个 spring.factories 添加 org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.baeldung.greeter.autoconfigure.GreeterAutoConfiguration
  

 @Configuration
 @ConditionalOnClass(Greeter.class)
 @EnableConfigurationProperties(GreeterProperties.class)
 public class GreeterAutoConfiguration {

 @Autowired
 private GreeterProperties greeterProperties;

 @Bean
 @ConditionalOnMissingBean
 public GreetingConfig greeterConfig() {

 String userName = greeterProperties.getUserName() == null
 ? System.getProperty("user.name")
 : greeterProperties.getUserName();

 GreetingConfig greetingConfig = new GreetingConfig();
 greetingConfig.put(USER_NAME, userName);

 return greetingConfig;
 }

 @Bean
 @ConditionalOnMissingBean
 public Greeter greeter(GreetingConfig greetingConfig) {
 return new Greeter(greetingConfig);
 }
 }

