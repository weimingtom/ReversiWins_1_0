package com.iteye.weimingtom.reversiwins;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.iteye.weimingtom.tween.SimpleTweener;

public class Test009Scene extends GameScene {
	public static final String NAME = Test009Scene.class.getName();
	
	private Sprite bg;
	
	private static final int ANI_TYPE_FADE = 0;
	private static final int ANI_TYPE_MOVE = 1;
	private int aniType;

	private final static int STATUS_INIT = 0;
	private final static int STATUS_FADEIN = 1;
	private final static int STATUS_SHOW = 2;
	private final static int STATUS_FADEOUT = 3;
	private int status;
	
	//aniType == ANI_TYPE_FADE
	private long lastTime;
	private float bgAlpha;
	
	//aniType == ANI_TYPE_MOVE
	private float posX, posY;
	
	private static final int INIT_TIME = 10;
	private static final int SHOW_TIME = 10;
	private static final int FADE_FRAME_TIME = 20;
	private static final int TWEEN_FRAMES = 20;
	private SimpleTweener tween;
	private boolean isInit = false;
	
	private final static boolean TEST_LOGO_ONLY = false;
	
	public Test009Scene() {
		this.setInitResType(GameSceneContext.INIT_RES_TYPE_MIN);
	}
	
	@Override
	protected void onInit(GameSceneContext context) {
		status = STATUS_INIT;
		aniType = ANI_TYPE_FADE; //FADE
		
		bg = new Sprite(0, 0, context.bgLogo001Region);
		if (aniType == ANI_TYPE_FADE) {
			bgAlpha = 0;
			posX = 0;
			posY = 0;
		} else if (aniType == ANI_TYPE_MOVE) { 
			bgAlpha = 0;
			posX = bg.getWidth();
			posY = 0;
		}
		bg.setAlpha(bgAlpha);
		bg.setPosition(posX, posY);
		bg.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		context.scene.attachChild(bg);
		
		lastTime = System.currentTimeMillis();
	}
	
	@Override
	protected void onPreUpdate(GameSceneContext context, BaseGameActivity activity) {
		super.onPreUpdate(context, activity);
		if (status == STATUS_SHOW && this.isInit == false) {
			if (!TEST_LOGO_ONLY) {
				context.initAll(activity);
			}
			this.isInit = true;
		}
	}
	
	@Override
	protected void onUpdate(GameSceneContext context) {
		super.onUpdate(context);
		
		if (aniType == ANI_TYPE_FADE) {
			long time = System.currentTimeMillis();
			switch (status) {
			case STATUS_INIT:
				if (time - lastTime >= INIT_TIME) {
					bgAlpha = 0;
					lastTime = time;
					status = STATUS_FADEIN;
				}
				break;
			
			case STATUS_FADEIN:
				if (time - lastTime >= FADE_FRAME_TIME) {
					lastTime = time;
					bgAlpha += 0.1f;
				}
				if (bgAlpha >= 1) {
					bgAlpha = 1;
					lastTime = time; 
					status = STATUS_SHOW;
				}
				break;
				
			case STATUS_SHOW:
				if (time - lastTime >= SHOW_TIME) {
					bgAlpha = 1;
					lastTime = time;
					status = STATUS_FADEOUT;
				}
				break;
				
			case STATUS_FADEOUT:
				if (time - lastTime >= FADE_FRAME_TIME) {
					lastTime = time;
					bgAlpha -= 0.1f;
				}
				if (bgAlpha <= 0) {
					bgAlpha = 0;
					lastTime = time; 
					if (!TEST_LOGO_ONLY) {
						this.nextSceneType = Test006Scene.NAME;
					}
				}
				break;
			}
		} else if (aniType == ANI_TYPE_MOVE) {
			long time = System.currentTimeMillis();
			switch (status) {
			case STATUS_INIT:
				if (time - lastTime >= INIT_TIME) {
					bgAlpha = 0;
					lastTime = time;
					tween = new SimpleTweener.EaseOutCubic();
					tween.startTween(bg.getWidth(), 0, 0, 0, TWEEN_FRAMES);
					status = STATUS_FADEIN;
				}
				break;
			
			case STATUS_FADEIN:
				if (tween.update()) {
					posX = tween.currentX();
					posY = tween.currentY();
				} else {
					status = STATUS_SHOW;
					lastTime = time;
				}
				break;
				
			case STATUS_SHOW:
				if (time - lastTime >= SHOW_TIME) {
					lastTime = time;
					status = STATUS_FADEOUT;
					tween = new SimpleTweener.EaseInCubic();
					tween.startTween(0, -bg.getWidth(), 0, 0, TWEEN_FRAMES);
				}
				break;
				
			case STATUS_FADEOUT:
				if (tween.update()) {
					posX = tween.currentX();
					posY = tween.currentY();
				} else {
					if (!TEST_LOGO_ONLY) {
						this.nextSceneType = Test006Scene.NAME;
					}
				}
				break;
			}
			bgAlpha = (bg.getWidth() - Math.abs(posX)) / bg.getWidth();
		}
	}

	@Override
	protected void onRender(GameSceneContext context) {
		super.onRender(context);
		bg.setPosition(posX, posY);
		bg.setAlpha(bgAlpha);
	}

	@Override
	protected void onQuit(GameSceneContext context) {
		context.scene.detachChild(bg);
		bg = null;
	}

	@Override
	protected boolean onBack(GameSceneContext context) {
		//this.nextSceneType = ExitScene.NAME;
		this.nextSceneType = Test012Scene.NAME;
		return true;
	}
}
