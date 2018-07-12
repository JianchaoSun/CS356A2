package A2;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class UserViewPanel extends JFrame{
	private JPanel pan;
	private JTextField textField,tweetField;
	private JList followingList,feedList;
	private DefaultListModel<String> followingModel,feedModel;
	
	private JButton button,tweet;
	private User user;
	private TwitterService twitterService;
	public UserViewPanel(User newUser, TwitterService tService){
		JLabel name = new JLabel(newUser.getId()+" 's tweeter");
		twitterService = tService;
		user = newUser;
		this.setSize(600,600);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension dim=tk.getScreenSize();
		pan = new JPanel();
		pan.add(name);
		ListenForButton lfb  = new ListenForButton();
		textField = new JTextField("",6);
		pan.add(textField);
		button = new JButton("add User");
		button.addActionListener(lfb);
		pan.add(button);
		
		followingModel = new DefaultListModel<String>();
		followingModel.addElement("Following member");
		for (int i = 0; i < user.getList(1).size(); i++)
		{
		    followingModel.addElement(user.getList(1).get(i).toString());
		}
		followingList =new JList(followingModel);
		
		feedModel = new DefaultListModel<String>();
		feedModel.addElement("News Feed ");
		for (int i = 0; i < user.getList(3).size(); i++)
		{
		    feedModel.addElement(user.getList(3).get(i).toString());
		}
		feedList =new JList(feedModel);
		pan.add(feedList);
		
		pan.add(followingList);
		
		pan.setVisible(true);
		
		tweetField = new JTextField("", 30);
		
		pan.add(tweetField);
		
		tweet = new JButton("send Tweet");
		tweet.addActionListener(lfb);
		pan.add(tweet);
		
		
		this.add(pan);
		this.setVisible(true);
	}
	private class ListenForButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button){
				user.attach(twitterService.searchUser(textField.getText()));
				followingModel.addElement(textField.getText());
			}else if(e.getSource()==tweet){
				user.update(new Message(user.getId()+": "+tweetField.getText()));
			}
		}
	}


}
