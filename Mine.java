package minesweeper;
import java.math.*;

public class Mine {
	
	int x;
	int y;

	public Mine(){
		x = (int)((Math.random() * 600) % 24) * 25;
		y = (int)((Math.random() * 600) % 24) * 25;
	}
}
