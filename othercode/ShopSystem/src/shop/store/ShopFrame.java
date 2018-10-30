package shop.store;

import jadex.bdiv3.runtime.ICapability;
import jadex.bridge.IComponentStep;
import jadex.bridge.IExternalAccess;
import jadex.bridge.IInternalAccess;
import jadex.bridge.component.IMonitoringComponentFeature;
import jadex.bridge.service.types.monitoring.IMonitoringEvent;
import jadex.bridge.service.types.monitoring.IMonitoringService.PublishEventLevel;
import jadex.commons.future.IFuture;
import jadex.commons.future.IResultListener;
import jadex.commons.future.IntermediateDefaultResultListener;
import jadex.commons.gui.SGUI;
import jadex.commons.gui.future.SwingIntermediateResultListener;
import jadex.commons.transformation.annotations.Classname;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class ShopFrame extends JFrame {
	/**
	 * @author J
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *  Create a new frame.
	 */
	public ShopFrame(final ICapability capa)
	{
//		super(capa.getAgent().getExternalAccess().getComponentIdentifier().getName());
		super(((String)capa.getAgent().getArgument("shopname")).split(",")[0]);

		add(new ShopPanel(capa));
		pack();
		setLocation(SGUI.calculateMiddlePosition(this));
		this.setSize(600, 400);
		setVisible(true);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
//				agent.killAgent();
				capa.getAgent().getExternalAccess().killComponent();
			}
		});
		// Dispose frame on exception.
		IResultListener<Void>	dislis	= new IResultListener<Void>()
		{
			public void exceptionOccurred(Exception exception)
			{
				dispose();
			}
			public void resultAvailable(Void result)
			{
			}
		};
		capa.getAgent().scheduleStep(new IComponentStep<Void>()
		{
			@Classname("dispose")
			public IFuture<Void> execute(IInternalAccess ia)
			{
//				ia.subscribeToEvents(IMonitoringEvent.TERMINATION_FILTER, false, PublishEventLevel.COARSE)
//					.addResultListener(new SwingIntermediateResultListener<IMonitoringEvent>(new IntermediateDefaultResultListener<IMonitoringEvent>()
				ia.getComponentFeature(IMonitoringComponentFeature.class).subscribeToEvents(IMonitoringEvent.TERMINATION_FILTER, false, PublishEventLevel.COARSE)
				.addResultListener(new SwingIntermediateResultListener<IMonitoringEvent>(new IntermediateDefaultResultListener<IMonitoringEvent>()		
				{
					public void intermediateResultAvailable(IMonitoringEvent result)
					{
						setVisible(false);
						dispose();
					}
				}));
				return IFuture.DONE;
			}
		}).addResultListener(dislis); //把结果集添加到监听器
	}
}
