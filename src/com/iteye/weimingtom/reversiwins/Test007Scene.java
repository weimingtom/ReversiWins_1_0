package com.iteye.weimingtom.reversiwins;

import org.anddev.andengine.entity.sprite.Sprite;

import android.graphics.RectF;

public class Test007Scene extends GameScene {
	public static final String NAME = Test007Scene.class.getName();
	
	private Sprite bg1, bg2, bg3, bg4;
	private RectF rect0 = new RectF(5, 72, 5 + 42, 72 + 42); //back
	private RectF rect1 = new RectF(114, 7, 114 + 42, 7 + 42); //bg1
	private RectF rect2 = new RectF(166, 7, 166 + 42, 7 + 42); //bg2
	private RectF rect3 = new RectF(216, 7, 216 + 42, 7 + 42); //bg3
	private RectF rect4 = new RectF(267, 7, 267 + 42, 7 + 42); //bg4
	
	private int page = 0;
	
	public Test007Scene() {
		
	}
	
	@Override
	protected void onInit(GameSceneContext context) {
		bg1 = new Sprite(0, 0, context.bgHelp001Region);
		bg1.setVisible(true);
		context.scene.attachChild(bg1);
		
		bg2 = new Sprite(0, 0, context.bgHelp002Region);
		bg2.setVisible(false);
		context.scene.attachChild(bg2);
		
		bg3 = new Sprite(0, 0, context.bgHelp003Region);
		bg3.setVisible(false);
		context.scene.attachChild(bg3);
		
		bg4 = new Sprite(0, 0, context.bgHelp004Region);
		bg4.setVisible(false);
		context.scene.attachChild(bg4);
	}
	
	@Override
	protected void onQuit(GameSceneContext context) {
		context.scene.detachChild(bg1);
		bg1 = null;
		
		context.scene.detachChild(bg2);
		bg2 = null;
		
		context.scene.detachChild(bg3);
		bg3 = null;
		
		context.scene.detachChild(bg4);
		bg4 = null;
	}
	
	@Override
	protected void onMouseUp(GameSceneContext context, float x, float y) {
		if (rect0.contains(x, y)) {
			showPage(-1);
		} else if (rect1.contains(x, y)) {
			showPage(0);
			this.page = 0;
		} else if (rect2.contains(x, y)) {
			showPage(1);
			this.page = 1;
		} else if (rect3.contains(x, y)) {
			showPage(2);
			this.page = 2;
		} else if (rect4.contains(x, y)) {
			showPage(3);
			this.page = 3;
		} else {
			this.page++;
			showPage(this.page);
		}
	}
	
	private void showPage(int page) {
		switch (page) {
		case -1:
		default:
			this.nextSceneType = Test006Scene.NAME;
			break;
			
		case 0:
			bg1.setVisible(true);
			bg2.setVisible(false);
			bg3.setVisible(false);
			bg4.setVisible(false);
			break;
			
		case 1:
			bg1.setVisible(false);
			bg2.setVisible(true);
			bg3.setVisible(false);
			bg4.setVisible(false);
			break;
		
		case 2:
			bg1.setVisible(false);
			bg2.setVisible(false);
			bg3.setVisible(true);
			bg4.setVisible(false);
			break;
		
		case 3:
			bg1.setVisible(false);
			bg2.setVisible(false);
			bg3.setVisible(false);
			bg4.setVisible(true);
			break;
		}
	}
	
	@Override
	protected boolean onBack(GameSceneContext context) {
		this.nextSceneType = Test006Scene.NAME;
		return true;
	}
}
