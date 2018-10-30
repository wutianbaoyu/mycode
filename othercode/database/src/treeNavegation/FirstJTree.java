package treeNavegation;

import java.awt.Color;
import java.awt.Container;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;


import register.*;
import audit.First_Area_au;
import fileinformation.FileSelect;
import com.modelCl.OrganizationCl;
import register.*;


import login.denglu;
import login.zhujiemian;


public class FirstJTree  implements TreeSelectionListener
{

	JFrame jf = null;

	Container c = null;
	
	public static JScrollPane jsp = null;
	
	JTree tree;
	//定义几个初始节点
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("档案登记"); 
	DefaultMutableTreeNode registration = new DefaultMutableTreeNode("档案登记"); 
	DefaultMutableTreeNode review = new DefaultMutableTreeNode("档案复核");
	DefaultMutableTreeNode massage = new DefaultMutableTreeNode("档案信息");
	DefaultMutableTreeNode select = new DefaultMutableTreeNode("档案查询");
	DefaultMutableTreeNode delete = new DefaultMutableTreeNode("档案删除");
	DefaultMutableTreeNode update = new DefaultMutableTreeNode("档案变更");

	
	Reg_Area ra = null;
	FileSelect fs = null;
	//RegLogin rl = null;

	public void init(){
		ra = new Reg_Area();
	}
	public FirstJTree()
	{

		fs = new FileSelect();
		
		jf = new JFrame();
		
		c = jf.getContentPane();
		
		//通过add方法建立树节点之间的父子关系				
		massage.add(select);
		massage.add(delete);
		massage.add(update);
		
		//massage.set
		
		root.add(registration);
		root.add(review);
		root.add(massage);
		//以根节点创建树
		tree = new JTree(root);
		//tree.setOpaque(false);
		DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();
		//render.setIcon(new ImageIcon("images/smalllogo2.png"));
		render.setClosedIcon(new ImageIcon("images/smalllogo2.png"));
		render.setOpenIcon(new ImageIcon("images/smalllogo3.png"));//设置目录的图标
		render.setLeafIcon(new ImageIcon("images/smalllogo.png"));//设置叶节点的图标
		//render = tree.getTre
		render.setBackgroundNonSelectionColor(new Color(233,224,217));
		//render.backgroundNonSelectionColor();
		
		tree.addTreeSelectionListener(this);
		tree.setCellRenderer(render);//应用到树里面
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
		//tree.setBorder(BorderFactory.createLineBorder(Color.yellow));
		//tree.setSize(100,200);
		
		jsp = new JScrollPane(tree);
		
		
		jsp.setOpaque(false);
		jsp.setBorder(null);
		init();
	}

//    public static void main(String[] args) 
//    {
//		new FirstJTree();
//    }

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		//Reg_Area ra = new Reg_Area();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
        if(node.isLeaf()){
                String path = node.toString();
                
                if(path.equals("档案复核")){
                	if(denglu.grade > 4){
	                	RegLogin rl = new RegLogin();               	
	                	zhujiemian.xiaoGuo(rl.c,"->人力资源档案管理->档案复核");
                	}else{
                		JOptionPane.showMessageDialog(jf, "对不起，您的权限不够.");
                	}
                }
                if(path.equals("档案登记")){
                	
                	if(denglu.grade >= 3){
                		zhujiemian.xiaoGuo(ra.c,"->人力资源档案管理->档案登记");
                	}else{
                		JOptionPane.showMessageDialog(jf, "对不起，您的权限不够.");
                	}
                }
                if(path.equals("档案查询")){
                	
                	zhujiemian.xiaoGuo(fs.c,"->人力资源档案管理->档案查询");
                	//PaidTree.tree.requestFocus(false);
                }
        }
		
	}
	
//	protected static ImageIcon createImageIcon(String path) {
//		java.net.URL imgURL =FileTree.class.getResource(path); //取得图标的URL
//		if (imgURL != null) {
//		return new ImageIcon(imgURL); //利用取得的URL返回ImageIcon对象
//		} else {
//		System.err.println("Couldn't find file: " + path);
//		return null;
//		}
//		}
}
