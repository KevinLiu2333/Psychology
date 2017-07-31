<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>松江区政务数据中心-索引使用帮助 - 门户检索系统</title>
<link href="/luceneDemo/skins/css/common.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div align="center">
<div class="div_dark" >
		<table width="90%" >
			<tr>
				<td>
					<span class="tittle_1">索引使用说明</span>
				</td>
			</tr>
			<tr>
				<td>
					<div align="left" >
						<ul >
							<li ><span style="font-size: 18px;">文件来源:</span></li>
							<li class="nonePr">文件来源分为：门户本地网页文件，门户本地普通文件，远程服务器文件。
							其中门户文件必须位于GTMH项目fabu文件夹下，远程文件必须不位于fabu文件夹下。</li>
							<li ><span style="font-size: 18px;">远程文件索引方法:</span></li>
							<li class="nonePr">远程文件暂时需拷贝到本地和本地文件一起建立索引，拷贝时必须遵循一定命名方式：
							文件ID + ### + 文件名。（分隔符###可在application.properties中更改）</li>
							<li ><span style="font-size: 18px;">索引方式分类:</span></li>
							<li class="nonePr">索引方式分为完全索引和增量索引：完全索引对指定目录下全部资源文件进行索引，
							并覆盖之前索引文件。增量索引，仅对新增或已修改资源文件进行索引，仅更新而不是覆盖之前索引文件。注意：
							第一次建立索引必须使用完全索引。</li>
						</ul>
					</div>
				</td>
			</tr>
		</table>
</div>
</div>
</body>
</html>