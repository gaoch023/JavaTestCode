package OverloadPriority;

import java.io.Serializable;

/**
 * 重载方法匹配优先级
 * @author Administrator
 *
 */
public class OverloadPriority {
	public static void sayHello(Object arg){
		System.out.println("hello Object");
	}
	
/*	public static void sayHello(int arg){
		System.out.println("2");
		System.out.println("hello int");
	} */
	
	/*	public static void sayHello(long arg){
		System.out.println("3");
		System.out.println("hello long");
	}*/
	
	/*	public static void sayHello(Character arg){
		System.out.println("4");
		System.out.println("hello Character");
	}*/
	
/*	public static void sayHello(char arg){
        System.out.println("1");
		System.out.println("hello char");
	} */
	
	public static void sayHello(Serializable arg){
		System.out.println("hello Serializable");
	}
	
	public static void main(String[] args){
		sayHello('a');
	}
}
