package com.iteye.weimingtom.reversiwins;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.util.Debug;

import com.iteye.weimingtom.tween.SimpleTweener;

public class Test005Scene extends GameScene {
	public static final String NAME = Test005Scene.class.getName();
		
	public Sprite face;
	
	private static final double TWEEN_TIME = 0.05;
	/**
	 * FIXME: new GeometricTweener(0.333, false);
	 */
	private SimpleTweener tween = new SimpleTweener.EaseOutCubic();
	
	public Test005Scene() {
		
	}
	
	@Override
	protected void onInit(GameSceneContext context) {
		final int centerX = (GameSceneContext.CAMERA_WIDTH - context.badgeRegion.getWidth()) / 2;
		final int centerY = (GameSceneContext.CAMERA_HEIGHT - context.badgeRegion.getHeight()) / 2;
		face = new Sprite(centerX, centerY, context.badgeRegion);
		context.scene.attachChild(face);
	}
	
	protected void onQuit(GameSceneContext context) {
		context.scene.detachChild(face);
		face = null;
	}
	
	@Override
	protected void onUpdate(GameSceneContext context) {
		if (tween.update()) {
			face.setPosition(tween.currentX(), tween.currentY());
		}
	}
	
	@Override
	protected void onRender(GameSceneContext context) {
		
	}
	
	@Override
	protected void onMouseDown(GameSceneContext context, float x, float y) {
		double distance = Math.hypot(x - face.getX(), y - face.getY());
		Debug.d("x = " + x + ", " + "y = " + y + ", " + "distence = " + distance);
		if (!tween.isTweening()) {
			tween.startTween(face.getX(), x - face.getWidth() / 2, face.getY(), y - face.getHeight() / 2, (long)(distance * TWEEN_TIME));
		} else {
			tween.startTween(face.getX(), x - face.getWidth() / 2, face.getY(), y - face.getHeight() / 2, (long)(distance * TWEEN_TIME));
		}
	}
}
