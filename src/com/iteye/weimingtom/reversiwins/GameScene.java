package com.iteye.weimingtom.reversiwins;

import org.anddev.andengine.ui.activity.BaseGameActivity;

public class GameScene {
	public static final String NAME = "GameScene"; 
	
	protected String nextSceneType;
	protected int frameCounter;
	protected String type;
	private int initResType = GameSceneContext.INIT_RES_TYPE_ALL;
	
	public GameScene() {
		nextSceneType = null;
		frameCounter = 0;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
	private void updateScene(GameSceneContext context) {
		frameCounter++;
	    onUpdate(context);
	}
	
	public GameScene mainLoop(GameSceneContext context) {
		updateScene(context);
		onRender(context);
		GameScene nextScene = GameSceneFactory.createGameScene(nextSceneType);
		if (nextScene != null) {
			this.onQuit(context);
			nextScene.onInit(context);
			return nextScene;
		} else {
			return this;
		}
	}
	
	public int getInitResType() {
		return initResType;
	}

	public void setInitResType(int initResType) {
		this.initResType = initResType;
	}
	
	protected void onInit(GameSceneContext context) {
		
	}
	
	protected void onQuit(GameSceneContext context) {
		
	}
	
	protected void onUpdate(GameSceneContext context) {
		
	}
	
	protected void onRender(GameSceneContext context) {
		
	}
	
	protected void onMouseDown(GameSceneContext context, float x, float y) {
		
	}
	
	protected void onMouseMove(GameSceneContext context, float x, float y) {
		
	}
	
	protected void onMouseUp(GameSceneContext context, float x, float y) {
		
	}
	
	protected void onPreUpdate(GameSceneContext context, BaseGameActivity activity) {
		
	}
	
	protected boolean onBack(GameSceneContext context) {
		return false;
	}
}
