--�ӿڶ���
delete from IFDEFine ;
insert into IFDEFine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1', 'CICHYDEV', '��������', '01', '01', 'http://192.168.230.110:8080/cmc-hycic-test/services/cmCallService?wsdl', '{"namespace":"http://192.168.230.110:8080/cmc-hycic-test/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('2', 'CICYFDEV', '�����Ÿ�', '01', '01', 'http://192.168.230.110:8080/cmc-yfcic-test/services/cmCallService?wsdl', '{"namespace":"http://192.168.230.110:8080/cmc-yfcic-test/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('3', 'CICMZDEV', '��������', '01', '01', 'http://192.168.230.110:8080/cmc-mzcic-test/services/cmCallService?wsdl', '{"namespace":"http://192.168.230.110:8080/cmc-mzcic-test/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('4', 'CICDPDEV', '����', '01', '01', 'http://10.0.6.254:6668/cmc-rtts-dpf/services/cmCallService?wsdl', '{"namespace":"http://10.0.6.254:6668/cmc-rtts-dpf/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('5', 'CICLSJDEV', '��ʳ', '01', '01', 'http://10.0.103.148/lsjfb.WebSite.Test/Service.asmx?wsdl', '{"namespace":"http://tempuri.org/","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('6', 'CICDAJDEV', '����', '01', '01', 'http://10.103.101.233:8080/msda/services/mzj?wsdl', '{"namespace":"mzj","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('7', 'CMCLDDEV', '�Ͷ�', '01', '01', 'http://192.168.230.112:8080/cmc-servicemock/services/dispatchService?wsdl', '{"namespace":"http://192.168.230.112:8080/cmc-servicemock/services/dispatchService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('8', 'CMCSBDEV', '�籣', '01', '01', 'http://192.168.230.112:8080/cmc-servicemock/services/dispatchService?wsdl', '{"namespace":"http://192.168.230.112:8080/cmc-servicemock/services/dispatchService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('9', 'CICFDDEV', '�������ޱ���', '01', '01', 'http://192.168.230.110:8080/cmc-fdcic-test/services/cmCallService?wsdl', '{"namespace":"http://192.168.230.110:8080/cmc-fdcic-test/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('10', 'CICFDJSFDEV', '���ؾ��ʷ�', '01', '01', 'http://192.168.230.110:8080/cmc-fdjsfcic-test/services/cmCallService?wsdl', '{"namespace":"http://192.168.230.110:8080/cmc-fdjsfcic-test/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('11', 'CICFDSRHDDEV', '��������˶�', '01', '01', 'http://192.168.230.110:8080/cmc-mzsrhdcic-test/services/cmCallService?wsdl', '{"namespace":"http://192.168.230.110:8080/cmc-mzsrhdcic-test/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('12', 'CICSBKDEV', '�籣��', '01', '01', 'http://10.102.86.25:8080/axis2/services/CommonImpl_test?wsdl', '{"namespace":"http://ws.apache.org/axis2","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('13', 'CICYBDEV', 'ҽ��', '01', '01', 'http://10.110.51.221:8080/cmc-ybcic-v2.0/services/dispatchService?wsdl', '{"namespace":"http://10.110.51.221:8080/cmc-ybcic-v2.0/services/dispatchService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('14', 'CICJZZDEV', '������ס֤', '01', '01', 'http://10.102.86.25:8081/shjzz/axis/ExernalWebservice_test?wsdl', '{"namespace":"jzz","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('15', 'CICJZDJDEV', '������ס�Ǽ�', '01', '01', 'http://10.110.46.93:8080/jk_jzdj/services/jzdj_test?wsdl', '{"namespace":"http://service","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('16', 'CICGHDEV', '�ܹ���', '01', '01', 'http://10.121.55.132:8080/cmc-zghcic/services/cmCallService?wsdl', '{"namespace":"http://10.121.55.132:8080/cmc-zghcic/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFine (IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('17', 'CICSJPT', '�м�ƽ̨', '01', '01', 'http://192.168.230.112:8180/cmc-sjptcic/services/cmCallService?wsdl', '{"namespace":"http://192.168.230.112:8180/cmc-sjptcic/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

