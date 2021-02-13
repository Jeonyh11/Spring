package kr.or.ddit.batch.ranger;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath:/kr/or/ddit/config/spring/batch-context.xml",
						"classpath:/kr/or/ddit/config/spring/scheduler-context.xml",
						"classpath:/kr/or/ddit/config/spring/batch-test-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RangerBatchTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	
	@Test
	public void rangerTaskTest() throws Exception {
		//batch �׽�Ʈ�� job Ÿ������ ��ϵ� ������ ���� �ϳ����� �ȴ�
		//job �̸��� ������� �ʾƵ� container���� �ϳ��� ��ġ job�� ��ϵǾ� �ִٴ�
		//������ �ֱ� ������ ������ job �̸��� ������� �ʴ´�
		JobExecution execution = jobLauncherTestUtils.launchJob();
		
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
	}

}
