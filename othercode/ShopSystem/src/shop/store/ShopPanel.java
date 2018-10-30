package shop.store;

import jadex.bdi.examples.booktrading.common.GuiPanel;
import jadex.bdi.examples.booktrading.common.Order;
import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.bdiv3.runtime.ICapability;
import jadex.bdiv3.runtime.impl.BeliefAdapter;
import jadex.bridge.IComponentStep;
import jadex.bridge.IExternalAccess;
import jadex.bridge.IInternalAccess;
import jadex.bridge.service.types.clock.IClockService;
import jadex.commons.SUtil;
import jadex.commons.future.DelegationResultListener;
import jadex.commons.future.Future;
import jadex.commons.future.IFuture;
import jadex.commons.future.IResultListener;
import jadex.commons.gui.SGUI;
import jadex.commons.gui.future.SwingDefaultResultListener;
import jadex.commons.gui.future.SwingResultListener;
import jadex.commons.transformation.annotations.Classname;
import jadex.micro.annotation.Binding;
import jadex.rules.eca.ChangeInfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import shop.*;
import shop.CustomerCapability.BuyItem;
import shop.ShopCapa.AddItem;
import shop.ShopCapa.EditItem;
import shop.ShopCapa.RemoveItem;
import domain.*;

/**
 * 商店面板
 * 
 * @author J
 */
public class ShopPanel extends JPanel {

	// -------- attributes --------

	/**
	 * @author J
	 */
	private static final long serialVersionUID = 1L;
	protected ICapability capa;
	protected Map shops;
	private String itemlabel;
	protected JTable shoptable;
	protected List shoplist = new ArrayList();
	protected AbstractTableModel shopmodel;
	private DateFormat dformat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	protected String shopname;

	/**
	 * 构造函数 ，面板组件在这里定义
	 */
	@SuppressWarnings({ "serial", "rawtypes" })
	public ShopPanel(final ICapability capa) // 能力为参数的构造函数
	{

		this.capa = capa;
		this.shops = new HashMap(); // 商户名哈希映射
		// 获取实体链表
		try {

	
			shopname = (String) capa.getAgent().getArgument("shopname");
			System.out.println("输出参数：" + shopname);
			// shoplist = new shop.service.Service().getShopItemInfo(1);
			shoplist = new shop.service.Service().getShopInfoByShopName(shopname);
			System.out.println("shoplist is " + ((ItemInfo) shoplist.get(0)).getName());
			shopmodel = new ItemTableModel(shoplist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 下面开始面板布局
		setLayout(new BorderLayout());

		final JTextField money = new JTextField(8);
		money.setEditable(false);
		JPanel north = new JPanel();
		north.add(new JLabel("盈利总额："));
		north.add(money);
		add(BorderLayout.NORTH, north); 
		
		JPanel itempanel = new JPanel(new BorderLayout());// 设置一个实体面板
		itempanel.setBorder(new TitledBorder(new EtchedBorder(), "属性")); // Properties
																			// 设置面板属性

		shoptable = new JTable(shopmodel);// 把抽象TableModel添加到Table中
		shoptable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focus,
					int row, int column) {
				Component comp = super.getTableCellRendererComponent(table, value, selected, focus, row, column);
				setOpaque(true);
				if (column == 0) {
					setHorizontalAlignment(LEFT);
				} else {
					setHorizontalAlignment(RIGHT);
				}
				if (!selected) {
					Object state = shopmodel.getValueAt(row, 6);
					if (ItemInfo.DONE.equals(state)) // 排序
					{
						comp.setBackground(new Color(211, 255, 156));// 设置背景颜色
					} else if (ItemInfo.FAILED.equals(state)) {
						comp.setBackground(new Color(255, 211, 156));// 设置背景颜色
					} else {
						comp.setBackground(table.getBackground());// 设置背景
					}
				}
				if (value instanceof Date) {
					setValue(dformat.format(value));
				}
				return comp;
			}
		});
		shoptable.setPreferredScrollableViewportSize(new Dimension(600, 120));// 设置为可以滑动
		JScrollPane scroll = new JScrollPane(shoptable);
		itempanel.add(BorderLayout.CENTER, scroll);// 支持滚动，为位置Center
		shoptable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 单选
		shoptable.addMouseListener(new MouseAdapter() { // 鼠标事件
			public void mouseClicked(MouseEvent e) {
				int selectedRow = shoptable.getSelectedRow(); // 获得选中行索引
				@SuppressWarnings("unused")
				Object oa = shopmodel.getValueAt(selectedRow, 0);
				@SuppressWarnings("unused")
				Object ob = shopmodel.getValueAt(selectedRow, 1);

			}
		});

