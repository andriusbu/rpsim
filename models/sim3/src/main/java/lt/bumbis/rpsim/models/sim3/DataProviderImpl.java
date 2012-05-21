package lt.bumbis.rpsim.models.sim3;

import java.util.HashMap;
import java.util.Map;

import lt.bumbis.rpsim.core.IDataProvider;

public class DataProviderImpl implements IDataProvider {

	public void init() {		
	}
	
	public Map<String, Object> getData() {
		Borrower borrower = new Borrower();
		String num = Long.toString(getRandomNum(1, 100));
		borrower.setFirstName("FistName" + num);
		borrower.setLasName("LastName" + num);
		borrower.setAge(getRandomAge());
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("borrower", borrower);
		return data;
	}
	
	private long getRandomNum(long from, long to) {
		return 1;
	}
	
	private int getRandomAge() {
		return 16;
	}
}
