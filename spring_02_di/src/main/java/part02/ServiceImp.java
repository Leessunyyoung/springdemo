package part02;

import java.util.Random;

public class ServiceImp implements Service {
	private String name;
	private int age;
	private Random ran;
	
	public ServiceImp() {

	}
	public ServiceImp(String name) {
		this.name=name;

	}
	public ServiceImp(String name,int age) {
		this.name=name;
		this.age = age;
	}
	
	public ServiceImp(Random ran) {
		this.ran = ran;
	}
	
	@Override
	public void prn1() {
		System.out.printf("name:%s \n",name);
		
	}
	
	public void prn2() {
		System.out.printf("name:%s, age=%d \n",name,age);
		}
	
	public void prn3() {
		System.out.printf("ran:%.2f\n",ran.nextDouble());
	}
}
