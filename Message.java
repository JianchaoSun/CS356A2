package A2;

public class Message {
	private String message;
	public Message(String m){
		message =m;
	}
	public void setMessage(String m){
		message =m;
	}
	public String getMessage(){
		return message;
	}
	public boolean isPositive(){
		String s1 = "good";
		String s2 = "positive";
		String s3 = "happy";
		String s4 = "funny";
		String checker=message.toLowerCase();
	    if(checker.contains(s1.toLowerCase())||(checker.contains(s2.toLowerCase())||checker.contains(s3.toLowerCase()))){
	    	return true;
	    }else{
	    	return false;
	    }
	}
	public String toString(){
		return message;
	}

}
