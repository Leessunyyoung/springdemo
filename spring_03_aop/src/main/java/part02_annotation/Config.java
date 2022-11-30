package part02_annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//자바소스를 이용하여 환경설정
@Configuration
@EnableAspectJAutoProxy //->aop.xml 	<aop:aspectj-autoproxy>

public class Config {
	
	public Config() {
		
	}
	
	@Bean
	public ServiceImp svc() { //svc->bean 이름
		return new ServiceImp();
	}
	@Bean
	public AdviceCommon common() {
		return new AdviceCommon();
	}
}