--�ӿڶ�����ʷ��¼
delete from IFDEFhis ;
insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('1', '1', 'CICHYDEV', '��������', '01', '01', 'http://192.168.230.110:8080/cmc-hycic-test/services/cmCallService?wsdl', '{"namespace":"http://192.168.230.110:8080/cmc-hycic-test/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('2', '2', 'CICYFDEV', '�����Ÿ�', '01', '01', 'http://192.168.230.110:8080/cmc-yfcic-test/services/cmCallService?wsdl', '{"namespace":"http://192.168.230.110:8080/cmc-yfcic-test/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('3', '3', 'CICMZDEV', '��������', '01', '01', 'http://192.168.230.110:8080/cmc-mzcic-test/services/cmCallService?wsdl', '{"namespace":"http://192.168.230.110:8080/cmc-mzcic-test/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('4', '4', 'CICDPDEV', '����', '01', '01', 'http://10.0.6.254:6668/cmc-rtts-dpf/services/cmCallService?wsdl', '{"namespace":"http://10.0.6.254:6668/cmc-rtts-dpf/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('5', '5', 'CICLSJDEV', '��ʳ', '01', '01', 'http://10.0.103.148/lsjfb.WebSite.Test/Service.asmx?wsdl', '{"namespace":"http://tempuri.org/","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('6', '6', 'CICDAJDEV', '����', '01', '01', 'http://10.103.101.233:8080/msda/services/mzj?wsdl', '{"namespace":"mzj","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('7', '7', 'CMCLDDEV', '�Ͷ�', '01', '01', 'http://192.168.230.112:8080/cmc-servicemock/services/dispatchService?wsdl', '{"namespace":"http://192.168.230.112:8080/cmc-servicemock/services/dispatchService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('8', '8', 'CMCSBDEV', '�籣', '01', '01', 'http://192.168.230.112:8080/cmc-servicemock/services/dispatchService?wsdl', '{"namespace":"http://192.168.230.112:8080/cmc-servicemock/services/dispatchService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('9', '9', 'CICFDDEV', '�������ⷿ', '01', '01', 'http://192.168.230.110:8080/cmc-fdcic-test/services/cmCallService?wsdl', '{"namespace":"http://192.168.230.110:8080/cmc-fdcic-test/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('10', '10', 'CICFDJSFDEV', '���ؾ��ʷ�', '01', '01', 'http://192.168.230.110:8080/cmc-fdjsfcic-test/services/cmCallService?wsdl', '{"namespace":"http://192.168.230.110:8080/cmc-fdjsfcic-test/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('11', '11', 'CICFDSRHDDEV', '��������˶�', '01', '01', 'http://192.168.230.110:8080/cmc-mzsrhdcic-test/services/cmCallService?wsdl', '{"namespace":"http://192.168.230.110:8080/cmc-mzsrhdcic-test/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('12', '12', 'CICSBKDEV', '�籣��', '01', '01', 'http://10.102.86.25:8080/axis2/services/CommonImpl_test?wsdl', '{"namespace":"http://ws.apache.org/axis2","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('13', '13', 'CICYBDEV', 'ҽ��', '01', '01', 'http://10.110.51.221:8080/cmc-ybcic-v2.0/services/dispatchService?wsdl', '{"namespace":"http://10.110.51.221:8080/cmc-ybcic-v2.0/services/dispatchService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('14', '14', 'CICJZZDEV', '������ס֤', '01', '01', 'http://10.102.86.25:8081/shjzz/axis/ExernalWebservice_test?wsdl', '{"namespace":"jzz","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('15', '15', 'CICJZDJDEV', '������ס�Ǽ�', '01', '01', 'http://10.110.46.93:8080/jk_jzdj/services/jzdj_test?wsdl', '{"namespace":"http://service","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('16', '16', 'CICGHDEV', '�ܹ���', '01', '01', 'http://10.121.55.132:8080/cmc-zghcic/services/cmCallService?wsdl', '{"namespace":"http://10.121.55.132:8080/cmc-zghcic/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into IFDEFhis (IFDEFINEHISID, IFDEFINEID, IFDEFCODE, IFDEFNAME, IFTYPE, IFSUBTYPE, IFURI, IFEXT, VALID, USRID, USRNAME, EDTIME)
values ('17', '17','CICSJPT', '�м�ƽ̨', '01', '01', 'http://192.168.230.112:8180/cmc-sjptcic/services/cmCallService?wsdl', '{"namespace":"http://192.168.230.112:8180/cmc-sjptcic/services/cmCallService","service":"service","params":"flag,message","paramstype":"byte,byte[]","returntype":"byte[]","flag":"false","path":"","username":"","password":""}', '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));


--�ӿڶ������
delete from IFTRANSSUM ;
insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('1', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('2', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('3', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('4', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('5', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('6', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('7', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('8', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('9', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('10', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('11', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('12', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('13', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('14', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('15', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('16', '0', '0', '0');

insert into IFTRANSSUM (IFDEFINEID, TRANSSUM, TRANSSUC, TRANSFAIL)
values ('17', '0', '0', '0');

--��ض�����
delete from ifmondef ;
insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1', '1', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('2', '2', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('3', '3', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('4', '4', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('5', '5', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('6', '6', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('7', '7', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('8', '8', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('9', '9', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('10', '10', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('11', '11', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('12', '12', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('13', '13', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('14', '14', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('15', '15', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('16', '16', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondef (IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('17', '17', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));


--��ض�������ʷ
delete from ifmondefhis ;

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('1', '1', '1', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('2', '2', '2', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('3', '3', '3', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('4', '4', '4', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('5', '5', '5', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('6', '6', '6', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('7', '7', '7', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('8', '8', '8', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('9', '9', '9', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('10', '10', '10', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('11', '11', '11', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('12', '12', '12', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('13', '13', '13', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('14', '14', '14', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('15', '15', '15', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('16', '16', '16', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));

insert into ifmondefhis (IFMONDEFHISID, IFMONDEFID, IFDEFINEID, LSMONTIME, LSMONSTATUS, LSMONDESC, VALID, USRID, USRNAME, EDTIME)
values ('17', '17', '17', null, null, null, '1', '1', '��������', to_date('24-02-2016', 'dd-mm-yyyy'));
