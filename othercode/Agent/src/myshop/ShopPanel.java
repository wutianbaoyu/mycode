package myshop;

import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.bdiv3.runtime.ICapability;
import jadex.bdiv3.runtime.impl.BeliefAdapter;
import jadex.bridge.IComponentStep;
import jadex.bridge.IInternalAccess;
import jadex.commons.SUtil;
import jadex.commons.future.IFuture;
import jadex.commons.future.IResultListener;
import jadex.commons.gui.SGUI;
import jadex.commons.gui.future.SwingDefaultResultListener;
import jadex.commons.gui.future.SwingResultListener;
import jadex.commons.transformation.annotations.Classname;
import jadex.rules.eca.ChangeInfo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import myshop.service.IShopItemService;
import myshop.service.ShopItemServiceImpl;
import myshop.ShopCapa.addItemGoal;
import myshop.ShopCapa.chargeMoneyGoal;
import myshop.ShopCapa.deleteItemGoal;
import myshop.ShopCapa.getShopItemsGoal;
import myshop.ShopCapa.updateItemGoal;


/**
 * Created by MCY on 2016/9/19.
 */
public class ShopPanel extends JPanel{

	protected IShopItemService shopItemService =new ShopItemServiceImpl();
	protected ICapability capa;  //能力
	protected JTextField money;  //钱
	protected List<ItemInfo> shoplist ;  //商店商品
	protected JTable shoptable; //商店表格
	protected AbstractTableModel shopmodel; //商店表格模型
	protected JButton delete=new JButton("删除"),add=new JButton("添加"),alter=new JButton("修改");
	
