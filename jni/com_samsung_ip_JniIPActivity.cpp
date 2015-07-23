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

bool objectToBool(JNIEnv *env, jobject input){

	bool Value;

	jclass inputClass = env->FindClass("java/lang/Boolean");

	if(env->IsInstanceOf(input, inputClass)== JNI_TRUE){

			jmethodID ValueMID   = env->GetMethodID(inputClass, "booleanValue", "()Z");
			Value        = (bool) env->CallBooleanMethod(input, ValueMID);

	}

	return Value;
}

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
struct SLCEFuncParameter getSLCEFuncParameter(JNIEnv *env, jobject algorithmObject, jclass algorithmClass, int i){

	struct SLCEFuncParameter paramSet;
	jstring objectKey;
	jobject objectValue;
	jmethodID method = env->GetMethodID(algorithmClass, "getParameter","(ILjava/lang/String;)Ljava/lang/Object;");

	objectKey = env->NewStringUTF("SLCE_MASK");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.SLCE_MASK = objectToBool(env,objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "SLCE_MASK %d",paramSet.SLCE_MASK);

	objectKey = env->NewStringUTF("SLCE_BT");
	objectValue = env->CallObjectMethod(algorithmObject, method, i,objectKey);
	paramSet.SLCE_BT = objectToBool(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "SLCE_BT %d",paramSet.SLCE_BT);

	objectKey = env->NewStringUTF("SLCE_MO");
	objectValue = env->CallObjectMethod(algorithmObject, method, i,objectKey);
	paramSet.SLCE_MO = objectToBool(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "SLCE_MO %d",paramSet.SLCE_MO);

	objectKey = env->NewStringUTF("SLCE_ON");
	objectValue = env->CallObjectMethod(algorithmObject, method, i,objectKey);
	paramSet.SLCE_ON = objectToBool(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "SLCE_ON %d",paramSet.SLCE_ON);

	objectKey = env->NewStringUTF("SLCE_G");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.SLCE_G = objectToInt(env,objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "SLCE_G %d",paramSet.SLCE_G);

	objectKey = env->NewStringUTF("SLCE_CG");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.SLCE_CG = objectToInt(env,objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "SLCE_CG %d",paramSet.SLCE_CG);

	objectKey = env->NewStringUTF("SLCE_SO");
	objectValue = env->CallObjectMethod(algorithmObject, method, i,objectKey);
	paramSet.SLCE_SO = objectToBool(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "SLCE_SO %d",paramSet.SLCE_SO);

	objectKey = env->NewStringUTF("SLCE_SR");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.SLCE_SR = objectToInt(env,objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "SLCE_SR %d",paramSet.SLCE_SR);

	objectKey = env->NewStringUTF("SLCE_SMD");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.SLCE_SMD = objectToInt(env,objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "SLCE_SMD %d",paramSet.SLCE_SMD);

	objectKey = env->NewStringUTF("SLCE_IG");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.SLCE_IG = objectToInt(env,objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "SLCE_IG %d",paramSet.SLCE_IG);

	objectKey = env->NewStringUTF("SLCE_RO");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.SLCE_RO = objectToInt(env,objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "SLCE_RO %d",paramSet.SLCE_RO);

	objectKey = env->NewStringUTF("SLCE_RG");
	objectValue = env->CallObjectMethod(algorithmObject, method, i,objectKey);
	paramSet.SLCE_RG = objectToBool(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "SLCE_RG %d",paramSet.SLCE_RG);

	objectKey = env->NewStringUTF("SLCE_REF_GAIN");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.SLCE_REF_GAIN = objectToInt(env,objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "SLCE_REF_GAIN %d",paramSet.SLCE_REF_GAIN);

	objectKey = env->NewStringUTF("SLCE_H");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.SLCE_H = objectToInt(env,objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "SLCE_H %d",paramSet.SLCE_H);

