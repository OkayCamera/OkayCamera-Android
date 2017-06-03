#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_okaycamera_okcamera_Main_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello Okay Camera!";
    return env->NewStringUTF(hello.c_str());
}