		scroll.setViewportView(shoptable);

		// ===========================================================================

		JPanel south = new JPanel();
		// south.setBorder(new TitledBorder(new EtchedBorder(), " Control "));
		JButton add = new JButton("添加商品");
		final JButton remove = new JButton("删除商品");
		final JButton edit = new JButton("修改商品");
		add.setMinimumSize(remove.getMinimumSize());
		add.setPreferredSize(remove.getPreferredSize());
		edit.setMinimumSize(remove.getMinimumSize());
		edit.setPreferredSize(remove.getPreferredSize());
		south.add(add);
		south.add(remove);
		south.add(edit);
		remove.setEnabled(false);
		edit.setEnabled(false);

		JSplitPane splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitter.add(itempanel);
		splitter.setOneTouchExpandable(true);
		// splitter.setDividerLocation();
		add(BorderLayout.CENTER, splitter); // 布局的第1部分
		add(BorderLayout.SOUTH, south); // 布局的第2部分

		// 下面分别给增删改查添加监听器
		shoptable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				refreshDetails();
			}
		});

		final InputDialog dia = new InputDialog(true);

		final NumberFormat df = NumberFormat.getInstance();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		
		capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>()// 购物能力构件安排步，作用是实现智能构件与外部构件（例如界面）之间数据变化时的一致性
		{
			@Classname("initialMoney") // 初始化资金
			public IFuture<Void> execute(IInternalAccess ia)// 执行内部存取ia
			{
				ShopCapa shop = (ShopCapa) capa.getPojoCapability();
				final double mon = shop.getMoney();
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						money.setText(df.format(mon));// 按格式给资金正文域赋值
					}
				});
				return IFuture.DONE;
			}
		});
		capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>()// 购物能力构件安排步，作用是实现智能构件与外部构件（例如界面）之间数据变化时的一致性
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
				
		capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>() {
			@Classname("catalog")
			public IFuture<Void> execute(IInternalAccess ia) {
				try {
					capa.addBeliefListener("catalog", new BeliefAdapter<Object>()
					//catalog是一个脏数据  可能会被多个线程同时调用 因此加上一个锁
					// TODO 刷新监听器
					{
						public void factChanged(ChangeInfo<Object> object) {
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									System.out.println("shop_factchange");
									shopname = (String) capa.getAgent().getArgument("shopname");
									List<ItemInfo> tempShoplist = new shop.service.Service()
											.getShopInfoByShopName(shopname);
									shoplist.clear();
									shoplist.addAll(tempShoplist);
									shopmodel.fireTableDataChanged();
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

		/**
		 * 新增监听器
		 */
		// 为添加按钮添加事件,add监听器
		add.addActionListener(new ActionListener() {
			// TODO 新增监听器
			public void actionPerformed(ActionEvent e) {
				dia.shopname.setText(shopname); // 设置 add编制文本框的商店名
				dia.shopname.setEditable(false);// 设置为 不可以编辑
				dia.itemName.setText(""); // 设置商品名为空
				dia.itemPrice.setText(""); // 设置商品数量为空
				dia.itemNumber.setText(""); // 设置商品数量为空
				System.out.println("0");
				dia.requestInput();
				System.out.println("1");
				while (dia.isfunish) {
					System.out.println("2");
					try {
						System.out.println("3");
						String itemName = dia.itemName.getText();// 把添加数据的文本框的
																	// 商品名
																	// 数据拿出来，进行数据库添加操作
						double itemPrice = Double.parseDouble(dia.itemPrice.getText());// 把商品价格数据拿出来，进行数据库存储
						int quantity = Integer.parseInt(dia.itemNumber.getText());// 把商品数量在文本框中拿出来，进行数据存储操作。

						final ItemInfo itemInfo = new ItemInfo(itemName, itemPrice, quantity);
						capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>() {
							// 商店能力构件安排步，作用是实现智能构件与外部构件（例如界面）之间数据变化时的一致性;
							// 安排步，当两个线程共同争夺资源，一个在使用时，加锁，别人在使用了就不能进去。别人出来了再进去。

							@Classname("add")
							@Override
							public IFuture<Void> execute(IInternalAccess ia) {

								AddItem add = new AddItem(itemInfo, shopname);
//								IFuture<AddItem> ret = capa.getAgent().dispatchTopLevelGoal(add);// 处理添加目标其结果一个POJO的目标等待调度
								IFuture<AddItem> ret = capa.getAgent().getComponentFeature(IBDIAgentFeature.class).dispatchTopLevelGoal(add);// 处理添加目标其结果一个POJO的目标等待调度
								ret.addResultListener(new SwingResultListener<AddItem>(new IResultListener<AddItem>()
								// swing如果不要的话，服务传过来的结果会在哪放？控制台才能看到。加了的话可以在shop面板看到。
								{
									public void resultAvailable(AddItem result) {
										shoplist.add(itemInfo);// 添加数据到 list 上
										shopmodel.fireTableDataChanged();// 刷新panel
										dia.isfunish = true;
										dia.setVisible(false);
									}

									public void exceptionOccurred(Exception exception) {
										// Update number of available items
										shopmodel.fireTableDataChanged();// 刷新panel
										String text = SUtil.wrapText("商品不能添加 " + exception.getMessage());
										JOptionPane.showMessageDialog(SGUI.getWindowParent(ShopPanel.this), text,
												"Add problem", JOptionPane.INFORMATION_MESSAGE);
									}
								}));

								return IFuture.DONE;
							}
						});
						break;// 通过中断，关闭编辑框
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					}

				}
				// return IFuture.DONE;
			}
		});
		//
		// }
		// });

		shoptable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		shoptable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				boolean selected = shoptable.getSelectedRow() >= 0;
				remove.setEnabled(selected);
				edit.setEnabled(selected);
			}
		});

		remove.addActionListener(new ActionListener() {
			// TODO 移除按钮监听器
			public void actionPerformed(ActionEvent e) {
				System.out.println("这里是remove按钮事件");
				final int row = shoptable.getSelectedRow();
				if (row >= 0 && row < shoplist.size()) {

					final ItemInfo item = (ItemInfo) shoplist.remove(row);

					capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>() {
						@Classname("remove")
						@Override
						public IFuture<Void> execute(IInternalAccess ia) {
							RemoveItem big = new RemoveItem(item, shopname); // 创建一个购买项
//							IFuture<RemoveItem> ret = capa.getAgent().dispatchTopLevelGoal(big);// 分派购买目标
							IFuture<RemoveItem> ret = capa.getAgent().getComponentFeature(IBDIAgentFeature.class).dispatchTopLevelGoal(big);// 分派购买目标
							ret.addResultListener(
									new SwingResultListener<RemoveItem>(new IResultListener<RemoveItem>() {
								public void resultAvailable(RemoveItem result) {
									shopmodel.fireTableRowsDeleted(row, row); // 删除面板上锁选择的商品
								}

								public void exceptionOccurred(Exception exception) {
									// Update number of available items
									String text = SUtil.wrapText("商品不能被移除");
									JOptionPane.showMessageDialog(SGUI.getWindowParent(ShopPanel.this), text,
											"Remove problem", JOptionPane.INFORMATION_MESSAGE);
								}
							}));

							return IFuture.DONE;
						}

					});

				}

			}
		});

		// 新建一个弹出框，edit监听器
		final InputDialog edit_dialog = new InputDialog(true);
		edit.addActionListener(new ActionListener() //创建了一个button，对他添加一个监听器，在这个监听器当中就有一个actionPerformed方法。如果你要求这个button做一些事情，你就可以在actionPerformed方法中写你要做的事情
		{
			// TODO 修改监听器
			public void actionPerformed(ActionEvent e) {
						int row = shoptable.getSelectedRow();
						if (row >= 0 && row < shoplist.size()) {
							final ItemInfo iteminfo = (ItemInfo) shoplist.get(row);

							final ItemInfo oldItemInfo = new ItemInfo();
							oldItemInfo.setName(iteminfo.getName());
							oldItemInfo.setPrice(iteminfo.getPrice());
							oldItemInfo.setQuantity(iteminfo.getQuantity());

							edit_dialog.shopname.setText(shopname); // 设置 编制文本框的
																	// 商店名
							edit_dialog.shopname.setEditable(false); // 设置为不可以编辑

							edit_dialog.itemName.setText(iteminfo.getName());// 获取商品名，放在文本框中
							edit_dialog.itemPrice.setText(String.valueOf(iteminfo.getPrice()));// 获取商品数量，放在文本框中
							edit_dialog.itemNumber.setText(String.valueOf(iteminfo.getQuantity()));
							
							System.out.println("abort"+edit_dialog.aborted);
							while (edit_dialog.requestInput()) {
								System.out.println("in");
								try {
									System.out.println("in abort"+edit_dialog.aborted);
									String itemName = edit_dialog.itemName.getText(); // 获取修改后的文本框中的
																						// 商品名信息
									double itemPrice = Double.parseDouble(edit_dialog.itemPrice.getText());// 获取修改后的文本框中的商品价格信息
									int quantity = Integer.parseInt(edit_dialog.itemNumber.getText());// 获取修改后的文本框中的商品数量信息

									iteminfo.setName(itemName);
									iteminfo.setPrice(itemPrice);
									iteminfo.setQuantity(quantity);

									capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>() {
										@Override
										public IFuture<Void> execute(IInternalAccess ia) {

											EditItem big = new EditItem(iteminfo, oldItemInfo, shopname); // 创建一个购买项
//											IFuture<EditItem> ret = capa.getAgent().dispatchTopLevelGoal(big);// 处理编辑目标，其结果一个POJO的目标等待调度
											IFuture<EditItem> ret = capa.getAgent().getComponentFeature(IBDIAgentFeature.class).dispatchTopLevelGoal(big);// 处理编辑目标，其结果一个POJO的目标等待调度
											ret.addResultListener(
												new SwingResultListener<EditItem>(new IResultListener<EditItem>() 
													// swing如果不要的话，服务传过来的结果会在哪放？控制台才能看到。加了的话可以在shop面板看到。
												{
													public void resultAvailable(EditItem result) {
														shopmodel.fireTableDataChanged();// 刷新
												}

													public void exceptionOccurred(Exception exception) {

														String text = SUtil.wrapText("商品不能被修改");
														JOptionPane.showMessageDialog(SGUI.getWindowParent(ShopPanel.this),
															text, "Edit problem", JOptionPane.INFORMATION_MESSAGE);
												}
											}));
											return IFuture.DONE;
										}
									});

									break;// 通过中断，关闭编辑框

								}//#try 
								catch (NumberFormatException e1) {
									JOptionPane.showMessageDialog(ShopPanel.this, "Price limit must be integer.",
											"Input error", JOptionPane.ERROR_MESSAGE);
								}

							} // #while
						}//#if
			}
		});

	}

	/**
	 * Method to be called when goals may have changed.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void refresh(IShopService shop) {

		System.out.println("数据刷新。。。。。。。。。。。。");
		if (shop != null) {
			shop.getCatalog().addResultListener(

			new SwingDefaultResultListener(ShopPanel.this) { // 监听器，监听ShopPanel
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

	/////////////////////////////////
	/**
	 * Refresh the details panel.
	 */
	public void refreshDetails() {
		System.out.println("刷新明细表信息。。。。。。。。。。");

	}

	/////////////////////////////////
	/**
	 * Refresh the details panel.
	 */
	public void updateDetil() {
		// TODO 商店面板刷新
		System.out.println("刷新明细表信息。。。。。。。。。。");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				shopname = (String) capa.getAgent().getArgument("shopname");
				shoplist = new shop.service.Service().getShopInfoByShopName(shopname);
				int columnIndex = shopmodel.getColumnCount();// 获取总行数
				int rowIndex = shopmodel.getRowCount();
				//
				for (int i = 0; i < rowIndex; i++) {
					for (int j = 0; j < columnIndex; j++) {
						System.out.println("输出panel数据 getValueAt(i,j)：" + shopmodel.getValueAt(i, j));
						// shopmodel.setValueAt(aValue, i, j);
						ItemInfo object = (ItemInfo) shoplist.get(i);
						if (j == 0) {
							shopmodel.setValueAt(object.getName(), i, j);
						} else if (j == 1) {
							shopmodel.setValueAt(object.getPrice(), i, j);
						} else if (j == 2) {
							shopmodel.setValueAt(object.getQuantity(), i, j);
						}
					}
				} // .end for

				shopmodel.fireTableDataChanged();
			}
		});
	}

	/**
	 * Get the frame.
	 */
	public Frame getFrame() {
		Container parent = this.getParent();
		while (parent != null && !(parent instanceof Frame))
			parent = parent.getParent();
		return (Frame) parent;
	}

	// -------- inner classes --------

	/**
	 * The input dialog. 设置弹出框
	 */
	private class InputDialog extends JDialog {
		// TODO Auto-generated method stub
		/**
		 * @author J
		 */
		private static final long serialVersionUID = 1L;
		private JTextField shopname = new JTextField(20); // 设置商店名
		private JTextField itemName = new JTextField(20); //  商品名称文本框
		private JTextField itemNumber = new JTextField(20);// 商品数量文本框
		private JTextField itemPrice = new JTextField(20);//  商品价格文本框
		private boolean aborted = true;
		private boolean isfunish = false;
		
		InputDialog(final boolean buy) {
			super(getFrame(), "明细表单", true);
			
			try {
				System.out.println("这里是  InputDialog 。。。。");
			} catch (Exception exception) {

			}

			JPanel center = new JPanel(new GridBagLayout());// 定义中央面板
			center.setBorder(new EmptyBorder(5, 5, 5, 5));// 定义中央面板边界位置
			getContentPane().add(BorderLayout.CENTER, center);// 编辑面板的相对位置

			JLabel label;
			Dimension labeldim = new JLabel("预订的订单 ").getPreferredSize();
			int row = 0;

			GridBagConstraints leftcons = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
					GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0);
			GridBagConstraints rightcons = new GridBagConstraints(1, 0, GridBagConstraints.REMAINDER, 1, 1, 0,
					GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0);
			leftcons.gridy = rightcons.gridy = row++;
			label = new JLabel("商店名");// 属性名-
			label.setMinimumSize(labeldim);//
			label.setPreferredSize(labeldim);
			center.add(label, leftcons);// 把组件添加到中央面板
			center.add(shopname, rightcons);// 把多选框组件添加到中央面板

			leftcons.gridy = rightcons.gridy = row++;
			label = new JLabel("商品名");
			label.setMinimumSize(labeldim);
			label.setPreferredSize(labeldim);
			center.add(label, leftcons);
			center.add(itemName, rightcons);// 添加文本框组件

			leftcons.gridy = rightcons.gridy = row++;
			label = new JLabel("价格");
			label.setMinimumSize(labeldim);
			label.setPreferredSize(labeldim);
			center.add(label, leftcons);
			center.add(itemPrice, rightcons);// 把商品价格文本框,添加到中央面板

			leftcons.gridy = rightcons.gridy = row++;
			label = new JLabel("数量");
			label.setMinimumSize(labeldim);
			label.setPreferredSize(labeldim);
			center.add(label, leftcons);
			center.add(itemNumber, rightcons);

			////////////////////////////////////////////
			JPanel south = new JPanel();
			// south.setBorder(new TitledBorder(new
			// EtchedBorder(), " Control "));
			getContentPane().add(BorderLayout.SOUTH, south);
			JButton ok = new JButton("确定");
			JButton cancel = new JButton("取消");
			ok.setMinimumSize(cancel.getMinimumSize());
			ok.setPreferredSize(cancel.getPreferredSize());
			south.add(ok);
			south.add(cancel);

			// // 确认按钮触发器
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("OK按钮.......");
					aborted = false;
					isfunish = true;
					setVisible(false);
				}
			});
			// 取消按钮，触发器
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Cancel按钮.......");
					setVisible(false);
				}
			});

		}// #end

		// @Override
		// public void exceptionOccurred(Exception exception) {
		// // TODO Auto-generated method stub
		// e = exception;
		// }

		// }));
		//
		// return IFuture.DONE;
		// }
		// });
		//
		// }

		public boolean requestInput() {
			// this.deadline.setText(dformat.format(new Date(currenttime +
			// 300000)));
			aborted = true;
			isfunish = false;
			System.out.println("5");
			this.pack(); // 打包显示对话框
			this.setLocation(SGUI.calculateMiddlePosition(getFrame(), this));
			System.out.println("4");
			this.setVisible(true);
			
			
			System.out.println("aboted :" + aborted);
			System.out.println("isfunish :" + isfunish);
			return !aborted;
		}
	}

}
