package com.iteye.weimingtom.reversiwins;

import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.atlas.bitmap.source.PictureBitmapTextureAtlasSource;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.Debug.DebugLevel;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Picture;
import android.graphics.Typeface;

public class GameSceneContext {
	public static final boolean KILL_PROCESS = true;
	
	public static final DebugLevel DEBUG_LEVEL = DebugLevel.ERROR;
	public static final ScreenOrientation ORIENTATION = ScreenOrientation.PORTRAIT;
	public static final int CAMERA_WIDTH = 320;
	public static final int CAMERA_HEIGHT = 480;
	public static final int FPS = 12;
	
	public static final boolean ENABLE_BUG_REPORT = true;
	public static final boolean TEST_BUG_REPORT = false;
	public static final boolean USE_MT_RANDOM = true;
	public static final boolean DONNOT_LOAD_LAYOUT_XML = false;
	
	public static final String INIT_GAME_SCENE = 
			//Test006Scene.NAME;
			Test009Scene.NAME;
			//Test011Scene.NAME;
	
	public static final float TIMER_DURATION = 1.0f / FPS;

	public static final int INIT_RES_TYPE_ALL = 0;
	public static final int INIT_RES_TYPE_MIN = 1;
	
	public final static int FONT_NUMBER1_SIZE = 2;
	public final static int FONT_NUMBER2_SIZE = 10;	
	public final static int FONT_NUMBER3_SIZE = 10;	
	
	public Scene scene;
	
	public final static int DIFFICULTY_LOW = GameData.DIFFICULTY_LOW;
	public final static int DIFFICULTY_MEDIUM = GameData.DIFFICULTY_MEDIUM;
	public final static int DIFFICULTY_HIGH = GameData.DIFFICULTY_HIGH;
	public int difficulty = DIFFICULTY_LOW;
	
	public final static int BLACK_RANDOM = 0;
	public final static int BLACK_COMPUTER = 1;
	public final static int BLACK_HUMAN = 2;
	public int blackplayer = BLACK_RANDOM;

	public int highestScore;
	
	private static final String SHARE_PREF_NAME = "pref";
	private static final String SHARE_PREF_DIFFICULTY= "difficulty";
	private static final String SHARE_PREF_BLACKPLAYER= "blackplayer";
	private static final String SHARE_PREF_HIGHESTSCORE= "highestScore";
	
	//logo
	
	public TextureRegion badgeRegion;
	private BitmapTextureAtlas badgeTexture;

	//bg
	
	public TextureRegion bgLogo001Region;
	private BitmapTextureAtlas bgLogo001Texture;
	
	public TextureRegion bgTitle001Region;
	private BitmapTextureAtlas bgTitle001Texture;

	public TextureRegion bgHelp001Region;
	private BitmapTextureAtlas bgHelp001Texture;

	public TextureRegion bgHelp002Region;
	private BitmapTextureAtlas bgHelp002Texture;
	
	public TextureRegion bgHelp003Region;
	private BitmapTextureAtlas bgHelp003Texture;
	
	public TextureRegion bgHelp004Region;
	private BitmapTextureAtlas bgHelp004Texture;
	
	public TextureRegion bgGame001Region;
	private BitmapTextureAtlas bgGame001Texture;

	public TextureRegion bgSettings001Region;
	private BitmapTextureAtlas bgSettings001Texture;
	
	//
	public TiledTextureRegion[] fontNumber001Region;
	private BitmapTextureAtlas[] fontNumber001Texture;

	public TiledTextureRegion[] fontNumber002Region;
	private BitmapTextureAtlas[] fontNumber002Texture;	

	public TiledTextureRegion[] fontNumber003Region;
	private BitmapTextureAtlas[] fontNumber003Texture;	
	
	public TextureRegion spriteBlack001Region;
	private BitmapTextureAtlas spriteBlack001Texture;
	
	public TextureRegion spriteWhite001Region;
	private BitmapTextureAtlas spriteWhite001Texture;

	public TextureRegion spriteAIFirst001Region;
	private BitmapTextureAtlas spriteAIFirst001Texture;

	public TextureRegion spriteYouFirst001Region;
	private BitmapTextureAtlas spriteYouFirst001Texture;
	
	public TextureRegion spriteThinking001Region;
	private BitmapTextureAtlas spriteThinking001Texture;
	
	public TextureRegion spriteDraw001Region;
	private BitmapTextureAtlas spriteDraw001Texture;

	public TextureRegion spriteYouWin001Region;
	private BitmapTextureAtlas spriteYouWin001Texture;
	
	public TextureRegion spriteYouLose001Region;
	private BitmapTextureAtlas spriteYouLose001Texture;

	public TextureRegion spriteLeftPointer001Region;
	private BitmapTextureAtlas spriteLeftPointer001Texture;
	
	public TextureRegion spriteRightPointer001Region;
	private BitmapTextureAtlas spriteRightPointer001Texture;
	
	public TextureRegion buttonExampleUpRegion;
	private BitmapTextureAtlas buttonExampleUpTexture;
	
	public TextureRegion buttonExampleOverRegion;
	private BitmapTextureAtlas buttonExampleOverTexture;	
	
