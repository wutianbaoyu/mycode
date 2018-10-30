package myshop;

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
 * 
 */
@Agent
@Arguments(
{
	@Argument(name="name", clazz=String.class, defaultvalue="jadex.commons.SUtil.createUniqueId(\"Shop\",2)"),
})
public class ShopAndCustomerBDI
{
	//-------- attributes --------

	/** The agent. */
	@Agent
	@SuppressFBWarnings(value="UR_UNINIT_READ", justification="Agent field injected by interpreter")
	protected IInternalAccess agent;
	
	/** The customer capability. */
	@Capability(beliefmapping=@Mapping("money"))
	protected CustomerCapability customercap = new CustomerCapability((String)agent.getComponentFeature(IArgumentsResultsFeature.class).getArguments().get("name"));

	/** The shop capability. */
	@Capability(beliefmapping=@Mapping(value="money", target="money"))
	protected ShopCapa shopcap = new ShopCapa((String)agent.getComponentFeature(IArgumentsResultsFeature.class).getArguments().get("name"));
	
	/** The money. */
	@Belief
	protected double money	= 100.0;	//默认金钱
}
