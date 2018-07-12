package A2;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class  AdminControlPanel  extends JFrame {
	private static AdminControlPanel instance = null;
	private TwitterService twitterService = TwitterService.getInstance();
	List<Users>list=new ArrayList<Users>();
	private TreeView treeView;
	private JButton button;
	private JButton button2;
	private JTextField textField,textField2;
	private JButton userTotalButton,groupTotalButton,positiveButton,messageButton;
	private Visitor visitor = new ButtonVisitor();
	private JPanel pan;
	
//Using singleon pattern	
	public static AdminControlPanel getInstance(){
		if(instance ==null){
			instance =new AdminControlPanel();
		}
		return instance;
	}
	
	
	private AdminControlPanel(){
		
		this.setSize(600,600);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension dim=tk.getScreenSize();
		pan=new JPanel();
		
	    treeView = new TreeView();
	    ListenForSelection lfs=new ListenForSelection();
	  	treeView.getTree().addTreeSelectionListener(lfs);
		pan.add(treeView);	
		textField =new JTextField("",6);
		JLabel Label = new JLabel("if you want to add a user or a group to a group, enter user or group id on 1st field, and group id on the second field");
		JLabel Label2 = new JLabel("use add user button to add user to group, use add group button to add group to group, leave 2nd field blank if add to root");
		pan.add(Label);
		pan.add(Label2);
		pan.add(textField);
		ListenForButton lfb=new ListenForButton();
	    Border bd=BorderFactory.createTitledBorder(" Tweeter ");
	    
	    button =new JButton("add user");
        button.addActionListener(lfb);
      
	    pan.add(button);
	    pan.setBorder(bd);
	   		
	    textField2=new JTextField("",6);
		pan.add(textField2);
		button2=new JButton("Add Group");
		button2.addActionListener(lfb);
		pan.add(button2);
		
		userTotalButton = new JButton("show User total");
		userTotalButton.addActionListener(lfb);
		groupTotalButton = new JButton("show group total");
		groupTotalButton.addActionListener(lfb);
		positiveButton = new JButton("show positive percentage");
		positiveButton.addActionListener(lfb);
		messageButton = new JButton("show message total");
		messageButton.addActionListener(lfb);
		
		pan.add(userTotalButton);
		pan.add(groupTotalButton);
		pan.add(positiveButton);
		pan.add(messageButton);
		
		this.add(pan);
		this.setVisible(true);
	}
    private class ListenForButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button){
				try{
					twitterService.addUser(new User(textField.getText()));
					
					if(textField2.getText().trim().length() != 0){
						treeView.updateTree(new User(textField.getText()),new UserGroup(textField2.getText()));
					}else {
						treeView.updateTree(new User(textField.getText()));
						}
				}
				catch(Exception ec){
				}
			}else if(e.getSource()==button2){
				try{
					if(!twitterService.hasSameGroup(textField2.getText())){
					twitterService.addUser(new UserGroup(textField2.getText()));
					}
					if(textField.getText().trim().length() != 0){
						twitterService.addUser(new UserGroup(textField.getText()));
						treeView.updateTree(new UserGroup(textField.getText()),new UserGroup(textField2.getText()));
					}else {
						twitterService.addUser(new UserGroup(textField2.getText()));
						treeView.updateTree(new UserGroup(textField2.getText()));
						}
				}catch(Exception ec){
				}
				
			}else if(e.getSource() == userTotalButton){
				visitor.setUserZero();
				visitor.visitUserTotal(twitterService);
				JOptionPane.showMessageDialog(null, "total User is: "+ visitor.getUserTotal());
			}else if(e.getSource() == messageButton){
                visitor.setMessageZero();
				for(int i = 0; i < twitterService.getUserList().size(); i++){
				visitor.visitMessagesTotal(twitterService.getUserList().get(i));
				}
				 JOptionPane.showMessageDialog(null, "total message is: "+ visitor.getMessageTotal());

			}else if(e.getSource() == positiveButton){
				for(int i = 0; i < twitterService.getUserList().size(); i++){
					visitor.visitPositivePercentage(twitterService.getUserList().get(i));
					visitor.visitMessagesTotal(twitterService.getUserList().get(i));
					}
					 JOptionPane.showMessageDialog(null, "percentage is: "+ visitor.getPositivePercentage());
				
			}else if(e.getSource() == groupTotalButton){
				visitor.setGroupZero();
				visitor.visitGroupTotal((twitterService));
				JOptionPane.showMessageDialog(null, "total User is: "+ visitor.getGroupTotal());
			}
			
		}
		}
    
	public class ListenForSelection implements TreeSelectionListener{
		
		public void valueChanged(TreeSelectionEvent e) {
/*failed to connect this selection to the open user panel button,  
 * so when select a user in tree view, it will automatically open 
 * the user panel of the user selected.
 */
			new UserViewPanel((User)twitterService.searchUser(e.getPath().getLastPathComponent().toString()),twitterService);
		}
	}
 

}