	//menu
	
	public TextureRegion menuStart001Region;
	private BitmapTextureAtlas menuStart001Texture;	 
	
	public TextureRegion menuSettings001Region;
	private BitmapTextureAtlas menuSettings001Texture;	
	
	public TextureRegion menuHelp001Region;
	private BitmapTextureAtlas menuHelp001Texture;	
	
	public TextureRegion menuQuit001Region;
	private BitmapTextureAtlas menuQuit001Texture;	
	
	public TextureRegion menuStart002Region;
	private BitmapTextureAtlas menuStart002Texture;	 
	
	public TextureRegion menuSettings002Region;
	private BitmapTextureAtlas menuSettings002Texture;	
	
	public TextureRegion menuHelp002Region;
	private BitmapTextureAtlas menuHelp002Texture;	
	
	public TextureRegion menuQuit002Region;
	private BitmapTextureAtlas menuQuit002Texture;	
	
	//button
	
	public TextureRegion buttonQuit001Region;
	private BitmapTextureAtlas buttonQuit001Texture;	 
	
	public TextureRegion buttonNewgame001Region;
	private BitmapTextureAtlas buttonNewgame001Texture;	

	public TextureRegion buttonQuit002Region;
	private BitmapTextureAtlas buttonQuit002Texture;	 
	
	public TextureRegion buttonNewgame002Region;
	private BitmapTextureAtlas buttonNewgame002Texture;	
	
	
	public TextureRegion buttonLow001Region;
	private BitmapTextureAtlas buttonLow001Texture;	 
	
	public TextureRegion buttonMedium001Region;
	private BitmapTextureAtlas buttonMedium001Texture;	

	public TextureRegion buttonHigh001Region;
	private BitmapTextureAtlas buttonHigh001Texture;	

	public TextureRegion buttonRandom001Region;
	private BitmapTextureAtlas buttonRandom001Texture;	 
	
	public TextureRegion buttonComputer001Region;
	private BitmapTextureAtlas buttonComputer001Texture;	

	public TextureRegion buttonHuman001Region;
	private BitmapTextureAtlas buttonHuman001Texture;
	
	
	public TextureRegion buttonLow002Region;
	private BitmapTextureAtlas buttonLow002Texture;	 
	
	public TextureRegion buttonMedium002Region;
	private BitmapTextureAtlas buttonMedium002Texture;	

	public TextureRegion buttonHigh002Region;
	private BitmapTextureAtlas buttonHigh002Texture;	

	public TextureRegion buttonRandom002Region;
	private BitmapTextureAtlas buttonRandom002Texture;	 
	
	public TextureRegion buttonComputer002Region;
	private BitmapTextureAtlas buttonComputer002Texture;	

	public TextureRegion buttonHuman002Region;
	private BitmapTextureAtlas buttonHuman002Texture;

	
	public TextureRegion buttonOK001Region;
	private BitmapTextureAtlas buttonOK001Texture;

	public TextureRegion buttonOK002Region;
	private BitmapTextureAtlas buttonOK002Texture;
	
	//
	
	public Font font;
	private BitmapTextureAtlas fontTexture;
	
	public float currentFPS;
	
	public TextureRegion pictureRegion;
	public Picture picture;
	private PictureBitmapTextureAtlasSource pictureSource;
	private BitmapTextureAtlas pictureTexture;
	
	public GameSceneContext() {		
		
	}

	public void init(BaseGameActivity activity, int initType) {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		if (initType == INIT_RES_TYPE_ALL) {
			initMin(activity);
			initAll(activity);
		} else if (initType == INIT_RES_TYPE_MIN){
			initMin(activity);
		}
	}
	