	//面板构造方法
	public ShopPanel(final ICapability capa, final JFrame shopFrame) {
		this.capa = capa;
		setLayout(new BorderLayout());
		
		//金钱面板内容
		JPanel moneyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		moneyPanel.setBorder(new TitledBorder("资产"));
		moneyPanel.add(new JLabel("金钱："));
		money=new JTextField(5);
		money.setEditable(false);
		JButton chargeMoneyBtn = new JButton("充值");
		moneyPanel.add(money);
		moneyPanel.add(chargeMoneyBtn);
		add(moneyPanel,BorderLayout.NORTH);
		//商品面板内容
		shoplist =((ShopCapa)capa.getPojoCapability()).getCatalog();
		shopmodel = new ItemTableModel(shoplist);
		shoptable=new JTable(shopmodel);
		shoptable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 单选
		JScrollPane goodsPane = new JScrollPane(shoptable);
		goodsPane.setBorder(new TitledBorder("商品"));
		add(goodsPane,BorderLayout.CENTER);
		//按钮面板内容
		JPanel operate = new JPanel();
		operate.setBorder(new TitledBorder("操作"));
		operate.add(delete);
		operate.add(add);
		operate.add(alter);
		add(operate,BorderLayout.SOUTH);
		
		final NumberFormat df = NumberFormat.getInstance();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		
		// 取得金钱数据
		capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>() {
            @Override
            public IFuture<Void> execute(IInternalAccess ia) {
                final double value = ((ShopCapa)capa.getPojoCapability()).getMoney();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        money.setText(df.format(value));
                    }
                });
                return IFuture.DONE;
            }
        });
		
		//监听钱的变化，更新界面
		capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>()
				{
					@Classname("money")
					public IFuture<Void> execute(IInternalAccess ia)
					{
						//钱的信念监听器
						capa.addBeliefListener("money", new BeliefAdapter<Object>()
						{
							public void beliefChanged(final ChangeInfo<Object> info)
							{
								SwingUtilities.invokeLater(new Runnable()
								{
									public void run()
									{
										money.setText(df.format(info.getValue()));
									}
								});
							}
						});
						return IFuture.DONE;
					}
				});
		
		//取得商品目录
		capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>()
				{
					@Classname("getShopData")
					public IFuture<Void> execute(IInternalAccess ia) {
						getShopItemsGoal gsd = new getShopItemsGoal();
						ia.getComponentFeature(IBDIAgentFeature.class).dispatchTopLevelGoal(gsd);
						return IFuture.DONE;
					}
				});
		
		//监听目录的变化，更新界面
		capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>()
				{
					//商品目录的信念监听器
					public IFuture<Void> execute(IInternalAccess ia) {
						capa.addBeliefListener("catalog", new BeliefAdapter<Object>()
						{
							@Override
							public void factRemoved(final ChangeInfo<Object> value)
							{
								SwingUtilities.invokeLater(new Runnable()
								{
									public void run()
									{
										shopmodel.fireTableDataChanged();
									}
								});
							}
							@Override
							public void factAdded(final ChangeInfo<Object> value)
							{
								SwingUtilities.invokeLater(new Runnable()
								{
									public void run()
									{
										shopmodel.fireTableDataChanged();
									}
								});
							}
							//对象内容发生变化时启动
							@Override
							public void factChanged(ChangeInfo<Object> object)
							{
								SwingUtilities.invokeLater(new Runnable()
								{
									public void run()
									{
										shopmodel.fireTableDataChanged();
									}
								});
							}
						});
						return IFuture.DONE;
					}
				});
		
		
		//为按钮添加监听器
		//充值金钱
		chargeMoneyBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				chargeJDialog cJDialog = new chargeJDialog(shopFrame);
				final double chargemoney = cJDialog.value;
				if(cJDialog.isOk){
					capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>()
						{
							@Override
							public IFuture<Void> execute(IInternalAccess ia) {
								chargeMoneyGoal goal = new chargeMoneyGoal(chargemoney);
								IFuture<chargeMoneyGoal> rs = ia.getComponentFeature(IBDIAgentFeature.class).dispatchTopLevelGoal(goal);
								//添加结果监听器
								rs.addResultListener(new SwingResultListener<chargeMoneyGoal>(new IResultListener<chargeMoneyGoal>()
										{
											@Override
											public void resultAvailable(chargeMoneyGoal result) {
												String text = SUtil.wrapText("成功冲入:$"+result.getMoney());
												JOptionPane.showMessageDialog(SGUI.getWindowParent(ShopPanel.this), text, "充值成功", JOptionPane.INFORMATION_MESSAGE);
											}
											@Override
											public void exceptionOccurred(Exception exception) {
												String text = SUtil.wrapText("充值失败:"+exception.getMessage());
												JOptionPane.showMessageDialog(SGUI.getWindowParent(ShopPanel.this), text, "充值失败", JOptionPane.WARNING_MESSAGE);
											}
										}));
								return IFuture.DONE;
							}
						});
				}
			}
		});
		
		//删除按钮
		delete.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//通过智能体构件和界面做删除操作
				int select = shoptable.getSelectedRow();
				if(select!=-1){//选中选项时有删除操作,否则抛出list空指向异常
					final String name = (String) shopmodel.getValueAt(select, 0);
					capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>()
							{
								@Override
								public IFuture<Void> execute(IInternalAccess ia) {
									deleteItemGoal delItemGoal = new deleteItemGoal(name);
									ia.getComponentFeature(IBDIAgentFeature.class).dispatchTopLevelGoal(delItemGoal);
									return IFuture.DONE;
								}
							});
				}
			}});
		
		//添加按钮
		add.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				MyJDialog myJDialog = new MyJDialog(shopFrame,"添加商品",false,-1);
				final String name = myJDialog.newName;
				final double price = myJDialog.newPrice;
				final int qauntity = myJDialog.newQuantity;
				if(myJDialog.isOk){
					capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>()
							{
								@Override
								public IFuture<Void> execute(IInternalAccess ia) {
									addItemGoal addItemGoal = new addItemGoal(name,price,qauntity);
									ia.getComponentFeature(IBDIAgentFeature.class).dispatchTopLevelGoal(addItemGoal);
									return IFuture.DONE;
								}
							});
				}
			}
		});
		
		//更新按钮
		alter.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int select = shoptable.getSelectedRow();
				if(select==-1){
					JOptionPane.showMessageDialog(null, "请选择要修改的商品", "错误信息", JOptionPane.ERROR_MESSAGE);
					return;
				}
				MyJDialog myJDialog = new MyJDialog(shopFrame,"更新商品",true,select);
				final ItemInfo selItem = shoplist.get(select);
				final ItemInfo newItem = new ItemInfo(myJDialog.newName,myJDialog.newPrice,myJDialog.newQuantity);
				if(myJDialog.isOk){
					capa.getAgent().getExternalAccess().scheduleStep(new IComponentStep<Void>()
							{
								@Override
								public IFuture<Void> execute(IInternalAccess ia) {
									updateItemGoal updateGoal = new updateItemGoal(selItem,newItem);
									ia.getComponentFeature(IBDIAgentFeature.class).dispatchTopLevelGoal(updateGoal);
									return IFuture.DONE;
							}
					});
				}
			}
		});
	}
	
	class MyJDialog extends JDialog{
		public  String newName = "";
		public double newPrice = 0;
		public int newQuantity = 0;
		public boolean isOk = false;
		public boolean isUpdate = false;
		JTextField name ;
		JTextField price ;
		JTextField quantity ;
		JButton save;
		JButton cancel;
		
		MyJDialog(JFrame parent,String title,final boolean isUpdate,final int select){
			super(parent,true);
			this.isUpdate = isUpdate;
			name = new JTextField(30);
			price = new JTextField(30);
			quantity = new JTextField(30);
			save = new JButton("保存");
			cancel = new JButton("取消");
			setTitle(title);
			setSize(330,200);
			setLocationRelativeTo(parent);
			setResizable(false);
			setLayout(null);
			JLabel Text1 = new JLabel("商品名字:");
			JLabel Text2 = new JLabel("价       钱:");
			JLabel Text3 = new JLabel("数       量:");
			add(Text1);
			add(Text2);
			add(Text3);
			Text1.setBounds(50, 30, 60, 25);
			Text2.setBounds(50, 60, 60, 25);
			Text3.setBounds(50, 90, 60, 25);
			add(name);
			add(price);
			add(quantity);
			name.setBounds(120, 30, 120, 25);
			price.setBounds(120, 60, 120, 25);
			quantity.setBounds(120, 90, 120, 25);
			add(save);
			add(cancel);
			save.setBounds(70, 130, 80, 25);
			cancel.setBounds(170, 130, 80, 25);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			if(isUpdate){
				name.setText(shoplist.get(select).getName());
				price.setText(shoplist.get(select).getPrice()+"");
				quantity.setText(shoplist.get(select).getQuantity()+"");
			}
			save.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					String tName = name.getText().trim();
					String tPrice = price.getText().trim();
					String tQuantity = quantity.getText().trim();
					if(tName.equals("")){
						JOptionPane.showMessageDialog(null, "名字不能为空", "错误信息", JOptionPane.ERROR_MESSAGE);
						return;
					}else if(shoplist.contains(new ItemInfo(tName))){
						if(!isUpdate){
							JOptionPane.showMessageDialog(null, "此商品已存在", "错误信息", JOptionPane.ERROR_MESSAGE);
							return;
						}else if(!shoplist.get(select).getName().equals(name.getText().trim())){				//判断修改的名字是否改为其他已有的名字
							JOptionPane.showMessageDialog(null, "此商品已存在", "错误信息", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}else if(tPrice.replaceAll("[0.0-9.0]", "").length()!=0 || tPrice.equals("")
							||price.getText().split("\\.").length>2||price.getText().endsWith(".")){
						JOptionPane.showMessageDialog(null, "价格：输入中含有非法字符或为空", "错误信息", JOptionPane.ERROR_MESSAGE);
						return;	
					}else if(tQuantity.replaceAll("[0-9]", "").length()!=0 || tQuantity.equals("")){
						JOptionPane.showMessageDialog(null, "数量:输入中含有非法字符或为空", "错误信息", JOptionPane.ERROR_MESSAGE);
						return;
					}
					newName = name.getText().trim();
					newPrice = Double.valueOf(price.getText());
					newQuantity = Integer.valueOf(quantity.getText());
					isOk=true;
					dispose();
				}
				
			});
			cancel.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			
			setVisible(true);
		}
	}
	
	class chargeJDialog extends JDialog{
		public double value = 0;
		public boolean isOk = false;
		JTextField moneyText ;
		JButton ok;
		JButton cancel;
		
		public chargeJDialog(JFrame parent){
			super(parent,true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setTitle("充值金钱");
			setSize(300,120);
			setLocationRelativeTo(parent);
			setResizable(false);
			setLayout(null);
			moneyText = new JTextField(30);
			ok = new JButton("充值");
			cancel = new JButton("取消");
			JLabel Text1 = new JLabel("充入金额:");
			add(Text1);
			add(moneyText);
			add(ok);
			add(cancel);
			Text1.setBounds(40, 25, 60, 25);
			moneyText.setBounds(110, 25, 120, 25);
			ok.setBounds(60, 60, 80, 25);
			cancel.setBounds(160, 60, 80, 25);
			
			ok.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					String tMoney = moneyText.getText().trim();
					if(tMoney.replaceAll("[0.0-9.0]", "").length()!=0 || tMoney.equals("")
							||moneyText.getText().split("\\.").length>2||moneyText.getText().endsWith(".")){
						JOptionPane.showMessageDialog(null, "输入金额中含有非法字符或为空", "错误信息", JOptionPane.ERROR_MESSAGE);
						return;	
					}
					value = Double.valueOf(moneyText.getText());
					isOk=true;
					dispose();
				}
			});
			
			cancel.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			
			setVisible(true);
		}
		
	}

}
