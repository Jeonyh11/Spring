package kr.or.ddit.batch.ranger;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import ch.qos.logback.classic.Logger;

public class RangerReader implements ItemReader<String>{
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(RangerReader.class);
	
	private List<String> rangers;
	private int index = 0;
	
	public RangerReader() {
		rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("sally");
		rangers.add("cony");
		rangers.add("moon");
		rangers.add("james");
	}
	
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		//�� �̻� ���� �����Ͱ� ���ٰ� �˷��ִ� ��� : null
		if(rangers.size() >= index) {
			
			String ranger = rangers.get(index++);
			
			logger.debug("ranger : {}", ranger);
			return 	ranger;
		}else {
			index = 0;
			return null;
		}
	}
		

}
