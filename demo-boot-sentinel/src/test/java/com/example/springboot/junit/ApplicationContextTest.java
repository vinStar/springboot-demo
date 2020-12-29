//package com.example.springboot.junit;
//
//import com.example.springboot.config.ApplicationContextProvider;
//import lombok.extern.slf4j.Slf4j;
//import lombok.val;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ApplicationContext;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Arrays;
//import java.util.IntSummaryStatistics;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//import java.util.stream.Stream;
//import java.util.stream.StreamSupport;
//
//
///**
// * Created by vin on 2019/4/24.
// */
//@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class ApplicationContextTest {
//
//	@Autowired
//	ApplicationContextProvider applicationContextProvider;
//
//	@Test
//	public void getBeans() {
//		val appContext = applicationContextProvider.getApplicationContext();
//		log.info("beans count {} ", appContext.getBeanDefinitionCount());
//		Arrays.stream(appContext.getBeanDefinitionNames())
//				//.parallel()
//				.forEach(x -> log.info(x));
//
//
//		IntStream.range(1, 34).filter(x -> x > 4).forEach(System.out::println);
//		System.out.println("=== iterate ===");
//		Stream.iterate(1, x -> x + 1).limit(10).forEach(System.out::println);
//
//		List<Integer> list = Arrays.asList(23, 43, 12, 25);
//		IntSummaryStatistics stats = list.stream()
//				.collect(Collectors.summarizingInt(i -> i + i));
//		System.out.println("Sum:" + stats.getSum());
//
//		Item item = new Item("AA");
//		Stream<String> stream = Stream.generate(item::getName).limit(100);
//		stream.forEach(s -> System.out.println(s));
//
//
//	}
//
//	public class Item {
//		private String name;
//
//		public Item(String name) {
//			this.name = name;
//		}
//
//		public String getName() {
//			return name;
//		}
//	}
//
//}
