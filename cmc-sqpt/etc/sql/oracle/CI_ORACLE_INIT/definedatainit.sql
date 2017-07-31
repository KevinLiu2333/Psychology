alter sequence SEQ_IFDEFINEID increment by 1000;
select SEQ_IFDEFINEID.nextval from dual;
alter sequence SEQ_IFDEFINEID increment by 1;
alter sequence SEQ_IFDEFINEHISID increment by 1000;
select SEQ_IFDEFINEHISID.nextval from dual;
alter sequence SEQ_IFDEFINEHISID increment by 1;
alter sequence SEQ_IFMONDEFID increment by 1000;
select SEQ_IFMONDEFID.nextval from dual;
alter sequence SEQ_IFMONDEFID increment by 1;
alter sequence SEQ_IFMONDEFHISID increment by 1000;
select SEQ_IFMONDEFHISID.nextval from dual;
alter sequence SEQ_IFMONDEFHISID increment by 1;
delete from ifdefine;
insert into ifdefine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1003', 'CICSJPT', '市级平台', '01', '01', 'http://10.125.8.223:8080/cmc-sjptcic/services/cmCallService?wsdl', '{"namespace":"http://10.125.8.223:8080/cmc-sjptcic/services/cmCallService","service":"service","params":"flag,service","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:29:05', 'dd-mm-yyyy hh24:mi:ss'));

insert into ifdefine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1006', 'CICDAJDEV', '档案', '01', '01', 'http://10.103.101.233:8080/msda/services/mzj?wsdl', '{"namespace":"mzj","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:32:48', 'dd-mm-yyyy hh24:mi:ss'));

insert into ifdefine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1009', 'CICFDJSFDEV', '房地经适房', '01', '01', 'http://10.125.8.221:8080/cmc-fdjsfcic/services/cmCallService?wsdl', '{"namespace":"http://10.125.8.221:8080/cmc-fdjsfcic/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:36:48', 'dd-mm-yyyy hh24:mi:ss'));

insert into ifdefine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1012', 'CMCSBDEV', '社保', '01', '01', 'http://10.103.60.30:8080/cmc-cics-sb/services/dispatchService?wsdl', '{"namespace":"http://10.103.60.30:8080/cmc-cics-sb/services/dispatchService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:39:29', 'dd-mm-yyyy hh24:mi:ss'));

insert into ifdefine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1014', 'CMCLDDEV', '劳动', '01', '01', 'http://10.103.60.30:8080/cmc-cics-ld/services/dispatchService?wsdl', '{"namespace":"http://10.103.60.30:8080/cmc-cics-ld/services/dispatchService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:41:57', 'dd-mm-yyyy hh24:mi:ss'));

insert into ifdefine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1000', 'CICHYDEV', '民政婚姻', '01', '01', 'http://10.125.8.221:8080/cmc-hycic/services/cmCallService?wsdl', '{"namespace":"http://10.125.8.221:8080/cmc-hycic/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:25:35', 'dd-mm-yyyy hh24:mi:ss'));

insert into ifdefine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1002', 'CICMZDEV', '民政救助', '01', '01', 'http://10.125.8.221:8080/cmc-mzcic/services/cmCallService?wsdl', '{"namespace":"http://10.125.8.221:8080/cmc-mzcic/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:28:20', 'dd-mm-yyyy hh24:mi:ss'));

insert into ifdefine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1005', 'CICLSJDEV', '粮食', '01', '01', 'http://10.0.103.148/lsjfb.website/Service.asmx?wsdl', '{"namespace":"http://tempuri.org/","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:31:02', 'dd-mm-yyyy hh24:mi:ss'));

insert into ifdefine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1008', 'CICFDDEV', '房地租赁备案', '01', '01', 'http://10.125.8.221:8080/cmc-fdcic/services/cmCallService?wsdl', '{"namespace":"http://10.125.8.221:8080/cmc-fdcic/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:35:48', 'dd-mm-yyyy hh24:mi:ss'));

insert into ifdefine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1011', 'CICSBKDEV', '社保卡', '01', '01', 'http://10.102.86.25:8080/axis2/services/CommonImpl?wsdl', '{"namespace":"http://ws.apache.org/axis2","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:38:30', 'dd-mm-yyyy hh24:mi:ss'));

insert into ifdefine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1013', 'CICGHDEV', '总工会', '01', '01', 'http://10.121.55.131:8080/cmc-zghcic/services/cmCallService?wsdl', '{"namespace":"http://10.121.55.131:8080/cmc-zghcic/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:40:53', 'dd-mm-yyyy hh24:mi:ss'));

