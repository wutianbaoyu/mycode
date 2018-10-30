package shop;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Capability;
import jadex.bdiv3.annotation.Mapping;
import jadex.bridge.IInternalAccess;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.Argument;
import jadex.micro.annotation.Arguments;
import shop.service.Service;

import java.util.List;

//import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * 
 */
@Agent
@Arguments(
{
	@Argument(name="catalog", clazz=List.class),  //商品目录
	@Argument(name="shopname", clazz=String.class)//商点名称
})
public class ShopBDI
{
	//-------- attributes --------

	@Agent
	protected IInternalAccess	agent;
	
	// Principles: 
	//原则：
	// - each belief should only be represented as one field! (no assignments)
	//每个信念应只能被表示一个属性域（没有作业）
	// - access of beliefs of capabilities via getters/setters
	//能力体通过getter/setter信念的访问
	// - delegation to the outside via own getter/setters (allows renaming)
	//委托通过自己的getter/setter到外部
	// - abstract beliefs need to be declared via native getter/setter pairs
	//抽象信念需要通过本地成对的getter/setter被声明
	
	/** The customer capability. *///购物能力
	@Capability(beliefmapping=@Mapping("money"))
	protected ShopCapa shopcap	= new ShopCapa((String)agent.getArgument("shopname"), 
		(List<ItemInfo>)agent.getArgument("catalog"));
	
	/** 初始金额通过服务获取，金额存在数据库中. */
	@Belief
	//protected double	money	= 100;//资金
	protected double	money	= new Service().getShopMoneybyShopName(((String)agent.getArgument("shopname")).split(",")[0]);
}
