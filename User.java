package A2;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class User implements Users,Visitable{
	private String ID="";
	private List<Message>newsFeed;
	private List<Users>follower;
	private List<Users>following;
	private List<Message>ownMessage;
	
	public User(String id){
		ID = id;
		follower = new ArrayList<Users>();
		following = new ArrayList<Users>();
		newsFeed = new ArrayList<Message>();
		ownMessage = new ArrayList<Message>();
	}
	
	public int getPositive(){
		int totalPositive = 0;
		for(Message m : ownMessage){
			if(m.isPositive()){
				totalPositive++;
			}
		}
		return totalPositive;
	}
	@Override
	public String getId() {
		return ID;	
	}
	
	@Override
	public List getList(int whichList) {
		if(whichList==0){
		return follower;
		}else if(whichList==1){
			return following;
		}else if(whichList==5){
			return ownMessage;
		}else{
		    return newsFeed;
		}
	}
	
/*Using observer pattern, when this user update a message
 * the follower of this user will automatically receive the message
 * when this user follow other user, other user will have him on follower list
 */
	@Override
	public void update(Message m) {
		newsFeed.add(m);
		for(Users user:follower){
			user.getList(3).add(m);
		}
		ownMessage.add(m);
	}
	
	@Override
	public void attach(Users user) {
		following.add(user);
		user.getList(0).add(this);
	}
	
	public String toString(){
		return ID;
	}
	
/*Using visitor pattern to count the message of
 * each user
 */
	@Override
	public void accept(Visitor visitor) {
		visitor.visitMessagesTotal(this);
		visitor.visitPositivePercentage(this);
	}
	
	
}
