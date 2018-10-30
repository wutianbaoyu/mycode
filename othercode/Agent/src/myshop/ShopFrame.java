package myshop;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import jadex.bdiv3.runtime.ICapability;
import jadex.bridge.IComponentStep;
import jadex.bridge.IInternalAccess;
import jadex.bridge.component.IExecutionFeature;
import jadex.bridge.component.IMonitoringComponentFeature;
import jadex.bridge.service.types.monitoring.IMonitoringEvent;
import jadex.bridge.service.types.monitoring.IMonitoringService.PublishEventLevel;
import jadex.commons.future.IFuture;
import jadex.commons.future.IResultListener;
import jadex.commons.future.IntermediateDefaultResultListener;
import jadex.commons.gui.SGUI;
import jadex.commons.gui.future.SwingIntermediateResultListener;
import jadex.commons.transformation.annotations.Classname;

import javax.swing.JFrame;

import myshop.ShopPanel;

/**
 * Created by MCY on 2016/9/19.
 */
public class ShopFrame extends JFrame{

	public ShopFrame (final ICapability capa){
		super(((ShopCapa)capa.getPojoCapability()).getShopSeriverName());
		add(new ShopPanel(capa,this));
		
		pack();
		setLocation(SGUI.calculateMiddlePosition(this));//居中
		setSize(400, 450);//设置大小
		setVisible(true);//设置为可见
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
//				agent.killComponent();
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
		capa.getAgent().getComponentFeature(IExecutionFeature.class).scheduleStep(new IComponentStep<Void>()
		{
			@Classname("dispose")
			public IFuture<Void> execute(IInternalAccess ia)
			{
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
		}).addResultListener(dislis);
	}
}