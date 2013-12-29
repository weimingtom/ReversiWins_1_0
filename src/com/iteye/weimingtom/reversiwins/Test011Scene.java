package com.iteye.weimingtom.reversiwins;

public class Test011Scene extends GameScene {
	public static final String NAME = Test011Scene.class.getName();
	
	private MultiNumberUtil numberUtil;
	private long lastTime;
	private int point;
	
	@Override
	protected void onInit(GameSceneContext context) {
		numberUtil = new MultiNumberUtil(context.scene, context.fontNumber002Region, 6);
		final float w = numberUtil.getWidth() / 2;
		final float h = numberUtil.getHeight() / 2;
		final float centerX = (GameSceneContext.CAMERA_WIDTH - w) / 2;
		final float centerY = (GameSceneContext.CAMERA_HEIGHT - h) / 2;
		numberUtil.setLocation(centerX, centerY, w, h);
		numberUtil.setShowPrefix(false);
	}
	
	@Override
	protected void onQuit(GameSceneContext context) {
		numberUtil.release(context.scene);
		numberUtil = null;
	}

	@Override
	protected void onRender(GameSceneContext context) {
		super.onRender(context);
		long time = System.currentTimeMillis();
		if (time - lastTime > 5) {
			lastTime = time;
			numberUtil.setNumber(point+=100);
		}
	}
	
	@Override
	protected void onMouseDown(GameSceneContext context, float x, float y) {
		
	}
}
