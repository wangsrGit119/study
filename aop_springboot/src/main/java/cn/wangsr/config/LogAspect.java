package cn.wangsr.config;



import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Component
@Aspect
public class LogAspect {

	/**
	 * 切点：LoginController.withLog
	 */
	@Pointcut("execution(* cn.wangsr.controller.LoginController.withLog(..))")
	public void myPointCut01() {
		
	}
	
	/**
	 * 切点：LoginController.withLog2
	 */
	@Pointcut("execution(* cn.wangsr.controller.LoginController.withLog2(..))")
	public void myPointCut02() {
		
	}
	
	/**
	 * 切点：LoginController
	 */
	@Pointcut("execution(* cn.wangsr.controller.LoginController.*(..))")
	public void myPointCut03() {
		
	}
	
	
	/**
	 * 前置通知
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("myPointCut01()")
	 public void deBefore(JoinPoint joinPoint) throws Throwable {  
	        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
	        HttpServletRequest request = attributes.getRequest();  
	        System.out.println("URL : " + request.getRequestURL().toString());  
	        System.out.println("HTTP_METHOD : " + request.getMethod());  
	        System.out.println("IP : " + request.getRemoteAddr());  
	  }  
	
	/**
	 * 后置异常通知（适用于全局异常处理）
	 * @param joinPoint
	 */
	@AfterThrowing("myPointCut02()")
	public void throwMyHandler(JoinPoint joinPoint) {
		
		System.out.println("全局异常处理"+"class_method::"+joinPoint.getSignature().getDeclaringTypeName()+"::"+joinPoint.getSignature().getName());
	}
	
	/**
	 * 环绕通知
	 * @param pJoinPoint
	 * @throws Throwable 
	 */
	@Around("myPointCut03()")
	public  Object aroundForAll(ProceedingJoinPoint pJoinPoint) throws Throwable {
		Random random=new Random();
		boolean b=random.nextBoolean();
		System.err.println("环绕通知："+pJoinPoint.getSignature().getName());
		
		
		if(b) {
			System.out.println("认证成功调用方法");
			return pJoinPoint.proceed();//认证通过调用方法
		}else {
			System.out.println("认证失败不进行调用方法");
			return "认证失败";
		}
		
		
		
	
		
	}
	
	
	
	
}
