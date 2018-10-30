package garbagecollector;

import java.util.HashMap;
import java.util.Map;

import jadex.bdiv3.runtime.IGoal;
import jadex.bdiv3x.runtime.Plan;
import jadex.commons.future.DelegationResultListener;
import jadex.commons.future.Future;
import jadex.extension.envsupport.environment.IEnvironmentSpace;
import jadex.extension.envsupport.environment.ISpaceAction;

/**
 *  Burn a piece of garbage.
 */
public class BurnPlanEnv extends Plan
{
	/**
	 *  The plan body.
	 */
	public void body()
	{
//		System.out.println("Burn plan activated!");
		
		IEnvironmentSpace env = (IEnvironmentSpace)getBeliefbase().getBelief("env").getFact();

		// ��������
		IGoal pickup = createGoal("pick");//����Ŀ��pick
		dispatchSubgoalAndWait(pickup);//���Ŀ�겢�ҵȴ�

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ISpaceAction.ACTOR_ID, getComponentDescription());
		Future<Void> fut = new Future<Void>();
		//����Future
		env.performSpaceAction("burn", params, new DelegationResultListener<Void>(fut));
		fut.get();
	}
}
