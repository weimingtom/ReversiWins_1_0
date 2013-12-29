package com.iteye.weimingtom.reversiwins;

import org.anddev.andengine.entity.sprite.Sprite;

public class Test010Scene extends GameScene {
	public static final String NAME = Test010Scene.class.getName();
		
	private final static float BUTTON_1_X = 37;
	private final static float BUTTON_1_Y = 228;	
	
	private final static float BUTTON_2_X = 123;
	private final static float BUTTON_2_Y = 230;//228; //230;
	
	private final static float BUTTON_3_X = 242;
	private final static float BUTTON_3_Y = 226;//228; //226;	
	
	private final static float BUTTON_4_X = 21;
	private final static float BUTTON_4_Y = 331;//333;//331;	
	
	private final static float BUTTON_5_X = 118;
	private final static float BUTTON_5_Y = 333;
	
	private final static float BUTTON_6_X = 233;
	private final static float BUTTON_6_Y = 333;	
	
	private final static float BUTTON_OK_X = 100;
	private final static float BUTTON_OK_Y = 423;
	
	private Sprite bg;
	private Sprite buttonLow, buttonLowDown;
	private Sprite buttonMedium, buttonMediumDown;
	private Sprite buttonHigh, buttonHighDown;
	private Sprite buttonRandom, buttonRandomDown;
	private Sprite buttonComputer, buttonComputerDown;
	private Sprite buttonHuman, buttonHumanDown;
	private Sprite buttonOK, buttonOKDown;
	
	public Test010Scene() {
		
	}
	
	@Override
	protected void onInit(GameSceneContext context) {
		bg = new Sprite(0, 0, context.bgSettings001Region);
		context.scene.attachChild(bg);
		
		buttonLow = new Sprite(BUTTON_1_X, BUTTON_1_Y, context.buttonLow001Region);
		context.scene.attachChild(buttonLow);
		
		buttonMedium = new Sprite(BUTTON_2_X, BUTTON_2_Y, context.buttonMedium001Region);
		context.scene.attachChild(buttonMedium);
		
		buttonHigh = new Sprite(BUTTON_3_X, BUTTON_3_Y, context.buttonHigh001Region);
		context.scene.attachChild(buttonHigh);
		
		buttonRandom = new Sprite(BUTTON_4_X, BUTTON_4_Y, context.buttonRandom001Region);
		context.scene.attachChild(buttonRandom);
		
		buttonComputer = new Sprite(BUTTON_5_X, BUTTON_5_Y, context.buttonComputer001Region);
		context.scene.attachChild(buttonComputer);
		
		buttonHuman = new Sprite(BUTTON_6_X, BUTTON_6_Y, context.buttonHuman001Region);
		context.scene.attachChild(buttonHuman);
	
		buttonLowDown = new Sprite(BUTTON_1_X, BUTTON_1_Y, context.buttonLow002Region);
		context.scene.attachChild(buttonLowDown);
		
		buttonMediumDown = new Sprite(BUTTON_2_X, BUTTON_2_Y, context.buttonMedium002Region);
		context.scene.attachChild(buttonMediumDown);
		
		buttonHighDown = new Sprite(BUTTON_3_X, BUTTON_3_Y, context.buttonHigh002Region);
		context.scene.attachChild(buttonHighDown);
		
		buttonRandomDown = new Sprite(BUTTON_4_X, BUTTON_4_Y, context.buttonRandom002Region);
		context.scene.attachChild(buttonRandomDown);
		
		buttonComputerDown = new Sprite(BUTTON_5_X, BUTTON_5_Y, context.buttonComputer002Region);
		context.scene.attachChild(buttonComputerDown);
		
		buttonHumanDown = new Sprite(BUTTON_6_X, BUTTON_6_Y, context.buttonHuman002Region);
		context.scene.attachChild(buttonHumanDown);
		
		
		buttonOK = new Sprite(BUTTON_OK_X, BUTTON_OK_Y, context.buttonOK001Region);
		buttonOK.setVisible(true);
		context.scene.attachChild(buttonOK);

		buttonOKDown = new Sprite(BUTTON_OK_X, BUTTON_OK_Y, context.buttonOK002Region);
		buttonOKDown.setVisible(false);
		context.scene.attachChild(buttonOKDown);
		
		chooseDifficulty(context.difficulty);
		chooseBlack(context.blackplayer);
	}
	
	private void chooseDifficulty(int value) {
		switch (value) {
		case GameSceneContext.DIFFICULTY_LOW:
			buttonLow.setVisible(false);
			buttonMedium.setVisible(true);
			buttonHigh.setVisible(true);
			buttonLowDown.setVisible(true);
			buttonMediumDown.setVisible(false);
			buttonHighDown.setVisible(false);
			break;
			
		case GameSceneContext.DIFFICULTY_MEDIUM:
			buttonLow.setVisible(true);
			buttonMedium.setVisible(false);
			buttonHigh.setVisible(true);
			buttonLowDown.setVisible(false);
			buttonMediumDown.setVisible(true);
			buttonHighDown.setVisible(false);
			break;
			
		case GameSceneContext.DIFFICULTY_HIGH:
			buttonLow.setVisible(true);
			buttonMedium.setVisible(true);
			buttonHigh.setVisible(false);
			buttonLowDown.setVisible(false);
			buttonMediumDown.setVisible(false);
			buttonHighDown.setVisible(true);
			break;
		}
	}
	
