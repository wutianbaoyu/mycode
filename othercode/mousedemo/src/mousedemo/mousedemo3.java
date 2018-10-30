package mousedemo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class mousedemo3 extends JFrame implements MouseListener,MouseMotionListener
{
	int flag;//flag=1代表Mouse Moved，flag=2代表Mouse Dragged
	int x=0;//坐标变量
	int y=0;//坐标变量
	int startx,starty,endx,endy;//起始坐标与终点坐标
	
	public mousedemo3()
	{
		Container contentPane=getContentPane();
		contentPane.addMouseListener(this);
		contentPane.addMouseMotionListener(this);
		setSize(300,300);
		show();
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		
	}
	public void mousePressed(MouseEvent e)
	{
		startx=e.getX();
		starty=e.getY();
	}
	public void mouseReleased(MouseEvent e)
	{
		endx=e.getX();
		endy=e.getY();
	}
	
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mouseMoved(MouseEvent e)
	{
		flag=1;
		x=e.getX();
		y=e.getY();
		repaint();
	}
	public void mouseDragged(MouseEvent e)
	{
		flag=2;
		x=e.getX();
		y=e.getY();
		repaint();
		
	}
	/*
	repaint()方法被调用后，会去调用update（）方法，再由update（）方法
	调用paint（）方法，update（）与paint（）的参数Graphics会自动由系统
	传入，用户不必担心。在此，我们将覆写（Override）update（）方法。目的是
	要让以前的画面清除掉，只保留新的画面。
	*/
	public void update(Graphics g)
	{
		g.setColor(this.getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		paint(g);
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		if(flag==1)
		{
			g.drawString("鼠标坐标：（"+x+","+y+")", 10, 50);
			g.drawLine(startx, starty, endx, endy);
			
		}
		if(flag==2)
		{
			
			g.drawString("拖曳鼠标坐标：（"+x+","+y+")", 10, 50);
			g.drawLine(startx, starty, x, y);
		}
		
	}
	public static void main(String args[])
		{
			new mousedemo3();
		}

}