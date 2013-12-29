package com.iteye.weimingtom.reversiwins;

import org.anddev.andengine.opengl.texture.atlas.bitmap.source.PictureBitmapTextureAtlasSource;

import android.graphics.Picture;

public class SimplePictureBitmapTextureAtlasSource extends PictureBitmapTextureAtlasSource{
	public SimplePictureBitmapTextureAtlasSource(Picture pPicture) {
		super(pPicture);
	}
	
	public SimplePictureBitmapTextureAtlasSource(final Picture pPicture, final int pWidth, final int pHeight) {
		super(pPicture, 0, 0, pWidth, pHeight);
	}

	@Override
	public PictureBitmapTextureAtlasSource deepCopy() {
		return new SimplePictureBitmapTextureAtlasSource(this.mPicture);
	}
}
