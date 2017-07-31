--菜单权限
use cmcsqpt;
go
delete from uaauthinfo ;
SET IDENTITY_INSERT uaauthinfo ON;
insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('3000', '3000', '社区云平台', null, 'MENU', null, null, '10', '1', null);

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('410000', '410000', '接口管理', null, 'MENU', null, '3000', '10', '1', '6');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('410100', '410100', '分发接口', null, 'MENU', null, '410000', '10', '1', '0');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('410101', '410101', '分发接口管理', null, 'MENU', '/pages/m41/m410101/m410101.jsp', '410100', '10', '1', 'address');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('410200', '410200', '本地应用', null, 'MENU', null, '410000', '20', '1', '0');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('410201', '410201', 'WEB应用管理', null, 'MENU', '/pages/m41/m410201/m410201.jsp', '410200', '10', '1', 'project');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('410300', '410300', '端口管理', null, 'MENU', null, '410000', '30', '1', '0');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('410301', '410301', '开放端口管理', null, 'MENU', '/pages/m41/m410301/m410301.jsp', '410300', '10', '1', 'reportshop');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('410400', '410400', '下级平台', null, 'MENU', null, '410000', '40', '1', '0');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('410401', '410401', '下级平台接口管理', null, 'MENU', '/pages/m41/m410401/m410401.jsp', '410400', '10', '1', 'roll_manage');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('420000', '420000', '调度监控管理', null, 'MENU', null, '3000', '20', '1', '7');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('420100', '420100', '监控管理', null, 'MENU', null, '420000', '10', '1', '0');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('420101', '420101', '监控对象配置', null, 'MENU', '/pages/m42/m420101/m420101.jsp', '420100', '10', '1', 'exam_manage');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('420102', '420102', '参数配置', null, 'MENU', '/pages/m42/m420102/m420102.jsp', '420100', '20', '1', '420102');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('420200', '420200', '调度管理', null, 'MENU', null, '420000', '20', '1', '0');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('420201', '420201', '任务定义', null, 'MENU', '/pages/m42/m420201/m420201.jsp', '420200', '10', '1', 'itask');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('420202', '420202', '调度配置', null, 'MENU', '/pages/m42/m420202/m420202.jsp', '420200', '20', '1', 'info');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('420300', '420300', '其他管理', null, 'MENU', null, '420000', '30', '1', '0');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('420301', '420301', '常用工具', null, 'MENU', '/pages/m42/m420301/m420301.jsp', '420300', '10', '1', 'vehicle');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('430000', '430000', '查询统计', null, 'MENU', null, '3000', '30', '1', '8');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('430100', '430100', '统计管理', null, 'MENU', null, '430000', '10', '1', '0');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('430101', '430101', '统计页面', null, 'MENU', '/pages/m43/m430101/m430101.jsp', '430100', '10', '1', '430101');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('430200', '430200', '查询管理', null, 'MENU', null, '430000', '20', '1', '0');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('430201', '430201', '查询页面', null, 'MENU', '/pages/m43/m430201/m430201.jsp', '430200', '10', '1', 'score');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('380000', '380000', '用户管理', null, 'MENU', null, '3000', '40', '1', '3');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('380100', '380100', '用户管理', null, 'MENU', null, '380000', '10', '1', '0');

insert into uaauthinfo (NODEID, NODECODE, NODENAME, NODEDESC, NODETYPE, URL, PARENTNODEID, NODEORDER, STATUS, ICON1)
values ('380101', '380101', '用户管理', null, 'MENU', '/pages/m38/m380101/m380101.jsp', '380100', '10', '1', 'system');
go
set IDENTITY_INSERT uaauthinfo  off;
 
--角色权限
delete from UAROLEAUTH ;
insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '3000');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '410000');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '410100');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '410101');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '410200');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '410201');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '410300');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '410301');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '410400');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '410401');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '420000');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '420100');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '420101');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '420102');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '420200');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '420201');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '420202');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '420300');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '420301');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '430000');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '430100');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '430101');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '430200');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '430201');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '380000');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '380100');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('1', '380101');

insert into UAROLEAUTH (ROLEID, NODEID)
values ('2', '430201');

--角色
delete from UAROLEINFO ;
SET IDENTITY_INSERT UAROLEINFO ON;
insert into UAROLEINFO (ROLEID, ROLENAME, ROLEENNAME, STATUS, ROLETYPE)
values ('1', '管理角色', 'administrator', '1', null);

insert into UAROLEINFO (ROLEID, ROLENAME, ROLEENNAME, STATUS, ROLETYPE)
values ('2', '查询角色', 'query', '1', null);
go
set IDENTITY_INSERT UAROLEINFO  off;

--用户
delete from UAUSERINFO ;
SET IDENTITY_INSERT UAUSERINFO ON;
insert into UAUSERINFO (USERID, LOGINNAME, PASSWORD, DISPLAYNAME, STATUS, AUTHENTICTYPE, CERTIFICATE, EXT1, EXT2, EXT3, EXT4, EXT5)
values ('1', 'cmdev', '3d4f2bf07dc1be38b20cd6e46949a1071f9d0e3d', '社区管理', '1', 'loginname', null, null,null, null, null, null);
go
set IDENTITY_INSERT UAUSERINFO  off;
--用户角色
delete from UAUSERROLE ;
insert into UAUSERROLE (USERID, ROLEID)
values ('1', '1');

--任务定义
delete from QRTZ_TRIGGER_CONFIG ;
SET IDENTITY_INSERT QRTZ_TRIGGER_CONFIG ON;
insert into QRTZ_TRIGGER_CONFIG (ID, BEAN_NAME, BEAN_DESC, NOTES, VALIDITY, CREATE_TIME, CREATER, EXT1, EXT2)
values ('1', 'testDispatcherImpl', '条线接口监控', null, '1', cast('2016-02-24 00:00:00' as datetime), '1', null, null);

insert into QRTZ_TRIGGER_CONFIG (ID, BEAN_NAME, BEAN_DESC, NOTES, VALIDITY, CREATE_TIME, CREATER, EXT1, EXT2)
values ('2', 'testWebApplicationImpl', '应用监控', null, '1', cast('2016-02-24 00:00:00' as datetime), '1', null, null);

insert into QRTZ_TRIGGER_CONFIG (ID, BEAN_NAME, BEAN_DESC, NOTES, VALIDITY, CREATE_TIME, CREATER, EXT1, EXT2)
values ('3', 'testOpenPortImpl', '开放端口监控', null, '1', cast('2016-02-24 00:00:00' as datetime), '1', null, null);

insert into QRTZ_TRIGGER_CONFIG (ID, BEAN_NAME, BEAN_DESC, NOTES, VALIDITY, CREATE_TIME, CREATER, EXT1, EXT2)
values ('4', 'testLowerPlatFomImpl', '下级平台监控', null, '1', cast('2016-02-24 00:00:00' as datetime), '1', null, null);

go
set IDENTITY_INSERT QRTZ_TRIGGER_CONFIG  off;
