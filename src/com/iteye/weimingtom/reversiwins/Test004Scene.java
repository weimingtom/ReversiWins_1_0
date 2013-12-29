package com.iteye.weimingtom.reversiwins;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.Sprite;

public class Test004Scene extends GameScene {
	public static final String NAME = Test004Scene.class.getName();
	
	public static final int INCREMENT = 10;	
	public Sprite buttonUp;
	public Sprite buttonOver;
	public Rectangle rectangle;
	
	public int maxWidth; 
	private int currentWidth;
	public long lastTime;

	
	public Test004Scene() {
		
	}
	
	@Override
	protected void onInit(GameSceneContext context) {
		final int centerX = (GameSceneContext.CAMERA_WIDTH - context.buttonExampleUpRegion.getWidth()) / 2;
		final int centerY = (GameSceneContext.CAMERA_HEIGHT - context.buttonExampleUpRegion.getHeight()) / 2;
		
		buttonUp = new Sprite(centerX, centerY, context.buttonExampleUpRegion);
		buttonUp.setVisible(true);
		context.scene.attachChild(buttonUp);

		buttonOver = new Sprite(centerX, centerY, context.buttonExampleOverRegion);
		buttonOver.setVisible(false);
		context.scene.attachChild(buttonOver);
		
		maxWidth = buttonUp.getTextureRegion().getWidth();
		buttonUp.getTextureRegion().setWidth(0);
		buttonUp.setWidth(0);
		buttonOver.getTextureRegion().setWidth(0);
		buttonOver.setWidth(0);
	}
	
	@Override
	protected void onQuit(GameSceneContext context) {
		context.scene.detachChild(buttonUp);
		buttonUp = null;
		context.scene.detachChild(buttonOver);
		buttonOver = null;
	}
	
	@Override
	protected void onUpdate(GameSceneContext context) {
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastTime > 50) {
			lastTime = currentTime;
			if (currentWidth < maxWidth) {
				currentWidth += INCREMENT;
				if (currentWidth > maxWidth) {
					currentWidth = maxWidth;
				}
				buttonUp.getTextureRegion().setWidth(currentWidth);
				buttonUp.setWidth(currentWidth);
				buttonOver.getTextureRegion().setWidth(currentWidth);
				buttonOver.setWidth(currentWidth);
			}
		}
	}
	
	@Override
	protected void onMouseDown(GameSceneContext context, float x, float y) {
		if (buttonUp.contains(x, y)) {
			buttonUp.setVisible(false);
			buttonOver.setVisible(true);
		}
	}

	@Override
	protected void onMouseMove(GameSceneContext context, float x, float y) {
		if (buttonUp.contains(x, y)) {
			buttonUp.setVisible(false);
			buttonOver.setVisible(true);
		} else {
			buttonUp.setVisible(true);
			buttonOver.setVisible(false);			
		}
	}

	@Override
	protected void onMouseUp(GameSceneContext context, float x, float y) {
		if (buttonUp.contains(x, y)) {
			buttonUp.setVisible(true);
			buttonOver.setVisible(false);
		}
	}
}
