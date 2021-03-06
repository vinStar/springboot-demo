## springboot-demo
best practice and some xx examples

## 0x0 swagger

- multi group
- multi project

## 0x1 learn webpack

- static publish folder
- react package

## 0x2 Bean

- definition
- three ways : execute some method before bean init or bean destroy
           1.interface √ 2. annotation √ 3. xml
  
## 0x3 Thumbnailator

- Thumbnailator，一个google使用的开源的图片 工具类(图片压缩、旋转，裁切，加水印
- getFile util , recursive the specified folders and get file path

## 0x4 enum

- enum examples and best practices

## 0x5 concurrent

examples,best practices

- countdownlatch
  - await
  - countdown)
- semaphore
  - acquire
  - release 

## 0x6 okhttp3 
- use okhttp3 insteadof httpclient
- static inner class

## 0x7 performance test

- gc
  - jstat -gcutil pid xx_ms
  
- VM options  
  - -Xmx Runtime.getRuntime().maxMemory() / 1024 / 1024
  - -Xms Runtime.getRuntime().totalMemory() / 1024 / 1024
  - -Xss256k start more thread
  - -XX:+HeapDumpOnOutOfMemoryError
  - -XX:+UseParallelGC
  - -XX:+UseParallelOldGC
  - -XX:ParallelGCThreads=8
  
- jconsole monitor, remote connect  
  - -Dcom.sun.management.jmxremote.port=9779
  - -Dcom.sun.management.jmxremote.authenticate=false
  - -Dcom.sun.management.jmxremote.ssl=false
  - -Djava.rmi.server.hostname=127.0.0.1

- jvm 
  - Y:O = 1:2
  - EDEN:S0:S1 8:1:1 
  - ygc ,fgc
  - string test, a++ ,++a ; jvm 入栈（药柜），b++ 药柜内执行，非 Rload
  
## 0x8 logback

- logback
  - scan = true , auto load configuration
  - jmxConfigurator , jconsole monitor
  - STOUT, log file policy ,todo self filter on prod didn't out the console log
  - logback MDC , specified log by discriminator and test case  
  
## 0x9 redisson

- redis , redisson
  - config single,cluster,sentinel
  - distributed lock and test case  safe , unsafe
  - set(RBucket rBucket = redisson.getBucket("key");) 
  - map(RMap<String, Integer> map = redisson.getMap("myMap");)
  - incr(RAtomicDouble rAtomicDouble = redisson.getAtomicDouble("myIncr");)
  
## 0xa junit 

- junit
  - @RunWith(SpringRunner.class)
  - when springboot and mvc   
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
  - interface autowired

- todo Spring boot + **JUnit5** 
 

## 0xb lombok

- lombok
  - @Getter , @Setter , @Slf4j
  - Assert , Assert.assertEquals(null, rBucket.get()); 
  
  
## 0xc Create my own spring boot starter
  
- modules
  - base function
  - autoconfigure
  - starter

 - aop proxy
   - JDK  
     - dependence org.aspectj or  spring-boot-starter-aop
     - configure 
       - spring.aop.proxy-target-class=false (default)
       - my.aop.enable=false 
       - @ConditionalOnProperty(name = "my.aop.enable", havingValue = "false")
       - @Aspect
       - @Configuration
       - @Around("@annotation(LogExecutionTime)")
   - CGLIB
     - non-dependence but need to implements BeanPostProcessor
     - configure
       - spring.aop.proxy-target-class=true
       - @ConditionalOnClass({Enhancer.class})
       - @ConditionalOnProperty(name = "my.aop.enable", havingValue = "true")
       - @ConditionalOnMissingBean
     
## 0xd 2019-09-24  
  1. es   (curd)  
  2. one-time password  
  3. rocketmq  
  4. feign read time out  
  5. ribbon retry  
  6. graceful shutdown  
  7. SnowIdWorker
  8. des
  9. JavaStackTest 栈深度测试，递归深度，defalut stack height:32593 ； -Xss100m = 4429156   
  10. String.join  逗号分隔
  11. mycat-demo-parent ， mycat 分表 demo  
  12. demo-check-request-parameters 入参检验，全局异常，自定义异常， 注解校验 javax.validation.constraints.NotBlank;   
  13. easyexcel-encapsulation 便捷导出 excel

## 0xe es  

  1. es , match query \ bool  query (multi fields , range , page , sort )   
  2. java call python script  