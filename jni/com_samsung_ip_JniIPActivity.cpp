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
struct FSFuncParameter getFSFuncParameter(JNIEnv *env, jobject algorithmObject, jclass algorithmClass, int i){

	struct FSFuncParameter paramSet;
	jmethodID method = env->GetMethodID(algorithmClass, "getParameter","(ILjava/lang/String;)Ljava/lang/Object;");

	jstring param1 = env->NewStringUTF("param1");
	jobject intObject = env->CallObjectMethod(algorithmObject, method, i, param1);
	paramSet.FS_param1 = objectToInt(env,intObject);

	jstring param2 = env->NewStringUTF("param2");
	jobject floatObject = env->CallObjectMethod(algorithmObject, method, i, param2);
	paramSet.FS_param2 = objectToFloat(env,floatObject);

	jstring param3 = env->NewStringUTF("param3");
	jobject doubleObject = env->CallObjectMethod(algorithmObject, method, i, param3);
	paramSet.FS_param3 = objectToDouble(env,doubleObject);

	jstring param4 = env->NewStringUTF("param4");
	jobject longObject = env->CallObjectMethod(algorithmObject, method, i, param4);
	paramSet.FS_param4 = objectToLong(env,longObject);

	return paramSet;
}

struct HDRGlareFuncParameter getHDRGlareFuncParameter(JNIEnv *env, jobject algorithmObject, jclass algorithmClass, int i){

	struct HDRGlareFuncParameter paramSet;
	jmethodID method = env->GetMethodID(algorithmClass, "getParameter","(ILjava/lang/String;)Ljava/lang/Object;");

	jstring param1 = env->NewStringUTF("param1");
	jobject intObject = env->CallObjectMethod(algorithmObject, method, i, param1);
	paramSet.HDRGlare_param1 = objectToInt(env,intObject);

	jstring param2 = env->NewStringUTF("param2");
	jobject floatObject = env->CallObjectMethod(algorithmObject, method, i, param2);
	paramSet.HDRGlare_param2 = objectToFloat(env,floatObject);

	jstring param3 = env->NewStringUTF("param3");
	jobject doubleObject = env->CallObjectMethod(algorithmObject, method, i, param3);
	paramSet.HDRGlare_param3 = objectToDouble(env,doubleObject);

	return paramSet;

}

struct NRFuncParameter getNRFuncParameter(JNIEnv *env, jobject algorithmObject, jclass algorithmClass, int i){

	struct NRFuncParameter paramSet;
	jmethodID method = env->GetMethodID(algorithmClass, "getParameter","(ILjava/lang/String;)Ljava/lang/Object;");

	jstring param1 = env->NewStringUTF("param1");
	jobject intObject = env->CallObjectMethod(algorithmObject, method, i, param1);
	paramSet.NR_param1 = objectToInt(env,intObject);

	jstring param2 = env->NewStringUTF("param2");
	jobject floatObject = env->CallObjectMethod(algorithmObject, method, i, param2);
	paramSet.NR_param2 = objectToFloat(env,floatObject);

	jstring param3 = env->NewStringUTF("param3");
	jobject doubleObject = env->CallObjectMethod(algorithmObject, method, i, param3);
	paramSet.NR_param3 = objectToDouble(env,doubleObject);

	return paramSet;

}

struct FADEFuncParameter getFADEFuncParameter(JNIEnv *env, jobject algorithmObject, jclass algorithmClass, int i){

	struct FADEFuncParameter paramSet;
	jmethodID method = env->GetMethodID(algorithmClass, "getParameter","(ILjava/lang/String;)Ljava/lang/Object;");

	jstring param1 = env->NewStringUTF("param1");
	jobject intObject = env->CallObjectMethod(algorithmObject, method, i, param1);
	paramSet.FADE_param1 = objectToInt(env,intObject);

	jstring param2 = env->NewStringUTF("param2");
	jobject floatObject = env->CallObjectMethod(algorithmObject, method, i, param2);
	paramSet.FADE_param2 = objectToFloat(env,floatObject);

