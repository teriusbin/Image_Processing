#include "com_samsung_ip_JniIPActivity.h"

#include <jni.h>
#include <android/log.h>
#include <stdio.h>
#include <stdlib.h>

#include "image_processing.h"

/* For JNI: C++ compiler need this */
#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     com_semo_jnigl_JniGLActivity
 * Method:    nativeOnCreate
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_samsung_ip_JniIPActivity_nativeOnCreate
  (JNIEnv *, jobject)
{
  //test()

}

/*
 * Class:     com_semo_jnigl_JniGLActivity
 * Method:    nativeOnPause
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_samsung_ip_JniIPActivity_nativeOnPause
  (JNIEnv *, jobject)
{

}

/*
 * Class:     com_semo_jnigl_JniGLActivity
 * Method:    nativeOnResume
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_samsung_ip_JniIPActivity_nativeOnResume
  (JNIEnv *, jobject)
{

}

/*
 * Class:     com_semo_jnigl_JniGLActivity
 * Method:    nativeOnDestroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_samsung_ip_JniIPActivity_nativeOnDestroy
  (JNIEnv *, jobject)
{

}

/*
 * Class:     com_semo_jnigl_JniGLActivity
 * Method:    nativeInitGL
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_samsung_ip_JniIPActivity_nativeInitGL
  (JNIEnv *, jobject)
{

}

/*
 * Class:     com_semo_jnigl_JniGLActivity
 * Method:    nativeResize
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_com_samsung_ip_JniIPActivity_nativeResize
  (JNIEnv *, jobject, jint w, jint h)
{

}

/*
 * Class:     com_semo_jnigl_JniGLActivity
 * Method:    nativeDrawIteration
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_samsung_ip_JniIPActivity_nativeDrawIteration
  (JNIEnv *, jclass, jfloat mx, jfloat my)
{

}

JNIEXPORT void JNICALL Java_com_samsung_ip_JniIPActivity_nativeOnTrackballEvent
  (JNIEnv *, jclass, jint e,jfloat x, jfloat y)
{

}

JNIEXPORT void JNICALL Java_com_samsung_ip_JniIPActivity_nativeOnTouchEvent
  (JNIEnv *, jclass, jint e,jfloat x, jfloat y)
{

}

/*
 * Class:     com_nornenjs_android_JniGLActivity
 * Method:    nativeSetTextureData
 * Signature: ([III)V
 */
JNIEXPORT jint JNICALL Java_com_samsung_ip_JniIPActivity_nativeGetOutputPixel
(JNIEnv *env, jclass thiz, jbyteArray inputarr, jbyteArray  outpuarr, jint width, jint height)
{
	int c_height = height<<1;
	int c_width  = width<<1;
	int size = c_height * c_width;

	unsigned char *srcdata = new unsigned char[(width*height)<<2];
	env->GetByteArrayRegion (inputarr, 0, (width*height)<<2, reinterpret_cast<jbyte*>(srcdata)); //inputData

	unsigned char **InputImage = MemoryCopy1Dto2D(srcdata, c_width, c_height);
	unsigned char **OutputImage = ImageProcessing(InputImage,  c_width,  c_height);
	unsigned char *destdata = MemoryCopy2Dto1D(OutputImage, c_width, c_height);

	env->SetByteArrayRegion(outpuarr, 0, (width*height)<<2, reinterpret_cast<jbyte*>(destdata));
	env->ReleaseByteArrayElements(inputarr, (jbyte *)srcdata, 0);

	delete destdata;
	delete InputImage;
	delete OutputImage;

	return (width*height)<<2;
}


#ifdef __cplusplus
}
#endif
