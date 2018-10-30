package action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dao.GoddessDao;
import dao.iDao;
import model.Goddess;
import model.i;

public class GoddessAction {

	public void add(Goddess goddess) throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.addGoddess(goddess);
	}
	public void readCols(Goddess goddess)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.readGoddess(goddess);
	}
	
	public void readBai(Goddess goddess)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.getBai(goddess);
	}
	
	public void getcolsone(Goddess goddess)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.getcolsone(goddess);
	}
	
	public void getcolstwo(Goddess goddess)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.getcolstwo(goddess);
	}
	
	public void getcolsthree(Goddess goddess)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.getcolsthree(goddess);
	}
	
	public void test(Goddess goddess)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.test(goddess);
	}
	
	public void yc(Goddess goddess)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.yc(goddess);
	}
	
	public void yc2(Goddess goddess)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.yc2(goddess);
	}
	
	public void yc3(Goddess goddess)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.yc3(goddess);
	}
	
	public void getjioucolsone(Goddess g)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.getjioucolsone(g);
	}
	public void getjioucolstwo(Goddess g)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.getjioucolstwo(g);
	}
	
	public void getjioucolsthree(Goddess g)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.getjioucolsthree(g);
	}
	
	public void ycjioubai(Goddess g)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.ycjioubai(g);
	}
	
	public void ycjioushi(Goddess g)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.ycjioushi(g);
	}
	
	public void ycjiouge(Goddess g)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.ycjiouge(g);
	}
	public void getbenwei(Goddess g)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.getbenweicolsone(g);
		dao.getbenweicolstwo(g);
		dao.getbenweicolsthree(g);
	}
	public void getwu(Goddess g)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.getwucolsone(g);
		dao.getwucolstwo(g);
		dao.getwucolsthree(g);
	}
	public void ycbenwei(Goddess g)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.ycbai(g);
		dao.ycshi(g);
		dao.ycge(g);
	}
	public void ycwuwei(Goddess g)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.ycwubai(g);
		dao.ycwushi(g);
		dao.ycwuge(g);
	}
	public void getshunxuwei(Goddess g)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.getshunxucolsone(g);
		dao.getshunxucolstwo(g);
		dao.getshunxucolsthree(g);
	}
	
	public void ycshunxuwei(Goddess g)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.ycshunxubai(g);
		dao.ycshunxushi(g);
		dao.ycshunxuge(g);
	}
	
	public void getchazhichusan(Goddess g)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.getchazhicolsone(g);
		dao.getchazhicolstwo(g);
		dao.getchazhicolsthree(g);
	}
	
	public void ycchazhichusan(Goddess g)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.ycchazhi(g);
		dao.ycchazhi2(g);
		dao.ycchazhi3(g);
	}
	
	public void liangchusan(Goddess g)throws Exception{
		
		ycsj(g);
		ycchazhichusan(g);
		
	}
	
	public void gethxchusan(Goddess g)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.gethxcolsone(g);
		dao.gethxcolstwo(g);
		dao.gethxcolsthree(g);
	}
	
	public void ychengxiangwei(Goddess g)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.ychx(g);
		dao.ychx2(g);
		dao.ychx3(g);
	}
	
	public void getsj(Goddess g)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.getsjcolsone(g);
		dao.getsjcolstwo(g);
		dao.getsjcolsthree(g);


		
		
		

	}
	public void ycsj(Goddess g)throws Exception{
		GoddessDao dao=new GoddessDao();
		dao.ycsj(g);
		dao.ycsj2(g);
		dao.ycsj3(g);
	}
}
