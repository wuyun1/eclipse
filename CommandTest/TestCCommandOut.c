#include <stdio.h>
#include <string.h>



int main(){
	char *cmd = "ancmd";
    char buffer[1024*5];                         //���建����                        
    FILE* pipe = _popen(cmd, "r");            //�򿪹ܵ�����ִ������ 
    if (!pipe)
          return 0;                      //����0��ʾ����ʧ�� 
        
    while(!feof(pipe)) {
    if(fgets(buffer, 1024, pipe)){             //���ܵ������result�� 
            puts(buffer);
        }
    }
    _pclose(pipe);
    // system("pause");                          //��ͣ�Բ鿴��� 
} 