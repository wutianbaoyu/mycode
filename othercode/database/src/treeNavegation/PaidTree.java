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

import fileinformation.FileSelect;
import paidmanagement.PaidLogin;
import paidmanagement.PaidProject;
import paidmanagement.PaidReg;
import paidmanagement.PaidSend;
import paidmanagement.PaidSendDetail;
import paidmanagement.Paidselect;
import register.RegLogin;
import register.Reg_Area;

public class PaidTree implements TreeSelectionListener{

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
        if(node.isLeaf()){
                String path = node.toString();
                
                if(path.equals("酬薪项目登记")){
                	zhujiemian.xiaoGuo(pp.c,"->酬薪标准管理->酬薪项目登记");
                }
                if(path.equals("酬薪标准登记")){
                	
                	zhujiemian.xiaoGuo(new PaidLogin().c,"->酬薪标准管理->酬薪标准登记");
                }
                if(path.equals("酬薪标准登记复核")){

                	zhujiemian.xiaoGuo(new PaidReg().c,"->酬薪标准管理->酬薪标准登记复核");
                }
                if(path.equals("酬薪标准查询")){

                	zhujiemian.xiaoGuo(ps.c,"->酬薪标准管理->酬薪标准查询");
                }
                if(path.equals("酬薪发放登记")){
                	
                	zhujiemian.xiaoGuo(psend.c,"->酬薪标准管理->酬薪发放登记");
                }
//                if(path.equals("酬薪发放登记明细")){
//                	
//                	zhujiemian.xiaoGuo(ps.c,"->酬薪标准管理->酬薪发放登记明细");
//                }
        }
		
	}
	JFrame jf = null;

	Container c = null;
	
	public static JScrollPane jsp = null;
	
	public static JTree tree;
	//定义几个初始节点
	
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("酬薪标准管理"); 
	DefaultMutableTreeNode paidproject = new DefaultMutableTreeNode("酬薪项目登记"); 
	DefaultMutableTreeNode paidregistration = new DefaultMutableTreeNode("酬薪标准登记"); 
	DefaultMutableTreeNode paidreview = new DefaultMutableTreeNode("酬薪标准登记复核");
	DefaultMutableTreeNode paidselect = new DefaultMutableTreeNode("酬薪标准查询");
	DefaultMutableTreeNode paidrelease = new DefaultMutableTreeNode("酬薪发放登记");
	//DefaultMutableTreeNode paiddetail = new DefaultMutableTreeNode("酬薪发放登记明细");


	
	Paidselect ps = null;	
	PaidSend psend = null;
	PaidProject pp = null;
	
	public PaidTree()
	{
		
		ps = new Paidselect();		
		psend = new PaidSend();
		pp = new PaidProject();
		
		jf = new JFrame();
		
		c = jf.getContentPane();
		
		//通过add方法建立树节点之间的父子关系				
//		massage.add(select);
//		massage.add(delete);
//		massage.add(update);
		
		//massage.set
		
		root.add(paidproject);
		root.add(paidregistration);
		root.add(paidreview);
		root.add(paidselect);
		root.add(paidrelease);
		//root.add(paiddetail);
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

	}
}
