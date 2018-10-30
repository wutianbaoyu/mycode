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
	//���弸����ʼ�ڵ�
	DefaultMutableTreeNode about = new DefaultMutableTreeNode("����"); 
	DefaultMutableTreeNode Company_profile = new DefaultMutableTreeNode("��˾���");
	DefaultMutableTreeNode Enterprise_culture = new DefaultMutableTreeNode("��ҵ�Ļ�");
	DefaultMutableTreeNode Enterprise_resources = new DefaultMutableTreeNode("��ҵ��Դ");
	DefaultMutableTreeNode Core_advantage = new DefaultMutableTreeNode("��������"); 
	DefaultMutableTreeNode Management_team = new DefaultMutableTreeNode("�����Ŷ�");
	DefaultMutableTreeNode Development_course = new DefaultMutableTreeNode("��չ����"); 
	DefaultMutableTreeNode connect = new DefaultMutableTreeNode("��ϵ����");

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
		
		//�Ը��ڵ㴴����
		tree = new JTree(about);
		//tree.setOpaque(false);
		DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();
		render.setLeafIcon(new ImageIcon("images/smalllogo.png"));//����Ҷ�ڵ��ͼ��
		
		//render = tree.getTre
		render.setBackgroundNonSelectionColor(new Color(233,224,217));	
		
		tree.addTreeSelectionListener(this);
		tree.setCellRenderer(render);
		//Ĭ������
		tree.putClientProperty("JTree.lineStyle" , "Angeled");
		//û������
		//tree.putClientProperty("JTree.lineStyle" , "None");
		//ˮƽ�ָ���
		//tree.putClientProperty("JTree.lineStyle" , "Horizontal"); 


		//�����Ƿ���ʾ���ڵ�ġ�չ��/�۵���ͼ��,Ĭ����false
		//tree.setShowsRootHandles(true);
		//���ýڵ��Ƿ�ɼ�,Ĭ����true
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
//                if(path.equals("��˾���")){
//                	
//                	MainJrame.xiaoGuo(First_Area.c);
//                }
//        }
		
	}
}
