package shop;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Capability;
import jadex.bdiv3.annotation.Mapping;
import jadex.bridge.IInternalAccess;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.Argument;
import jadex.micro.annotation.Arguments;

import java.util.List;

//import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * 
 */
@Agent
@Arguments(
{
	@Argument(name="catalog", clazz=List.class), 
	@Argument(name="shopname", clazz=String.class)
})
public class ShopAndCustomerBDI
{
	//-------- attributes --------

	/** The agent. */
	@Agent
//	@SuppressFBWarnings(value="UR_UNINIT_READ", justification="Agent field injected by interpreter")
	protected IInternalAccess	agent;
	
	/** 客户能力. */
	@Capability(beliefmapping=@Mapping("money"))
	protected CustomerCapability	customercap	= new CustomerCapability();

	/** 商店能力. */
	@Capability(beliefmapping=@Mapping(value="money", target="money"))
	protected ShopCapa shopcap	= new ShopCapa((String)agent.getArgument("shopname"), (List<ItemInfo>)agent.getArgument("catalog"));
	
	/** 初始金额. */
	@Belief
	protected double money	= 100.0;
}
