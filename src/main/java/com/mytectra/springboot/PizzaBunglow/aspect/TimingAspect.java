package com.mytectra.springboot.PizzaBunglow.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.PizzaBunglow.model.AddOns;

@Aspect
@Component
public class TimingAspect {
	//
	//@Pointcut("execution(* com.mytectra.springboot.PizzaBunglow.Dao.*.*(..))")
	
	/*@Pointcut("this(com.mytectra.springboot.PizzaBunglow.Dao.AddOnsDao")
	public void implementingAdonsDao() {
	}
	
	@Pointcut("within(com.mytectra.springboot.PizzaBunglow.Dao..*)")
	public void allUnderDaoPackage() {
	}
*/
	/*@Pointcut("execution(* com.mytectra.springboot.PizzaBunglow.Dao.*.*(*)) && args(id)")
	public void getIdsPC(int id) {
	}
	
	@Before("getIdsPC(id)")
	public void getInput(int id ) throws Throwable {
		System.out.println("Arg recived inside aspect- "+ id);
		
	}*/
	
	/*@Around("@annotation(com.mytectra.springboot.PizzaBunglow.aspect.Timer)")
	public Object evaluateTime(ProceedingJoinPoint jp ) throws Throwable {
		//System.out.println("Arg recived - "+ id);
		long t1 = System.currentTimeMillis();
		Object result = jp.proceed();
		long t2 = System.currentTimeMillis();
		System.out.println( "Time taken to excute " + jp + "  = "  + ((t2 - t1)/1000) + "secs");
		return result;
	}*/
	
	/*@Pointcut("execution(void com.mytectra.springboot.PizzaBunglow.Dao.AddOnsDao.saveAddOns(..))")
	public void trimingAddonsNameAdonsDao() {
	}*/
	
	/*@Pointcut("target(com.mytectra.springboot.PizzaBunglow.Dao.AddOnsDao)")
	public void trimingAddonsNameAdonsDao() {
	}*/
	
	@Pointcut("within(com.mytectra.springboot.PizzaBunglow.Dao.AddOnsDao)")
	public void trimingAddonsNameAdonsDao() {
	}
    
	@Before("trimingAddonsNameAdonsDao() && args(addons)")
	public void getInput(AddOns addons) throws Throwable {
		System.out.println("Name of addons- "+ addons.getName());
		addons.setName(addons.getName().trim()+"Sachin");
		
	}

}

