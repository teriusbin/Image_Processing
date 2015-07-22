LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := jnigl
LOCAL_SRC_FILES := com_samsung_ip_JniIPActivity.cpp \ image_processing.cpp \
APP_STL := stlport_static
LOCAL_LDLIBS := -lGLESv1_CM -ldl -llog
                 
include $(BUILD_SHARED_LIBRARY)
