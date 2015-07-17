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

void getAlgorithm_1_parameter(JNIEnv *env){

	jclass parameterClass =  env->FindClass("com/samsung/ip/Alogrithm$Algorithm_1_parameter");
	jmethodID parameterClassConstructor = env->GetMethodID(parameterClass, "<init>", "(Lcom/samsung/ip/Alogrithm;)V");
	jobject object = env->NewObject(parameterClass, parameterClassConstructor, NULL);

	jmethodID metId = env->GetMethodID(parameterClass,"getParameter_1","()I");
	int parameter1 = env->CallIntMethod(object,metId);
	__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******inner class...%d" , parameter1);

}

void getAlgorithmName(JNIEnv *env, jobject algorithmObject){

	jclass algorithmClass = env->GetObjectClass(algorithmObject);

	/*direct access member value*/
	jfieldID fid = env->GetFieldID(algorithmClass, "nonce", "I");
	int myInt = env->GetIntField(algorithmObject, fid);
	__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******first...%d", myInt);

	/*getter access member value*/
	jmethodID metId = env->GetMethodID(algorithmClass, "getNonce", "()I");
	int test = env->CallIntMethod(algorithmObject, metId);
	__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******second...%d", test);

	/*getter access string value*/
	jmethodID method = env->GetMethodID(algorithmClass, "getName","()Ljava/lang/String;");
	jstring resultJNIStr = (jstring) env->CallObjectMethod(algorithmObject,method);
	const char *resultCStr = env->GetStringUTFChars(resultJNIStr, NULL);
	__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******third...%s",resultCStr);

	env->ReleaseStringUTFChars(resultJNIStr, resultCStr);

}

JNIEXPORT jint JNICALL Java_com_samsung_ip_JniIPActivity_nativeGetOutputPixel
(JNIEnv *env, jclass thiz, jbyteArray inputarr, jbyteArray  outpuarr, jobject algorithmObject, jint width, jint height)
{


	getAlgorithm_1_parameter(env);
	getAlgorithmName(env, algorithmObject);

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
