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
 * Class:     com_nornenjs_android_JniGLActivity
 * Method:    nativeSetTextureData
 * Signature: ([III)V
 */

struct ImageProcessing1 getAlgorithm_1_parameter(JNIEnv *env){

	jclass parameterClass =  env->FindClass("com/samsung/ip/Alogrithm$Algorithm_1_parameter");
	jmethodID parameterClassConstructor = env->GetMethodID(parameterClass, "<init>", "(Lcom/samsung/ip/Alogrithm;)V");
	jobject object = env->NewObject(parameterClass, parameterClassConstructor, NULL);

	jmethodID metId = env->GetMethodID(parameterClass,"getParameter_1","()I");
	int parameter1 = env->CallIntMethod(object,metId);

	struct ImageProcessing1 paramSet;

	paramSet.param1 = parameter1;

	return paramSet;
	//__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******inner class...%d" , parameter1);

}

struct ImageProcessing2 getAlgorithm_2_parameter(JNIEnv *env){

	jclass parameterClass =  env->FindClass("com/samsung/ip/Alogrithm$Algorithm_2_parameter");
	jmethodID parameterClassConstructor = env->GetMethodID(parameterClass, "<init>", "(Lcom/samsung/ip/Alogrithm;)V");
	jobject object = env->NewObject(parameterClass, parameterClassConstructor, NULL);

	jmethodID metId = env->GetMethodID(parameterClass,"getParameter_2","()I");
	int parameter1 = env->CallIntMethod(object,metId);

	struct ImageProcessing2 paramSet;

	paramSet.param1 = parameter1;

	return paramSet;
	//__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******inner class...%d" , parameter1);

}

struct ImageProcessing3 getAlgorithm_3_parameter(JNIEnv *env){

	jclass parameterClass =  env->FindClass("com/samsung/ip/Alogrithm$Algorithm_3_parameter");
	jmethodID parameterClassConstructor = env->GetMethodID(parameterClass, "<init>", "(Lcom/samsung/ip/Alogrithm;)V");
	jobject object = env->NewObject(parameterClass, parameterClassConstructor, NULL);

	jmethodID metId = env->GetMethodID(parameterClass,"getParameter_3","()I");
	int parameter1 = env->CallIntMethod(object,metId);

	struct ImageProcessing3 paramSet;

	paramSet.param1 = parameter1;

	return paramSet;
	//__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******inner class...%d" , parameter1);

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

const char* getAlgorithm(JNIEnv *env, jobject algorithmObject, jclass algorithmClass, int i){


	jmethodID method = env->GetMethodID(algorithmClass, "getName","(I)Ljava/lang/String;");
	jstring resultJNIStr = (jstring) env->CallObjectMethod(algorithmObject,method, i);

	const char *resultCStr = env->GetStringUTFChars(resultJNIStr, NULL);
	__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******third...%s", resultCStr);

	//env->ReleaseStringUTFChars(resultJNIStr, resultCStr);

	return resultCStr;

}

JNIEXPORT jint JNICALL Java_com_samsung_ip_JniIPActivity_nativeGetOutputPixel
(JNIEnv *env, jclass thiz, jbyteArray inputarr, jbyteArray  outpuarr, jobject algorithmObject, jint width, jint height)
{


	unsigned char **OutputImage;
	int c_height = height<<1;
	int c_width  = width<<1;
	int size = c_height * c_width;

	unsigned char *srcdata = new unsigned char[(width*height)<<2];
	env->GetByteArrayRegion (inputarr, 0, (width*height)<<2, reinterpret_cast<jbyte*>(srcdata)); //inputData

	unsigned char **InputImage = MemoryCopy1Dto2D(srcdata, c_width, c_height);

	//getAlgorithmName(env, algorithmObject);
	jclass algorithmClass = env->GetObjectClass(algorithmObject);
	jmethodID method = env->GetMethodID(algorithmClass, "getListSize", "()I");

	int length = env->CallIntMethod(algorithmObject, method);

	for (int i = 0; i < length; i++) {

		const char *name = getAlgorithm(env,algorithmObject,algorithmClass,i);

		if(!strcmp("algorithm1",name)){

			struct ImageProcessing1 paramSet = getAlgorithm_1_parameter(env);
		 	OutputImage = ImageProcessing1(InputImage,  c_width,  c_height ,paramSet);
		 	InputImage = memcpy2DTo2D(OutputImage,c_width,  c_height);
		 	__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******parameter %d", paramSet.param1);


		}else if(!strcmp("algorithm2",name)){

			struct ImageProcessing2 paramSet = getAlgorithm_2_parameter(env);
			OutputImage = ImageProcessing2(InputImage,  c_width,  c_height ,paramSet);
			InputImage = memcpy2DTo2D(OutputImage,c_width,  c_height);
			__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******parameter %d", paramSet.param1);

		}else if(!strcmp("algorithm3",name)){

			struct ImageProcessing3 paramSet = getAlgorithm_3_parameter(env);
			OutputImage = ImageProcessing3(InputImage,  c_width,  c_height ,paramSet);
			InputImage = memcpy2DTo2D(OutputImage,c_width,  c_height);
			__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******parameter %d", paramSet.param1);

		}

	}

	unsigned char *destdata = MemoryCopy2Dto1D(InputImage, c_width, c_height);
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
