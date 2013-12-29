package com.iteye.weimingtom.reversiwins;

import android.graphics.Point;

public class GameData {
	public final static int DIFFICULTY_LOW = 0;
	public final static int DIFFICULTY_MEDIUM = 1;
	public final static int DIFFICULTY_HIGH = 2;
	private int difficulty = DIFFICULTY_LOW;
	
	private final static int[][][] POINTS; //points table int[,,]
	static {
		POINTS = new int[8][][];
		for (int x = 0; x < 8; x++) {
			POINTS[x] = new int[8][];
			for (int y = 0; y < 8; y++) {
				POINTS[x][y] = new int[2];
				POINTS[x][y][0] = -1;
				POINTS[x][y][1] = 0;
			}
		}
		POINTS[0][0][0] = POINTS[7][7][0] = POINTS[7][0][0] = POINTS[0][7][0] = 1000;
		POINTS[1][1][0] = POINTS[6][6][0] = POINTS[6][1][0] = POINTS[1][6][0] = -50;
		POINTS[1][0][0] = POINTS[0][1][0] = POINTS[6][7][0] = POINTS[7][6][0] =
		POINTS[7][1][0] = POINTS[1][7][0] = POINTS[6][0][0] = POINTS[0][6][0] = -30;
		POINTS[3][3][0] = POINTS[3][4][0] = POINTS[4][4][0] = POINTS[4][3][0] = 5;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				POINTS[x][y][1] = 1;
			}
		}
	}
	
	public boolean isPlayerBlack;
	public boolean isPlayerTurn;
	public int[][] board; //int[x, y]
	private int count;
	
	public GameData() {
		//0=empty 1=black 2=white
		board = new int[8][];
		for (int x = 0; x < 8; x++) {
			board[x] = new int[8];
			for (int y = 0; y < 8; y++) {
				board[x][y] = 0;
			}
		}
	}
	
	public void initialize() {
		isPlayerBlack = false;
		isPlayerTurn = false;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				board[x][y] = 0;
			}
		}
		board[3][4] = board[4][3] = 1;
		board[3][3] = board[4][4] = 2;
		count = 0;
	}
	
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	public boolean canPutStone(int s) {
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (checkStone(x, y, s) == true) {
					return true;
				}
			}
		}
		return false;
	}
	
	public int countStone(int s) {
		int count = 0;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (board[x][y] == s) 
					count++;
			}
		}
		return count;
	}
	
	private static boolean checkStoneBoard(int x, int y, int s, int[][] b) {
		int es;
		int x2;
		int y2;
		
		if (b[x][y] != 0) {
			return false; //it's not empty, cannot place here.
		}
		//expand to 8 directions
		es = (s == 1) ? 2 : 1; //enemy's piece
		for (int vx = -1; vx < 2; vx++) {
			for (int vy = -1; vy < 2; vy++) {
				if (vx == 0 && vy == 0) {
					continue;
				}
				if (x + vx < 0 || x + vx > 7 || y + vy < 0 || y + vy > 7) {
					continue;
				}
				if (b[x + vx][y + vy] != es) { 
					continue;
				}
				//When encountering enemy in that direction,
				//it's better that there is my piece in the expand line.
				x2 = x + vx * 2;
				y2 = y + vy * 2;
				while (true) {
					if (x2 < 0 || x2 > 7 || y2 < 0 || y2 > 7) {
						break;//Cannot be out of range. 
					}
					if (b[x2][y2] == 0) {
						break;//Cannot sencounter empty.
					}
					if (b[x2][y2] == s) { 
						return true;//between two.
					}
					x2 += vx; 
					y2 += vy;
				}
			}
		}
		return false;
	}
	
	public boolean checkStone(int x, int y, int s) {
		return checkStoneBoard(x, y, s, board);
	}
	
	private static void putStoneBoard(Point xy, int s, int[][] b) {
		//Use CheckStoneBoard
		int x = xy.x;
		int y = xy.y;
		
		b[x][y] = s;
		int es = (s == 1) ? 2 : 1;//enimy's piece
		
		for (int vx = -1; vx < 2; vx++) {
			for (int vy = -1; vy < 2; vy++) {
				if (vx == 0 && vy == 0) { 
					continue;
				}
				if (x + vx < 0 || x + vx > 7 || y + vy < 0 || y + vy > 7) {
					continue;
				}
				if (b[x + vx][y + vy] != es) { 
					continue;
				}
				int x2 = x + vx * 2;
				int y2 = y + vy * 2;
				while (true) {
					if (x2 < 0 || x2 > 7 || y2 < 0 || y2 > 7) {
						break;
					}
					if (b[x2][y2] == 0) {
						break;
					}
					if (b[x2][y2] == s) {
						int x3 = x + vx;
						int y3 = y + vy;
						while (x3 != x2 || y3 != y2) {
							b[x3][y3] = s;
							x3 += vx; 
							y3 += vy;
						}
						break;
					}
					x2 += vx; 
					y2 += vy;
				}

			}
		}
		return;
	}
	
	public void putStone(Point xy, int s) {
		//trace("PutStone(" + x + "," + y + "," + s + ")");
		putStoneBoard(xy, s, board);
		count++;
	}
	
	public void computerTurn(Point sxy, int stone) {
		think(sxy, stone, board, ((count > 50) ? 1 : 0), difficulty);
	}
	
	private static void think(Point sxy, int st, int[][] b, int mode, int difficulty) {
		//simple vertical search
		int score = -9999;
		int xx = -1;
		int yy = -1; //highest point and place.
		int[][] brd = cloneArray(b);
		
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (checkStoneBoard(x, y, st, brd) == true) {
					int sc = 0;
					//origin : when mode==0, level==5, when mode==1, level==10
					//now : when mode==0, level==2, when mode==1, level==5
					switch (difficulty) {
					case 0:
						if (mode == 0) {
							sc = calcScore(x, y, st, brd, 0, 2, score);
						} else {
							sc = calcScore(x, y, st, brd, 1, 5, score);
						}
						break;
						
					case 1:
						if (mode == 0) {
							sc = calcScore(x, y, st, brd, 0, 3, score);
						} else {
							sc = calcScore(x, y, st, brd, 1, 8, score);
						}
						break;
						
					case 2:
						if (mode == 0) {
							sc = calcScore(x, y, st, brd, 0, 5, score);
						} else {
							sc = calcScore(x, y, st, brd, 1, 10, score);
						}
						break;						
					}
					if (sc > score) {
						xx = x; 
						yy = y; 
						score = sc;                        
					}
				}
			}
		}
		sxy.x = xx; 
		sxy.y = yy;
	}
	
	//TODO: here consume CPU
	private static int calcScore(int x, int y, int st, int[][] b, int mode, int level, int bscore) {
		//compute score
		int[][] cb = cloneArray(b);
		if (x != -1) {
			putStoneBoard(new Point(x, y), st, cb);
		}
		if (level == 0) {
			return analyzeBoard(cb, st, mode);
		}
		int score = -9999;
		//FIXME:
		//int xx = -1;
		//int yy = -1;
		int est = (st == 1) ? 2 : 1;
		for (int y2 = 0; y2 < 8; y2++) {
			for (int x2 = 0; x2 < 8; x2++) {
				if (checkStoneBoard(x2, y2, est, cb) == true) {
					int sc = calcScore(x2, y2, est, cb, mode, level - 1, score);
					if ( -sc <= bscore) {
						return -sc;
					}
					if (sc > score) {
						//xx = x2; 
						//yy = y2; 
						score = sc;
					}
				}
			}
		}
		if (score == -9999) {
			return -calcScore(-1, -1, est, cb, mode, level - 1, score);
		}
		return -score;
	}
	
	private static int analyzeBoard(int[][] b, int st, int mode) {
		int score = 0;
		boolean mystoneexist = false;
		boolean enemystoneexist = false;
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (b[x][y] == 0) {
					continue;
				}
				if (b[x][y] == st) {
					mystoneexist = true;
					score += POINTS[x][y][mode];
				} else {
					enemystoneexist = true;
					score -= POINTS[x][y][mode];
				}
			}
		}
		if (!mystoneexist) {
			return -5000; // lose completely
		}
		if (!enemystoneexist) {
			return 5000;// win completely
		}
		return score;
	}
	
	private static int[][] cloneArray(int[][] b) {
		int[][] arr = new int[8][];
		for (int i = 0; i < 8; i++) {
			arr[i] = new int[8];
			for (int j = 0; j < 8; j++) {
				arr[i][j] = b[i][j];
			}
		}
		return arr;
	}
	
	public int[][] cloneBoard() {
		return cloneArray(board);
	}
}
