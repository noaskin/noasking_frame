package com.noasking.thrift.rpc.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

//服务端启动
public class Server {

	public static void main(String[] args) {
		try {
			System.out.println("aa");
			new ClassPathXmlApplicationContext("classpath:spring-context-thrift-server.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
