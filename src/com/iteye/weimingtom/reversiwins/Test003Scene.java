package com.iteye.weimingtom.reversiwins;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.util.Debug;

import android.graphics.Point;

public class Test003Scene extends GameScene {
	public static final String NAME = Test003Scene.class.getName();
	
	private static final int PIECE_X_NUM = 8;
	private static final int PIECE_Y_NUM = 8;
	private static final float PIECE_X_MIN = 8f; // 85
	private static final float PIECE_Y_MIN = 96f; // 96
	private static final float PIECE_X_DISTANCE = 38.5f;
	private static final float PIECE_Y_DISTANCE = 38.5f;
	
	private static final float BOARD_X_MIN = 5;
	private static final float BOARD_Y_MIN = 93;
	private static final float BOARD_X_DISTANCE = 309f / 8f;
	private static final float BOARD_Y_DISTANCE = 310f / 8f;	
	
	private static final float NUMBER_1_X = 84;
	private static final float NUMBER_1_Y = 42;
	private static final float NUMBER_2_X = 178;
	private static final float NUMBER_2_Y = 42;
	
	private static final float BUTTON_1_X = 15;
	private static final float BUTTON_1_Y = 423;
	private static final float BUTTON_2_X = 164;
	private static final float BUTTON_2_Y = 423;

	private static final float HISCORE_X = 162;
	private static final float HISCORE_Y = 3;
	private static final float HISCORE_W = 90;
	private static final float HISCORE_H = 30;	
	
	private static final float SCORE_X = 104;
	private static final float SCORE_Y = 228;	
	private static final float SCORE_W = 120;
	private static final float SCORE_H = 40;
	
	private static final float WARNING_X = 83;
	private static final float WARNING_Y = 201;
	
	private static final float THINKING_X = 83;
	private static final float THINKING_Y = 23;	
	
	private static final float DIALOG_X = 61;
	private static final float DIALOG_Y = 118;
	
	private static final float LEFT_POINTER_X = 68;
	private static final float LEFT_POINTER_Y = 47;
	
	private static final float RIGHT_POINTER_X = 234;
	private static final float RIGHT_POINTER_Y = 48;
	
	private static final boolean SHOW_PUT_TEXT = false;
	private static final String TEXT_DEFAULT = "INFO> ";
	
	private static final boolean TEST_DIALOG = false;
	
	private Sprite bg;
	private Sprite[][] blackPieces;
	private Sprite[][] whitePieces;
	private TiledSprite number1;
	private TiledSprite number2;
	private ChangeableText notification;
	private Sprite button1, button1Down;
	private Sprite button2, button2Down;
	private Sprite sprYouFirst, sprAIFirst, sprThinking;
	private Sprite sprDraw, sprYouWin, sprYouLose;
	private Sprite sprLeftPointer, sprRightPointer;
	
	private MultiNumberUtil hiScore, dialogScore;
	
	private GameData data;
	private Point compxy = new Point();
	private String tftext = TEXT_DEFAULT;

	private final static int STATUS_PLAYER_THINK = 1;
	private final static int STATUS_PLAYER_PUT = 2;
	private final static int STATUS_COMPUTER_THINK = 3;
	private final static int STATUS_COMPUTER_PUT = 4;
	private final static int STATUS_GAMEOVER = 5;
	private final static int STATUS_PLAYER_TIP = 6;
	private final static int STATUS_START_COMPUTER = 7;
	private final static int STATUS_START_PLAYER = 8;
	private int status;
	private int lastPutX;
	private int lastPutY;
	private long aniTime, startTime;
	private int[][] previousBoard;
	private int[][] tipBoard;
	
	private int difficulty;
	private int blackplayer;
	private int highestScore;
	
	private MersenneTwisterRandom mt;
	
	public Test003Scene() {
		
	}
	
