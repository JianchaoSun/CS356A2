package A2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwitterService implements Visitable {
/*This class serve as the root group that include all the group
 * and users. it uses singleton pattern since there can be only one root group
 * also use composite pattern, can add both user and group to same collection,
 * add both into usergroup map
 */
	private static TwitterService instance;
	private static Map<String,Users>userGroup;
	private static List<User> userList; //use to count the amount of user
	private static List<UserGroup>groupList; //count amount of group
	private boolean isInvalid = false;
	public static TwitterService getInstance(){
		if(instance ==null){
			instance =new TwitterService();
		}
		return instance;
	}
	
	private TwitterService(){
		userGroup=new HashMap<String,Users>();
		userList = new ArrayList<User>(); 
		groupList = new ArrayList<UserGroup>();
	}
	public void addUser(Users user){
		if(userGroup.containsKey(user.getId())){
			isInvalid = true;
		}
		if(user.getId().contains(" ")){
			isInvalid = true;
		}
		userGroup.put(user.getId(),user);
		if(user instanceof User){
			userList.add((User) user);
		}else{
			groupList.add((UserGroup) user);
		}
	}
	
	public boolean hasSameGroup(String str){
		return userGroup.containsKey(str);
	}
	
	public Users searchUser(String id){
		return userGroup.get(id);
		
	}
	
	public List<User> getUserList(){
		return userList;
	}
	
	public List<UserGroup> getGroupList(){
		return groupList;
	}
	
	public Map getMap(){
		return userGroup;
	}
	@Override
	public void accept(Visitor visitor) {
		visitor.visitGroupTotal(this);
		visitor.visitUserTotal(this);
	}
	//check if contain invalid id
	public boolean isInvalid(){
		return isInvalid;
	}
	

}
