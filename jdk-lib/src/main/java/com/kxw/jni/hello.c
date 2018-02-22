#include<stdio.h>
#include"com_kxw_jni_Demo01.h"
JNIEXPORT void JNICALL Java_com_kxw_jni_Demo01_hello(JNIEnv * a, jobject b){
	printf("hello world");
}
