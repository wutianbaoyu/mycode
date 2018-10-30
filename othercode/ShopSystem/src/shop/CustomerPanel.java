package shop;

import shop.CustomerCapability.BuyItem;
import shop.service.Service;
import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.bdiv3.runtime.ICapability;
import jadex.bdiv3.runtime.impl.BeliefAdapter;
import jadex.bridge.IComponentStep;
import jadex.bridge.IInternalAccess;
import jadex.bridge.service.component.IRequiredServicesFeature;
import jadex.commons.SUtil;
import jadex.commons.future.DelegationResultListener;
import jadex.commons.future.Future;
import jadex.commons.future.IFuture;
import jadex.commons.future.IResultListener;
import jadex.commons.gui.SGUI;
import jadex.commons.gui.future.SwingDefaultResultListener;
import jadex.commons.gui.future.SwingResultListener;
import jadex.commons.transformation.annotations.Classname;
import jadex.rules.eca.ChangeInfo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

/**
 * Customer gui that allows buying items at different shops.
 */
public class CustomerPanel extends JPanel {
	// -------- attributes --------

	private static final long serialVersionUID = 1L;
	protected ICapability capa;
	@SuppressWarnings("rawtypes")
	protected List shoplist = new ArrayList();
	protected JCheckBox remote;
	protected JTable shoptable;
	protected AbstractTableModel shopmodel = new ItemTableModel(shoplist);
	protected Service service = new Service() ;

	@SuppressWarnings("rawtypes")
	//客户资产列表
	protected List invlist = new ArrayList();
//	protected List invlist = service.getCustomerInventory(1);
	protected AbstractTableModel invmodel = new ItemTableModel(invlist);
	protected JTable invtable;
	@SuppressWarnings("rawtypes")
	protected Map shops;
	// -------- constructors --------

