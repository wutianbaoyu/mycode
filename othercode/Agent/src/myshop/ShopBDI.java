package myshop;

import myshop.service.IShopItemService;
import myshop.service.ShopItemServiceImpl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Capability;
import jadex.bdiv3.annotation.Mapping;
import jadex.bridge.IInternalAccess;
import jadex.bridge.component.IArgumentsResultsFeature;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.Argument;
import jadex.micro.annotation.Arguments;

/**
 *  Shop bdi agent.
 */
@Agent
@Arguments(
{
	@Argument(name="shopname", clazz=String.class, defaultvalue="jadex.commons.SUtil.createUniqueId(\"Shop\",2)")
})
public class ShopBDI
{
	//-------- attributes --------
	
	protected IShopItemService shopItemService =new ShopItemServiceImpl();
	
	@Agent
	@SuppressFBWarnings(value="UR_UNINIT_READ", justification="Agent field injected by interpreter")
	protected IInternalAccess agent;
	
	// Principles: 
	// - each belief should only be represented as one field! (no assignments)
	// - access of beliefs of capabilities via getters/setters
	// - delegation to the outside via own getter/setters (allows renaming)
	// - abstract beliefs need to be declared via native getter/setter pairs
	
	/** The customer capability. */
	@Capability(beliefmapping=@Mapping("money"))
	protected ShopCapa shopcap	= new ShopCapa((String)agent.getComponentFeature(IArgumentsResultsFeature.class).getArguments().get("shopname"));
	/** The money. */
	@Belief
	protected double	money	= 100;		//�����
	
}
