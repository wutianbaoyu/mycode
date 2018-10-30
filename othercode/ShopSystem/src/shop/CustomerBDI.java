package shop;

import java.util.ArrayList;
import java.util.List;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Capability;
import jadex.bdiv3.annotation.Mapping;
import jadex.micro.annotation.Agent;
import shop.service.Service;

/**
 *  Customer capability.
 */
@Agent
public class CustomerBDI
{
	//-------- attributes --------

	/** The customer capability. */
	@Capability(beliefmapping=@Mapping("money"))
	protected CustomerCapability	cap	= new CustomerCapability();
	
	/** The money. */
	@Belief
	//通过数据库确认有多少钱，放在数据库里方便充值
	protected double	money	= new Service().getMoneyByCustomerId(1);

	
}
