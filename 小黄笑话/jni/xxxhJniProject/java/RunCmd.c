#include "RunCmd.h"
#include "string.h"
#include <windows.h> 

char* jstringToWindows( JNIEnv *env, jstring jstr )  
{  
  int length = (*env)->GetStringLength(env,jstr);  
  const jchar* jcstr = (*env)->GetStringChars(env,jstr, 0);  
  char* rtn = (char*)malloc(length*2+1);  
  int size = 0;  
  size = WideCharToMultiByte(CP_ACP, 0, (LPCWSTR)jcstr, length, rtn,(length*2+1), NULL, NULL);  
  if( size <= 0 )  
    return NULL;  
  (*env)->ReleaseStringChars(env,jstr, jcstr);  
  rtn[size] = 0;  
  return rtn;  
}  
jstring WindowsTojstring( JNIEnv *env, const char* str )  
{  
  jstring rtn = 0;  
  int slen = strlen(str);  
  unsigned short* buffer = 0;  
  if( slen == 0 )  
    rtn = (*env)->NewStringUTF(env,str);   
  else  
  {  
    int length = MultiByteToWideChar(CP_ACP, 0, (LPCSTR)str, slen, NULL, 0);  
    buffer = (unsigned short*)malloc(length*2 + 1);  
    if( MultiByteToWideChar(CP_ACP, 0, (LPCSTR)str, slen, (LPWSTR)buffer, length) >0)  
      rtn = (*env)->NewString(env,(jchar*)buffer, length);  
  }  
  if(buffer)  
  free(buffer);  
  return rtn;  
}  

jobject createCmdResult(JNIEnv *env, int levelError ,jstring cmdResult){
	jclass CmdResult = (*env)->FindClass(env,"CmdResult");
	jmethodID m_mid   = (*env)->GetMethodID(env,CmdResult,"<init>","()V");
	jfieldID  m_fid_levelError = (*env)->GetFieldID(env,CmdResult,"levelError","I");  
      jfieldID  m_fid_consoleStr = (*env)->GetFieldID(env,CmdResult,"consoleStr","Ljava/lang/String;");
      jobject   m_obj   =	(*env)->NewObject(env,CmdResult,m_mid);   
                        			(*env)->SetObjectField(env,m_obj,m_fid_consoleStr,cmdResult);  
                       			(*env)->SetIntField(env,m_obj,m_fid_levelError,levelError);  
    return m_obj;  
}

jstring createjstring(JNIEnv* pEnv) {
	 
	 return (jstring)(*pEnv)->NewStringUTF(pEnv,"");
}

jstring jstringconcat(JNIEnv* pEnv,	jstring s1,	jstring s2) {
	 jclass clsString = (*pEnv)->FindClass(pEnv,"java/lang/String");
	 jmethodID md_concat = (*pEnv)->GetMethodID(pEnv,clsString, "concat", "(Ljava/lang/String;)Ljava/lang/String;");
	 return (jstring) (*pEnv)->CallNonvirtualObjectMethod(pEnv, s1, clsString, md_concat,	s2);
}


/*
 * Class:     Java_RunCmd_runCommand
 * Method:    runCommand
 * Signature: (Ljava/lang/String;)[Ljava/lang/String;
 */

JNIEXPORT jobject JNICALL Java_RunCmd_runCommand  (JNIEnv *env, jobject x, jstring jsCmdStr){
	int errorcode;
	const char* cmdStr = jstringToWindows(env,jsCmdStr);
	char szline[256];
	jstring  result = createjstring(env);

	FILE *fp;
	
	if ((fp = _popen (cmdStr, "r")) == NULL)
	{
		sprintf (szline,"the command %s not exist\n", cmdStr);
		if(cmdStr) free((void*)cmdStr);		
		return createCmdResult(env,-1,(*env)->NewStringUTF(env,szline));
	}
	if(cmdStr) free((void*)cmdStr);
	while (fgets (szline, sizeof (szline) - 1, fp) != NULL)
	{
		result = jstringconcat(env,	result,	WindowsTojstring(env,szline));
	};
	errorcode = _pclose(fp);
	return createCmdResult(env,errorcode,result);  
}