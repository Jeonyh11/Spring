package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:/kr/or/ddit/ioc/ioc.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CollectionBeanTest {
	
	//collenctionBean 스프링빈이 정상적으로 생성 되었는지

	@Resource(name="collectionBean")
	private CollectionBean collectionBean;
	
	@Test
	public void collectionTest() {
		assertNotNull(collectionBean);
		assertNotNull(collectionBean.getList().size());
		assertEquals(3, collectionBean.getList().size());
		assertEquals("sally",collectionBean.getList().get(1));
		assertEquals("브라운",collectionBean.getMap().get("usernm"));
	}

}
