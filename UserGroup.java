package A2;

import java.util.ArrayList;
import java.util.List;

public class UserGroup implements Users {
	private String groupID;
	private List<Users>groups;
	private long creationTime;
	public UserGroup(String id){
		groupID=id;
		groups=new ArrayList<Users>();
		creationTime = System.currentTimeMillis();
	}
	
	public long getCreationTime(){
		return creationTime;
	}

	@Override
	public String getId() {
		return groupID;
	}

	@Override
	public List getList(int WhichList) {
		return groups;
	}
//add user to this group
	@Override
	public void attach(Users user) {
		groups.add(user);
	}

	@Override
	public void update(Message m) {

	}
	
	public String toString(){
		return  groupID;
	}

	

}