	jstring param3 = env->NewStringUTF("param3");
	jobject doubleObject = env->CallObjectMethod(algorithmObject, method, i, param3);
	paramSet.FADE_param3 = objectToDouble(env,doubleObject);

	return paramSet;

}

struct CSFuncParameter getCSFuncParameter(JNIEnv *env, jobject algorithmObject, jclass algorithmClass, int i){

	struct CSFuncParameter paramSet;
	jmethodID method = env->GetMethodID(algorithmClass, "getParameter","(ILjava/lang/String;)Ljava/lang/Object;");

	jstring param1 = env->NewStringUTF("param1");
	jobject intObject = env->CallObjectMethod(algorithmObject, method, i, param1);
	paramSet.CS_param1 = objectToInt(env,intObject);

	jstring param2 = env->NewStringUTF("param2");
	jobject floatObject = env->CallObjectMethod(algorithmObject, method, i, param2);
	paramSet.CS_param2 = objectToFloat(env,floatObject);

	jstring param3 = env->NewStringUTF("param3");
	jobject doubleObject = env->CallObjectMethod(algorithmObject, method, i, param3);
	paramSet.CS_param3 = objectToDouble(env,doubleObject);

	return paramSet;

}

struct CCFuncParameter getCCFuncParameter(JNIEnv *env, jobject algorithmObject, jclass algorithmClass, int i){
	//std::vector<int> blah;
	struct CCFuncParameter paramSet;
	jmethodID method1 = env->GetMethodID(algorithmClass, "getParameterMapSize", "(I)I");
		int paramLength = env->CallIntMethod(algorithmObject, method1, i);

		jmethodID method2 = env->GetMethodID(algorithmClass, "getKey", "(I)V");
		env->CallVoidMethod(algorithmObject, method2,i);

		for(int j=0; j<paramLength; j++){

			jmethodID method4 = env->GetMethodID(algorithmClass, "getKeyString", "(I)Ljava/lang/Object;");
			jstring resultJNIStr = (jstring) env->CallObjectMethod(algorithmObject,method4, j);

			const char *resultCStr = env->GetStringUTFChars(resultJNIStr, NULL);
			jstring newParmam = env->NewStringUTF(resultCStr);
			jmethodID method3 = env->GetMethodID(algorithmClass, "getParameter","(ILjava/lang/String;)Ljava/lang/Object;");
			jobject Object = env->CallObjectMethod(algorithmObject, method3, i, newParmam);

			if(env->IsInstanceOf(Object, env->FindClass("java/lang/Integer")))
			{
				paramSet.CC_param1 = objectToInt(env,Object);

			}else if(env->IsInstanceOf(Object,env->FindClass("java/lang/Float")))
			{
				paramSet.CC_param2 = objectToFloat(env,Object);

			}else if(env->IsInstanceOf(Object,env->FindClass("java/lang/Double")))
			{
				paramSet.CC_param3 = objectToDouble(env,Object);
			}
		}

	return paramSet;

}

struct ASCRFuncParameter getASCRFuncParameter(JNIEnv *env, jobject algorithmObject, jclass algorithmClass, int i){

	struct ASCRFuncParameter paramSet;

	jmethodID method1 = env->GetMethodID(algorithmClass, "getParameterMapSize", "(I)I");
	int paramLength = env->CallIntMethod(algorithmObject, method1, i);

	jmethodID method2 = env->GetMethodID(algorithmClass, "getKey", "(I)V");
	env->CallVoidMethod(algorithmObject, method2,i);

