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
	//���弸����ʼ�ڵ�
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("�����Ǽ�"); 
	DefaultMutableTreeNode registration = new DefaultMutableTreeNode("�����Ǽ�"); 
	DefaultMutableTreeNode review = new DefaultMutableTreeNode("��������");
	DefaultMutableTreeNode massage = new DefaultMutableTreeNode("������Ϣ");
	DefaultMutableTreeNode select = new DefaultMutableTreeNode("������ѯ");
	DefaultMutableTreeNode delete = new DefaultMutableTreeNode("����ɾ��");
	DefaultMutableTreeNode update = new DefaultMutableTreeNode("�������");

	
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
		
		//ͨ��add�����������ڵ�֮��ĸ��ӹ�ϵ				
		massage.add(select);
		massage.add(delete);
		massage.add(update);
		
		//massage.set
		
		root.add(registration);
		root.add(review);
		root.add(massage);
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
                
                if(path.equals("��������")){
                	if(denglu.grade > 4){
	                	RegLogin rl = new RegLogin();               	
	                	zhujiemian.xiaoGuo(rl.c,"->������Դ��������->��������");
                	}else{
                		JOptionPane.showMessageDialog(jf, "�Բ�������Ȩ�޲���.");
                	}
                }
                if(path.equals("�����Ǽ�")){
                	
                	if(denglu.grade >= 3){
                		zhujiemian.xiaoGuo(ra.c,"->������Դ��������->�����Ǽ�");
                	}else{
                		JOptionPane.showMessageDialog(jf, "�Բ�������Ȩ�޲���.");
                	}
                }
                if(path.equals("������ѯ")){
                	
                	zhujiemian.xiaoGuo(fs.c,"->������Դ��������->������ѯ");
                	//PaidTree.tree.requestFocus(false);
                }
        }
		
	}
	
//	protected static ImageIcon createImageIcon(String path) {
//		java.net.URL imgURL =FileTree.class.getResource(path); //ȡ��ͼ���URL
//		if (imgURL != null) {
//		return new ImageIcon(imgURL); //����ȡ�õ�URL����ImageIcon����
//		} else {
//		System.err.println("Couldn't find file: " + path);
//		return null;
//		}
//		}
}
