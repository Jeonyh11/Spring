package kr.or.ddit.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
@org.aspectj.lang.annotation.Aspect
public class Aspect {

	private static final Logger logger = LoggerFactory.getLogger(Aspect.class);

	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {}
	
	//Ư�� �޼ҵ尡 ����Ǳ� ���� ���� �Ǿ���� ������ ���ɻ���
	@Before("dummy()")
	public void beforeMethod(JoinPoint joinPoint) {
		logger.debug("aspect.beforeMethod");
	}
	
	//around : Ư�� �޼ҵ� ���� ����
	//    �޼ҵ� ������ - ���� ���ɻ���
	//    �޼ҵ� ���� ����
	//    �޼ҵ� ���� �� - ���� ���ɻ���
	@Around("dummy()")
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		 
		//�޼ҵ� �� ���� ���� ��
		long startTime = System.nanoTime();
		
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		
		Object ret = joinPoint.proceed(joinPoint.getArgs());
		
		//�޼ҵ� �� ���� ���� ��
		long endTime = System.nanoTime();
		
		logger.debug("{}  {}  : duration : {}",className, methodName, endTime-startTime);
		
		
		return ret;
	}
}
