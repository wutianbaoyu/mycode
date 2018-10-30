package garbagecollector;

import java.util.Map;

import jadex.commons.SimplePropertyObject;
import jadex.extension.envsupport.environment.IEnvironmentSpace;
import jadex.extension.envsupport.environment.ISpaceAction;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.space2d.Space2D;
import jadex.extension.envsupport.math.IVector2;
import jadex.extension.envsupport.math.Vector2Int;

/**
 *  The go action for moving one field in one of four directions.
 *  移动向四方的行动
 */
public class GoAction extends SimplePropertyObject implements ISpaceAction
{
	//-------- constants --------

	/** 定义方向 */
	public static final String UP = "up";
	public static final String DOWN = "down";
	public static final String LEFT = "left";
	public static final String RIGHT = "right";

	public static final String DIRECTION = "direction";
	
	
	//-------- methods --------
	
	/**
	 * Performs the action.
	 * @param parameters parameters for the action
	 * @param space the environment space
	 * @return action return value
	 */
	public Object perform(Map<String, Object> parameters, IEnvironmentSpace space)
	{
//		System.out.println("go action: "+parameters);
		
		String dir = (String)parameters.get(DIRECTION);
		Object oid = parameters.get(ISpaceAction.OBJECT_ID);
		ISpaceObject obj = space.getSpaceObject(oid);
		IVector2 pos = (IVector2)obj.getProperty(Space2D.PROPERTY_POSITION);
		
//		IVector2 size = ((Space2D)space).getAreaSize();
//		int sizex = size.getXAsInteger();
//		int sizey = size.getYAsInteger();
		int px = pos.getXAsInteger();//获取x坐标
		int py = pos.getYAsInteger();//获取y坐标
		if(dir.equals(UP))
		{
//			pos = new Vector2Int(px, (py-1+sizey)%sizey);
			pos = new Vector2Int(px, py-1);//向上的话y坐标-1
		}
		else if(dir.equals(DOWN))
		{
//			pos = new Vector2Int(px, (py+1)%sizey);
			pos = new Vector2Int(px, py+1);//向下的话y坐标+1
		}
		else if(dir.equals(LEFT))
		{
//			pos = new Vector2Int((px-1+sizex)%sizex, py);
			pos = new Vector2Int(px-1, py);//向左的话x坐标-1
		}
		else if(dir.equals(RIGHT))
		{
//			pos = new Vector2Int((px+1)%sizex, py);
			pos = new Vector2Int(px+1, py);//向右的话x坐标+1
		}
		
		((Space2D)space).setPosition(oid, pos);//设置新坐标
		
		obj.setProperty("lastmove", dir);
		
//		System.out.println("Go action: "+obj.getProperty(ISpaceAction.ACTOR_ID)+" "+pos);
		
//		obj.fireObjectEvent(new ObjectEvent(POSITION_CHANGED));
		
		return null;
	}

	/**
	 * Returns the ID of the action.
	 * @return ID of the action
	 */
	public Object getId()
	{
		// todo: remove here or from application xml?
		return "go";
	}
}
