package A2;


public class ButtonVisitor implements Visitor{
	private int userTotal = 0;
	private int messageTotal = 0;
	private int totalPositive = 0;
	private int groupTotal = 0;

	@Override
	public void visitUserTotal(TwitterService ts) {
		userTotal += ts.getUserList().size();
	}

	@Override
	public void visitMessagesTotal(User user) {
		messageTotal +=user.getList(5).size();
	}

	@Override
	public void visitGroupTotal(TwitterService ts) {
		groupTotal += ts.getGroupList().size();
	}

	@Override
	public void visitPositivePercentage(User user) {
		totalPositive += user.getPositive();
	}
	
	public int getUserTotal() {
		return userTotal;
	}

	public int getMessageTotal() {
		return messageTotal;
	}

	public String getPositivePercentage() {
		if(totalPositive ==0 && messageTotal == 0){
			return "0";
		}
		String x="";
		double y = (totalPositive*100)/messageTotal;
		x = y+"%";
		return x;
	}
	
	public int getGroupTotal() {
		return groupTotal;
	}

	@Override
	public void setMessageZero() {
		messageTotal = 0;
	}

	@Override
	public void setPositiveZero() {
		totalPositive = 0;
	}

	@Override
	public void setGroupZero() {
		groupTotal = 0;
	}

	@Override
	public void setUserZero() {
		userTotal = 0;
	}

	
}