	public void initMin(BaseGameActivity activity) {
		this.difficulty = getLastDifficulty(activity);
		this.blackplayer = getLastBlackPlayer(activity);
		this.highestScore = getLastHighestScore(activity);
		
		this.bgLogo001Texture = new BitmapTextureAtlas(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.bgLogo001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bgLogo001Texture, activity, "bg_logo_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.bgLogo001Texture);
	}
	
	public void initAll(BaseGameActivity activity) {
		if (TEST_BUG_REPORT) {
			this.fontTexture.clearTextureAtlasSources();
		}
		
		this.fontTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.font = new Font(this.fontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 12, true, Color.BLACK);
		activity.getEngine().getTextureManager().loadTexture(this.fontTexture);
		activity.getFontManager().loadFont(this.font);
		
		this.badgeTexture = new BitmapTextureAtlas(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.badgeRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.badgeTexture, activity, "badge.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.badgeTexture);
		
		this.bgGame001Texture = new BitmapTextureAtlas(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.bgGame001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bgGame001Texture, activity, "bg_game_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.bgGame001Texture);

		this.bgSettings001Texture = new BitmapTextureAtlas(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.bgSettings001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bgSettings001Texture, activity, "bg_settings_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.bgSettings001Texture);
		
		this.fontNumber001Region = new TiledTextureRegion[FONT_NUMBER1_SIZE];
		this.fontNumber001Texture = new BitmapTextureAtlas[FONT_NUMBER1_SIZE];
		for (int i = 0; i < fontNumber001Texture.length; i++) {
			this.fontNumber001Texture[i] = new BitmapTextureAtlas(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			this.fontNumber001Region[i] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.fontNumber001Texture[i], activity, "font_number_001.png", 0, 0, 5, 13);
			activity.getEngine().getTextureManager().loadTexture(this.fontNumber001Texture[i]);
		}
		
		this.fontNumber002Region = new TiledTextureRegion[FONT_NUMBER2_SIZE];
		this.fontNumber002Texture = new BitmapTextureAtlas[FONT_NUMBER2_SIZE];
		for (int i = 0; i < fontNumber002Texture.length; i++) {
			this.fontNumber002Texture[i] = new BitmapTextureAtlas(512, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			this.fontNumber002Region[i] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.fontNumber002Texture[i], activity, "font_number_002.png", 0, 0, 10, 1);
			activity.getEngine().getTextureManager().loadTexture(this.fontNumber002Texture[i]);
		}

		this.fontNumber003Region = new TiledTextureRegion[FONT_NUMBER3_SIZE];
		this.fontNumber003Texture = new BitmapTextureAtlas[FONT_NUMBER3_SIZE];
		for (int i = 0; i < fontNumber003Texture.length; i++) {
			this.fontNumber003Texture[i] = new BitmapTextureAtlas(512, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			this.fontNumber003Region[i] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.fontNumber003Texture[i], activity, "font_number_003.png", 0, 0, 10, 1);
			activity.getEngine().getTextureManager().loadTexture(this.fontNumber003Texture[i]);
		}
		
		// bg
	
		this.bgTitle001Texture = new BitmapTextureAtlas(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.bgTitle001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bgTitle001Texture, activity, "bg_title_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.bgTitle001Texture);

		this.bgHelp001Texture = new BitmapTextureAtlas(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.bgHelp001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bgHelp001Texture, activity, "bg_help_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.bgHelp001Texture);

		this.bgHelp002Texture = new BitmapTextureAtlas(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.bgHelp002Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bgHelp002Texture, activity, "bg_help_002.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.bgHelp002Texture);
		
		this.bgHelp003Texture = new BitmapTextureAtlas(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.bgHelp003Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bgHelp003Texture, activity, "bg_help_003.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.bgHelp003Texture);
		
		this.bgHelp004Texture = new BitmapTextureAtlas(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.bgHelp004Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bgHelp004Texture, activity, "bg_help_004.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.bgHelp004Texture);
		
		//sprite
		
		this.spriteBlack001Texture = new BitmapTextureAtlas(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.spriteBlack001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.spriteBlack001Texture, activity, "sprite_black_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.spriteBlack001Texture);
		
		this.spriteWhite001Texture = new BitmapTextureAtlas(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.spriteWhite001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.spriteWhite001Texture, activity, "sprite_white_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.spriteWhite001Texture);

		this.spriteAIFirst001Texture = new BitmapTextureAtlas(256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.spriteAIFirst001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.spriteAIFirst001Texture, activity, "sprite_aifirst_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.spriteAIFirst001Texture);

		this.spriteYouFirst001Texture = new BitmapTextureAtlas(256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.spriteYouFirst001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.spriteYouFirst001Texture, activity, "sprite_youfirst_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.spriteYouFirst001Texture);

		this.spriteThinking001Texture = new BitmapTextureAtlas(256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.spriteThinking001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.spriteThinking001Texture, activity, "sprite_thinking_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.spriteThinking001Texture);

		this.spriteDraw001Texture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.spriteDraw001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.spriteDraw001Texture, activity, "sprite_draw_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.spriteDraw001Texture);

		this.spriteYouWin001Texture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.spriteYouWin001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.spriteYouWin001Texture, activity, "sprite_youwin_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.spriteYouWin001Texture);

		this.spriteYouLose001Texture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.spriteYouLose001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.spriteYouLose001Texture, activity, "sprite_youlose_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.spriteYouLose001Texture);

		this.spriteLeftPointer001Texture = new BitmapTextureAtlas(32, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.spriteLeftPointer001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.spriteLeftPointer001Texture, activity, "sprite_leftpointer_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.spriteLeftPointer001Texture);

		this.spriteRightPointer001Texture = new BitmapTextureAtlas(32, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.spriteRightPointer001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.spriteRightPointer001Texture, activity, "sprite_rightpointer_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.spriteRightPointer001Texture);
		
		
		
		this.buttonExampleUpTexture = new BitmapTextureAtlas(256, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonExampleUpRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonExampleUpTexture, activity, "button_example_up.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonExampleUpTexture);
		
		this.buttonExampleOverTexture = new BitmapTextureAtlas(256, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonExampleOverRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonExampleOverTexture, activity, "button_example_over.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonExampleOverTexture);
		
		//menu
		
		this.menuStart001Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.menuStart001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuStart001Texture, activity, "menu_start_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.menuStart001Texture);

		this.menuSettings001Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.menuSettings001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuSettings001Texture, activity, "menu_settings_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.menuSettings001Texture);
		
		this.menuHelp001Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.menuHelp001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuHelp001Texture, activity, "menu_help_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.menuHelp001Texture);
		
		this.menuQuit001Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.menuQuit001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuQuit001Texture, activity, "menu_quit_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.menuQuit001Texture);

		this.menuStart002Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.menuStart002Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuStart002Texture, activity, "menu_start_002.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.menuStart002Texture);

		this.menuSettings002Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.menuSettings002Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuSettings002Texture, activity, "menu_settings_002.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.menuSettings002Texture);
		
		this.menuHelp002Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.menuHelp002Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuHelp002Texture, activity, "menu_help_002.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.menuHelp002Texture);
		
		this.menuQuit002Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.menuQuit002Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.menuQuit002Texture, activity, "menu_quit_002.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.menuQuit002Texture);
		
		//button
		
		this.buttonQuit001Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonQuit001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonQuit001Texture, activity, "button_quit_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonQuit001Texture);
		
		this.buttonNewgame001Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonNewgame001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonNewgame001Texture, activity, "button_newgame_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonNewgame001Texture);

		this.buttonQuit002Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonQuit002Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonQuit002Texture, activity, "button_quit_002.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonQuit002Texture);
		
		this.buttonNewgame002Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonNewgame002Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonNewgame002Texture, activity, "button_newgame_002.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonNewgame002Texture);
		
		
		
		
		
		this.buttonLow001Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonLow001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonLow001Texture, activity, "button_low_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonLow001Texture);
		
		this.buttonMedium001Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonMedium001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonMedium001Texture, activity, "button_medium_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonMedium001Texture);
		
		this.buttonHigh001Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonHigh001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonHigh001Texture, activity, "button_high_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonHigh001Texture);
		
		this.buttonRandom001Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonRandom001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonRandom001Texture, activity, "button_random_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonRandom001Texture);
		
		this.buttonComputer001Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonComputer001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonComputer001Texture, activity, "button_computer_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonComputer001Texture);
		
		this.buttonHuman001Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonHuman001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonHuman001Texture, activity, "button_human_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonHuman001Texture);
				
		
		this.buttonLow002Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonLow002Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonLow002Texture, activity, "button_low_002.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonLow002Texture);
		
		this.buttonMedium002Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonMedium002Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonMedium002Texture, activity, "button_medium_002.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonMedium002Texture);
		
		this.buttonHigh002Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonHigh002Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonHigh002Texture, activity, "button_high_002.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonHigh002Texture);
		
		this.buttonRandom002Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonRandom002Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonRandom002Texture, activity, "button_random_002.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonRandom002Texture);
		
		this.buttonComputer002Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonComputer002Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonComputer002Texture, activity, "button_computer_002.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonComputer002Texture);
		
		this.buttonHuman002Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonHuman002Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonHuman002Texture, activity, "button_human_002.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonHuman002Texture);
		

		this.buttonOK001Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonOK001Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonOK001Texture, activity, "button_ok_001.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonOK001Texture);
		
		this.buttonOK002Texture = new BitmapTextureAtlas(256, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.buttonOK002Region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.buttonOK002Texture, activity, "button_ok_002.png", 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.buttonOK002Texture);
		
		
		//
		//if (false) {
		this.picture = new Picture();
		this.pictureSource = new SimplePictureBitmapTextureAtlasSource(picture, CAMERA_WIDTH, CAMERA_HEIGHT);
		this.pictureTexture = new BitmapTextureAtlas(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.pictureRegion = BitmapTextureAtlasTextureRegionFactory.createFromSource(this.pictureTexture, this.pictureSource, 0, 0);
		activity.getEngine().getTextureManager().loadTexture(this.pictureTexture);
		//}
	}
	
	public void releaseAll(BaseGameActivity activity) {
		setLastDifficulty(activity, this.difficulty);
		setLastBlackPlayer(activity, this.blackplayer);
		setLastHighestScore(activity, this.highestScore);
		
		if (this.bgLogo001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.bgLogo001Texture);
			this.bgLogo001Texture.setLoadedToHardware(false);
			this.bgLogo001Texture.setUpdateOnHardwareNeeded(false);
			this.bgLogo001Texture.clearTextureAtlasSources();
			this.bgLogo001Texture = null;
		}
		this.bgLogo001Region = null;
				
		if (this.fontTexture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.fontTexture);
			this.fontTexture.setLoadedToHardware(false);
			this.fontTexture.setUpdateOnHardwareNeeded(false);
			this.fontTexture.clearTextureAtlasSources();
			this.fontTexture = null;
		}
		
		activity.getFontManager().clear();
		this.font = null;
		
		if (this.badgeTexture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.badgeTexture);
			this.badgeTexture.setLoadedToHardware(false);
			this.badgeTexture.setUpdateOnHardwareNeeded(false);
			this.badgeTexture.clearTextureAtlasSources();
			this.badgeTexture = null;
		}
		this.badgeRegion = null;
		
		
		if (this.bgGame001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.bgGame001Texture);
			this.bgGame001Texture.setLoadedToHardware(false);
			this.bgGame001Texture.setUpdateOnHardwareNeeded(false);
			this.bgGame001Texture.clearTextureAtlasSources();
			this.bgGame001Texture = null;
		}
		this.bgGame001Region = null;
		
		if (this.bgSettings001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.bgSettings001Texture);
			this.bgSettings001Texture.setLoadedToHardware(false);
			this.bgSettings001Texture.setUpdateOnHardwareNeeded(false);
			this.bgSettings001Texture.clearTextureAtlasSources();
			this.bgSettings001Texture = null;
		}
		this.bgSettings001Region = null;
		
		if (fontNumber001Texture != null && fontNumber001Region != null) {
			for (int i = 0; i < this.fontNumber001Texture.length; i++) {
				if (this.fontNumber001Texture[i] != null) {
					activity.getEngine().getTextureManager().unloadTexture(this.fontNumber001Texture[i]);
					this.fontNumber001Texture[i].setLoadedToHardware(false);
					this.fontNumber001Texture[i].setUpdateOnHardwareNeeded(false);
					this.fontNumber001Texture[i].clearTextureAtlasSources();
					this.fontNumber001Texture[i] = null;
				}
				this.fontNumber001Region[i] = null;
			}
			this.fontNumber001Texture = null;
			this.fontNumber001Region = null;
		}
		
		if (fontNumber002Texture != null && fontNumber002Region != null) {
			for (int i = 0; i < this.fontNumber002Texture.length; i++) {
				if (this.fontNumber002Texture[i] != null) {
					activity.getEngine().getTextureManager().unloadTexture(this.fontNumber002Texture[i]);
					this.fontNumber002Texture[i].setLoadedToHardware(false);
					this.fontNumber002Texture[i].setUpdateOnHardwareNeeded(false);
					this.fontNumber002Texture[i].clearTextureAtlasSources();
					this.fontNumber002Texture[i] = null;
				}
				this.fontNumber002Region[i] = null;
			}
			this.fontNumber002Texture = null;
			this.fontNumber002Region = null;
		}

		if (fontNumber003Texture != null && fontNumber003Region != null) {
			for (int i = 0; i < this.fontNumber003Texture.length; i++) {
				if (this.fontNumber003Texture[i] != null) {
					activity.getEngine().getTextureManager().unloadTexture(this.fontNumber003Texture[i]);
					this.fontNumber003Texture[i].setLoadedToHardware(false);
					this.fontNumber003Texture[i].setUpdateOnHardwareNeeded(false);
					this.fontNumber003Texture[i].clearTextureAtlasSources();
					this.fontNumber003Texture[i] = null;
				}
				this.fontNumber003Region[i] = null;
			}
			this.fontNumber003Texture = null;
			this.fontNumber003Region = null;
		}
		
		if (this.bgTitle001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.bgTitle001Texture);
			this.bgTitle001Texture.setLoadedToHardware(false);
			this.bgTitle001Texture.setUpdateOnHardwareNeeded(false);
			this.bgTitle001Texture.clearTextureAtlasSources();
			this.bgTitle001Texture = null;
		}
		this.bgTitle001Region = null;
		
		if (this.bgHelp001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.bgHelp001Texture);
			this.bgHelp001Texture.setLoadedToHardware(false);
			this.bgHelp001Texture.setUpdateOnHardwareNeeded(false);
			this.bgHelp001Texture.clearTextureAtlasSources();
			this.bgHelp001Texture = null;
		}
		this.bgHelp001Region = null;
		
		if (this.bgHelp002Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.bgHelp002Texture);
			this.bgHelp002Texture.setLoadedToHardware(false);
			this.bgHelp002Texture.setUpdateOnHardwareNeeded(false);
			this.bgHelp002Texture.clearTextureAtlasSources();
			this.bgHelp002Texture = null;
		}
		this.bgHelp002Region = null;
		
		if (this.bgHelp003Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.bgHelp003Texture);
			this.bgHelp003Texture.setLoadedToHardware(false);
			this.bgHelp003Texture.setUpdateOnHardwareNeeded(false);
			this.bgHelp003Texture.clearTextureAtlasSources();
			this.bgHelp003Texture = null;
		}
		this.bgHelp003Region = null;
		
		if (this.bgHelp004Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.bgHelp004Texture);
			this.bgHelp004Texture.setLoadedToHardware(false);
			this.bgHelp004Texture.setUpdateOnHardwareNeeded(false);
			this.bgHelp004Texture.clearTextureAtlasSources();
			this.bgHelp004Texture = null;
		}
		this.bgHelp004Region = null;
		
		if (this.spriteBlack001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.spriteBlack001Texture);
			this.spriteBlack001Texture.setLoadedToHardware(false);
			this.spriteBlack001Texture.setUpdateOnHardwareNeeded(false);
			this.spriteBlack001Texture.clearTextureAtlasSources();
			this.spriteBlack001Texture = null;
		}
		this.spriteBlack001Region = null;
		
		if (this.spriteWhite001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.spriteWhite001Texture);
			this.spriteWhite001Texture.setLoadedToHardware(false);
			this.spriteWhite001Texture.setUpdateOnHardwareNeeded(false);
			this.spriteWhite001Texture.clearTextureAtlasSources();
			this.spriteWhite001Texture = null;
		}
		this.spriteWhite001Region = null;

		if (this.spriteAIFirst001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.spriteAIFirst001Texture);
			this.spriteAIFirst001Texture.setLoadedToHardware(false);
			this.spriteAIFirst001Texture.setUpdateOnHardwareNeeded(false);
			this.spriteAIFirst001Texture.clearTextureAtlasSources();
			this.spriteAIFirst001Texture = null;
		}
		this.spriteAIFirst001Region = null;
		
		if (this.spriteYouFirst001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.spriteYouFirst001Texture);
			this.spriteYouFirst001Texture.setLoadedToHardware(false);
			this.spriteYouFirst001Texture.setUpdateOnHardwareNeeded(false);
			this.spriteYouFirst001Texture.clearTextureAtlasSources();
			this.spriteYouFirst001Texture = null;
		}
		this.spriteYouFirst001Region = null;
		
		if (this.spriteThinking001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.spriteThinking001Texture);
			this.spriteThinking001Texture.setLoadedToHardware(false);
			this.spriteThinking001Texture.setUpdateOnHardwareNeeded(false);
			this.spriteThinking001Texture.clearTextureAtlasSources();
			this.spriteThinking001Texture = null;
		}
		this.spriteThinking001Region = null;
		
		if (this.spriteDraw001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.spriteDraw001Texture);
			this.spriteDraw001Texture.setLoadedToHardware(false);
			this.spriteDraw001Texture.setUpdateOnHardwareNeeded(false);
			this.spriteDraw001Texture.clearTextureAtlasSources();
			this.spriteDraw001Texture = null;
		}
		this.spriteDraw001Region = null;
		
		if (this.spriteYouWin001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.spriteYouWin001Texture);
			this.spriteYouWin001Texture.setLoadedToHardware(false);
			this.spriteYouWin001Texture.setUpdateOnHardwareNeeded(false);
			this.spriteYouWin001Texture.clearTextureAtlasSources();
			this.spriteYouWin001Texture = null;
		}
		this.spriteYouWin001Region = null;
		
		if (this.spriteYouLose001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.spriteYouLose001Texture);
			this.spriteYouLose001Texture.setLoadedToHardware(false);
			this.spriteYouLose001Texture.setUpdateOnHardwareNeeded(false);
			this.spriteYouLose001Texture.clearTextureAtlasSources();
			this.spriteYouLose001Texture = null;
		}
		this.spriteYouLose001Region = null;

		if (this.spriteLeftPointer001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.spriteLeftPointer001Texture);
			this.spriteLeftPointer001Texture.setLoadedToHardware(false);
			this.spriteLeftPointer001Texture.setUpdateOnHardwareNeeded(false);
			this.spriteLeftPointer001Texture.clearTextureAtlasSources();
			this.spriteLeftPointer001Texture = null;
		}
		this.spriteLeftPointer001Region = null;
		
		if (this.spriteRightPointer001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.spriteRightPointer001Texture);
			this.spriteRightPointer001Texture.setLoadedToHardware(false);
			this.spriteRightPointer001Texture.setUpdateOnHardwareNeeded(false);
			this.spriteRightPointer001Texture.clearTextureAtlasSources();
			this.spriteRightPointer001Texture = null;
		}
		this.spriteRightPointer001Region = null;
		
		
		