	objectKey = env->NewStringUTF("SLCE_V");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.SLCE_V = objectToInt(env,objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "SLCE_V %d",paramSet.SLCE_V);

	objectKey = env->NewStringUTF("SLCE_BTH");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.SLCE_BTH = objectToInt(env,objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "SLCE_BTH %d",paramSet.SLCE_BTH);

	objectKey = env->NewStringUTF("SLCE_BSR");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.SLCE_BSR = objectToInt(env,objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "SLCE_BSR %d",paramSet.SLCE_BSR);

	objectKey = env->NewStringUTF("SLCE_DTH");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.SLCE_DTH = objectToInt(env,objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "SLCE_DTH %d",paramSet.SLCE_DTH);

	objectKey = env->NewStringUTF("SLCE_MRO");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.SLCE_MRO = objectToInt(env,objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "SLCE_MRO %d",paramSet.SLCE_MRO);

	return paramSet;
}

struct NRFuncParameter getNRFuncParameter(JNIEnv *env, jobject algorithmObject, jclass algorithmClass, int i){

	struct NRFuncParameter paramSet;
	jstring objectKey;
	jobject objectValue;
	jmethodID method = env->GetMethodID(algorithmClass, "getParameter","(ILjava/lang/String;)Ljava/lang/Object;");

	objectKey = env->NewStringUTF("NR_E");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.NR_E = objectToBool(env,objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "NR_E %d",paramSet.NR_E);

	return paramSet;

}

struct DE_FAFuncParameter getDE_FAFuncParameter(JNIEnv *env, jobject algorithmObject, jclass algorithmClass, int i){

	struct DE_FAFuncParameter paramSet;

	jstring objectKey;
	jobject objectValue;
	jmethodID method = env->GetMethodID(algorithmClass, "getParameter","(ILjava/lang/String;)Ljava/lang/Object;");

	objectKey = env->NewStringUTF("DE_E");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.DE_E = objectToBool(env,objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "DE_E %d",paramSet.DE_E);

	objectKey = env->NewStringUTF("DE_G");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.DE_G = objectToInt(env,objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "DE_G %d",paramSet.DE_G);

	objectKey = env->NewStringUTF("DE_M1");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.DE_M1 = objectToInt(env,objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "DE_M1 %d",paramSet.DE_M1);

	objectKey = env->NewStringUTF("DE_M2");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.DE_M2 = objectToInt(env,objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "DE_M2 %d",paramSet.DE_M2);

	objectKey = env->NewStringUTF("FE_E");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.FE_E = objectToBool(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "FE_E %d",paramSet.FE_E);

	objectKey = env->NewStringUTF("FA_ET");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.FA_ET = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "FA_ET %d",paramSet.FA_ET);

	objectKey = env->NewStringUTF("FA_SP");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.FA_SP = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "FA_SP %d",paramSet.FA_SP);

	objectKey = env->NewStringUTF("FA_SN");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.FA_SN = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "FA_SN %d",paramSet.FA_SN);

	objectKey = env->NewStringUTF("FA_MDG");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.FA_MDG = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "FA_MDG %d",paramSet.FA_MDG);

	objectKey = env->NewStringUTF("FA_PP");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.FA_PP = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "FA_PP %d",paramSet.FA_PP);

	objectKey = env->NewStringUTF("FA_SC");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.FA_SC = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "FA_SC %d",paramSet.FA_SC);

	objectKey = env->NewStringUTF("FA_D");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.FA_D = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "FA_D %d",paramSet.FA_D);

	objectKey = env->NewStringUTF("FA_DD");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.FA_DD = objectToLong(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "FA_DD %d",paramSet.FA_DD);

	objectKey = env->NewStringUTF("FA_PMW");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.FA_PMW = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "FA_PMW %d",paramSet.FA_PMW);

	objectKey = env->NewStringUTF("FA_FMW");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.FA_FMW = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "FA_FMW %d",paramSet.FA_FMW);

	objectKey = env->NewStringUTF("FA_SZW");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.FA_SZW = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "FA_SZW %d",paramSet.FA_SZW);

	objectKey = env->NewStringUTF("FA_SZH");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.FA_SZH = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "FA_SZH %d",paramSet.FA_SZH);

	objectKey = env->NewStringUTF("FA_OC10C");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.FA_OC10 = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "FA_OC10 %d",paramSet.FA_OC10);

	objectKey = env->NewStringUTF("FA_OC20C");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.FA_OC20C = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "FA_OC20C %d",paramSet.FA_OC20C);


	return paramSet;

}


