package sshop;

import javax.swing.JComponent;
import javax.swing.JPanel;

import jadex.base.gui.componentviewer.AbstractComponentViewerPanel;

/**
 *  Panel for the customer view.
 */
public class CustomerViewerPanel extends AbstractComponentViewerPanel
{
	//-------- attributes --------
	
	/** The panel. */
	protected JPanel panel	= new JPanel();	// Todo...
	
	//-------- methods --------
	
//	/**
//	 *  Called once to initialize the panel.
//	 *  Called on the swing thread.
//	 *  @param jcc	The jcc.
//	 * 	@param component The component.
//	 */
//	public IFuture<Void> init(IControlCenter jcc, final IExternalAccess component)
//	{
//		final Future<Void> ret = new Future<Void>();
//		super.init(jcc, component).addResultListener(new IResultListener<Void>()
//		{
//			public void resultAvailable(Void result)
//			{
//				panel = new CustomerPanel((IBDIExternalAccess)component);
//				ret.setResult(result);
//			}
//			
//			public void exceptionOccurred(Exception exception)
//			{
//				ret.setException(exception);
//			}
//		});
//		return ret;
//	}
	
	/**
	 *  The component to be shown in the gui.
	 *  @return	The component to be displayed.
	 */
	public JComponent getComponent()
	{
		return panel;
	}
}