insert into ifdefine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1001', 'CICYFDEV', '民政优抚', '01', '01', 'http://10.125.8.221:8080/cmc-yfcic/services/cmCallService?wsdl', '{"namespace":"http://10.125.8.221:8080/cmc-yfcic/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:26:49', 'dd-mm-yyyy hh24:mi:ss'));

insert into ifdefine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1004', 'CICDPDEV', '残联', '01', '01', 'http://10.0.6.254:6668/cmc-rtts-dpf/services/cmCallService?wsdl', '{"namespace":"http://10.0.6.254:6668/cmc-rtts-dpf/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:30:11', 'dd-mm-yyyy hh24:mi:ss'));

insert into ifdefine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1007', 'CICYBDEV', '医保', '01', '01', 'http://10.110.51.220:8080/cmc-ybcic-v2.0/services/dispatchService?wsdl', '{"namespace":"http://10.110.51.220:8080/cmc-ybcic-v2.0/services/dispatchService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:33:35', 'dd-mm-yyyy hh24:mi:ss'));

insert into ifdefine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1010', 'CICFDSRHDDEV', '房地收入核对', '01', '01', 'http://10.125.8.221:8080/cmc-mzsrhdcic/services/cmCallService?wsdl', '{"namespace":"http://10.125.8.221:8080/cmc-mzsrhdcic/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:37:48', 'dd-mm-yyyy hh24:mi:ss'));

insert into ifdefine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1015', 'CICJZZDEV', '公安居住证', '01', '01', 'http://10.102.86.25:8081/shjzz/axis/ExernalWebservice?wsdl', '{"namespace":"jzz","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:42:59', 'dd-mm-yyyy hh24:mi:ss'));

insert into ifdefine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1016', 'CICJZDJDEV', '公安居住登记', '01', '01', 'http://10.110.46.93:8080/jk_jzdj/services/jzdj?wsdl', '{"namespace":"http://service","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:43:37', 'dd-mm-yyyy hh24:mi:ss'));

