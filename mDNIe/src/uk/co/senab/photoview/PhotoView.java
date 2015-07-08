/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package uk.co.senab.photoview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher.OnMatrixChangedListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnViewTapListener;

public class PhotoView extends ImageView implements IPhotoView {

    private PhotoViewAttacher mAttacher;

    private ScaleType mPendingScaleType;

    public PhotoView(Context context) {
        this(context, null);
    }

    public PhotoView(Context context, AttributeSet attr) {
        this(context, attr, 0);
    }

    public PhotoView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        super.setScaleType(ScaleType.MATRIX);
        init();
    }

    protected void init() {
        if (null == mAttacher || null == mAttacher.getImageView()) {
            mAttacher = new PhotoViewAttacher(this);
        }

        if (null != mPendingScaleType) {
            setScaleType(mPendingScaleType);
            mPendingScaleType = null;
        }
    }

    /**
     * @deprecated use {@link #setRotationTo(float)}
     */
    
    public void setPhotoViewRotation(float rotationDegree) {
        mAttacher.setRotationTo(rotationDegree);
    }

    
    public void setRotationTo(float rotationDegree) {
        mAttacher.setRotationTo(rotationDegree);
    }

    
    public void setRotationBy(float rotationDegree) {
        mAttacher.setRotationBy(rotationDegree);
    }

    
    public boolean canZoom() {
        return mAttacher.canZoom();
    }

    
    public RectF getDisplayRect() {
        return mAttacher.getDisplayRect();
    }

    
    public Matrix getDisplayMatrix() {
        return mAttacher.getDisplayMatrix();
    }

    
    public boolean setDisplayMatrix(Matrix finalRectangle) {
        return mAttacher.setDisplayMatrix(finalRectangle);
    }

    
    @Deprecated
    public float getMinScale() {
        return getMinimumScale();
    }

    
    public float getMinimumScale() {
        return mAttacher.getMinimumScale();
    }

    
    @Deprecated
    public float getMidScale() {
        return getMediumScale();
    }

    
    public float getMediumScale() {
        return mAttacher.getMediumScale();
    }

    
    @Deprecated
    public float getMaxScale() {
        return getMaximumScale();
    }

    
    public float getMaximumScale() {
        return mAttacher.getMaximumScale();
    }

    
    public float getScale() {
        return mAttacher.getScale();
    }

    
    public ScaleType getScaleType() {
        return mAttacher.getScaleType();
    }

    
    public void setAllowParentInterceptOnEdge(boolean allow) {
        mAttacher.setAllowParentInterceptOnEdge(allow);
    }

    
    @Deprecated
    public void setMinScale(float minScale) {
        setMinimumScale(minScale);
    }

    
    public void setMinimumScale(float minimumScale) {
        mAttacher.setMinimumScale(minimumScale);
    }

    
    @Deprecated
    public void setMidScale(float midScale) {
        setMediumScale(midScale);
    }

    
    public void setMediumScale(float mediumScale) {
        mAttacher.setMediumScale(mediumScale);
    }

    
    @Deprecated
    public void setMaxScale(float maxScale) {
        setMaximumScale(maxScale);
    }

    
    public void setMaximumScale(float maximumScale) {
        mAttacher.setMaximumScale(maximumScale);
    }

    
    public void setScaleLevels(float minimumScale, float mediumScale, float maximumScale) {
        mAttacher.setScaleLevels(minimumScale, mediumScale, maximumScale);
    }

    
    // setImageBitmap calls through to this method
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (null != mAttacher) {
            mAttacher.update();
        }
    }

    
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        if (null != mAttacher) {
            mAttacher.update();
        }
    }

    
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        if (null != mAttacher) {
            mAttacher.update();
        }
    }

    
    public void setOnMatrixChangeListener(OnMatrixChangedListener listener) {
        mAttacher.setOnMatrixChangeListener(listener);
    }

    
    public void setOnLongClickListener(OnLongClickListener l) {
        mAttacher.setOnLongClickListener(l);
    }

    
    public void setOnPhotoTapListener(OnPhotoTapListener listener) {
        mAttacher.setOnPhotoTapListener(listener);
    }

    
    public OnPhotoTapListener getOnPhotoTapListener() {
        return mAttacher.getOnPhotoTapListener();
    }

    
    public void setOnViewTapListener(OnViewTapListener listener) {
        mAttacher.setOnViewTapListener(listener);
    }

    
    public OnViewTapListener getOnViewTapListener() {
        return mAttacher.getOnViewTapListener();
    }

    
    public void setScale(float scale) {
        mAttacher.setScale(scale);
    }

    
    public void setScale(float scale, boolean animate) {
        mAttacher.setScale(scale, animate);
    }

    
    public void setScale(float scale, float focalX, float focalY, boolean animate) {
        mAttacher.setScale(scale, focalX, focalY, animate);
    }

    
    public void setScaleType(ScaleType scaleType) {
        if (null != mAttacher) {
            mAttacher.setScaleType(scaleType);
        } else {
            mPendingScaleType = scaleType;
        }
    }

    
    public void setZoomable(boolean zoomable) {
        mAttacher.setZoomable(zoomable);
    }

    
    public Bitmap getVisibleRectangleBitmap() {
        return mAttacher.getVisibleRectangleBitmap();
    }

    
    public void setZoomTransitionDuration(int milliseconds) {
        mAttacher.setZoomTransitionDuration(milliseconds);
    }

    
    public IPhotoView getIPhotoViewImplementation() {
        return mAttacher;
    }

    
    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener newOnDoubleTapListener) {
        mAttacher.setOnDoubleTapListener(newOnDoubleTapListener);
    }

    
    public void setOnScaleChangeListener(PhotoViewAttacher.OnScaleChangeListener onScaleChangeListener) {
        mAttacher.setOnScaleChangeListener(onScaleChangeListener);
    }

    
    protected void onDetachedFromWindow() {
        mAttacher.cleanup();
        super.onDetachedFromWindow();
    }

    
    protected void onAttachedToWindow() {
        init();
        super.onAttachedToWindow();
    }
}