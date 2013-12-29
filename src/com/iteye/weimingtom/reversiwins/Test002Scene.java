package com.iteye.weimingtom.reversiwins;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.util.HorizontalAlign;

public class Test002Scene extends GameScene {
	public static final String NAME = Test002Scene.class.getName();
	
	private Text textCenter;
	private ChangeableText fpsText;
			
	public Test002Scene() {
		
	}
	
	@Override
	protected void onInit(GameSceneContext context) {
		Scene scene = context.scene;
		Font font = context.font;
		
		textCenter = new Text(0, 0, font, "Hello AndEngine!\nYou can even have multilined text!", HorizontalAlign.CENTER);
		textCenter.setScaleCenter(0, 0);
		textCenter.setScale(GameSceneContext.CAMERA_WIDTH / textCenter.getBaseWidth());
		textCenter.setPosition(0, 0);
		scene.attachChild(textCenter);
		
		fpsText = new ChangeableText(0, 0, font, "FPS:", "FPS: XXXXX".length());
		fpsText.setPosition(0, GameSceneContext.CAMERA_HEIGHT - fpsText.getBaseHeight());
		scene.attachChild(fpsText);
	}
	
	@Override
	protected void onQuit(GameSceneContext context) {
		Scene scene = context.scene;
		
		scene.detachChild(textCenter);	
		textCenter = null;
		
		scene.detachChild(fpsText);
		fpsText = null;
	}
	
	@Override
	protected void onUpdate(GameSceneContext context) {
		fpsText.setText("FPS: " + context.currentFPS);
	}
	
	@Override
	protected void onMouseDown(GameSceneContext context, float x, float y) {
		this.nextSceneType = Test003Scene.NAME;
	}
}
