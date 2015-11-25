#include <stdio.h>
#include <string.h>



int main(){
	char *cmd = "ancmd";
    char buffer[1024*5];                         //定义缓冲区                        
    FILE* pipe = _popen(cmd, "r");            //打开管道，并执行命令 
    if (!pipe)
          return 0;                      //返回0表示运行失败 
        
    while(!feof(pipe)) {
    if(fgets(buffer, 1024, pipe)){             //将管道输出到result中 
            puts(buffer);
        }
    }
    _pclose(pipe);
    // system("pause");                          //暂停以查看结果 
} 