package A2;

import java.util.List;
import java.util.Map;

public interface Users {
	public String getId();
	public List getList(int whichList);
	public void update(Message m);
	public void attach(Users user);
}
