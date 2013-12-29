package com.iteye.weimingtom.reversiwins;

import org.anddev.andengine.entity.sprite.Sprite;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Test001Scene extends GameScene {
	public static final String NAME = Test001Scene.class.getName();
	
	private Sprite face;
	private Sprite pic;
	private Paint paint = new Paint();
	
	public Test001Scene() {
		
	}
	
	@Override
	protected void onInit(GameSceneContext context) {
		final int centerX = (GameSceneContext.CAMERA_WIDTH - context.badgeRegion.getWidth()) / 2;
		final int centerY = (GameSceneContext.CAMERA_HEIGHT - context.badgeRegion.getHeight()) / 2;
		face = new Sprite(centerX, centerY, context.badgeRegion);
		context.scene.attachChild(face);
		
		pic = new Sprite(0, 0, context.pictureRegion);
		context.scene.attachChild(pic);
		drawCircle(context, Color.RED);
	}
	
	@Override
	protected void onQuit(GameSceneContext context) {
		context.scene.detachChild(face);
		face = null;
		context.scene.detachChild(pic);
		pic = null;
	}
	
	@Override
	protected void onMouseDown(GameSceneContext context, float x, float y) {
		if (face.contains(x, y)) {
			this.nextSceneType = Test002Scene.NAME;
		}
		drawCircle(context, Color.rgb((int)x & 0xff, (int)y, 0xff));
	}
	
	private void drawCircle(GameSceneContext context, int color) {
		Canvas canvas = context.beginDrawPicture();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(color);
		canvas.drawCircle(30, 30, 30, paint);
		context.endDrawPicture();
	}
}
