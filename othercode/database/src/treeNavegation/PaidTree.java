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
                
                if(path.equals("��н��Ŀ�Ǽ�")){
                	zhujiemian.xiaoGuo(pp.c,"->��н��׼����->��н��Ŀ�Ǽ�");
                }
                if(path.equals("��н��׼�Ǽ�")){
                	
                	zhujiemian.xiaoGuo(new PaidLogin().c,"->��н��׼����->��н��׼�Ǽ�");
                }
                if(path.equals("��н��׼�ǼǸ���")){

                	zhujiemian.xiaoGuo(new PaidReg().c,"->��н��׼����->��н��׼�ǼǸ���");
                }
                if(path.equals("��н��׼��ѯ")){

                	zhujiemian.xiaoGuo(ps.c,"->��н��׼����->��н��׼��ѯ");
                }
                if(path.equals("��н���ŵǼ�")){
                	
                	zhujiemian.xiaoGuo(psend.c,"->��н��׼����->��н���ŵǼ�");
                }
//                if(path.equals("��н���ŵǼ���ϸ")){
//                	
//                	zhujiemian.xiaoGuo(ps.c,"->��н��׼����->��н���ŵǼ���ϸ");
//                }
        }
		
	}
	JFrame jf = null;

	Container c = null;
	
	public static JScrollPane jsp = null;
	
	public static JTree tree;
	//���弸����ʼ�ڵ�
	
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("��н��׼����"); 
	DefaultMutableTreeNode paidproject = new DefaultMutableTreeNode("��н��Ŀ�Ǽ�"); 
	DefaultMutableTreeNode paidregistration = new DefaultMutableTreeNode("��н��׼�Ǽ�"); 
	DefaultMutableTreeNode paidreview = new DefaultMutableTreeNode("��н��׼�ǼǸ���");
	DefaultMutableTreeNode paidselect = new DefaultMutableTreeNode("��н��׼��ѯ");
	DefaultMutableTreeNode paidrelease = new DefaultMutableTreeNode("��н���ŵǼ�");
	//DefaultMutableTreeNode paiddetail = new DefaultMutableTreeNode("��н���ŵǼ���ϸ");


	
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
		
		//ͨ��add�����������ڵ�֮��ĸ��ӹ�ϵ				
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
		//�Ը��ڵ㴴����
		tree = new JTree(root);
		//tree.setOpaque(false);
		DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();
		//render.setIcon(new ImageIcon("images/smalllogo2.png"));
		render.setClosedIcon(new ImageIcon("images/smalllogo2.png"));
		render.setOpenIcon(new ImageIcon("images/smalllogo3.png"));//����Ŀ¼��ͼ��
		render.setLeafIcon(new ImageIcon("images/smalllogo.png"));//����Ҷ�ڵ��ͼ��
		//render = tree.getTre
		render.setBackgroundNonSelectionColor(new Color(233,224,217));
		//render.backgroundNonSelectionColor();
		
		tree.addTreeSelectionListener(this);
		tree.setCellRenderer(render);//Ӧ�õ�������
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
		//tree.setBorder(BorderFactory.createLineBorder(Color.yellow));
		//tree.setSize(100,200);
		
		jsp = new JScrollPane(tree);
		
		
		jsp.setOpaque(false);
		jsp.setBorder(null);

	}
}