	/**
	 * Create a new gui.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CustomerPanel(final ICapability capa) {
		this.capa = capa;
		this.shops = new HashMap();
		
		final JComboBox shopscombo = new JComboBox();// 选择是哪一个商店的商品 
		shopscombo.addItem("none");// 商户名组合框加项目
		shopscombo.addItemListener(new ItemListener()// 商户名组合框加项目监听
		{
			public void itemStateChanged(ItemEvent e)// 商户名项状态变化
			{
				// 如果商户名组合框选择项是购物服务的实例
				if (shops.get(shopscombo.getSelectedItem()) instanceof IShopService) 
				{// 刷新并得到商户名组合框的选择项
					refresh((IShopService) shops.get(shopscombo.getSelectedItem()));
				}
			}
		});

		remote = new JCheckBox("远程"); 
		remote.setToolTipText("同时查询远程商店平台。");
		final JButton searchbut = new JButton("搜索"); 
		searchbut.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)// 搜索按钮被按下
			{
				searchbut.setEnabled(false);// 设置按钮不可使用

				// SServiceProvider.getServices(agent.getServiceProvider(),
				// IShop.class, remote.isSelected(), true)
				IFuture<Collection<IShopService>> ret = capa.getAgent().getExternalAccess()
						.scheduleStep(new IComponentStep<Collection<IShopService>>()// 购物能力构件安排步，作用是实现智能构件与外部构件（例如界面）之间数据变化时的一致性，并创建一个服务步通道
				{
					public IFuture<Collection<IShopService>> execute(IInternalAccess ia)// 执行内部存取ia
					{
						Future<Collection<IShopService>> ret = new Future<Collection<IShopService>>();// 创建一个服务通道
						if (remote.isSelected()) // 如果远程搜索被选
						{
//							IFuture<Collection<IShopService>> fut = capa.getServiceContainer()
//									.getRequiredServices("remoteshopservices");// 调用远程购物服务，并创建一个服务通道
							IFuture<Collection<IShopService>> fut = capa.getAgent().getComponentFeature(IRequiredServicesFeature.class).getRequiredServices("remoteshopservices");
							fut.addResultListener(new DelegationResultListener<Collection<IShopService>>(ret));// 加分派远程购物服务监听
						} else {
//							IFuture<Collection<IShopService>> fut = capa.getServiceContainer()
//									.getRequiredServices("localshopservices");//调用本地购物服务，并创建一个服务通道
							IFuture<Collection<IShopService>> fut = capa.getAgent().getComponentFeature(IRequiredServicesFeature.class).getRequiredServices("localshopservices");
							fut.addResultListener(new DelegationResultListener<Collection<IShopService>>(ret));//加本地购物服务结果监听
						}
						return ret;//返回购物服务结果
					}
				});

				ret.addResultListener(new SwingDefaultResultListener<Collection<IShopService>>(CustomerPanel.this)// 加购物服务步结果监听，连接客户界面控件
				{
					public void customResultAvailable(Collection<IShopService> coll)// 购物服务结果可用
					{
						searchbut.setEnabled(true);// 按钮使能
						// System.out.println("Customer search result:
						// "+result);
						((DefaultComboBoxModel) shopscombo.getModel()).removeAllElements();// 移除所有商店选择框元素
						shops.clear();
						if (coll != null && coll.size() > 0) {//如果搜索结果不空
							for (Iterator<IShopService> it = coll.iterator(); it.hasNext();) {
								IShopService shop = it.next();
								shops.put(shop.getName(), shop);
								((DefaultComboBoxModel) shopscombo.getModel()).addElement(shop.getName());
							}
						} else {
							((DefaultComboBoxModel) shopscombo.getModel()).addElement("none");
						}
					}

					public void customExceptionOccurred(Exception exception) {
						searchbut.setEnabled(true);
						super.customExceptionOccurred(exception);
					}
				});
			}
		});

		final NumberFormat df = NumberFormat.getInstance();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);

		final JTextField money = new JTextField(5);
		
		//资产回显
		capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>() {
			@Classname("initInventory")//初始化资产
			public IFuture<Void> execute(IInternalAccess ia) {	//执行，传进来了，内部存储。 得到一个服务容器。
				final List<ItemInfo> itemInfoList = service.getCustomerInventory(1);
				SwingUtilities.invokeLater(new Runnable() {//线程
					public void run() {
						invlist.addAll(itemInfoList);
						invmodel.fireTableDataChanged();
					}
				});
				return IFuture.DONE;
			}
		});
		
		

		capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>()
				// 购物能力构件安排步，作用是实现智能构件与外部构件（例如界面）之间数据变化时的一致性
		{
			@Classname("initialMoney") // 初始化资金
			public IFuture<Void> execute(IInternalAccess ia)// 执行内部存取ia
			{
				CustomerCapability cust = (CustomerCapability) capa.getPojoCapability();// 装备客户能力
				final double mon = cust.getMoney();// 通过客户能力得到资金值
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						money.setText(df.format(mon));// 按格式给资金正文域赋值
					}
				});
				return IFuture.DONE;
			}
		});
		money.setEditable(true);
		// 购物能力构件安排步，作用是实现智能构件与外部构件（例如界面）之间数据变化时的一致性
		capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>()
		{
			@Classname("money")
			public IFuture<Void> execute(IInternalAccess ia) {
				capa.addBeliefListener("money", new BeliefAdapter<Object>() {
					public void beliefChanged(final ChangeInfo<Object> info) {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								money.setText(df.format(info.getValue()));
							}
						});
					}
				});
				return IFuture.DONE;
			}
		});

		JPanel selpanel = new JPanel(new GridBagLayout());
		selpanel.setBorder(new TitledBorder(new EtchedBorder(), "属性"));
		int x = 0;
		int y = 0;
		selpanel.add(new JLabel("金额: "), new GridBagConstraints(x, y, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		x++;
		selpanel.add(money, new GridBagConstraints(x, y, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(2, 2, 2, 2), 0, 0));
		x++;
		
		// 修改金额的按钮
		JButton update_money = new JButton("修改金额");
		selpanel.add(update_money, new GridBagConstraints(x, y, 1, 1, 1, 0, GridBagConstraints.WEST,
						GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		x++;
		
		//更新金额 按钮点击事件
		update_money.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (money.isEditable()) {
					//钱被设置后文本框被设置为不可更改
					money.setEditable(false);
					final double update_money = Double.parseDouble(money.getText());

					// 更新实体中的金额
					capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>() {
						@Override
						public IFuture<Void> execute(IInternalAccess ia) {
							//能力实装
							CustomerCapability cust = (CustomerCapability) capa.getPojoCapability();
							//更新金额
							cust.setMoney(update_money);
							return IFuture.DONE;
						}
					});
					// 更改数据库中用户金额
					 service.updateCustomerMoney(Double.valueOf(money.getText()),1);
				} else {
					money.setEditable(true);
				}
			}
		});

		
		selpanel.add(new JLabel("可以使用的商店: "), new GridBagConstraints(x, y, 1, 1, 0, 0, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		x++;
		selpanel.add(shopscombo, new GridBagConstraints(x, y, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
		x++;
		selpanel.add(searchbut, new GridBagConstraints(x, y, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
		x++;
		selpanel.add(remote, new GridBagConstraints(x, y, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(2, 2, 2, 2), 0, 0));

		JPanel shoppanel = new JPanel(new BorderLayout());
		shoppanel.setBorder(new TitledBorder(new EtchedBorder(), "商店目录"));
		shoptable = new JTable(shopmodel);
		shoptable.setPreferredScrollableViewportSize(new Dimension(600, 120));
		shoptable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		shoppanel.add(BorderLayout.CENTER, new JScrollPane(shoptable));

		JPanel invpanel = new JPanel(new BorderLayout());
		invpanel.setBorder(new TitledBorder(new EtchedBorder(), "客户资产"));
		invtable = new JTable(invmodel);
		invtable.setPreferredScrollableViewportSize(new Dimension(600, 120));
		invpanel.add(BorderLayout.CENTER, new JScrollPane(invtable));

		capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>() {
			@Classname("inventory")
			public IFuture<Void> execute(IInternalAccess ia) {
				try {
					//信念监听器
					capa.addBeliefListener("inventory", new BeliefAdapter<Object>() {
						public void factRemoved(final ChangeInfo<Object> value) {
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									invlist.remove(value.getValue());
									invmodel.fireTableDataChanged();
								}
							});
						}

						public void factAdded(final ChangeInfo<Object> value) {
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									invlist.add(value.getValue());
									invmodel.fireTableDataChanged();
								}
							});
						}

						public void factChanged(ChangeInfo<Object> object) {
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									// System.out.println("factchanged:
									// "+value);
									invmodel.fireTableDataChanged();
								}
							});
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
				return IFuture.DONE;
			}
		});

		JPanel butpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		// butpanel.setBorder(new TitledBorder(new EtchedBorder(), "Actions"));
		JButton buy = new JButton("购买");
		final JTextField item = new JTextField(8);
		item.setEditable(false);
		butpanel.add(new JLabel("选中的商品："));
		butpanel.add(item);
		butpanel.add(buy);
		buy.addActionListener(new ActionListener() {	//加激活监听
			public void actionPerformed(ActionEvent e) {
				int sel = shoptable.getSelectedRow();	//选择一个商品项
				if (sel != -1) {
					final String name = (String) shopmodel.getValueAt(sel, 0);		//得到一个商品项名
					final Double price = (Double) shopmodel.getValueAt(sel, 1);		//得到一个商品项价格
					final int quantity = (int) shopmodel.getValueAt(sel, 2);		//得到一个商品项价格
					final IShopService shop = (IShopService) shops.get(shopscombo.getSelectedItem());//得到商户名
					capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>() {
						//客户能力构件安排步，作用是实现智能构件与外部构件（例如界面）之间数据变化时的一致性;
						//安排步，两个线程，争夺资源，一个在使用时，加锁，别人在使用了就不能进去。别人出来了再进去。
						@Classname("buy")
						public IFuture<Void> execute(IInternalAccess ia) {	//执行，传进来了，内部存储。 得到一个服务容器。
							BuyItem big = new BuyItem(name, shop, price.doubleValue(),quantity);	//创建一个购买项
//							IFuture<BuyItem> ret = capa.getAgent().dispatchTopLevelGoal(big);//分派购买目标
							IFuture<BuyItem>	ret	= capa.getAgent().getComponentFeature(IBDIAgentFeature.class).dispatchTopLevelGoal(big);
							ret.addResultListener(new SwingResultListener<BuyItem>(new IResultListener<BuyItem>() {
								public void resultAvailable(BuyItem result) {
									refresh(shop);
								}

								public void exceptionOccurred(Exception exception) {
									// Update number of available items
									refresh(shop);

									String text = SUtil.wrapText("商品不能被买： " + exception.getMessage());
									JOptionPane.showMessageDialog(SGUI.getWindowParent(CustomerPanel.this), text,
											"Buy problem", JOptionPane.INFORMATION_MESSAGE);
								}
							}));
							return IFuture.DONE;
						}
					});
				}
			}
		});

		shoptable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int sel = shoptable.getSelectedRow();
				if (sel != -1) {
					item.setText("" + shopmodel.getValueAt(sel, 0));
				}
			}
		});

		setLayout(new GridBagLayout());
		x = 0;
		y = 0;
		add(selpanel, new GridBagConstraints(x, y++, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		add(shoppanel, new GridBagConstraints(x, y++, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		add(invpanel, new GridBagConstraints(x, y++, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));
		add(butpanel, new GridBagConstraints(x, y++, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0));

		// refresh();
	}

	/**
	 * Create a customer gui frame. / public static void createCustomerGui(final
	 * IBDIExternalAccess agent) { final JFrame f = new JFrame(); f.add(new
	 * CustomerPanel(agent)); f.pack();
	 * f.setLocation(SGUI.calculateMiddlePosition(f)); f.setVisible(true);
	 * f.addWindowListener(new WindowAdapter() { public void
	 * windowClosing(WindowEvent e) { agent.killAgent(); } });
	 * agent.addAgentListener(new IAgentListener() { public void
	 * agentTerminating(AgentEvent ae) { f.setVisible(false); f.dispose(); }
	 * 
	 * public void agentTerminated(AgentEvent ae) { } }); }
	 */