	private void chooseBlack(int value) {
		switch (value) {
		case GameSceneContext.BLACK_RANDOM:
			buttonRandom.setVisible(false);
			buttonComputer.setVisible(true);
			buttonHuman.setVisible(true);
			buttonRandomDown.setVisible(true);
			buttonComputerDown.setVisible(false);
			buttonHumanDown.setVisible(false);
			break;
			
		case GameSceneContext.BLACK_COMPUTER:			
			buttonRandom.setVisible(true);
			buttonComputer.setVisible(false);
			buttonHuman.setVisible(true);
			buttonRandomDown.setVisible(false);
			buttonComputerDown.setVisible(true);
			buttonHumanDown.setVisible(false);
			break;
			
		case GameSceneContext.BLACK_HUMAN:
			buttonRandom.setVisible(true);
			buttonComputer.setVisible(true);
			buttonHuman.setVisible(false);
			buttonRandomDown.setVisible(false);
			buttonComputerDown.setVisible(false);
			buttonHumanDown.setVisible(true);
			break;
		}
	}
	
	@Override
	protected void onQuit(GameSceneContext context) {
		context.scene.detachChild(bg);
		bg = null;
		
		context.scene.detachChild(buttonLow);
		buttonLow = null;
		
		context.scene.detachChild(buttonMedium);
		buttonMedium = null;
		
		context.scene.detachChild(buttonHigh);
		buttonHigh = null;
		
		context.scene.detachChild(buttonRandom);
		buttonRandom = null;
		
		context.scene.detachChild(buttonComputer);
		buttonComputer = null;
		
		context.scene.detachChild(buttonHuman);
		buttonHuman = null;
		
		context.scene.detachChild(buttonLowDown);
		buttonLowDown = null;
		
		context.scene.detachChild(buttonMediumDown);
		buttonMediumDown = null;
		
		context.scene.detachChild(buttonHighDown);
		buttonHighDown = null;
		
		context.scene.detachChild(buttonRandomDown);
		buttonRandomDown = null;
		
		context.scene.detachChild(buttonComputerDown);
		buttonComputerDown = null;
		
		context.scene.detachChild(buttonHumanDown);
		buttonHumanDown = null;	

		
		context.scene.detachChild(buttonOK);
		buttonOK = null;
		
		context.scene.detachChild(buttonOKDown);
		buttonOKDown = null;	
	}
	
	@Override
	protected void onMouseDown(GameSceneContext context, float x, float y) {
		if (buttonLow.contains(x, y)) {
			context.difficulty = GameSceneContext.DIFFICULTY_LOW;
			chooseDifficulty(GameSceneContext.DIFFICULTY_LOW);
		}
		if (buttonMedium.contains(x, y)) {
			context.difficulty = GameSceneContext.DIFFICULTY_MEDIUM;
			chooseDifficulty(GameSceneContext.DIFFICULTY_MEDIUM);
		}
		if (buttonHigh.contains(x, y)) {
			context.difficulty = GameSceneContext.DIFFICULTY_HIGH;
			chooseDifficulty(GameSceneContext.DIFFICULTY_HIGH);
		}
		if (buttonRandom.contains(x, y)) {
			context.blackplayer = GameSceneContext.BLACK_RANDOM; 
			chooseBlack(GameSceneContext.BLACK_RANDOM);
		}
		if (buttonComputer.contains(x, y)) {
			context.blackplayer = GameSceneContext.BLACK_COMPUTER; 
			chooseBlack(GameSceneContext.BLACK_COMPUTER);
		}
		if (buttonHuman.contains(x, y)) {
			context.blackplayer = GameSceneContext.BLACK_HUMAN; 
			chooseBlack(GameSceneContext.BLACK_HUMAN);
		}
		if (buttonOK.contains(x, y)) {
			buttonOK.setVisible(false);
			buttonOKDown.setVisible(true);
		}
	}
	
	@Override
	protected void onMouseMove(GameSceneContext context, float x, float y) {
		if (buttonOK.contains(x, y)) {
			buttonOK.setVisible(false);
			buttonOKDown.setVisible(true);
		} else {
			buttonOK.setVisible(true);
			buttonOKDown.setVisible(false);			
		}
	}

	@Override
	protected void onMouseUp(GameSceneContext context, float x, float y) {
		if (buttonOK.contains(x, y)) {
			buttonOK.setVisible(true);
			buttonOKDown.setVisible(false);
			this.nextSceneType = Test006Scene.NAME;
		}
	}
	
	@Override
	protected boolean onBack(GameSceneContext context) {
		this.nextSceneType = Test006Scene.NAME;
		return true;
	}
}
