rem 通过axis生成webservice本地代码
rem 通过axis的包路径
set Axis_Lib=E:\webService\axis-src-1_4\axis-1_4\lib
	
        set Java_Cmd=java -Djava.ext.dirs=%Axis_Lib%
      	rem 生成客户端代码路径
        set Output_Path=E:\webService
	rem 生成客户端包路径
        set Package=com
  	rem 远程webservice地址
        %Java_Cmd% org.apache.axis.wsdl.WSDL2Java -o%Output_Path% -p%Package% http://localhost:8080/services/XZSP_CI_JCJ_JCF_BusinessCollectService?wsdl  
        pause  