struct CSFuncParameter getCSFuncParameter(JNIEnv *env, jobject algorithmObject, jclass algorithmClass, int i){

	struct CSFuncParameter paramSet;
	jstring objectKey;
	jobject objectValue;
	jmethodID method = env->GetMethodID(algorithmClass, "getParameter","(ILjava/lang/String;)Ljava/lang/Object;");

	objectKey = env->NewStringUTF("CE_E");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.CE_E = objectToBool(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "CE_E %d",paramSet.CE_E);

	objectKey = env->NewStringUTF("CS_E");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.CS_E = objectToBool(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "CS_E %d",paramSet.CS_E);

	return paramSet;

}

struct CCFuncParameter getCCFuncParameter(JNIEnv *env, jobject algorithmObject, jclass algorithmClass, int i){

	struct CCFuncParameter paramSet;
	jstring objectKey;
	jobject objectValue;
	jmethodID method = env->GetMethodID(algorithmClass, "getParameter","(ILjava/lang/String;)Ljava/lang/Object;");

	objectKey = env->NewStringUTF("TestValue");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.TestValue = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "TestValue %d",paramSet.TestValue);

	return paramSet;

}

struct ASCRFuncParameter getASCRFuncParameter(JNIEnv *env, jobject algorithmObject, jclass algorithmClass, int i){

	struct ASCRFuncParameter paramSet;
	jstring objectKey;
	jobject objectValue;
	jmethodID method = env->GetMethodID(algorithmClass, "getParameter","(ILjava/lang/String;)Ljava/lang/Object;");

