package com.iteye.weimingtom.reversiwins;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.util.FPSCounter;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.view.RenderSurfaceView;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.Debug;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ReversiWinsActivity extends BaseGameActivity implements ITimerCallback, IOnSceneTouchListener {
	private GameScene gameScene;
	private GameSceneContext context;
	private FPSCounter fpsCounter;
	private FPSLogger fpsLogger;
	private TimerHandler timerHandler;
	private boolean isExitActivity = false;
	private AlertDialog.Builder builder;
	
	private static final int MENU_FEEDBACK = Menu.FIRST;
	private static final int MENU_ABOUT = Menu.FIRST + 1;
	
	private static final int DIALOG_ABOUT_ID = 0;
	
	RelativeLayout mAdContainer;

	@Override
	protected void onSetContentView() {
		if (GameSceneContext.DONNOT_LOAD_LAYOUT_XML) {
			super.onSetContentView();
		} else {
			super.setContentView(R.layout.main);
			this.mRenderSurfaceView = (RenderSurfaceView) this.findViewById(R.id.rendersurfaceview);
			this.mRenderSurfaceView.setEGLConfigChooser(false);
			this.mRenderSurfaceView.setRenderer(this.mEngine);
			
			mAdContainer = (RelativeLayout) findViewById(R.id.adcontainer);
			//创建一个320x50的广告View
			/*
			mAdview320x50 = new DomobAdView(this, GameSceneContext.PUBLISHER_ID, DomobAdView.INLINE_SIZE_320X50);
			mAdview320x50.setKeyword("game");
			mAdview320x50.setUserGender("male");
			mAdview320x50.setUserBirthdayStr("2000-08-08");
			mAdview320x50.setUserPostcode("123456");
			
			//设置广告view的监听器。
			mAdview320x50.setOnAdListener(new DomobAdListener() {
				@Override
				public void onReceivedFreshAd(DomobAdView adview) {
					Log.i("DomobSDKDemo", "onReceivedFreshAd");
				}
				@Override
				public void onFailedToReceiveFreshAd(DomobAdView adview) {
					Log.i("DomobSDKDemo", "onFailedToReceiveFreshAd");
				}
				@Override
				public void onLandingPageOpening() {
					Log.i("DomobSDKDemo", "onLandingPageOpening");
				}
				@Override
				public void onLandingPageClose() {
					Log.i("DomobSDKDemo", "onLandingPageClose");
				}
			});
			//将广告View增加到视图中。
			mAdContainer.addView(mAdview320x50);
			*/
		}
	}

	@Override
	public Engine onLoadEngine() {
		Debug.setDebugLevel(GameSceneContext.DEBUG_LEVEL);
		builder = new AlertDialog.Builder(this);
		
		if (GameSceneContext.ENABLE_BUG_REPORT) {
			Thread.setDefaultUncaughtExceptionHandler(new BugReport(this));
			BugReport.SendBugReport(this);
		}
		
		EngineOptions options = new EngineOptions(
				true, GameSceneContext.ORIENTATION, 
				new RatioResolutionPolicy(GameSceneContext.CAMERA_WIDTH, GameSceneContext.CAMERA_HEIGHT), 
				new Camera(0, 0, GameSceneContext.CAMERA_WIDTH, GameSceneContext.CAMERA_HEIGHT));
		/**
		 * Texture disappears
		 * @see http://www.andengine.org/forums/development/texture-disappears-t4294.html
		 * @see http://www.andengine.org/forums/bugs/texture-disappears-t3962.html
		 */
		options.getRenderOptions().disableExtensionVertexBufferObjects();
		options.getTouchOptions().setRunOnUpdateThread(true);
		return new Engine(options);
	}

	@Override
	public void onLoadResources() {
		this.context = new GameSceneContext();
		this.gameScene = GameSceneFactory.createGameScene(GameSceneContext.INIT_GAME_SCENE);
		
		this.context.init(this, this.gameScene.getInitResType());
	}

	@Override
	public Scene onLoadScene() {
		Scene scene = new Scene();
		scene.setBackground(new ColorBackground(1f, 1f, 1f));
		//scene.setBackgroundEnabled(false);
		scene.setOnSceneTouchListener(this);
		context.scene = scene;
		
		this.gameScene.onInit(context);
		
		fpsCounter = new FPSCounter();
		mEngine.registerUpdateHandler(fpsCounter);
		fpsLogger = new FPSLogger();
		mEngine.registerUpdateHandler(fpsLogger);
		
		timerHandler = new TimerHandler(GameSceneContext.TIMER_DURATION, true, this);
		mEngine.registerUpdateHandler(timerHandler);
		
		return scene;
	}

	@Override
	public void onLoadComplete() {
		
	}
	
	@Override
	public void onTimePassed(TimerHandler pTimerHandler) {
		//Debug.d("onTimePassed");
		if (this.gameScene != null) {
			if (context != null) {
				context.currentFPS = fpsCounter.getFPS();
			}
			this.gameScene.onPreUpdate(context, this);
			this.gameScene = this.gameScene.mainLoop(context);
			if (GameSceneFactory.isExitScene(this.gameScene)) {
				this.exitActivity();
			}
		} else {
			Debug.e("Scene is wrong");
			this.exitActivity();
		}
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		float x = pSceneTouchEvent.getX();
		float y = pSceneTouchEvent.getY();
		if (pSceneTouchEvent.isActionDown()) {
			Debug.d("onSceneTouchEvent, isActionDown : " + 
					"x=" + x + ", " + 
					"y=" + y);
			if (gameScene != null) {
				gameScene.onMouseDown(context, x, y);
			}
		}
		if (pSceneTouchEvent.isActionMove()) {
			Debug.d("onSceneTouchEvent, isActionMove : " + 
					"x=" + x + ", " + 
					"y=" + y);
			if (gameScene != null) {
				gameScene.onMouseMove(context, x, y);
			}
		}
		if (pSceneTouchEvent.isActionUp()) {
			Debug.d("onSceneTouchEvent, isActionUp : " + 
					"x=" + x + ", " + 
					"y=" + y);
			if (gameScene != null) {
				gameScene.onMouseUp(context, x, y);
			}
		}
		return true;
	}
	
	public void exitActivity() {
		if (isExitActivity == false) {
			isExitActivity = true;
			
			//Debug.e("==============exitActivity");
			
			mEngine.unregisterUpdateHandler(timerHandler);
			//timerHandler = null;
			
			context.scene.clearChildScene();
			context.scene.detachChildren();
			
			context.scene.clearUpdateHandlers();
			context.scene.clearTouchAreas();
			context.scene.clearEntityModifiers();
			
			context.scene.reset();
			context.scene.detachSelf();
			
			//context.scene = null;
			
			context.releaseAll(this);
			//context = null;
			
			finish();
			if (GameSceneContext.KILL_PROCESS) {
				android.os.Process.killProcess(android.os.Process.myPid());
			}
		}
	}
	
	@Override
	public void onBackPressed() {
		if (gameScene != null) {
			if (gameScene.onBack(context)) {
				//do nothing
			} else {
				//When onBack returns false, it's default behavior.
				super.onBackPressed();
			}
		} else {
			super.onBackPressed();
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(final Menu pMenu) {
		pMenu.add(Menu.NONE, MENU_FEEDBACK, Menu.NONE, R.string.feedback).setIcon(android.R.drawable.ic_dialog_email);
		pMenu.add(Menu.NONE, MENU_ABOUT, Menu.NONE, R.string.about).setIcon(android.R.drawable.ic_menu_help);
		return super.onCreateOptionsMenu(pMenu);
	}

	@Override
	public boolean onPrepareOptionsMenu(final Menu pMenu) {
		//pMenu.findItem(MENU_TRACE).setTitle(this.mEngine.isMethodTracing() ? "Stop Method Tracing" : "Start Method Tracing");
		return super.onPrepareOptionsMenu(pMenu);
	}

	@Override
	public boolean onMenuItemSelected(final int pFeatureId, final MenuItem pItem) {
		switch(pItem.getItemId()) {
			case MENU_FEEDBACK:
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_SENDTO);
				intent.setData(Uri.parse(getString(R.string.mail_address)));
				intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject_title));
				try {
					startActivity(intent);
				} catch (ActivityNotFoundException e) {
					Toast.makeText(this, getString(R.string.activity_not_found), Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
				return true;
				
			case MENU_ABOUT:
				this.showDialog(DIALOG_ABOUT_ID);
				return true;
				
			default:
				return super.onMenuItemSelected(pFeatureId, pItem);
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
	    Dialog dialog = null;
	    switch(id) {
	    case DIALOG_ABOUT_ID:
	    	if (builder != null) {
	    		return builder
	    			.setIcon(R.drawable.ic_launcher)
	    			.setTitle(R.string.about_title)
	    			.setMessage(R.string.about_message)
	    			.setPositiveButton(R.string.about_ok, new DialogInterface.OnClickListener() {
	    				public void onClick(DialogInterface dialog, int id) {
	    	                dialog.cancel();
	    				}
	    			})
	    	       .create();
	    	}
	        break;
	    }
	    return dialog;
	}
	
}
