package com.iteye.weimingtom.reversiwins;

import org.anddev.andengine.entity.sprite.TiledSprite;

public class Test008Scene extends GameScene {
	public static final String NAME = Test008Scene.class.getName();
	
	private int point = 0;
	private TiledSprite number;

	public Test008Scene() {
		
	}
	
	@Override
	protected void onInit(GameSceneContext context) {
		final int centerX = (GameSceneContext.CAMERA_WIDTH - context.badgeRegion.getWidth()) / 2;
		final int centerY = (GameSceneContext.CAMERA_HEIGHT - context.badgeRegion.getHeight()) / 2;
		number = new TiledSprite(centerX, centerY, context.fontNumber001Region[0]);
		number.setCurrentTileIndex(point++);
		context.scene.attachChild(number);
	}
	
	@Override
	protected void onQuit(GameSceneContext context) {
		context.scene.detachChild(number);
		number = null;
	}
	
	@Override
	protected void onMouseDown(GameSceneContext context, float x, float y) {
		if (number.contains(x, y)) {
			//this.nextSceneType = Test002Scene.NAME;
			number.setCurrentTileIndex((point++) % 65);
		}
	}
}
