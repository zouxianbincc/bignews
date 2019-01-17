#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_zouxianbin_c_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";


    return env->NewStringUTF(hello.c_str());
}

extern "C" {
//引入bspatch.c里的main方法
extern int bspatch_main(int argc, char *argv[]);
}


extern "C"
JNIEXPORT jboolean JNICALL
Java_com_example_zouxianbin_news_BigNews_bspatch(JNIEnv *env, jclass type, jstring oldFilePath_,
                                                 jstring patchFilePath_, jstring newFilePath_) {
    const char *oldFilePath = env->GetStringUTFChars(oldFilePath_, 0);
    const char *patchFilePath = env->GetStringUTFChars(patchFilePath_, 0);
    const char *newFilePath = env->GetStringUTFChars(newFilePath_, 0);

    int argc = 4;
    char *argv[4];
    argv[0] = "bspatch";
    argv[1] = const_cast<char *>(oldFilePath);
    argv[2] = const_cast<char *>(newFilePath);
    argv[3] = const_cast<char *>(patchFilePath);


    int rel = bspatch_main(argc, argv);

    printf("BSUtil", "%s", "合并APK完成");

    env->ReleaseStringUTFChars(oldFilePath_, oldFilePath);
    env->ReleaseStringUTFChars(newFilePath_, newFilePath);
    env->ReleaseStringUTFChars(patchFilePath_, patchFilePath);

    printf("BSUtil", "%s", rel);

    return !rel;
}

