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

int objectToInt(JNIEnv *env, jobject input){

	int Value;

	jclass inputClass = env->FindClass("java/lang/Integer");

	if(env->IsInstanceOf(input, inputClass)== JNI_TRUE){

			jmethodID ValueMID   = env->GetMethodID(inputClass, "intValue", "()I");
			Value        = (int) env->CallIntMethod(input, ValueMID);

	}

	return Value;
}
float objectToFloat(JNIEnv *env, jobject input){

	float Value;

	jclass inputClass = env->FindClass("java/lang/Float");

	if(env->IsInstanceOf(input, inputClass)== JNI_TRUE){

			jmethodID ValueMID   = env->GetMethodID(inputClass, "floatValue", "()F");
			Value        = (float) env->CallFloatMethod(input, ValueMID);

	}

	return Value;
}
double objectToDouble(JNIEnv *env, jobject input){

	double Value;

	jclass inputClass = env->FindClass("java/lang/Double");

	if(env->IsInstanceOf(input, inputClass)== JNI_TRUE){

			jmethodID ValueMID   = env->GetMethodID(inputClass, "doubleValue", "()D");
			Value        = (double) env->CallDoubleMethod(input, ValueMID);

	}

	return Value;
}
long objectToLong(JNIEnv *env, jobject input){

	long Value;

	jclass inputClass = env->FindClass("java/lang/Long");

	if(env->IsInstanceOf(input, inputClass)== JNI_TRUE){

			jmethodID ValueMID   = env->GetMethodID(inputClass, "longValue", "()J");
			Value        = (long) env->CallLongMethod(input, ValueMID);

	}

	return Value;
}
struct ImageProcessing1 getAlgorithm_1_parameter(JNIEnv *env, jobject algorithmObject, jclass algorithmClass, int i){

	struct ImageProcessing1 paramSet;
	jmethodID method = env->GetMethodID(algorithmClass, "getParameter","(ILjava/lang/String;)Ljava/lang/Object;");

	jstring param1 = env->NewStringUTF("param1");
	jobject intObject = env->CallObjectMethod(algorithmObject, method, i, param1);
	paramSet.algo1_param1 = objectToInt(env,intObject);

	jstring param2 = env->NewStringUTF("param2");
	jobject floatObject = env->CallObjectMethod(algorithmObject, method, i, param2);
	paramSet.algo1_param2 = objectToFloat(env,floatObject);

	jstring param3 = env->NewStringUTF("param3");
	jobject doubleObject = env->CallObjectMethod(algorithmObject, method, i, param3);
	paramSet.algo1_param3 = objectToDouble(env,doubleObject);

	jstring param4 = env->NewStringUTF("param4");
	jobject longObject = env->CallObjectMethod(algorithmObject, method, i, param4);
	paramSet.algo1_param4 = objectToLong(env,longObject);

	return paramSet;
}

struct ImageProcessing2 getAlgorithm_2_parameter(JNIEnv *env, jobject algorithmObject, jclass algorithmClass, int i){

	struct ImageProcessing2 paramSet;
	jmethodID method = env->GetMethodID(algorithmClass, "getParameter","(ILjava/lang/String;)Ljava/lang/Object;");

	jstring param1 = env->NewStringUTF("param1");
	jobject intObject = env->CallObjectMethod(algorithmObject, method, i, param1);
	paramSet.algo2_param1 = objectToInt(env,intObject);

	jstring param2 = env->NewStringUTF("param2");
	jobject floatObject = env->CallObjectMethod(algorithmObject, method, i, param2);
	paramSet.algo2_param2 = objectToFloat(env,floatObject);

	jstring param3 = env->NewStringUTF("param3");
	jobject doubleObject = env->CallObjectMethod(algorithmObject, method, i, param3);
	paramSet.algo2_param3 = objectToDouble(env,doubleObject);


	return paramSet;
	//__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******inner class...%d" , parameter1);

}

struct ImageProcessing3 getAlgorithm_3_parameter(JNIEnv *env, jobject algorithmObject, jclass algorithmClass, int i){

	struct ImageProcessing3 paramSet;
	jmethodID method = env->GetMethodID(algorithmClass, "getParameter","(ILjava/lang/String;)Ljava/lang/Object;");

	jstring param1 = env->NewStringUTF("param1");
	jobject intObject = env->CallObjectMethod(algorithmObject, method, i, param1);
	paramSet.algo3_param1 = objectToInt(env,intObject);

	jstring param2 = env->NewStringUTF("param2");
	jobject floatObject = env->CallObjectMethod(algorithmObject, method, i, param2);
	paramSet.algo3_param2 = objectToFloat(env,floatObject);

	jstring param3 = env->NewStringUTF("param3");
	jobject doubleObject = env->CallObjectMethod(algorithmObject, method, i, param3);
	paramSet.algo3_param3 = objectToDouble(env,doubleObject);


	return paramSet;
	//__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******inner class...%d" , parameter1);
}

const char* getAlgorithm(JNIEnv *env, jobject algorithmObject, jclass algorithmClass, int i, const char* key){

	jmethodID method = env->GetMethodID(algorithmClass, "getName","(ILjava/lang/String;)Ljava/lang/Object;");

	jstring keyJString = env->NewStringUTF(key);
	jstring resultJNIStr = (jstring) env->CallObjectMethod(algorithmObject,method, i,keyJString);

	const char *resultCStr = env->GetStringUTFChars(resultJNIStr, NULL);
	__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******algoname %s", resultCStr);
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

	unsigned char **InputImage = memoryCopy1Dto2D(srcdata, c_width, c_height);

	jclass algorithmClass = env->GetObjectClass(algorithmObject);

	jmethodID method = env->GetMethodID(algorithmClass, "getAlgorithmListSize", "()I");
	int algoLength = env->CallIntMethod(algorithmObject, method);


	for (int i = 0; i < algoLength; i++) {

		const char *name = getAlgorithm(env, algorithmObject, algorithmClass, i, "algorithmName");

		if (!strcmp("algorithm1", name)) {
			__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******test~~~~~~~~~~~1111~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			struct ImageProcessing1 paramSet = getAlgorithm_1_parameter(env,algorithmObject, algorithmClass, i);
			OutputImage = ImageProcessing1(InputImage, c_width, c_height,paramSet);
			InputImage = memoryCopy2DTo2D(OutputImage, c_width, c_height);

		} else if (!strcmp("algorithm2", name)) {
			__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******test~~~~~~~~~~~2222~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			struct ImageProcessing2 paramSet = getAlgorithm_2_parameter(env,algorithmObject, algorithmClass, i);
			OutputImage = ImageProcessing2(InputImage, c_width, c_height,paramSet);
			InputImage = memoryCopy2DTo2D(OutputImage, c_width, c_height);


		} else if (!strcmp("algorithm3", name)) {
			__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******test~~~~~~~~~~~3333~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			struct ImageProcessing3 paramSet = getAlgorithm_3_parameter(env,algorithmObject, algorithmClass, i);
			OutputImage = ImageProcessing3(InputImage, c_width, c_height,paramSet);
			InputImage = memoryCopy2DTo2D(OutputImage, c_width, c_height);

		}

	}

	unsigned char *destdata = memoryCopy2Dto1D(InputImage, c_width, c_height);
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
