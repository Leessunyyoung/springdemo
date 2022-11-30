package part01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * 의존성 주입(Dependency Injection)
 * DI는 컨테이너를 통해 서로 강하게 결합되어 있는 두 클래스를 분리하고 두 객체간의 관계를
 * 결정해 줌으로써 결합도를 낮추고 유연성 확보를 하기 위해서다.
 */

public class SpringTest {

	public static void main(String[] args) {
//		MessageBean bean = null;
//		bean = new MessageBeanKorea();
//		display(bean,"스프링");
		
		//ApplicationContext : Bean의 라이프 사이클을 관리해주는 컨테이너이다.
		String path = "part01/di.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(path);
		MessageBean bean = (MessageBean)context.getBean("mb");//mb=di.xml아이디
		display(bean,"Spring");
		
		
	}//end main()
	public static void display(MessageBean bean,String subject) {

		bean.sayHello(subject); //결합도가 낮다
	
	}//end main()
}//end class
