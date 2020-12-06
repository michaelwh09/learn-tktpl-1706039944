#include <jni.h>
#include <string>
#include<math.h>

extern "C" JNIEXPORT jint JNICALL
Java_id_ac_ui_cs_mobileprogramming_michaelwh_lab4_ui_main_MainFragment_fibonacci(
        JNIEnv* env,
        jobject thiz,
        jlong input) {
    double phi = (1 + sqrt(5)) / 2;
    return round(pow(phi, input) / sqrt(5));
}