		if (this.buttonExampleUpTexture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonExampleUpTexture);
			this.buttonExampleUpTexture.setLoadedToHardware(false);
			this.buttonExampleUpTexture.setUpdateOnHardwareNeeded(false);
			this.buttonExampleUpTexture.clearTextureAtlasSources();
			this.buttonExampleUpTexture = null;
		}
		this.buttonExampleUpRegion = null;
		
		if (this.buttonExampleOverTexture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonExampleOverTexture);
			this.buttonExampleOverTexture.setLoadedToHardware(false);
			this.buttonExampleOverTexture.setUpdateOnHardwareNeeded(false);
			this.buttonExampleOverTexture.clearTextureAtlasSources();
			this.buttonExampleOverTexture = null;
		}
		this.buttonExampleOverRegion = null;
		
		if (this.menuStart001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.menuStart001Texture);
			this.menuStart001Texture.setLoadedToHardware(false);
			this.menuStart001Texture.setUpdateOnHardwareNeeded(false);
			this.menuStart001Texture.clearTextureAtlasSources();
			this.menuStart001Texture = null;
		}
		this.menuStart001Region = null;
		
		if (this.menuSettings001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.menuSettings001Texture);
			this.menuSettings001Texture.setLoadedToHardware(false);
			this.menuSettings001Texture.setUpdateOnHardwareNeeded(false);
			this.menuSettings001Texture.clearTextureAtlasSources();
			this.menuSettings001Texture = null;
		}
		this.menuSettings001Region = null;
		
		if (this.menuHelp001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.menuHelp001Texture);
			this.menuHelp001Texture.setLoadedToHardware(false);
			this.menuHelp001Texture.setUpdateOnHardwareNeeded(false);
			this.menuHelp001Texture.clearTextureAtlasSources();
			this.menuHelp001Texture = null;
		}
		this.menuHelp001Region = null;
		
		if (this.menuQuit001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.menuQuit001Texture);
			this.menuQuit001Texture.setLoadedToHardware(false);
			this.menuQuit001Texture.setUpdateOnHardwareNeeded(false);
			this.menuQuit001Texture.clearTextureAtlasSources();
			this.menuQuit001Texture = null;
		}
		this.menuQuit001Region = null;
		
		if (this.menuStart002Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.menuStart002Texture);
			this.menuStart002Texture.setLoadedToHardware(false);
			this.menuStart002Texture.setUpdateOnHardwareNeeded(false);
			this.menuStart002Texture.clearTextureAtlasSources();
			this.menuStart002Texture = null;
		}
		this.menuStart002Region = null;
		
		if (this.menuSettings002Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.menuSettings002Texture);
			this.menuSettings002Texture.setLoadedToHardware(false);
			this.menuSettings002Texture.setUpdateOnHardwareNeeded(false);
			this.menuSettings002Texture.clearTextureAtlasSources();
			this.menuSettings002Texture = null;
		}
		this.menuSettings002Region = null;
		
		if (this.menuHelp002Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.menuHelp002Texture);
			this.menuHelp002Texture.setLoadedToHardware(false);
			this.menuHelp002Texture.setUpdateOnHardwareNeeded(false);
			this.menuHelp002Texture.clearTextureAtlasSources();
			this.menuHelp002Texture = null;
		}
		this.menuHelp002Region = null;
		
		if (this.menuQuit002Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.menuQuit002Texture);
			this.menuQuit002Texture.setLoadedToHardware(false);
			this.menuQuit002Texture.setUpdateOnHardwareNeeded(false);
			this.menuQuit002Texture.clearTextureAtlasSources();
			this.menuQuit002Texture = null;
		}
		this.menuQuit002Region = null;
		
		if (this.buttonQuit001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonQuit001Texture);
			this.buttonQuit001Texture.setLoadedToHardware(false);
			this.buttonQuit001Texture.setUpdateOnHardwareNeeded(false);
			this.buttonQuit001Texture.clearTextureAtlasSources();
			this.buttonQuit001Texture = null;
		}
		this.buttonQuit001Region = null;
		
		if (this.buttonNewgame001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonNewgame001Texture);
			this.buttonNewgame001Texture.setLoadedToHardware(false);
			this.buttonNewgame001Texture.setUpdateOnHardwareNeeded(false);
			this.buttonNewgame001Texture.clearTextureAtlasSources();
			this.buttonNewgame001Texture = null;
		}
		this.buttonNewgame001Region = null;
		
		if (this.buttonQuit002Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonQuit002Texture);
			this.buttonQuit002Texture.setLoadedToHardware(false);
			this.buttonQuit002Texture.setUpdateOnHardwareNeeded(false);
			this.buttonQuit002Texture.clearTextureAtlasSources();
			this.buttonQuit002Texture = null;
		}
		this.buttonQuit002Region = null;
		
		if (this.buttonNewgame002Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonNewgame002Texture);
			this.buttonNewgame002Texture.setLoadedToHardware(false);
			this.buttonNewgame002Texture.setUpdateOnHardwareNeeded(false);
			this.buttonNewgame002Texture.clearTextureAtlasSources();
			this.buttonNewgame002Texture = null;
		}
		this.buttonNewgame002Region = null;
		
		if (this.buttonLow001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonLow001Texture);
			this.buttonLow001Texture.setLoadedToHardware(false);
			this.buttonLow001Texture.setUpdateOnHardwareNeeded(false);
			this.buttonLow001Texture.clearTextureAtlasSources();
			this.buttonLow001Texture = null;
		}
		this.buttonLow001Region = null;
		
		if (this.buttonMedium001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonMedium001Texture);
			this.buttonMedium001Texture.setLoadedToHardware(false);
			this.buttonMedium001Texture.setUpdateOnHardwareNeeded(false);
			this.buttonMedium001Texture.clearTextureAtlasSources();
			this.buttonMedium001Texture = null;
		}
		this.buttonMedium001Region = null;
		
		if (this.buttonHigh001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonHigh001Texture);
			this.buttonHigh001Texture.setLoadedToHardware(false);
			this.buttonHigh001Texture.setUpdateOnHardwareNeeded(false);
			this.buttonHigh001Texture.clearTextureAtlasSources();
			this.buttonHigh001Texture = null;
		}
		this.buttonHigh001Region = null;
		
		if (this.buttonRandom001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonRandom001Texture);
			this.buttonRandom001Texture.setLoadedToHardware(false);
			this.buttonRandom001Texture.setUpdateOnHardwareNeeded(false);
			this.buttonRandom001Texture.clearTextureAtlasSources();
			this.buttonRandom001Texture = null;
		}
		this.buttonRandom001Region = null;
		
		if (this.buttonComputer001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonComputer001Texture);
			this.buttonComputer001Texture.setLoadedToHardware(false);
			this.buttonComputer001Texture.setUpdateOnHardwareNeeded(false);
			this.buttonComputer001Texture.clearTextureAtlasSources();
			this.buttonComputer001Texture = null;
		}
		this.buttonComputer001Region = null;
		
		if (this.buttonHuman001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonHuman001Texture);
			this.buttonHuman001Texture.setLoadedToHardware(false);
			this.buttonHuman001Texture.setUpdateOnHardwareNeeded(false);
			this.buttonHuman001Texture.clearTextureAtlasSources();
			this.buttonHuman001Texture = null;
		}
		this.buttonHuman001Region = null;
		
		if (this.buttonLow002Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonLow002Texture);
			this.buttonLow002Texture.setLoadedToHardware(false);
			this.buttonLow002Texture.setUpdateOnHardwareNeeded(false);
			this.buttonLow002Texture.clearTextureAtlasSources();
			this.buttonLow002Texture = null;
		}
		this.buttonLow002Region = null;
		
		if (this.buttonMedium002Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonMedium002Texture);
			this.buttonMedium002Texture.setLoadedToHardware(false);
			this.buttonMedium002Texture.setUpdateOnHardwareNeeded(false);
			this.buttonMedium002Texture.clearTextureAtlasSources();
			this.buttonMedium002Texture = null;
		}
		this.buttonMedium002Region = null;
		
		if (this.buttonHigh002Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonHigh002Texture);
			this.buttonHigh002Texture.setLoadedToHardware(false);
			this.buttonHigh002Texture.setUpdateOnHardwareNeeded(false);
			this.buttonHigh002Texture.clearTextureAtlasSources();
			this.buttonHigh002Texture = null;
		}
		this.buttonHigh002Region = null;
		
		if (this.buttonRandom002Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonRandom002Texture);
			this.buttonRandom002Texture.setLoadedToHardware(false);
			this.buttonRandom002Texture.setUpdateOnHardwareNeeded(false);
			this.buttonRandom002Texture.clearTextureAtlasSources();
			this.buttonRandom002Texture = null;
		}
		this.buttonRandom002Region = null;
		
		if (this.buttonComputer002Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonComputer002Texture);
			this.buttonComputer002Texture.setLoadedToHardware(false);
			this.buttonComputer002Texture.setUpdateOnHardwareNeeded(false);
			this.buttonComputer002Texture.clearTextureAtlasSources();
			this.buttonComputer002Texture = null;
		}
		this.buttonComputer002Region = null;
		
		if (this.buttonHuman002Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonHuman002Texture);
			this.buttonHuman002Texture.setLoadedToHardware(false);
			this.buttonHuman002Texture.setUpdateOnHardwareNeeded(false);
			this.buttonHuman002Texture.clearTextureAtlasSources();
			this.buttonHuman002Texture = null;
		}
		this.buttonHuman002Region = null;
		
		if (this.buttonOK001Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonOK001Texture);
			this.buttonOK001Texture.setLoadedToHardware(false);
			this.buttonOK001Texture.setUpdateOnHardwareNeeded(false);
			this.buttonOK001Texture.clearTextureAtlasSources();
			this.buttonOK001Texture = null;
		}
		this.buttonOK001Region = null;
		
		if (this.buttonOK002Texture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.buttonOK002Texture);
			this.buttonOK002Texture.setLoadedToHardware(false);
			this.buttonOK002Texture.setUpdateOnHardwareNeeded(false);
			this.buttonOK002Texture.clearTextureAtlasSources();
			this.buttonOK002Texture = null;
		}
		this.buttonOK002Region = null;
		
		if (this.pictureTexture != null) {
			activity.getEngine().getTextureManager().unloadTexture(this.pictureTexture);
			this.pictureTexture.setLoadedToHardware(false);
			this.pictureTexture.setUpdateOnHardwareNeeded(false);
			this.pictureTexture.clearTextureAtlasSources();
			this.pictureTexture = null;
		}
		this.pictureRegion = null;
	}

	public Canvas beginDrawPicture() {
		return picture.beginRecording(this.pictureRegion.getWidth(), this.pictureRegion.getHeight());
	}
	
	public void endDrawPicture() {
		picture.endRecording();
		this.pictureTexture.setUpdateOnHardwareNeeded(true);
	}
	
    private static void setLastDifficulty(Context context, int value) {
		Editor e = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE).edit();
		e.putInt(SHARE_PREF_DIFFICULTY, value);
		e.commit();
    }
	
    private static int getLastDifficulty(Context context) {
		SharedPreferences sp = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
		return sp.getInt(SHARE_PREF_DIFFICULTY, 0);
    }

    private static void setLastBlackPlayer(Context context, int value) {
		Editor e = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE).edit();
		e.putInt(SHARE_PREF_BLACKPLAYER, value);
		e.commit();
    }
	
    private static int getLastBlackPlayer(Context context) {
		SharedPreferences sp = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
		return sp.getInt(SHARE_PREF_BLACKPLAYER, 0);
    }
    
    private static void setLastHighestScore(Context context, int value) {
		Editor e = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE).edit();
		e.putInt(SHARE_PREF_HIGHESTSCORE, value);
		e.commit();
    }
	
    private static int getLastHighestScore(Context context) {
		SharedPreferences sp = context.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
		return sp.getInt(SHARE_PREF_HIGHESTSCORE, 0);
    }
}