	/**
	 * Method to be called when goals may have changed.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void refresh(IShopService shop) {
		System.out.println("CustomerPanel 开始  刷新面板 ");
		if (shop != null) {
			shop.getCatalog().addResultListener(new SwingDefaultResultListener(CustomerPanel.this) {
				public void customResultAvailable(Object result) {
					int sel = shoptable.getSelectedRow();
					ItemInfo[] aitems = (ItemInfo[]) result;
					shoplist.clear();
					for (int i = 0; i < aitems.length; i++) {
						if (!shoplist.contains(aitems[i])) {
							// System.out.println("added: "+aitems[i]);
							shoplist.add(aitems[i]);
						}
					}
					shopmodel.fireTableDataChanged();
					if (sel != -1 && sel < aitems.length)
						((DefaultListSelectionModel) shoptable.getSelectionModel()).setSelectionInterval(sel, sel);
				}
			});
		} else {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					shoplist.clear();
					shopmodel.fireTableDataChanged();
				}
			});
		}
	}

}

class ItemTableModel extends AbstractTableModel {
	/**
	 * @author J
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	protected List list;

	@SuppressWarnings("rawtypes")
	public ItemTableModel(List list) {
		this.list = list;
	}

	public int getRowCount() {
		return list.size();
	}

	public int getColumnCount() {
		return 3;
	}

	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "商品名";
		case 1:
			return "商品价格";
		case 2:
			return "商品数量";
		default:
			return "";
		}
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public Object getValueAt(int row, int column) {
		Object value = null;
		ItemInfo ii = (ItemInfo) list.get(row);
		if (column == 0) {
			value = ii.getName();
		} else if (column == 1) {
			value = Double.valueOf(ii.getPrice());
		} else if (column == 2) {
			value = Integer.valueOf(ii.getQuantity());
		}
		return value;
	}
};
