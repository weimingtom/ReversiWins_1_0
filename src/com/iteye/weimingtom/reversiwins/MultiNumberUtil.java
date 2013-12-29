package com.iteye.weimingtom.reversiwins;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

public class MultiNumberUtil {
	private TiledSprite[] sprites;
	private boolean isShowPrefix = true;
	private boolean isVisible = true;
	
	public MultiNumberUtil(Scene scene, TiledTextureRegion[] region, int size) {
		sprites = new TiledSprite[size];
		for (int i = 0; i < size; i++) {
			sprites[i] = new TiledSprite(i * region[i].getTileWidth(), 0, region[i]);
			scene.attachChild(sprites[i]);
		}
		setNumber(0);
	}
	
	public void release(Scene scene) {
		for (int i = 0; i < sprites.length; i++) {
			scene.detachChild(sprites[i]);
			sprites[i] = null;
		}
		sprites = null;
	}
	
	public void setNumber(long number) {
		String str = Long.toString(number, 10);
		for (int i = sprites.length - 1; i >= 0; i--) {
			int num;
			if (i < sprites.length - str.length()) {
				num = 0;
			} else {
				num = str.charAt(str.length() - (sprites.length - i)) - '0';
			}
			sprites[i].setCurrentTileIndex(num);
		}
		this.setVisible(this.isVisible);
	}
	
	public float getWidth() {
		return sprites[0].getWidth() * sprites.length;
	}
	
	public float getHeight() {
		return sprites[0].getHeight();
	}
	
	public float getX() {
		return sprites[0].getX();
	}
	
	public float getY() {
		return sprites[0].getY();
	}	
	
	public void setLocation(float x, float y, float width, float height) {
		for (int i = 0; i < sprites.length; i++) {
			sprites[i].setPosition(x + i * width / sprites.length, y);
			sprites[i].setSize(width / sprites.length, height);
		}
	}
	
	public void setVisible(boolean visible) {
		this.isVisible = visible;
		boolean isNotZoro = false;
		for (int i = 0; i < sprites.length; i++) {
			if (isShowPrefix) {
				sprites[i].setVisible(visible);
			} else {
				if (i < sprites.length - 1) {
					if (!isNotZoro && sprites[i].getCurrentTileIndex() == 0) {
						sprites[i].setVisible(false);
					} else {
						isNotZoro = true;
						sprites[i].setVisible(visible);
					}
				} else {
					sprites[i].setVisible(visible);
				}
			}
		}
	}
	
	public void setShowPrefix(boolean isShowPrefix) {
		this.isShowPrefix = isShowPrefix;
		this.setVisible(this.isVisible);
	}
}
