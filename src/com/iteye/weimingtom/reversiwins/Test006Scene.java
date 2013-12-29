package com.iteye.weimingtom.reversiwins;

import org.anddev.andengine.entity.sprite.Sprite;

public class Test006Scene extends GameScene {
	public static final String NAME = Test006Scene.class.getName();
	
	private final static float MENU_1_X = 15;
	private final static float MENU_1_Y = 361;	
	
	private final static float MENU_2_X = 164;
	private final static float MENU_2_Y = 361;
	
	private final static float MENU_3_X = 15;
	private final static float MENU_3_Y = 423;	
	
	private final static float MENU_4_X = 164;
	private final static float MENU_4_Y = 423;	
	
	private Sprite bg;
	private Sprite menuStart, menuStartDown;
	private Sprite menuSettings, menuSettingsDown;
	private Sprite menuHelp, menuHelpDown;
	private Sprite menuQuit, menuQuitDown;
		
	public Test006Scene() {
		
	}
	
	@Override
	protected void onInit(GameSceneContext context) {
		bg = new Sprite(0, 0, context.bgTitle001Region);
		context.scene.attachChild(bg);
		
		menuStart = new Sprite(MENU_1_X, MENU_1_Y, context.menuStart001Region);
		menuStart.setVisible(true);
		context.scene.attachChild(menuStart);
		menuSettings = new Sprite(MENU_2_X, MENU_2_Y, context.menuSettings001Region);
		menuSettings.setVisible(true);
		context.scene.attachChild(menuSettings);
		menuQuit = new Sprite(MENU_3_X, MENU_3_Y, context.menuQuit001Region);
		menuQuit.setVisible(true);
		context.scene.attachChild(menuQuit);
		menuHelp = new Sprite(MENU_4_X, MENU_4_Y, context.menuHelp001Region);
		menuHelp.setVisible(true);
		context.scene.attachChild(menuHelp);
		
		menuStartDown = new Sprite(MENU_1_X, MENU_1_Y, context.menuStart002Region);
		menuStartDown.setVisible(false);
		context.scene.attachChild(menuStartDown);
		menuSettingsDown = new Sprite(MENU_2_X, MENU_2_Y, context.menuSettings002Region);
		menuSettingsDown.setVisible(false);
		context.scene.attachChild(menuSettingsDown);
		menuQuitDown = new Sprite(MENU_3_X, MENU_3_Y, context.menuQuit002Region);
		menuQuitDown.setVisible(false);
		context.scene.attachChild(menuQuitDown);
		menuHelpDown = new Sprite(MENU_4_X, MENU_4_Y, context.menuHelp002Region);
		menuHelpDown.setVisible(false);
		context.scene.attachChild(menuHelpDown);

	}
	
	@Override
	protected void onQuit(GameSceneContext context) {
		context.scene.detachChild(bg);
		bg = null;
		
		context.scene.detachChild(menuStart);
		menuStart = null;
		context.scene.detachChild(menuSettings);
		menuSettings = null;
		context.scene.detachChild(menuHelp);
		menuHelp = null;
		context.scene.detachChild(menuQuit);
		menuQuit = null;
		
		context.scene.detachChild(menuStartDown);
		menuStartDown = null;
		context.scene.detachChild(menuSettingsDown);
		menuSettingsDown = null;
		context.scene.detachChild(menuHelpDown);
		menuHelpDown = null;
		context.scene.detachChild(menuQuitDown);
		menuQuitDown = null;
	}
	
	@Override
	protected void onMouseDown(GameSceneContext context, float x, float y) {
		if (menuStart.contains(x, y)) {
			menuStart.setVisible(false);
			menuStartDown.setVisible(true);
		}
		if (menuSettings.contains(x, y)) {
			menuSettings.setVisible(false);
			menuSettingsDown.setVisible(true);
		}
		if (menuQuit.contains(x, y)) {
			menuQuit.setVisible(false);
			menuQuitDown.setVisible(true);
		}
		if (menuHelp.contains(x, y)) {
			menuHelp.setVisible(false);
			menuHelpDown.setVisible(true);
		}
	}

	@Override
	protected void onMouseMove(GameSceneContext context, float x, float y) {
		if (menuStart.contains(x, y)) {
			menuStart.setVisible(false);
			menuStartDown.setVisible(true);
		} else {
			menuStart.setVisible(true);
			menuStartDown.setVisible(false);			
		}
		if (menuSettings.contains(x, y)) {
			menuSettings.setVisible(false);
			menuSettingsDown.setVisible(true);
		} else {
			menuSettings.setVisible(true);
			menuSettingsDown.setVisible(false);			
		}
		if (menuQuit.contains(x, y)) {
			menuQuit.setVisible(false);
			menuQuitDown.setVisible(true);
		} else {
			menuQuit.setVisible(true);
			menuQuitDown.setVisible(false);			
		}
		if (menuHelp.contains(x, y)) {
			menuHelp.setVisible(false);
			menuHelpDown.setVisible(true);
		} else {
			menuHelp.setVisible(true);
			menuHelpDown.setVisible(false);			
		}
	}

	@Override
	protected void onMouseUp(GameSceneContext context, float x, float y) {
		if (menuStart.contains(x, y)) {
			menuStart.setVisible(true);
			menuStartDown.setVisible(false);
			this.nextSceneType = Test003Scene.NAME;
		}
		if (menuSettings.contains(x, y)) {
			menuSettings.setVisible(true);
			menuSettingsDown.setVisible(false);
			this.nextSceneType = Test010Scene.NAME;
		}
		if (menuQuit.contains(x, y)) {
			menuQuit.setVisible(true);
			menuQuitDown.setVisible(false);
			//this.nextSceneType = ExitScene.NAME;
			this.nextSceneType = Test012Scene.NAME;
		}
		if (menuHelp.contains(x, y)) {
			menuHelp.setVisible(true);
			menuHelpDown.setVisible(false);
			this.nextSceneType = Test007Scene.NAME;
		}
	}
	
	@Override
	protected boolean onBack(GameSceneContext context) {
		//this.nextSceneType = ExitScene.NAME;
		this.nextSceneType = Test012Scene.NAME;
		return true;
	}
}