delete from IFDEFhis;

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1003', '1003', 'CICSJPT', '市级平台', '01', '01', 'http://10.125.8.223:8080/cmc-sjptcic/services/cmCallService?wsdl', '{"namespace":"http://10.125.8.223:8080/cmc-sjptcic/services/cmCallService","service":"service","params":"flag,service","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:29:05', 'dd-mm-yyyy hh24:mi:ss'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1006', '1006', 'CICDAJDEV', '档案', '01', '01', 'http://10.103.101.233:8080/msda/services/mzj?wsdl', '{"namespace":"mzj","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:32:48', 'dd-mm-yyyy hh24:mi:ss'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1009', '1009', 'CICFDJSFDEV', '房地经适房', '01', '01', 'http://10.125.8.221:8080/cmc-fdjsfcic/services/cmCallService?wsdl', '{"namespace":"http://10.125.8.221:8080/cmc-fdjsfcic/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:36:48', 'dd-mm-yyyy hh24:mi:ss'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1012', '1012', 'CMCSBDEV', '社保', '01', '01', 'http://10.103.60.30:8080/cmc-cics-sb/services/dispatchService?wsdl', '{"namespace":"http://10.103.60.30:8080/cmc-cics-sb/services/dispatchService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:39:29', 'dd-mm-yyyy hh24:mi:ss'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1014', '1014', 'CMCLDDEV', '劳动', '01', '01', 'http://10.103.60.30:8080/cmc-cics-ld/services/dispatchService?wsdl', '{"namespace":"http://10.103.60.30:8080/cmc-cics-ld/services/dispatchService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:41:57', 'dd-mm-yyyy hh24:mi:ss'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1000', '1000', 'CICHYDEV', '民政婚姻', '01', '01', 'http://10.125.8.221:8080/cmc-hycic/services/cmCallService?wsdl', '{"namespace":"http://10.125.8.221:8080/cmc-hycic/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:25:35', 'dd-mm-yyyy hh24:mi:ss'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1002', '1002', 'CICMZDEV ', '民政救助', '01', '01', 'http://10.125.8.221:8080/cmc-mzcic/services/cmCallService?wsdl', '{"namespace":"http://10.125.8.221:8080/cmc-mzcic/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:28:20', 'dd-mm-yyyy hh24:mi:ss'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1005', '1005', 'CICLSJDEV', '粮食', '01', '01', 'http://10.0.103.148/lsjfb.website/Service.asmx?wsdl', '{"namespace":"http://tempuri.org/","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:31:02', 'dd-mm-yyyy hh24:mi:ss'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1008', '1008', 'CICFDDEV', '房地租赁备案', '01', '01', 'http://10.125.8.221:8080/cmc-fdcic/services/cmCallService?wsdl', '{"namespace":"http://10.125.8.221:8080/cmc-fdcic/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:35:48', 'dd-mm-yyyy hh24:mi:ss'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1011', '1011', 'CICSBKDEV', '社保卡', '01', '01', 'http://10.102.86.25:8080/axis2/services/CommonImpl?wsdl', '{"namespace":"http://ws.apache.org/axis2","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:38:30', 'dd-mm-yyyy hh24:mi:ss'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1013', '1013', 'CICGHDEV', '总工会', '01', '01', 'http://10.121.55.131:8080/cmc-zghcic/services/cmCallService?wsdl', '{"namespace":"http://10.121.55.131:8080/cmc-zghcic/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:40:53', 'dd-mm-yyyy hh24:mi:ss'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1001', '1001', 'CICYFDEV', '民政优抚', '01', '01', 'http://10.125.8.221:8080/cmc-yfcic/services/cmCallService?wsdl', '{"namespace":"http://10.125.8.221:8080/cmc-yfcic/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:26:49', 'dd-mm-yyyy hh24:mi:ss'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1004', '1004', 'CICDPDEV', '残联', '01', '01', 'http://10.0.6.254:6668/cmc-rtts-dpf/services/cmCallService?wsdl', '{"namespace":"http://10.0.6.254:6668/cmc-rtts-dpf/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:30:11', 'dd-mm-yyyy hh24:mi:ss'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1007', '1007', 'CICYBDEV', '医保', '01', '01', 'http://10.110.51.220:8080/cmc-ybcic-v2.0/services/dispatchService?wsdl', '{"namespace":"http://10.110.51.220:8080/cmc-ybcic-v2.0/services/dispatchService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:33:35', 'dd-mm-yyyy hh24:mi:ss'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1010', '1010', 'CICFDSRHDDEV', '房地收入核对', '01', '01', 'http://10.125.8.221:8080/cmc-mzsrhdcic/services/cmCallService?wsdl', '{"namespace":"http://10.125.8.221:8080/cmc-mzsrhdcic/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:37:48', 'dd-mm-yyyy hh24:mi:ss'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1015', '1015', 'CICJZZDEV', '公安居住证', '01', '01', 'http://10.102.86.25:8081/shjzz/axis/ExernalWebservice?wsdl', '{"namespace":"jzz","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:42:59', 'dd-mm-yyyy hh24:mi:ss'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1016', '1016', 'CICJZDJDEV', '公安居住登记', '01', '01', 'http://10.110.46.93:8080/jk_jzdj/services/jzdj?wsdl', '{"namespace":"http://service","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '社区管理', to_date('28-03-2016 19:43:37', 'dd-mm-yyyy hh24:mi:ss'));

delete from IFTRANSSUM ;
insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('1000', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('1001', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('1002', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('1003', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('1004', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('1005', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('1006', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('1007', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('1008', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('1009', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('1010', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('1011', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('1012', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('1013', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('1014', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('1015', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('1016', '0', '0', '0');

delete from ifmondef ;
insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1000', '1000', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1001', '1001', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1002', '1002', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1003', '1003', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1004', '1004', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1005', '1005', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1006', '1006', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1007', '1007', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1008', '1008', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1009', '1009', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1010', '1010', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1011', '1011', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1012', '1012', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1013', '1013', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1014', '1014', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1015', '1015', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1016', '1016', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

--监控对象定义历史
delete from ifmondefhis ;
insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1000', '1000', '1000', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1001', '1001', '1001', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1002', '1002', '1002', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1003', '1003', '1003', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1004', '1004', '1004', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1005', '1005', '1005', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1006', '1006', '1006', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1007', '1007', '1007', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1008', '1008', '1008', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1009', '1009', '1009', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1010', '1010', '1010', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1011', '1011', '1011', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1012', '1012', '1012', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1013', '1013', '1013', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1014', '1014', '1014', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1015', '1015', '1015', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1016', '1016', '1016', null, null, null, '1', '1', '社区管理', to_date('24-02-2016', 'dd-mm-yyyy'));