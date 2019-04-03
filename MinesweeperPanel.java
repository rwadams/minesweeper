package minesweeper;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
@SuppressWarnings("serial")

public class MinesweeperPanel extends JPanel implements MouseListener {
	
	private int windowWidth = 600;
	private int squareSize = 25;
	private int difficulty = 1;
	private int numSquaresRow = windowWidth/squareSize;
	private Mine[] mines = populateMines(difficulty);
	private int[][] grid = new int[numSquaresRow][numSquaresRow];
	
	
	
	public MinesweeperPanel(){
		setPreferredSize(new Dimension(windowWidth,windowWidth));
		this.addMouseListener(this);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
		
		//determines what should be drawn there
		labelSquares();
		
		//draws squares
		for(int i = 0; i < windowWidth; i += squareSize){
			for(int j = 0; j < windowWidth; j += squareSize){
				g.setColor(Color.GRAY);	
				g.fillRect(i, j, squareSize, squareSize);
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(i + 2, j + 2, squareSize - 4, squareSize - 4);
				g.setColor(Color.WHITE);
				//draws vertical lines
				g.drawLine(i, 0, i, windowWidth);
				//draws horizontal lines
				g.drawLine(0, j, windowWidth, j);
			}
		}
	
		//draws labels/pictures
		g.setColor(Color.BLACK);
		for(int i = 0; i < numSquaresRow; i++){
			for(int j = 0; j < numSquaresRow; j++){
				if(grid[i][j] == -1){
					//draw image of mine
				} else {
					g.drawString("" + grid[i][j], j * squareSize + 8, i * squareSize + squareSize - 6);
				}
			}
		}
		
	}
	// adds mines to the board
	// May need to deal with duplicate mines
	// Also may need to create new object Mine() 
	public Mine[] populateMines(int difficulty){
		//difficulty given by user and determines number of mines to add
		int mineCount;
		if(difficulty == 1){
			mineCount = 60;
		} else if(difficulty == 2){
			mineCount = 80;
		} else {
			mineCount = 100;
		}
		//array of mines to be returned
	    Mine[] mineArray = new Mine[mineCount];
	    //loop through creating new mines to populate mineArray
	    for(int i = 0; i < mineCount; i++){
	    	Mine m = new Mine();
	    	mineArray[i] = m;
	    	
	    }
	    return mineArray;
	}
	
	//labels grid as either mine, blank, or number of mines adjacent
	public void labelSquares(){
		int mineCount = 0; //number of mines adjacent
		for(int i = 0; i < mines.length; i++){
			grid[mines[i].x / squareSize][mines[i].y / squareSize] = -1;
		}
		for(int i = 0; i < numSquaresRow; i++){
			for(int j = 0; j < numSquaresRow; j++){
				if(grid[i][j] != -1){
					if((i == 0)){ //if square in first row
						if((j == 0)){//if square in first column
							if(grid[i][j + 1] == -1){
								mineCount++;
							}
							if(grid[i + 1][j + 1] == -1){
								mineCount++;
							}
							if(grid[i + 1][j] == -1){
								mineCount++;
							}
						} else if((j == numSquaresRow - 1)){//if square in last column
							if(grid[i][j - 1] == -1){
								mineCount++;
							}
							if(grid[i + 1][j - 1] == -1){
								mineCount++;
							}
							if(grid[i + 1][j] == -1){
								mineCount++;
							}
						} else {
							if(grid[i][j - 1] == -1){
								mineCount++;
							}
							if(grid[i + 1][j - 1] == -1){
								mineCount++;
							}
							if(grid[i + 1][j] == -1){
								mineCount++;
							}
							if(grid[i + 1][j + 1] == -1){
								mineCount++;
							}
							if(grid[i][j + 1] == -1){
								mineCount++;
							}
						}
					} else if((i == numSquaresRow - 1)){ //if square in last row
                        if((j == 0)){//if square in first column
                        	if(grid[i - 1][j] == -1){
								mineCount++;
							}
							if(grid[i - 1][j + 1] == -1){
								mineCount++;
							}
							if(grid[i][j + 1] == -1){
								mineCount++;
							}
						} else if((j == numSquaresRow - 1)){//if square in last column
							if(grid[i - 1][j] == -1){
								mineCount++;
							}
							if(grid[i - 1][j - 1] == -1){
								mineCount++;
							}
							if(grid[i][j - 1] == -1){
								mineCount++;
							}
						} else {
							if(grid[i][j - 1] == -1){
								mineCount++;
							}
							if(grid[i - 1][j - 1] == -1){
								mineCount++;
							}
							if(grid[i - 1][j] == -1){
								mineCount++;
							}
							if(grid[i - 1][j + 1] == -1){
								mineCount++;
							}
							if(grid[i][j + 1] == -1){
								mineCount++;
							}
						}
					} else if(j == 0){ //square in first column(not corner)
						if(grid[i - 1][j] == -1){
							mineCount++;
						}
						if(grid[i - 1][j + 1] == -1){
							mineCount++;
						}
						if(grid[i][j + 1] == -1){
							mineCount++;
						}
						if(grid[i + 1][j + 1] == -1){
							mineCount++;
						}
						if(grid[i + 1][j] == -1){
							mineCount++;
						}
					} else if(j == numSquaresRow - 1){ //square in last column(not corner)
						if(grid[i - 1][j] == -1){
							mineCount++;
						}
						if(grid[i - 1][j - 1] == -1){
							mineCount++;
						}
						if(grid[i][j - 1] == -1){
							mineCount++;
						}
						if(grid[i + 1][j - 1] == -1){
							mineCount++;
						}
						if(grid[i + 1][j] == -1){
							mineCount++;
						}
					} else { //square not on edge
						if(grid[i - 1][j] == -1){
							mineCount++;
						}
						if(grid[i - 1][j + 1] == -1){
							mineCount++;
						}
						if(grid[i][j + 1] == -1){
							mineCount++;
						}
						if(grid[i + 1][j + 1] == -1){
							mineCount++;
						}
						if(grid[i + 1][j] == -1){
							mineCount++;
						}
						if(grid[i + 1][j - 1] == -1){
							mineCount++;
						}
						if(grid[i][j - 1] == -1){
							mineCount++;
						}
						if(grid[i - 1][j - 1] == -1){
							mineCount++;
						}
					}
					grid[i][j] = mineCount;
				}
				mineCount = 0;
			}
		}
	}
	
	//reveals the square to uncover mine, number or blank
	public void revealSquare(int x, int y){
		
		//if blank: reveal all adjacent blanks (recursive function)
		//if mine: end game
		//else, reveal number of mines adjacent to that square
		
	}
	
	//returns true if the coordinates contain a mine, false if not
	public Boolean isMine(int x, int y){
		for(int i = 0; i < mines.length; i++){
			if(x == mines[i].x && y == mines[i].y){
				return true;
			}
		}
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//gets coordinates
		int xmouse = e.getX();
		int ymouse = e.getY();
		//gets which square
		int xsquare = (xmouse % (windowWidth/squareSize)) * squareSize;
		int ysquare = (ymouse % (windowWidth/squareSize)) * squareSize;
		revealSquare(xsquare, ysquare);
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