	objectKey = env->NewStringUTF("ASCR_MASK");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_MASK = objectToBool(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_MASK %d",paramSet.ASCR_MASK);

	objectKey = env->NewStringUTF("ASCR_BT");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_BT = objectToBool(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_BT %d",paramSet.ASCR_BT);

	objectKey = env->NewStringUTF("ASCR_MO");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_MO = objectToBool(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_MO %d",paramSet.ASCR_MO);

	objectKey = env->NewStringUTF("ASCR_AO");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_AO = objectToBool(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_AO %d",paramSet.ASCR_AO);

	objectKey = env->NewStringUTF("ASCR_LO");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_LO = objectToBool(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_LO %d",paramSet.ASCR_LO);

	objectKey = env->NewStringUTF("ASCR_S");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_S = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_S %d",paramSet.ASCR_S);

	objectKey = env->NewStringUTF("ASCR_SCB");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_SCB = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_SCB %d",paramSet.ASCR_SCB);

	objectKey = env->NewStringUTF("ASCR_SCR");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_SCR = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_SCR %d",paramSet.ASCR_SCR);

	objectKey = env->NewStringUTF("ASCR_DU");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_DU = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_DU %d",paramSet.ASCR_DU);

	objectKey = env->NewStringUTF("ASCR_DD");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_DD = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_DD %d",paramSet.ASCR_DD);

	objectKey = env->NewStringUTF("ASCR_DR");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_DR = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_DR %d",paramSet.ASCR_DR);

	objectKey = env->NewStringUTF("ASCR_DL");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_DL = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_DL %d",paramSet.ASCR_DL);

	objectKey = env->NewStringUTF("ASCR_DDU");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_DDU = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_DDU %d",paramSet.ASCR_DDU);

	objectKey = env->NewStringUTF("ASCR_DDD");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_DDD = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_DDD %d",paramSet.ASCR_DDD);

	objectKey = env->NewStringUTF("ASCR_DDR");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_DDR = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_DDR %d",paramSet.ASCR_DDR);

	objectKey = env->NewStringUTF("ASCR_DDL");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_DDL = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_DDL %d",paramSet.ASCR_DDL);

	objectKey = env->NewStringUTF("ASCR_SRR");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_SRR = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_SRR %d",paramSet.ASCR_SRR);

	objectKey = env->NewStringUTF("ASCR_SRG");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_SRG = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_SRG %d",paramSet.ASCR_SRG);

	objectKey = env->NewStringUTF("ASCR_SRB");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_SRB = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_SRB %d",paramSet.ASCR_SRB);

	objectKey = env->NewStringUTF("ASCR_SYR");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_SYR = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_SYR %d",paramSet.ASCR_SYR);

	objectKey = env->NewStringUTF("ASCR_SYG");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_SYG = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_SYG %d",paramSet.ASCR_SYG);

	objectKey = env->NewStringUTF("ASCR_SYB");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_SYB = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_SYB %d",paramSet.ASCR_SYB);

	objectKey = env->NewStringUTF("ASCR_SMR");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_SMR = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_SMR %d",paramSet.ASCR_SMR);

	objectKey = env->NewStringUTF("ASCR_SMG");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_SMG = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_SMG %d",paramSet.ASCR_SMG);

	objectKey = env->NewStringUTF("ASCR_SMB");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_SMB = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_SMB %d",paramSet.ASCR_SMB);

	objectKey = env->NewStringUTF("ASCR_SWR");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_SWR = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_SWR %d",paramSet.ASCR_SWR);

	objectKey = env->NewStringUTF("ASCR_SWG");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_SWG = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_SWG %d",paramSet.ASCR_SWG);

	objectKey = env->NewStringUTF("ASCR_SWB");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_SWB = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_SWB %d",paramSet.ASCR_SWB);

	objectKey = env->NewStringUTF("ASCR_WCR");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WCR = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WCR %d",paramSet.ASCR_WCR);

	objectKey = env->NewStringUTF("ASCR_WRR");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WRR = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WRR %d",paramSet.ASCR_WRR);

	objectKey = env->NewStringUTF("ASCR_WCG");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WCG = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WCG %d",paramSet.ASCR_WCG);

	objectKey = env->NewStringUTF("ASCR_WRG");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WRG = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WRG %d",paramSet.ASCR_WRG);

	objectKey = env->NewStringUTF("ASCR_WCB");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WCB = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WCB %d",paramSet.ASCR_WCB);

	objectKey = env->NewStringUTF("ASCR_WRB");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WRB = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WRB %d",paramSet.ASCR_WRB);

	objectKey = env->NewStringUTF("ASCR_WMR");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WMR = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WMR %d",paramSet.ASCR_WMR);

	objectKey = env->NewStringUTF("ASCR_WGR");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WGR = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WGR %d",paramSet.ASCR_WGR);

	objectKey = env->NewStringUTF("ASCR_WMG");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WMG = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WMG %d",paramSet.ASCR_WMG);

	objectKey = env->NewStringUTF("ASCR_WGG");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WGG = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WGG %d",paramSet.ASCR_WGG);

	objectKey = env->NewStringUTF("ASCR_WMB");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WMB = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WMB %d",paramSet.ASCR_WMB);

	objectKey = env->NewStringUTF("ASCR_WGB");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WGB = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WGB %d",paramSet.ASCR_WGB);

	objectKey = env->NewStringUTF("ASCR_WYR");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WYR = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WYR %d",paramSet.ASCR_WYR);

	objectKey = env->NewStringUTF("ASCR_WBR");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WBR = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WBR %d",paramSet.ASCR_WBR);

	objectKey = env->NewStringUTF("ASCR_WYG");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WYG = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WYG %d",paramSet.ASCR_WYG);

	objectKey = env->NewStringUTF("ASCR_WBG");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WBG = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WBG %d",paramSet.ASCR_WBG);

	objectKey = env->NewStringUTF("ASCR_WYB");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WYB = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WYB %d",paramSet.ASCR_WYB);

	objectKey = env->NewStringUTF("ASCR_WBB");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WBB = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WBB %d",paramSet.ASCR_WBB);

	objectKey = env->NewStringUTF("ASCR_WWR");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WWR = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WWR %d",paramSet.ASCR_WWR);

	objectKey = env->NewStringUTF("ASCR_WKR");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WKR = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WKR %d",paramSet.ASCR_WKR);

	objectKey = env->NewStringUTF("ASCR_WWG");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WWG = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WWG %d",paramSet.ASCR_WWG);

	objectKey = env->NewStringUTF("ASCR_WKG");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WKG = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WKG %d",paramSet.ASCR_WKG);

	objectKey = env->NewStringUTF("ASCR_WWB");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WWB = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WWB %d",paramSet.ASCR_WWB);

	objectKey = env->NewStringUTF("ASCR_WKB");
	objectValue = env->CallObjectMethod(algorithmObject, method, i, objectKey);
	paramSet.ASCR_WKB = objectToInt(env, objectValue);
	__android_log_print(ANDROID_LOG_ERROR, "JNI", "ASCR_WKB %d",paramSet.ASCR_WKB);

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

		if (!strcmp("SLCE", name)) {
			__android_log_print(ANDROID_LOG_ERROR, "JNI", "---SLCE Algorithm");
			struct SLCEFuncParameter paramSet = getSLCEFuncParameter(env,algorithmObject, algorithmClass, i);
			OutputImage = SLCEFunc(InputImage, c_width, c_height,paramSet);
			InputImage = memoryCopy2DTo2D(OutputImage, c_width, c_height);

		} else if (!strcmp("NR", name)) {
			__android_log_print(ANDROID_LOG_ERROR, "JNI", "---NR Algorithm");
			struct NRFuncParameter paramSet = getNRFuncParameter(env,algorithmObject, algorithmClass, i);
			OutputImage = NRFunc(InputImage, c_width, c_height,paramSet);
			InputImage = memoryCopy2DTo2D(OutputImage, c_width, c_height);

		}else if (!strcmp("DE_FA", name)) {
			__android_log_print(ANDROID_LOG_ERROR, "JNI", "---DE_FA Algorithm");
			struct DE_FAFuncParameter paramSet = getDE_FAFuncParameter(env,algorithmObject, algorithmClass, i);
			OutputImage = DE_FAFunc(InputImage, c_width, c_height,paramSet);
			InputImage = memoryCopy2DTo2D(OutputImage, c_width, c_height);

		}else if (!strcmp("CS", name)) {
			__android_log_print(ANDROID_LOG_ERROR, "JNI", "---CS Algorithm");
			struct CSFuncParameter paramSet = getCSFuncParameter(env,algorithmObject, algorithmClass, i);
			OutputImage = CSFunc(InputImage, c_width, c_height,paramSet);
			InputImage = memoryCopy2DTo2D(OutputImage, c_width, c_height);

		}else if (!strcmp("CC", name)) {
			__android_log_print(ANDROID_LOG_ERROR, "JNI", "---CC Algorithm");
			struct CCFuncParameter paramSet = getCCFuncParameter(env,algorithmObject, algorithmClass, i);
			OutputImage = CCFunc(InputImage, c_width, c_height,paramSet);
			InputImage = memoryCopy2DTo2D(OutputImage, c_width, c_height);

		}else if (!strcmp("ASCR", name)) {
			__android_log_print(ANDROID_LOG_ERROR, "JNI", "---ASCR Algorithm");
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
