package A2;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class TreeView extends JPanel 
{
    private JTree tree;
    private DefaultMutableTreeNode root;
    public TreeView()
    {
        root = new DefaultMutableTreeNode(new UserGroup("RootGroup").getId());   
        tree = new JTree(root);
        add(tree);  
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);	
        this.setName("JTree ");   
        this.setVisible(true);
    }
    public JTree getTree(){
    	return tree;
    }
    
	public void updateTree(Users nodeToAdd) { 
//add to root group
		
   	    DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
        DefaultMutableTreeNode child = new DefaultMutableTreeNode(nodeToAdd);
        model.insertNodeInto(child, root, root.getChildCount());
        tree.scrollPathToVisible(new TreePath(child.getPath()));
   }
	
	public void updateTree(Users nodeToAdd,Users addTo) { 
//add user or group to group
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(nodeToAdd);
		DefaultMutableTreeNode newGroup = new DefaultMutableTreeNode(addTo);
		if(nodeExist(addTo)){
			model.insertNodeInto(newNode, searchNode(addTo), searchNode(addTo).getChildCount());
			
		}else{
			model.insertNodeInto(newGroup, root, root.getChildCount());
			model.insertNodeInto(newNode, searchNode(addTo), searchNode(addTo).getChildCount());
		}
    }
	
    private boolean nodeExist(Users nodeStr) {
//check if the group exist
		boolean checker=false;
		    DefaultMutableTreeNode node = null;
		    Enumeration e = root.breadthFirstEnumeration();
		    while (e.hasMoreElements()) {
		      node = (DefaultMutableTreeNode) e.nextElement();
		      if (nodeStr.getId().equals(node.getUserObject().toString())) {
		        checker=true;
		      }
		    }
		    return checker;
		  }
	
   private DefaultMutableTreeNode searchNode(Users nodeStr) {
//so i can add node to this node
	    DefaultMutableTreeNode node = null;
	    Enumeration e = root.breadthFirstEnumeration();
	    while (e.hasMoreElements()) {
	      node = (DefaultMutableTreeNode) e.nextElement();
	      if (nodeStr.getId().equals(node.getUserObject().toString())) {
	        return node;
	      }
	    }
	    return null;
	  }
   
}
