package A2;

import javax.swing.JFrame;
import javax.swing.JPanel;

public interface Visitor {
	   public void visitUserTotal(TwitterService ts);
	   public void visitMessagesTotal(User user);
	   public void visitGroupTotal(TwitterService ts);
	   public void visitPositivePercentage(User user);
	   public void setMessageZero();
	   public void setPositiveZero();
	   public void setGroupZero();
	   public void setUserZero();
	   public int getUserTotal();
       public int getMessageTotal();
	   public String getPositivePercentage();
	   public int getGroupTotal();
	   public User visitLastUpdateTime(TwitterService ts);
}
