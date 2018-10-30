package treeNavegation;

import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import login.zhujiemian;

import audit.First_Area_au;

public class AboutJTree implements TreeSelectionListener {
	

	JFrame jf = null;

	Container c = null;
	
	public static JScrollPane jsp = null;
	
	JTree tree;
	//定义几个初始节点
	DefaultMutableTreeNode about = new DefaultMutableTreeNode("关于"); 
	DefaultMutableTreeNode Company_profile = new DefaultMutableTreeNode("公司简介");
	DefaultMutableTreeNode Enterprise_culture = new DefaultMutableTreeNode("企业文化");
	DefaultMutableTreeNode Enterprise_resources = new DefaultMutableTreeNode("企业资源");
	DefaultMutableTreeNode Core_advantage = new DefaultMutableTreeNode("核心优势"); 
	DefaultMutableTreeNode Management_team = new DefaultMutableTreeNode("管理团队");
	DefaultMutableTreeNode Development_course = new DefaultMutableTreeNode("发展历程"); 
	DefaultMutableTreeNode connect = new DefaultMutableTreeNode("联系我们");

	public AboutJTree()
	{

		jf = new JFrame();
		
		c = jf.getContentPane();
		
		about.add(Company_profile);
		about.add(Enterprise_culture);
		about.add(Enterprise_resources);
		about.add(Core_advantage);
		about.add(Management_team);
		about.add(Development_course);
		about.add(connect);
		
		//以根节点创建树
		tree = new JTree(about);
		//tree.setOpaque(false);
		DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();
		render.setLeafIcon(new ImageIcon("images/smalllogo.png"));//设置叶节点的图标
		
		//render = tree.getTre
		render.setBackgroundNonSelectionColor(new Color(233,224,217));	
		
		tree.addTreeSelectionListener(this);
		tree.setCellRenderer(render);
		//默认连线
		tree.putClientProperty("JTree.lineStyle" , "Angeled");
		//没有连线
		//tree.putClientProperty("JTree.lineStyle" , "None");
		//水平分隔线
		//tree.putClientProperty("JTree.lineStyle" , "Horizontal"); 


		//设置是否显示根节点的“展开/折叠”图标,默认是false
		//tree.setShowsRootHandles(true);
		//设置节点是否可见,默认是true
		tree.setRootVisible(false);
		tree.setBackground(new Color(233,224,217));
		
		jsp = new JScrollPane(tree);
		
		
		jsp.setOpaque(false);
		jsp.setBorder(null);
	}

	@Override
	public void valueChanged(TreeSelectionEvent treeselectionevent) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
//        if(node.isLeaf()){
//                String path = node.toString();
//                
//                if(path.equals("公司简介")){
//                	
//                	MainJrame.xiaoGuo(First_Area.c);
//                }
//        }
		
	}
}
