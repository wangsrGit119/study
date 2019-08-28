package cn.wangsr.config;



import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
		String method = request.getMethod();
		StringBuffer paramsValue = new StringBuffer();
		Object paramsName=null;
		if (HttpMethod.GET.toString().equals(method)) {// get请求
			String queryString = request.getQueryString();
			if (StringUtils.isNotBlank(queryString)) {
				paramsName= JSON.parseObject(JSON.toJSONString(joinPoint.getSignature())).get("parameterNames");
				paramsValue.append( URLDecoder.decode(queryString,"UTF-8"));
			}
		} else {//其他请求
			Object[] paramsArray = joinPoint.getArgs();
			paramsName= JSON.parseObject(JSON.toJSONString(joinPoint.getSignature())).get("parameterNames");
			for (Object o :paramsArray){
				paramsValue.append(o+" ");
			}
		}
		logger.info("URLParamName  : " + paramsName);
		logger.info("URLParamValue  : " + paramsValue);
		logger.info("URL:  {}, HTTP_METHOD:  {}, IP:  {}, Method:  {} ",request.getRequestURL().toString(),request.getMethod(), request.getRemoteAddr(),joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());

	}


	/**
	 * 处理返回结果
	 * @param result
	 */
	@AfterReturning(returning = "result",pointcut = "myPointCut01()")
	  public void getResult(Object result){
			System.out.println("Result:"+result);
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
