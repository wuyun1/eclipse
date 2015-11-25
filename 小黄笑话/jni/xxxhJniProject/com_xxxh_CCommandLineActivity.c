#include "com_xxxh_CCommandLineActivity.h"
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
// jobject createCmdResult(JNIEnv *env, int levelError ,char* cmdResult);
// jstring createjstring(JNIEnv* pEnv);
// jstring jstringconcat(JNIEnv* pEnv,	jstring s1,	jstring s2);


jobject createCmdResult(JNIEnv *env, int levelError ,jstring cmdResult){
	jclass CmdResult = (*env)->FindClass(env,"com/xxxh/CmdResult");
	jmethodID m_mid   = (*env)->GetMethodID(env,CmdResult,"<init>","()V");
	jfieldID  m_fid_levelError = (*env)->GetFieldID(env,CmdResult,"levelError","I");  
      jfieldID  m_fid_consoleStr = (*env)->GetFieldID(env,CmdResult,"consoleStr","Ljava/lang/String;");
      jobject   m_obj   =	(*env)->NewObject(env,CmdResult,m_mid);   
                        			(*env)->SetObjectField(env,m_obj,m_fid_consoleStr,cmdResult);  
                       			(*env)->SetIntField(env,m_obj,m_fid_levelError,levelError);  
    return m_obj;  
}

jstring createjstring(JNIEnv* pEnv) {
	 jclass clsString = (*pEnv)->FindClass(pEnv,	"java/lang/String");
	 jmethodID construct = (*pEnv)->GetMethodID(pEnv, clsString, "<init>", "()V");
	 jstring strDst = (jstring) (*pEnv)->NewObject(pEnv,clsString, construct);
	 return strDst;
}

jstring jstringconcat(JNIEnv* pEnv,	jstring s1,	jstring s2) {
	 jclass clsString = (*pEnv)->FindClass(pEnv,"java/lang/String");
	 jmethodID md_concat = (*pEnv)->GetMethodID(pEnv,clsString, "concat", "(Ljava/lang/String;)Ljava/lang/String;");
	 jstring strDst = (jstring) (*pEnv)->CallNonvirtualObjectMethod(pEnv, s1, clsString, md_concat,	s2);
	 return strDst;
}



/*
 * Class:     com_xxxh_CCommandLineActivity
 * Method:    runCommand
 * Signature: (Ljava/lang/String;)[Ljava/lang/String;
 */

JNIEXPORT jobject JNICALL Java_com_xxxh_CCommandLineActivity_runCommand  (JNIEnv *env, jobject x, jstring jsCmdStr){
	int errorcode;
	const char* cmdStr = (char*)(*env)->GetStringUTFChars(env,jsCmdStr,JNI_FALSE);
	char szline[256];
	jstring  result = createjstring(env);

	FILE *fp;
	
	if ((fp = _popen (cmdStr, "r")) == NULL)
	{
		sprintf (szline,"the command %s not exist\n", cmdStr);
		
		return createCmdResult(env,-1,(*env)->NewStringUTF(env,szline));
	}
	while (fgets (szline, sizeof (szline) - 1, fp) != NULL)
	{
		result = jstringconcat(env,	result,	(*env)->NewStringUTF(env,szline));
	};
	errorcode = _pclose(fp);
	return createCmdResult(env,errorcode,result);  
}