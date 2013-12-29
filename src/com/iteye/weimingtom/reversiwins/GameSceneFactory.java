package com.iteye.weimingtom.reversiwins;

public class GameSceneFactory {
	public static GameScene createGameScene(String type) {
		GameScene scene = null;
		if (GameScene.NAME.equals(type)) {
			scene = new GameScene();
		} else if (ExitScene.NAME.equals(type)) {
			scene = new ExitScene();
		} else if (Test001Scene.NAME.equals(type)) {
			scene = new Test001Scene();
		} else if (Test002Scene.NAME.equals(type)) {
			scene = new Test002Scene();
		} else if (Test003Scene.NAME.equals(type)) {
			//playing
			scene = new Test003Scene();
		} else if (Test004Scene.NAME.equals(type)) {
			scene = new Test004Scene();
		} else if (Test005Scene.NAME.equals(type)) {
			scene = new Test005Scene();
		} else if (Test006Scene.NAME.equals(type)) {
			//main menu
			scene = new Test006Scene();
		} else if (Test007Scene.NAME.equals(type)) {
			//help
			scene = new Test007Scene();
		} else if (Test008Scene.NAME.equals(type)) {
			scene = new Test008Scene();
		} else if (Test009Scene.NAME.equals(type)) {
			//copyright LOGO
			scene = new Test009Scene();
		} else if (Test010Scene.NAME.equals(type)) {
			//settings
			scene = new Test010Scene();
		} else if (Test011Scene.NAME.equals(type)) {
			scene = new Test011Scene();
		} else if (Test012Scene.NAME.equals(type)) {
			//empty
			scene = new Test012Scene();
		}
 		if (scene != null) {
			scene.setType(type);
		}
		return scene;
	}
	
	public static boolean isExitScene(GameScene scene) {
		return scene != null && ExitScene.NAME.equals(scene.getType());
	}
}