	for(int j=0; j<paramLength; j++){

		jmethodID method4 = env->GetMethodID(algorithmClass, "getKeyString", "(I)Ljava/lang/Object;");
		jstring resultJNIStr = (jstring) env->CallObjectMethod(algorithmObject,method4, j);

		const char *resultCStr = env->GetStringUTFChars(resultJNIStr, NULL);
		jstring newParmam = env->NewStringUTF(resultCStr);
		jmethodID method3 = env->GetMethodID(algorithmClass, "getParameter","(ILjava/lang/String;)Ljava/lang/Object;");
		jobject Object = env->CallObjectMethod(algorithmObject, method3, i, newParmam);

		if(env->IsInstanceOf(Object, env->FindClass("java/lang/Integer")))
		{
			paramSet.ASCR_param1 = objectToInt(env,Object);

		}else if(env->IsInstanceOf(Object,env->FindClass("java/lang/Float")))
		{
			paramSet.ASCR_param2 = objectToFloat(env,Object);

		}else if(env->IsInstanceOf(Object,env->FindClass("java/lang/Double")))
		{
			paramSet.ASCR_param3 = objectToDouble(env,Object);
		}
	}
	return paramSet;

}



const char* getAlgorithm(JNIEnv *env, jobject algorithmObject, jclass algorithmClass, int i, const char* key){

	jmethodID method = env->GetMethodID(algorithmClass, "getName","(ILjava/lang/String;)Ljava/lang/Object;");

	jstring keyJString = env->NewStringUTF(key);
	jstring resultJNIStr = (jstring) env->CallObjectMethod(algorithmObject,method, i, keyJString);

	const char *resultCStr = env->GetStringUTFChars(resultJNIStr, NULL);

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

		if (!strcmp("FS", name)) {
			__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******test~~~~~~~~~~~FS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			struct FSFuncParameter paramSet = getFSFuncParameter(env,algorithmObject, algorithmClass, i);
			OutputImage = FSFunc(InputImage, c_width, c_height,paramSet);
			InputImage = memoryCopy2DTo2D(OutputImage, c_width, c_height);

		} else if (!strcmp("HDR_Glare", name)) {
			__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******test~~~~~~~~~~~HDR_Glare~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			struct HDRGlareFuncParameter paramSet = getHDRGlareFuncParameter(env,algorithmObject, algorithmClass, i);
			OutputImage = HDRGlareFunc(InputImage, c_width, c_height,paramSet);
			InputImage = memoryCopy2DTo2D(OutputImage, c_width, c_height);


		} else if (!strcmp("NR", name)) {
			__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******test~~~~~~~~~~~NR~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			struct NRFuncParameter paramSet = getNRFuncParameter(env,algorithmObject, algorithmClass, i);
			OutputImage = NRFunc(InputImage, c_width, c_height,paramSet);
			InputImage = memoryCopy2DTo2D(OutputImage, c_width, c_height);

		}else if (!strcmp("FA_DE", name)) {
			__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******test~~~~~~~~~~~FA_DE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			struct FADEFuncParameter paramSet = getFADEFuncParameter(env,algorithmObject, algorithmClass, i);
			OutputImage = FADEFunc(InputImage, c_width, c_height,paramSet);
			InputImage = memoryCopy2DTo2D(OutputImage, c_width, c_height);

		}else if (!strcmp("CS", name)) {
			__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******test~~~~~~~~~~~CS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			struct CSFuncParameter paramSet = getCSFuncParameter(env,algorithmObject, algorithmClass, i);
			OutputImage = CSFunc(InputImage, c_width, c_height,paramSet);
			InputImage = memoryCopy2DTo2D(OutputImage, c_width, c_height);

		}else if (!strcmp("CC", name)) {
			__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******test~~~~~~~~~~~CC~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			struct CCFuncParameter paramSet = getCCFuncParameter(env,algorithmObject, algorithmClass, i);
			OutputImage = CCFunc(InputImage, c_width, c_height,paramSet);
			InputImage = memoryCopy2DTo2D(OutputImage, c_width, c_height);

		}else if (!strcmp("ASCR", name)) {
			__android_log_print(ANDROID_LOG_ERROR, "GOOGLE", "******test~~~~~~~~~~~ASCR~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			struct ASCRFuncParameter paramSet = getASCRFuncParameter(env,algorithmObject, algorithmClass, i);
			OutputImage = ASCRFunc(InputImage, c_width, c_height,paramSet);
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
