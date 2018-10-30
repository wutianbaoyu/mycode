package ruanjianceshi2;

import static org.junit.Assert.*;

import org.junit.Test;

public class test1 {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	@Test
	public void ruojianzhuang(){
		assertEquals(1, new nextdate().nextDate(2014, 2, 29));
		
	}
}
