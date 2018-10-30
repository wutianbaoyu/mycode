package calendar3;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class JCalendarChooser extends JTextField{
	
	private  DateChooser dc=new DateChooser();
	
    public JCalendarChooser(int column){
    	super(column);
    	this.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e)  {
    			dc.show((JTextField)e.getSource());
    		}
    	});
    	
    	this.setEditable(false);
    	this.setBackground(Color.white);
    }
    public JCalendarChooser(){
    	
    	this(9);
        
    }

    
///////////////////////////////////////////////////////////////
	////////////////      以下代码测试用       ////////////////////////
	///////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//         final JFrame frame = new JFrame("TableSelectionDemo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//       //Create and set up the content pane.
//        JPanel newContentPane = new JPanel();
//        JCalendarChooser chooser = new JCalendarChooser();
//        newContentPane.add(chooser);
//        newContentPane.setOpaque(true); //content panes must be opaque
//        frame.setContentPane(newContentPane);
//
//        //Display the window.
//        frame.setSize(800,350);
//        frame.setVisible(true);
//    }
}
