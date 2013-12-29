package com.iteye.weimingtom.reversiwins;

public class Test012Scene extends GameScene {
	public static final String NAME = Test012Scene.class.getName();
	
	private long lastTime;
	
	public Test012Scene() {
		
	}

	@Override
	protected void onUpdate(GameSceneContext context) {
		super.onUpdate(context);
		if (lastTime == 0) {
			long time = System.currentTimeMillis();
			if (time - lastTime > 20) {
				lastTime = 0;
				nextSceneType = ExitScene.NAME;
			}
		}
	}
}
