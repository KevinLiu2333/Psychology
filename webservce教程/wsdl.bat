rem ͨ��axis����webservice���ش���
rem ͨ��axis�İ�·��
set Axis_Lib=E:\webService\axis-src-1_4\axis-1_4\lib
	
        set Java_Cmd=java -Djava.ext.dirs=%Axis_Lib%
      	rem ���ɿͻ��˴���·��
        set Output_Path=E:\webService
	rem ���ɿͻ��˰�·��
        set Package=com
  	rem Զ��webservice��ַ
        %Java_Cmd% org.apache.axis.wsdl.WSDL2Java -o%Output_Path% -p%Package% http://localhost:8080/services/XZSP_CI_JCJ_JCF_BusinessCollectService?wsdl  
        pause  