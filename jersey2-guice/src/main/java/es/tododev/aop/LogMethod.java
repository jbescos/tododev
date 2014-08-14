package es.tododev.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogMethod implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		System.out.println("I'm an aspect. Before: calling "+arg0.getMethod());
		Object obj = arg0.proceed();
		System.out.println("After");
		return obj;
	}
	
	@Retention(RetentionPolicy.RUNTIME) @Target(ElementType.METHOD)
	public static @interface LogIt {}

}
