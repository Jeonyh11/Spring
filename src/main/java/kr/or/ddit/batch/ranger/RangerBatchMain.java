package kr.or.ddit.batch.ranger;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RangerBatchMain {

	public static void main(String[] args) {
		
		
		//spring container == IOC container ==> container ==> container
		ApplicationContext  context =
		new ClassPathXmlApplicationContext(
										"classpath:/kr/or/ddit/config/spring/batch-context.xml");
		
		//job 角青 : jobLauncher, job
		
		//规过1
		//JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		
		//规过2
		JobLauncher jobLauncher = context.getBean("jobLauncher", JobLauncher.class);
		
		//import org.springframework.batch.core.Job; 狼 Job 努贰胶 
		Job job= context.getBean("rangersJob", Job.class);
		
		try {
			jobLauncher.run(job, new JobParameters());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
