package part01_xml;

import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
 * <!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.7</version>
    <scope>runtime</scope>
</dependency>
 */


/*AOP(Aspect Oriented Programming) : 관점지향프로그래밍
 * 1. AOP는 문제를 바라보는 관점을 기준으로 프로그래밍하는 기법이다.
 * 2. AOP는 문제를 해결하기 위한 핵심관심사향과 전체에 적용되는 공통관심사항을 기준으로
 * 		프로그래밍함으로써 공통 모듈을 여러 코드에 쉽게 적용할 수 있도록 해준다.
 * 
 */
public class SpringTest {

	public static void main(String[] args) {
		String path ="part01_xml/aop.xml";

		ApplicationContext context = new ClassPathXmlApplicationContext(path);

		Service  svc =null;
		svc = (Service) context.getBean("svc");
//		svc.prn1();
//		svc.prn1(10);
//		svc.prn1(new Random());
//		svc.prn1(10,20);
//		svc.prn2();
//		svc.prn3();
		
//		try {
//		svc.prn4();
//		}catch(Exception ex) {
//			System.out.println(ex.toString());
//		}
		
		svc.prn5();
		

	}//end main()

}//end class
