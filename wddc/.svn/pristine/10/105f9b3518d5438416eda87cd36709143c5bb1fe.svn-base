<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:x="urn:schemas-microsoft-com:office:excel">  
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
   <!-- 显示网格线 -->    
<xml>    
            <x:ExcelWorkbook>    
                <x:ExcelWorksheets>    
                    <x:ExcelWorksheet>    
                        <x:Name>Sheet1</x:Name>    
                        <x:WorksheetOptions>    
                            <x:Print>    
                                <x:ValidPrinterInfo />    
                            </x:Print>    
                        </x:WorksheetOptions>    
                    </x:ExcelWorksheet>    
                </x:ExcelWorksheets>    
            </x:ExcelWorkbook>    
        </xml>    
<!-- 显示网格线 --> 

</head>
<body>
 <%    
   response.setContentType("application/vnd.ms-excel");  
   response.setHeader("Content-Disposition", "inline; filename="  + "log.xls");  
    
    %>  
<table width="96%" align="center">
	<tr>
		<th>序号</th>
		<th>日志分类</th>
		<th>日志类型</th>
		<th>记录时间</th>
		<th>操作人</th>
		<th>操作单位</th>
	</tr>
	<c:forEach items="${obj.rows}" var="log" varStatus="row" >
		<tr>
			<td align="center">${(obj.pager.pageNumber-1)*obj.pager.pageSize + row.index+1 }</td>
       		<td align="center">${log.catalog }</td>
       		<td align="center">${log.logType }</td>
       		<td align="center"><fmt:formatDate pattern="yyyy-MM-dd" value="${log.logTime}"/></td>
       		<td align="center">${log.userName }</td>
       		<td align="center">${log.unitName }</td>
				
       	</tr>
	</c:forEach>
			
</table>
	
	
</body>
</html>
