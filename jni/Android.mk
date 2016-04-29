LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := libsync_clock_jni
LOCAL_SRC_FILES := sync_clock_jni.c sync_clock.c player.c

LOCAL_CFLAGS := -g -DUSE_LIBLOG

LOCAL_C_INCLUDES += $(JNI_H_INCLUDE) \
                    prebuilts/ndk/9/platforms/android-21/arch-x86/usr/include


# needed for logcat
LOCAL_SHARED_LIBRARIES := libandroid_runtime \
                          libnativehelper \
                          libui \
                          libcutils \
                          libutils \
                          libbinder \
                          libsonivox \
                          libicuuc \
                          libexpat \
                          libdl

LOCAL_LDLIBS := -lOpenSLES -llog

include $(BUILD_SHARED_LIBRARY)