	@Override
	protected void onInit(GameSceneContext context) {
		this.difficulty = context.difficulty;
		this.blackplayer = context.blackplayer;
		this.highestScore = context.highestScore;
		
		this.mt = new MersenneTwisterRandom();
		this.mt.init_genrand((int)System.currentTimeMillis());
		
		bg = new Sprite(0, 0, context.bgGame001Region);
		context.scene.attachChild(bg);
		blackPieces = new Sprite[PIECE_X_NUM][];
		whitePieces = new Sprite[PIECE_X_NUM][];
		for (int i = 0; i < PIECE_X_NUM; i++) {
			blackPieces[i] = new Sprite[PIECE_Y_NUM];
			whitePieces[i] = new Sprite[PIECE_Y_NUM];
			for (int j = 0; j < PIECE_Y_NUM; j++) {
				blackPieces[i][j] = new Sprite(0, 0, context.spriteBlack001Region);
				blackPieces[i][j].setVisible(false);
				blackPieces[i][j].setPosition(PIECE_X_MIN + i * PIECE_X_DISTANCE, PIECE_Y_MIN + j * PIECE_Y_DISTANCE);
				blackPieces[i][j].setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
				context.scene.attachChild(blackPieces[i][j]);
				whitePieces[i][j] = new Sprite(0, 0, context.spriteWhite001Region);
				whitePieces[i][j].setVisible(false);
				whitePieces[i][j].setPosition(PIECE_X_MIN + i * PIECE_X_DISTANCE, PIECE_Y_MIN + j * PIECE_Y_DISTANCE);
				whitePieces[i][j].setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
				context.scene.attachChild(whitePieces[i][j]);
			}
		}
		
		notification = new ChangeableText(0, 0, context.font, TEXT_DEFAULT, 50);
		notification.setPosition(0, GameSceneContext.CAMERA_HEIGHT - notification.getBaseHeight());
		if (SHOW_PUT_TEXT) {
			notification.setVisible(true);
		} else {
			notification.setVisible(false);
		}
		context.scene.attachChild(notification);
		
		number1 = new TiledSprite(NUMBER_1_X, NUMBER_1_Y, context.fontNumber001Region[0]);
		number1.setCurrentTileIndex(0);
		context.scene.attachChild(number1);
		
		number2 = new TiledSprite(NUMBER_2_X, NUMBER_2_Y, context.fontNumber001Region[1]);
		number2.setCurrentTileIndex(0);
		context.scene.attachChild(number2);
		
		button1 = new Sprite(BUTTON_1_X, BUTTON_1_Y, context.buttonQuit001Region);
		button1.setVisible(true);
		context.scene.attachChild(button1);
		
		button2 = new Sprite(BUTTON_2_X, BUTTON_2_Y, context.buttonNewgame001Region);
		button2.setVisible(true);
		context.scene.attachChild(button2);

		button1Down = new Sprite(BUTTON_1_X, BUTTON_1_Y, context.buttonQuit002Region);
		button1Down.setVisible(false);
		context.scene.attachChild(button1Down);
		
		button2Down = new Sprite(BUTTON_2_X, BUTTON_2_Y, context.buttonNewgame002Region);
		button2Down.setVisible(false);
		context.scene.attachChild(button2Down);

		sprAIFirst = new Sprite(WARNING_X, WARNING_Y, context.spriteAIFirst001Region);
		sprAIFirst.setVisible(false);
		context.scene.attachChild(sprAIFirst);

		sprYouFirst = new Sprite(WARNING_X, WARNING_Y, context.spriteYouFirst001Region);
		sprYouFirst.setVisible(false);
		context.scene.attachChild(sprYouFirst);
		
		sprThinking = new Sprite(THINKING_X, THINKING_Y, context.spriteThinking001Region);
		sprThinking.setVisible(false);
		context.scene.attachChild(sprThinking);
		
		sprDraw = new Sprite(DIALOG_X, DIALOG_Y, context.spriteDraw001Region);
		sprDraw.setVisible(false);
		context.scene.attachChild(sprDraw);

		sprYouWin = new Sprite(DIALOG_X, DIALOG_Y, context.spriteYouWin001Region);
		sprYouWin.setVisible(false);
		context.scene.attachChild(sprYouWin);
		
		sprYouLose = new Sprite(DIALOG_X, DIALOG_Y, context.spriteYouLose001Region);
		sprYouLose.setVisible(false);
		context.scene.attachChild(sprYouLose);

		sprLeftPointer = new Sprite(LEFT_POINTER_X, LEFT_POINTER_Y, context.spriteLeftPointer001Region);
		sprLeftPointer.setVisible(false);
		context.scene.attachChild(sprLeftPointer);
		
		sprRightPointer = new Sprite(RIGHT_POINTER_X, RIGHT_POINTER_Y, context.spriteRightPointer001Region);
		sprRightPointer.setVisible(false);
		context.scene.attachChild(sprRightPointer);
		
		hiScore = new MultiNumberUtil(context.scene, context.fontNumber002Region, 6);
		hiScore.setLocation(HISCORE_X, HISCORE_Y, HISCORE_W, HISCORE_H);
		hiScore.setShowPrefix(true);
		hiScore.setNumber(this.highestScore);
		
		dialogScore = new MultiNumberUtil(context.scene, context.fontNumber003Region, 6);
		dialogScore.setLocation(SCORE_X, SCORE_Y, SCORE_W, SCORE_H);
		dialogScore.setShowPrefix(false);
		dialogScore.setNumber(0);
		dialogScore.setVisible(false);
		
		data = new GameData();
		if (!TEST_DIALOG) {
			resetGame();
		} else {
			data.initialize();
			data.setDifficulty(this.difficulty);
			testGameSet();
			status = STATUS_GAMEOVER;
		}
	}
	
	@Override
	protected void onQuit(GameSceneContext context) {
		context.highestScore = this.highestScore;
		
		context.scene.detachChild(bg);
		bg = null;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				context.scene.detachChild(blackPieces[i][j]);
				blackPieces[i][j] = null;
				context.scene.detachChild(whitePieces[i][j]);
				whitePieces[i][j] = null;
			}
			blackPieces[i] = null;
			whitePieces[i] = null;
		}
		blackPieces = null;
		whitePieces = null;
		
		context.scene.detachChild(notification);
		notification = null;
		
		context.scene.detachChild(number1);
		number1 = null;

		context.scene.detachChild(number2);
		number2 = null;
		
		context.scene.detachChild(button1);
		button1 = null;
		
		context.scene.detachChild(button2);
		button2 = null;
		
		context.scene.detachChild(button1Down);
		button1Down = null;
		
		context.scene.detachChild(button2Down);
		button2Down = null;
		
		context.scene.detachChild(sprAIFirst);
		sprAIFirst = null;		
		
		context.scene.detachChild(sprYouFirst);
		sprYouFirst = null;	
		
		context.scene.detachChild(sprThinking);
		sprThinking = null;	

		context.scene.detachChild(sprDraw);
		sprDraw = null;		
		
		context.scene.detachChild(sprYouWin);
		sprYouWin = null;	
		
		context.scene.detachChild(sprYouLose);
		sprYouLose = null;	

		context.scene.detachChild(sprLeftPointer);
		sprLeftPointer = null;	
		
		context.scene.detachChild(sprRightPointer);
		sprRightPointer = null;	
		
		hiScore.release(context.scene);
		hiScore = null;
		
		dialogScore.release(context.scene);
		dialogScore = null;
	}

	@Override
	protected void onMouseDown(GameSceneContext context, float stageX, float stageY) {
		if (status == STATUS_GAMEOVER) {
			this.sprDraw.setVisible(false);
			this.sprYouWin.setVisible(false);
			this.sprYouLose.setVisible(false);
			this.dialogScore.setVisible(false);
			resetGame();
			return;
		}
		if (button1.contains(stageX, stageY)) {
			button1.setVisible(false);
			button1Down.setVisible(true);
		}
		if (button2.contains(stageX, stageY)) {
			button2.setVisible(false);
			button2Down.setVisible(true);			
		}
	}
	
	@Override
	protected void onMouseMove(GameSceneContext context, float stageX, float stageY) {
		if (button1.contains(stageX, stageY)) {
			button1.setVisible(false);
			button1Down.setVisible(true);
		} else {
			button1.setVisible(true);
			button1Down.setVisible(false);			
		}
		if (button2.contains(stageX, stageY)) {
			button2.setVisible(false);
			button2Down.setVisible(true);			
		} else {
			button2.setVisible(true);
			button2Down.setVisible(false);				
		}
	}
	
	@Override
	protected void onMouseUp(GameSceneContext context, float stageX, float stageY) {
		if (button1.contains(stageX, stageY)) {
			button1.setVisible(true);
			button1Down.setVisible(false);	
			this.nextSceneType = Test006Scene.NAME;
			return;
		}
		if (button2.contains(stageX, stageY)) {
			button2.setVisible(true);
			button2Down.setVisible(false);	
			if (status != STATUS_START_COMPUTER && status != STATUS_START_PLAYER) {
				resetGame();
			}
			/*
			if (data.isPlayerTurn == false) { 
				status = STATUS_COMPUTER_THINK;
			}
			*/
			return;			
		}
		if (data.isPlayerTurn == false) {
			return;
		}
		int x = (int)Math.floor((stageX - BOARD_X_MIN) / BOARD_X_DISTANCE);
		int y = (int)Math.floor((stageY - BOARD_Y_MIN) / BOARD_Y_DISTANCE);
		//Debug.e("x = " + x + ",y = " + y);
		if (x < 0 || x > 7 || y < 0 || y > 7) { 
			return;
		}
		if (data.checkStone(x, y, (data.isPlayerBlack == true) ? 1 : 2) == false) {
			if (status == STATUS_PLAYER_THINK) {
				status = STATUS_PLAYER_TIP;
			}
			return;
		}
		
		if (status == STATUS_PLAYER_THINK) {
			status = STATUS_PLAYER_PUT;
			lastPutX = x;
			lastPutY = y;
		}
	}
			
	private void resetGame() {
		data.initialize();
		data.setDifficulty(this.difficulty);
		int r = 0;
		switch (blackplayer) {
		case GameSceneContext.BLACK_RANDOM:
			if (GameSceneContext.USE_MT_RANDOM) {
				r = this.mt.nextInt(0, 1);
			} else {
				r = (int)Math.floor(Math.random() * 2);
			}
			break;
			
		case GameSceneContext.BLACK_COMPUTER:
			r = 1;
			break;
			
		case GameSceneContext.BLACK_HUMAN:
			r = 0;
			break;
		}
		if (r == 0) {
			data.isPlayerBlack = true;
			data.isPlayerTurn = true;
			//this.status = STATUS_PLAYER_THINK;
			this.status = STATUS_START_PLAYER;
			sprLeftPointer.setVisible(true);
			sprRightPointer.setVisible(false);
			this.putText("It's your turn, You are black.");
		} else {
			data.isPlayerBlack = false;
			data.isPlayerTurn = false;
			//this.status = STATUS_COMPUTER_THINK;
			this.status = STATUS_START_COMPUTER;
			sprLeftPointer.setVisible(false);
			sprRightPointer.setVisible(true);
			this.putText("It's turn of computer.");
		}
	}
	
	@Override
	protected void onUpdate(GameSceneContext context) {
		switch (status) {
		case STATUS_PLAYER_THINK:
			break;
			
		case STATUS_GAMEOVER:
			break;
			
		case STATUS_PLAYER_PUT:
			if (aniTime == 0) {
				aniTime = System.currentTimeMillis();
				previousBoard = data.cloneBoard();
				data.putStone(new Point(lastPutX, lastPutY), (data.isPlayerBlack == true) ? 1 : 2);
			} else {
				long curTime = System.currentTimeMillis();
				if (curTime - aniTime > 1000) {
					aniTime = 0;
					previousBoard = null;
					if (data.canPutStone((data.isPlayerBlack) ? 2 : 1) == true) {
						data.isPlayerTurn = false;
						putText("It's computer's turn.");
						status = STATUS_COMPUTER_THINK;
						this.sprThinking.setVisible(true);
					} else {
						if (data.canPutStone((data.isPlayerBlack) ? 1 : 2) == false) {
							gameSet();
							status = STATUS_GAMEOVER;
						} else {
							putText("Computer give up, it's your turn。you are"+((data.isPlayerBlack == true) ? "black" : "white") + ".");
							status = STATUS_PLAYER_THINK;
						}
					}
				}
			}
			break;
			
		case STATUS_COMPUTER_THINK:
			//this.sprThinking.setVisible(true);
			this.computerTurn();
			status = STATUS_COMPUTER_PUT;
			this.sprThinking.setVisible(false);
			break;
			
		case STATUS_COMPUTER_PUT:
			if (aniTime == 0) {
				aniTime = System.currentTimeMillis();
				previousBoard = data.cloneBoard();
				data.putStone(compxy, (data.isPlayerBlack) ? 2 : 1);
			} else {
				long curTime = System.currentTimeMillis();
				if (curTime - aniTime > 1000) {
					aniTime = 0;
					previousBoard = null;
					if (data.canPutStone((data.isPlayerBlack) ? 1 : 2) == true) {
						data.isPlayerTurn = true;
						putText("It's your turn. you are " + ((data.isPlayerBlack == true) ? "black" : "white") + ".");
						status = STATUS_PLAYER_THINK;
					} else {
						if (data.canPutStone((data.isPlayerBlack) ? 2 : 1) == false) {
							gameSet();
							status = STATUS_GAMEOVER;
						} else {
							putText("It's computer turn。");
							status = STATUS_COMPUTER_THINK;
							this.sprThinking.setVisible(true);
						}
					}
				}
			}
			break;
			
		case STATUS_PLAYER_TIP:
			if (aniTime == 0) {
				aniTime = System.currentTimeMillis();
				putText("Cannot put stone here!");
				tipBoard = getTipBoard();
			} else {
				long curTime = System.currentTimeMillis();
				if (curTime - aniTime > 1000) {
					aniTime = 0;
					tipBoard = null;
					//System.out.println("=======================");
					status = STATUS_PLAYER_THINK;
				}
			}
			break;
			
		case STATUS_START_COMPUTER:
			if (startTime == 0) {
				startTime = System.currentTimeMillis();
				this.sprAIFirst.setVisible(true);
			} else {
				long curTime = System.currentTimeMillis();
				if (curTime - startTime > 1000) {
					startTime = 0;
					this.sprAIFirst.setVisible(false);
					status = STATUS_COMPUTER_THINK;
					this.sprThinking.setVisible(true);
				}
			}
			break;
			
		case STATUS_START_PLAYER:
			if (startTime == 0) {
				startTime = System.currentTimeMillis();
				this.sprYouFirst.setVisible(true);
			} else {
				long curTime = System.currentTimeMillis();
				if (curTime - startTime > 1000) {
					startTime = 0;
					this.sprYouFirst.setVisible(false);
					status = STATUS_PLAYER_THINK;
				}
			}
			break;
		}
	}
	
	private int[][] getTipBoard() {
		int[][] result = data.cloneBoard();
		int tipColor = data.isPlayerBlack == true ? 5 : 6;//half black, half white
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (result[i][j] == 0) {//empty
					if (data.checkStone(i, j, (data.isPlayerBlack == true) ? 1 : 2)) {
						result[i][j] = tipColor;
					}
				}
			}
		}
		return result;
	}
	
	private int[][] getRenderBoard() {
		if (tipBoard == null && previousBoard == null) {
			return data.board;
		} else if (tipBoard != null) {
			return tipBoard;
		} else {
			int[][] arr = new int[8][];
			for (int i = 0; i < 8; i++) {
				arr[i] = new int[8];
				for (int j = 0; j < 8; j++) {
					if (previousBoard[i][j] == 0 || 
						previousBoard[i][j] == data.board[i][j]) {
						arr[i][j] = data.board[i][j];
					} else if (previousBoard[i][j] == 1){
						arr[i][j] = 3;//black -> white
					} else if (previousBoard[i][j] == 2) {
						arr[i][j] = 4;//white -> black
					}
				}
			}
			return arr;
		}
	}
	
	@Override
	protected void onRender(GameSceneContext context) {
		int[][] board = getRenderBoard();
		long time = System.currentTimeMillis() - aniTime;
		float alpha = 0f;
		boolean isFadein = true;
		if (tipBoard != null) {
			if (time >= 0 && time <= 500) {
				alpha = (float)(time) / 1000.0f; //target is 0.5
			} else if (time > 500 && time <= 1000) {
				alpha = (float)(1000 - time) / 1000.0f;
			} else {
				alpha = 0.5f;
			}
		} else {
			if (time >= 0 && time <= 500) {
				isFadein = true;
				alpha = (float)(500 - time) / 500.0f;
			} else if (time > 500 && time <= 1000){
				isFadein = false;
				alpha = (float)(time - 500) / 500.0f;
			} else {
				isFadein = false;
				alpha = 1f;
			}			
		}
		//if (status == STATUS_PLAYER_PUT) {
		//	System.out.println("==========>alpha : " + alpha);
		//}
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				switch (board[i][j]) {
				case 0://empty
					blackPieces[i][j].setVisible(false);
					blackPieces[i][j].setAlpha(0.0f);
					whitePieces[i][j].setVisible(false);
					whitePieces[i][j].setAlpha(0.8f);
					break;
					
				case 1://black
					blackPieces[i][j].setVisible(true);
					blackPieces[i][j].setAlpha(1.0f);
					whitePieces[i][j].setVisible(false);
					whitePieces[i][j].setAlpha(0.0f);
					break;
					
				case 2://white
					blackPieces[i][j].setVisible(false);
					blackPieces[i][j].setAlpha(0.0f);
					whitePieces[i][j].setVisible(true);
					whitePieces[i][j].setAlpha(1.0f);
					break;
					
				case 3://black -> white
					if (isFadein) {
						blackPieces[i][j].setVisible(true);
						blackPieces[i][j].setAlpha(alpha);
						whitePieces[i][j].setVisible(false);
						whitePieces[i][j].setAlpha(0.0f);
					} else {
						blackPieces[i][j].setVisible(false);
						blackPieces[i][j].setAlpha(0.0f);
						whitePieces[i][j].setVisible(true);
						whitePieces[i][j].setAlpha(alpha);
					}
					break;
					
				case 4://white -> black
					if (isFadein) {
						blackPieces[i][j].setVisible(false);
						blackPieces[i][j].setAlpha(0.0f);
						whitePieces[i][j].setVisible(true);
						whitePieces[i][j].setAlpha(alpha);
					} else {
						blackPieces[i][j].setVisible(true);
						blackPieces[i][j].setAlpha(alpha);
						whitePieces[i][j].setVisible(false);
						whitePieces[i][j].setAlpha(0.0f);
					}
					break;
					
				case 5://half black
					blackPieces[i][j].setVisible(true);
					blackPieces[i][j].setAlpha(alpha);
					whitePieces[i][j].setVisible(false);
					whitePieces[i][j].setAlpha(0.0f);
					break;
					
				case 6://half white
					blackPieces[i][j].setVisible(false);
					blackPieces[i][j].setAlpha(0.0f);
					whitePieces[i][j].setVisible(true);
					whitePieces[i][j].setAlpha(alpha);
					break;
				}
			}
		}
		notification.setText(tftext);
		number1.setCurrentTileIndex(data.countStone(1));
		number2.setCurrentTileIndex(data.countStone(2));
	}

	private void testGameSet() {
		int black = (int)(Math.random() * 1000);
		int white = (int)(Math.random() * 1000);
		gameSetCalc(black, white);
	}
	
	private void gameSet() {
		int black = data.countStone(1);
		int white = data.countStone(2);
		gameSetCalc(black, white);
	}

	private int getScore() {
		float gain = 1.0f;
		int black = data.countStone(1);
		int white = data.countStone(2);
		switch (this.difficulty) {
		case GameData.DIFFICULTY_LOW:
			gain = 1.0f;
			break;

		case GameData.DIFFICULTY_MEDIUM:
			gain = 2.0f;
			break;
			
		case GameData.DIFFICULTY_HIGH:
			gain = 4.0f;
			break;
		}
		if (data.isPlayerBlack) {
			if (black > white) { //win
				return (int)(black * gain * 1.5);
			} else {
				return (int)(black * gain);
			}
		} else {
			if (white > black) { //win
				return (int)(white * gain * 2.0);
			} else { 
				return (int)(white * gain);
			}
		}
	}
	
	private void gameSetCalc(int black, int white) {
		if (black > white) {
			if (data.isPlayerBlack) {
				this.sprDraw.setVisible(false);
				this.sprYouWin.setVisible(true);
				this.sprYouLose.setVisible(false);
			} else {
				this.sprDraw.setVisible(false);
				this.sprYouWin.setVisible(false);
				this.sprYouLose.setVisible(true);
			}
			putText("black win");
		} else if (black < white) {
			if (data.isPlayerBlack) {
				this.sprDraw.setVisible(false);
				this.sprYouWin.setVisible(false);
				this.sprYouLose.setVisible(true);
			} else {
				this.sprDraw.setVisible(false);
				this.sprYouWin.setVisible(true);
				this.sprYouLose.setVisible(false);
			}
			putText("white win");
		} else {
			this.sprDraw.setVisible(true);
			this.sprYouWin.setVisible(false);
			this.sprYouLose.setVisible(false);
			putText("draw");
		}
		this.dialogScore.setVisible(true);
		int score = this.getScore();
		this.dialogScore.setNumber(score);
		if (score > this.highestScore) {
			this.highestScore = score;
		}
		this.hiScore.setNumber(this.highestScore);
	}
	
	private void computerTurn() {
		data.computerTurn(compxy, (data.isPlayerBlack == true) ? 2 : 1);
	}
	
	private void putText(String text) {
		Debug.d(text);
		if (text != null) {
			tftext = TEXT_DEFAULT + text;
		} else {
			tftext = TEXT_DEFAULT;
		}
	}

	@Override
	protected boolean onBack(GameSceneContext context) {
		this.nextSceneType = Test006Scene.NAME;
		return true;
	}
}
