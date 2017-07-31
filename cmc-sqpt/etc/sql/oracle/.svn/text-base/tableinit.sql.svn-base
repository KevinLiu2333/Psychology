drop table IFDEFINE cascade constraints;

/*==============================================================*/
/* Table: IFDEFINE                                              */
/*==============================================================*/
create table IFDEFINE  (
   IFDEFINEID           number(16)                      not null,
   IFDEFCODE            varchar2(50)                    not null,
   IFDEFNAME            varchar2(100)                   not null,
   IFTYPE               varchar2(50)                    not null,
   IFSUBTYPE            varchar2(50)                    not null,
   IFURI                varchar2(500)                   not null,
   IFEXT                varchar2(2000),
   VALID                CHAR(1)                         not null,
   USRID                number(16)                      not null,
   USRNAME              varchar2(50)                    not null,
   EDTIME               DATE                            not null,
   constraint PK_IFDEFINE primary key (IFDEFINEID)
);

comment on table IFDEFINE is
'接口定义配置';

comment on column IFDEFINE.IFDEFINEID is
'接口定义配置ID';

comment on column IFDEFINE.IFDEFCODE is
'接口定义代码';

comment on column IFDEFINE.IFDEFNAME is
'接口定义名称';

comment on column IFDEFINE.IFTYPE is
'接口大类';

comment on column IFDEFINE.IFSUBTYPE is
'接口小类';

comment on column IFDEFINE.IFURI is
'访问地址';

comment on column IFDEFINE.IFEXT is
'扩展信息';

comment on column IFDEFINE.VALID is
'有效标志';

comment on column IFDEFINE.USRID is
'修改人';

comment on column IFDEFINE.USRNAME is
'修改人姓名';

comment on column IFDEFINE.EDTIME is
'修改时间';

drop table IFMONDEF cascade constraints;

/*==============================================================*/
/* Table: IFMONDEF                                              */
/*==============================================================*/
create table IFMONDEF  (
   IFMONDEFID           number(16)                      not null,
   IFDEFINEID           number(16)                      not null,
   LSMONTIME            DATE,
   LSMONSTATUS          VARCHAR2(3),
   LSMONDESC            VARCHAR2(200),
   VALID                CHAR(1)                         not null,
   USRID                number(16)                      not null,
   USRNAME              varchar2(50)                    not null,
   EDTIME               DATE                            not null,
   constraint PK_IFMONDEF primary key (IFMONDEFID)
);

comment on table IFMONDEF is
'接口监控定义配置';

comment on column IFMONDEF.IFMONDEFID is
'接口监控定义配置ID';

comment on column IFMONDEF.IFDEFINEID is
'接口定义配置ID';

comment on column IFMONDEF.LSMONTIME is
'上次监控时间';

comment on column IFMONDEF.LSMONSTATUS is
'上次监控状态';

comment on column IFMONDEF.LSMONDESC is
'上次监控状态备注';

comment on column IFMONDEF.VALID is
'有效标志';

comment on column IFMONDEF.USRID is
'修改人';

comment on column IFMONDEF.USRNAME is
'修改人姓名';

comment on column IFMONDEF.EDTIME is
'修改时间';

drop table IFMONDETAIL cascade constraints;

/*==============================================================*/
/* Table: IFMONDETAIL                                           */
/*==============================================================*/
create table IFMONDETAIL  (
   IFMONDETAILID        number(16)                      not null,
   IFMONDEFID           number(16),
   IFDEFINEID           number(16)                      not null,
   LSMONTIME            DATE,
   LSMONSTATUS          VARCHAR2(3),
   LSMONDESC            VARCHAR2(200),
   constraint PK_IFMONDETAIL primary key (IFMONDETAILID)
);

comment on table IFMONDETAIL is
'监控明细';

comment on column IFMONDETAIL.IFMONDETAILID is
'监控明细ID';

comment on column IFMONDETAIL.IFMONDEFID is
'接口监控定义配置ID';

comment on column IFMONDETAIL.IFDEFINEID is
'接口定义配置ID';

comment on column IFMONDETAIL.LSMONTIME is
'上次监控时间';

comment on column IFMONDETAIL.LSMONSTATUS is
'上次监控状态';

comment on column IFMONDETAIL.LSMONDESC is
'上次监控状态备注';

drop table IFTRANSSUM cascade constraints;

/*==============================================================*/
/* Table: IFTRANSSUM                                            */
/*==============================================================*/
create table IFTRANSSUM  (
   IFDEFINEID           number(16)                      not null,
   TRANSSUM             number(16)                      not null,
   TRANSSUC             number(16)                      not null,
   TRANSFAIL            number(16)                      not null,
   constraint PK_IFTRANSSUM primary key (IFDEFINEID)
);

comment on table IFTRANSSUM is
'接口监控定义交易汇总';

comment on column IFTRANSSUM.IFDEFINEID is
'接口定义配置ID';

comment on column IFTRANSSUM.TRANSSUM is
'总交易数';

comment on column IFTRANSSUM.TRANSSUC is
'成功笔数';

comment on column IFTRANSSUM.TRANSFAIL is
'失败笔数';


drop table IFTRANSDETAIL cascade constraints;

/*==============================================================*/
/* Table: IFTRANSDETAIL                                         */
/*==============================================================*/
create table IFTRANSDETAIL  (
   IFTRANSDETAILID      number(16)                      not null,
   IFDEFINEID           number(16)                      not null,
   TRANSNO              VARCHAR2(100),
   TRANSFUNC            VARCHAR2(100),
   TRANSSUBFUNC         VARCHAR2(100),
   REQCODE              VARCHAR2(100),
   REQUSRID             VARCHAR2(100),
   REQUSRNAME           VARCHAR2(100),
   RSPCODE              VARCHAR2(100),
   RSPUSRID             VARCHAR2(100),
   RSPUSRNAME           VARCHAR2(100),
   REQFLAG              char(1),
   REQXML               clob,
   REQENXML             clob,
   RSPFLAG              char(1),
   RSPXML               clob,
   RSPENXML             clob,
   REQTIME              date                            not null,
   RSPTIME              date                            not null,
   TRANSTIME            number(16,2)                    not null,
   TRANSFLAG1           VARCHAR2(10),
   TRANSFLAG2           VARCHAR2(10),
   ERRMSG               clob,
   constraint PK_IFTRANSDETAIL primary key (IFTRANSDETAILID)
);

comment on table IFTRANSDETAIL is
'交易明细';

comment on column IFTRANSDETAIL.IFTRANSDETAILID is
'监控明细ID';

comment on column IFTRANSDETAIL.IFDEFINEID is
'接口定义配置ID';

comment on column IFTRANSDETAIL.TRANSNO is
'交易标识';

comment on column IFTRANSDETAIL.TRANSFUNC is
'交易方法';

comment on column IFTRANSDETAIL.TRANSSUBFUNC is
'交易明细方法';

comment on column IFTRANSDETAIL.REQCODE is
'发起方代码';

comment on column IFTRANSDETAIL.REQUSRID is
'发起方用户';

comment on column IFTRANSDETAIL.REQUSRNAME is
'发起方用户名';

comment on column IFTRANSDETAIL.RSPCODE is
'接收方代码';

comment on column IFTRANSDETAIL.RSPUSRID is
'接收方用户';

comment on column IFTRANSDETAIL.RSPUSRNAME is
'接收方用户名';

comment on column IFTRANSDETAIL.REQFLAG is
'发送加密标志';

comment on column IFTRANSDETAIL.REQXML is
'发送报文';

comment on column IFTRANSDETAIL.REQENXML is
'发送加密报文';

comment on column IFTRANSDETAIL.RSPFLAG is
'接收加密标志';

comment on column IFTRANSDETAIL.RSPXML is
'接收报文';

comment on column IFTRANSDETAIL.RSPENXML is
'接收加密报文';

comment on column IFTRANSDETAIL.REQTIME is
'发送时间';

comment on column IFTRANSDETAIL.RSPTIME is
'接受时间';

comment on column IFTRANSDETAIL.TRANSTIME is
'交易时常';

comment on column IFTRANSDETAIL.TRANSFLAG1 is
'交易标志1';

comment on column IFTRANSDETAIL.TRANSFLAG2 is
'交易标志2';

comment on column IFTRANSDETAIL.ERRMSG is
'错误信息';

create index IDX_IFTRANSDETAIL_IFDEFINEID on IFTRANSDETAIL (IFDEFINEID);
create index IDX_IFTRANSDETAIL_REQTIME on IFTRANSDETAIL (reqtime);
create index IDX_IFTRANSDETAIL_TRANSNO on IFTRANSDETAIL (transno);
create index IDX_IFTRANSDETAIL_TRANSSUBFUNC on IFTRANSDETAIL (transsubfunc);

drop table IFDEFHIS cascade constraints;

/*==============================================================*/
/* Table: IFDEFHIS                                              */
/*==============================================================*/
create table IFDEFHIS  (
   IFDEFINEHISID        number(16)                      not null,
   IFDEFINEID           number(16)                      not null,
   IFDEFCODE            varchar2(50)                    not null,
   IFDEFNAME            varchar2(100)                   not null,
   IFTYPE               varchar2(50)                    not null,
   IFSUBTYPE            varchar2(50)                    not null,
   IFURI                varchar2(500)                   not null,
   IFEXT                varchar2(2000),
   VALID                CHAR(1)                         not null,
   USRID                number(16)                      not null,
   USRNAME              varchar2(50)                    not null,
   EDTIME               DATE                            not null,
   constraint PK_IFDEFHIS primary key (IFDEFINEHISID)
);

comment on table IFDEFHIS is
'接口定义历史配置';

comment on column IFDEFHIS.IFDEFINEHISID is
'接口定义历史配置ID';

comment on column IFDEFHIS.IFDEFINEID is
'接口定义配置ID';

comment on column IFDEFHIS.IFDEFCODE is
'接口定义代码';

comment on column IFDEFHIS.IFDEFNAME is
'接口定义名称';

comment on column IFDEFHIS.IFTYPE is
'接口大类';

comment on column IFDEFHIS.IFSUBTYPE is
'接口小类';

comment on column IFDEFHIS.IFURI is
'访问地址';

comment on column IFDEFHIS.IFEXT is
'扩展信息';

comment on column IFDEFHIS.VALID is
'有效标志';

comment on column IFDEFHIS.USRID is
'修改人';

comment on column IFDEFHIS.USRNAME is
'修改人姓名';

comment on column IFDEFHIS.EDTIME is
'修改时间';

drop table IFMONDEFHIS cascade constraints;

/*==============================================================*/
/* Table: IFMONDEFHIS                                           */
/*==============================================================*/
create table IFMONDEFHIS  (
   IFMONDEFHISID        number(16)                      not null,
   IFMONDEFID           number(16)                      not null,
   IFDEFINEID           number(16)                      not null,
   LSMONTIME            DATE,
   LSMONSTATUS          VARCHAR2(3),
   LSMONDESC            VARCHAR2(200),
   VALID                CHAR(1)                         not null,
   USRID                number(16)                      not null,
   USRNAME              varchar2(50)                    not null,
   EDTIME               DATE                            not null,
   constraint PK_IFMONDEFHIS primary key (IFMONDEFHISID)
);

comment on table IFMONDEFHIS is
'接口监控定义配置变更';

comment on column IFMONDEFHIS.IFMONDEFHISID is
'接口监控定义配置变更ID';

comment on column IFMONDEFHIS.IFMONDEFID is
'接口监控定义配置ID';

comment on column IFMONDEFHIS.IFDEFINEID is
'接口定义配置ID';

comment on column IFMONDEFHIS.LSMONTIME is
'上次监控时间';

comment on column IFMONDEFHIS.LSMONSTATUS is
'上次监控状态';

comment on column IFMONDEFHIS.LSMONDESC is
'上次监控状态备注';

comment on column IFMONDEFHIS.VALID is
'有效标志';

comment on column IFMONDEFHIS.USRID is
'修改人';

comment on column IFMONDEFHIS.USRNAME is
'修改人姓名';

comment on column IFMONDEFHIS.EDTIME is
'修改时间';

drop table IFSUBMON cascade constraints;

/*==============================================================*/
/* Table: IFSUBMON                                              */
/*==============================================================*/
create table IFSUBMON  (
   IFMONDEFID           number(16)                      not null,
   SUBMONSUM            number(4),
   SUBMONFAIL           number(4),
   SUBMONDETAIL         CLOB,
   constraint PK_IFSUBMON primary key (IFMONDEFID)
);

comment on table IFSUBMON is
'子接口监控定义配置';

comment on column IFSUBMON.IFMONDEFID is
'接口监控定义配置ID';

comment on column IFSUBMON.SUBMONSUM is
'子监控总数';

comment on column IFSUBMON.SUBMONFAIL is
'子监控问题数';

comment on column IFSUBMON.SUBMONDETAIL is
'子接口监控明细';

drop table IFSUBMONDETAIL cascade constraints;

/*==============================================================*/
/* Table: IFSUBMONDETAIL                                        */
/*==============================================================*/
create table IFSUBMONDETAIL  (
   IFMONDETAILID        number(16)                      not null,
   SUBMONSUM            number(4),
   SUBMONFAIL           number(4),
   SUBMONDETAIL         CLOB,
   constraint PK_IFSUBMONDETAIL primary key (IFMONDETAILID)
);

comment on table IFSUBMONDETAIL is
'子监控明细';

comment on column IFSUBMONDETAIL.IFMONDETAILID is
'监控明细ID';

comment on column IFSUBMONDETAIL.SUBMONSUM is
'子监控总数';

comment on column IFSUBMONDETAIL.SUBMONFAIL is
'子监控问题数';

comment on column IFSUBMONDETAIL.SUBMONDETAIL is
'子接口监控明细';


-- Create sequence 
drop sequence SEQ_IFDEFINEID;
create sequence SEQ_IFDEFINEID minvalue 1
maxvalue 9999999999999999
start with 1000
increment by 1
nocache;
-- Create sequence 
drop sequence SEQ_IFDEFINEHISID ;
create sequence SEQ_IFDEFINEHISID minvalue 1
maxvalue 9999999999999999
start with 1000
increment by 1
nocache;
-- Create sequence 
drop sequence SEQ_IFMONDEFID ;
create sequence SEQ_IFMONDEFID minvalue 1
maxvalue 9999999999999999
start with 1000
increment by 1
nocache;
-- Create sequence 
drop sequence SEQ_IFMONDEFHISID ;
create sequence SEQ_IFMONDEFHISID minvalue 1
maxvalue 9999999999999999
start with 1000
increment by 1
nocache;
-- Create sequence 
drop sequence SEQ_IFMONDETAILID ;
create sequence SEQ_IFMONDETAILID minvalue 1
maxvalue 9999999999999999
start with 1000
increment by 1
nocache;
-- Create sequence 
drop sequence SEQ_IFTRANSDETAILID ;
create sequence SEQ_IFTRANSDETAILID minvalue 1
maxvalue 9999999999999999
start with 1000
increment by 1
nocache;


-----------------------------------------------------------------------------------------------

-- Create table
drop table BUSILOG cascade constraints;
create table BUSILOG
(
  busilogid    NUMBER(16) not null,
  busitype     VARCHAR2(10) not null,
  opertime     DATE not null,
  operorgan    VARCHAR2(16) not null,
  operatorcode VARCHAR2(16) not null,
  operatorname VARCHAR2(50) not null,
  constraint PK_BUSILOG primary key (BUSILOGID)
);
-- Add comments to the table 
comment on table BUSILOG
  is '业务日志';
-- Add comments to the columns 
comment on column BUSILOG.busilogid
  is '业务日志ID';
comment on column BUSILOG.busitype
  is '业务类型';
comment on column BUSILOG.opertime
  is '经办时间';
comment on column BUSILOG.operorgan
  is '经办机构';
comment on column BUSILOG.operatorcode
  is '经办人代码';
comment on column BUSILOG.operatorname
  is '经办人姓名';

-- Create table
drop table UAAUTHINFO cascade constraints;
create table UAAUTHINFO
(
  nodeid       NUMBER(16) not null,
  nodecode     VARCHAR2(100),
  nodename     VARCHAR2(100),
  nodedesc     VARCHAR2(200),
  nodetype     VARCHAR2(10),
  url          VARCHAR2(100),
  parentnodeid NUMBER(16),
  nodeorder    NUMBER(4),
  status       CHAR(1),
  icon1        VARCHAR2(100),
  constraint PK_UAAUTHINFO primary key (NODEID)
);
-- Add comments to the columns 
comment on column UAAUTHINFO.nodeid
  is '权限主键ID';
comment on column UAAUTHINFO.nodecode
  is '节点ID';
comment on column UAAUTHINFO.nodename
  is '节点名称';
comment on column UAAUTHINFO.nodedesc
  is '节点描述';
comment on column UAAUTHINFO.nodetype
  is '节点类型';
comment on column UAAUTHINFO.url
  is '节点链接';
comment on column UAAUTHINFO.parentnodeid
  is '父节点ID';
comment on column UAAUTHINFO.nodeorder
  is '节点排序';
comment on column UAAUTHINFO.status
  is '状态';
comment on column UAAUTHINFO.icon1
  is '图标';

-- Create table
drop table UAROLEAUTH cascade constraints;
create table UAROLEAUTH
(
  roleid NUMBER(16),
  nodeid NUMBER(16)
);
-- Add comments to the columns 
comment on column UAROLEAUTH.roleid
  is '角色ID';
comment on column UAROLEAUTH.nodeid
  is '资源ID';


-- Create table
drop table UAROLEINFO cascade constraints;
create table UAROLEINFO
(
  roleid     NUMBER(16) not null,
  rolename   VARCHAR2(100),
  roleenname VARCHAR2(100),
  status     CHAR(1),
  roletype   VARCHAR2(20),
  constraint PK_UAROLEINFO primary key (ROLEID)
);
-- Add comments to the columns 
comment on column UAROLEINFO.roleid
  is '角色ID';
comment on column UAROLEINFO.rolename
  is '角色中文名';
comment on column UAROLEINFO.roleenname
  is '角色英文名';
comment on column UAROLEINFO.status
  is '状态';
comment on column UAROLEINFO.roletype
  is '角色类型';

drop table UAUSERINFO cascade constraints;
-- Create table
create table UAUSERINFO
(
  userid        NUMBER(16) not null,
  loginname     VARCHAR2(100),
  password      VARCHAR2(100),
  displayname   VARCHAR2(100),
  status        CHAR(1),
  authentictype VARCHAR2(60),
  certificate   VARCHAR2(500),
  ext1          VARCHAR2(200),
  ext2          VARCHAR2(200),
  ext3          VARCHAR2(200),
  ext4          VARCHAR2(200),
  ext5          VARCHAR2(1000),
  constraint PK_UAUSERINFO primary key (USERID)
);
-- Add comments to the columns 
comment on column UAUSERINFO.userid
  is '用户ID';
comment on column UAUSERINFO.loginname
  is '登录名';
comment on column UAUSERINFO.password
  is '密码';
comment on column UAUSERINFO.displayname
  is '显示名';
comment on column UAUSERINFO.status
  is '状态';
comment on column UAUSERINFO.authentictype
  is '认证类型';
comment on column UAUSERINFO.certificate
  is '证书';
comment on column UAUSERINFO.ext1
  is '扩展字段1';
comment on column UAUSERINFO.ext2
  is '扩展字段2';
comment on column UAUSERINFO.ext3
  is '扩展字段3';
comment on column UAUSERINFO.ext4
  is '扩展字段4';
comment on column UAUSERINFO.ext5
  is '扩展字段5';

drop table UAUSERROLE cascade constraints;
-- Create table
create table UAUSERROLE
(
  userid NUMBER(16),
  roleid NUMBER(16)
);
-- Add comments to the columns 
comment on column UAUSERROLE.userid
  is '用户ID';
comment on column UAUSERROLE.roleid
  is '角色ID';

drop table OPTSTATUS cascade constraints;
-- Create table
create table OPTSTATUS
(
  optstatusid    NUMBER(16) not null,
  operatorid     VARCHAR2(16) not null,
  operatorname   VARCHAR2(50) not null,
  userstreetcode VARCHAR2(16),
  userstreetname VARCHAR2(500),
  counterno      VARCHAR2(6) not null,
  pcmac          VARCHAR2(100) not null,
  loginstatus    VARCHAR2(3) not null,
  loginsession   VARCHAR2(500),
  logintime      DATE,
  constraint PK_OPTSTATUS primary key (OPTSTATUSID)
);
-- Add comments to the table 
comment on table OPTSTATUS
  is '系统登录状态表';
-- Add comments to the columns 
comment on column OPTSTATUS.optstatusid
  is '系统登录状态编号';
comment on column OPTSTATUS.operatorid
  is '操作员代码';
comment on column OPTSTATUS.operatorname
  is '操作员名称';
comment on column OPTSTATUS.userstreetcode
  is '所属街道代码';
comment on column OPTSTATUS.userstreetname
  is '所属街道名称';
comment on column OPTSTATUS.counterno
  is '所属柜台编号';
comment on column OPTSTATUS.pcmac
  is '计算机MAC地址';
comment on column OPTSTATUS.loginstatus
  is '登录状态';
comment on column OPTSTATUS.loginsession
  is '登录SESSION';
comment on column OPTSTATUS.logintime
  is '登录时间';

drop table OPTSTATUSLOG cascade constraints;
-- Create table
create table OPTSTATUSLOG
(
  optstatuslogid NUMBER(16) not null,
  operatorid     VARCHAR2(16) not null,
  operatorname   VARCHAR2(50) not null,
  userstreetcode VARCHAR2(16),
  userstreetname VARCHAR2(500),
  counterno      VARCHAR2(6) not null,
  pcmac          VARCHAR2(100) not null,
  loginstatus    VARCHAR2(3) not null,
  loginsession   VARCHAR2(500),
  logintime      DATE,
  logouttime     DATE,
  linehour       NUMBER(16)，
  constraint PK_OPTSTATUSLOG primary key (OPTSTATUSLOGID)
);
-- Add comments to the table 
comment on table OPTSTATUSLOG
  is '经办人系统登录状态日志表';
-- Add comments to the columns 
comment on column OPTSTATUSLOG.optstatuslogid
  is '系统登录状态编号';
comment on column OPTSTATUSLOG.operatorid
  is '操作员代码';
comment on column OPTSTATUSLOG.operatorname
  is '操作员名称';
comment on column OPTSTATUSLOG.userstreetcode
  is '所属街道代码';
comment on column OPTSTATUSLOG.userstreetname
  is '所属街道名称';
comment on column OPTSTATUSLOG.counterno
  is '所属柜台编号';
comment on column OPTSTATUSLOG.pcmac
  is '计算机MAC地址';
comment on column OPTSTATUSLOG.loginstatus
  is '登录状态';
comment on column OPTSTATUSLOG.loginsession
  is '登录SESSION';
comment on column OPTSTATUSLOG.logintime
  is '登录时间';
comment on column OPTSTATUSLOG.logouttime
  is '离开时间';
comment on column OPTSTATUSLOG.linehour
  is '在线时间';

-- Create sequence 
drop sequence SEQ_BUSILOGID ;
create sequence SEQ_BUSILOGID minvalue 1
maxvalue 9999999999999999
start with 1000
increment by 1
nocache;

-- Create sequence 
drop sequence SEQ_UANODEID;
create sequence SEQ_UANODEID minvalue 1
maxvalue 9999999999999999
start with 1000
increment by 1
nocache;

SEQ_UANODEID

-- Create sequence 
drop sequence SEQ_UAROLEID;
create sequence SEQ_UAROLEID minvalue 1
maxvalue 9999999999999999
start with 1000
increment by 1
nocache;

-- Create sequence 
drop sequence SEQ_UAUSERID ;
create sequence SEQ_UAUSERID minvalue 1
maxvalue 9999999999999999
start with 1000
increment by 1
nocache;

-- Create sequence 
drop sequence SEQ_OPTSTATUSID;
create sequence SEQ_OPTSTATUSID minvalue 1
maxvalue 9999999999999999
start with 1000
increment by 1
nocache;

-- Create sequence 
drop sequence SEQ_OPTSTATUSLOGID;
create sequence SEQ_OPTSTATUSLOGID minvalue 1
maxvalue 9999999999999999
start with 1000
increment by 1
nocache;

-----------------------------------------------------------------------------------------------
drop table IFUSER cascade constraints;

/*==============================================================*/
/* Table: IFUSER                                                */
/*==============================================================*/
create table IFUSER  (
   IFUSERID             number(16)                      not null,
   IFUSERCODE           varchar2(50)                    not null,
   IFUSERNAME           varchar2(100)                   not null,
   IFUSERTOKEN          varchar2(50)                    not null,
   IFUSERAESKEY         varchar2(50)                    not null,
   VALID                CHAR(1)                         not null,
   USRID                number(16)                      not null,
   USRNAME              varchar2(50)                    not null,
   EDTIME               DATE                            not null,
   constraint PK_IFUSER primary key (IFUSERID)
);

comment on table IFUSER is
'接口用户';

comment on column IFUSER.IFUSERID is
'接口用户ID';

comment on column IFUSER.IFUSERCODE is
'接口用户代码';

comment on column IFUSER.IFUSERNAME is
'接口用户名';

comment on column IFUSER.IFUSERTOKEN is
'接口用户TOKEN';

comment on column IFUSER.IFUSERAESKEY is
'接口用户AESKEY';

comment on column IFUSER.VALID is
'有效标志';

comment on column IFUSER.USRID is
'修改人';

comment on column IFUSER.USRNAME is
'修改人姓名';

comment on column IFUSER.EDTIME is
'修改时间';

drop table IFUSERHIS cascade constraints;

/*==============================================================*/
/* Table: IFUSERHIS                                             */
/*==============================================================*/
create table IFUSERHIS  (
   IFUSERHISID          number(16)                      not null,
   IFUSERID             number(16)                      not null,
   IFUSERCODE           varchar2(50)                    not null,
   IFUSERNAME           varchar2(100)                   not null,
   IFUSERTOKEN          varchar2(50)                    not null,
   IFUSERAESKEY         varchar2(50)                    not null,
   VALID                CHAR(1)                         not null,
   USRID                number(16)                      not null,
   USRNAME              varchar2(50)                    not null,
   EDTIME               DATE                            not null,
   constraint PK_IFUSERHIS primary key (IFUSERHISID)
);

comment on table IFUSERHIS is
'接口用户历史';

comment on column IFUSERHIS.IFUSERHISID is
'接口用户历史ID';

comment on column IFUSERHIS.IFUSERID is
'接口用户ID';

comment on column IFUSERHIS.IFUSERCODE is
'接口用户代码';

comment on column IFUSERHIS.IFUSERNAME is
'接口用户名';

comment on column IFUSERHIS.IFUSERTOKEN is
'接口用户TOKEN';

comment on column IFUSERHIS.IFUSERAESKEY is
'接口用户AESKEY';

comment on column IFUSERHIS.VALID is
'有效标志';

comment on column IFUSERHIS.USRID is
'修改人';

comment on column IFUSERHIS.USRNAME is
'修改人姓名';

comment on column IFUSERHIS.EDTIME is
'修改时间';

drop table IFUSERAUTH cascade constraints;

/*==============================================================*/
/* Table: IFUSERAUTH                                            */
/*==============================================================*/
create table IFUSERAUTH  (
   IFUSERAUTHID         number(16)                      not null,
   IFUSERID             number(16)                      not null,
   IFDEFINEID           number(16)                      not null,
   constraint PK_IFUSERAUTH primary key (IFUSERAUTHID)
);

comment on table IFUSERAUTH is
'接口用户权限';

comment on column IFUSERAUTH.IFUSERAUTHID is
'接口用户授权ID';

comment on column IFUSERAUTH.IFUSERID is
'接口用户ID';

comment on column IFUSERAUTH.IFDEFINEID is
'接口定义ID';

drop sequence SEQ_IFUSERID;
-- Create sequence 
create sequence SEQ_IFUSERID  minvalue 1
maxvalue 9999999999999999
start with 1
increment by 1
nocache;

drop sequence SEQ_IFUSERHISID;
-- Create sequence 
create sequence SEQ_IFUSERHISID  minvalue 1
maxvalue 9999999999999999
start with 1
increment by 1
nocache;

drop sequence SEQ_IFUSERAUTHID;
-- Create sequence 
create sequence SEQ_IFUSERAUTHID  minvalue 1
maxvalue 9999999999999999
start with 1
increment by 1
nocache;



-----------------------------------------------------------------------------------------------
--1.存放一个jobDetail信息
-- Create table
drop table QRTZ_JOB_DETAILS cascade constraints;
create table QRTZ_JOB_DETAILS
(
  job_name          VARCHAR2(200) not null,
  job_group         VARCHAR2(200) not null,
  description       VARCHAR2(250),
  job_class_name    VARCHAR2(250) not null,
  is_durable        VARCHAR2(1) not null,
  is_volatile       VARCHAR2(1) not null,
  is_stateful       VARCHAR2(1) not null,
  requests_recovery VARCHAR2(1) not null,
  job_data          BLOB
);
-- Create/Recreate indexes 
create index IDX_QRTZ_J_REQ_RECOVERY on QRTZ_JOB_DETAILS (REQUESTS_RECOVERY);
-- Create/Recreate primary, unique and foreign key constraints 
alter table QRTZ_JOB_DETAILS
  add primary key (JOB_NAME, JOB_GROUP);


--2.触发器的基本信息
-- Create table
drop table QRTZ_TRIGGERS cascade constraints;
create table QRTZ_TRIGGERS
(
  trigger_name   VARCHAR2(200) not null,
  trigger_group  VARCHAR2(200) not null,
  job_name       VARCHAR2(200) not null,
  job_group      VARCHAR2(200) not null,
  is_volatile    VARCHAR2(1) not null,
  description    VARCHAR2(250),
  next_fire_time NUMBER(13),
  prev_fire_time NUMBER(13),
  priority       NUMBER(13),
  trigger_state  VARCHAR2(16) not null,
  trigger_type   VARCHAR2(8) not null,
  start_time     NUMBER(13) not null,
  end_time       NUMBER(13),
  calendar_name  VARCHAR2(200),
  misfire_instr  NUMBER(2),
  job_data       BLOB
);
-- Create/Recreate indexes 
create index IDX_QRTZ_T_NEXT_FIRE_TIME on QRTZ_TRIGGERS (NEXT_FIRE_TIME);
create index IDX_QRTZ_T_NFT_ST on QRTZ_TRIGGERS (NEXT_FIRE_TIME, TRIGGER_STATE);
create index IDX_QRTZ_T_STATE on QRTZ_TRIGGERS (TRIGGER_STATE);
create index IDX_QRTZ_T_VOLATILE on QRTZ_TRIGGERS (IS_VOLATILE);
-- Create/Recreate primary, unique and foreign key constraints 
alter table QRTZ_TRIGGERS
  add primary key (TRIGGER_NAME, TRIGGER_GROUP);
alter table QRTZ_TRIGGERS
  add foreign key (JOB_NAME, JOB_GROUP)
  references QRTZ_JOB_DETAILS (JOB_NAME, JOB_GROUP);


--3.Trigger作为Blob类型存储(用于Quartz用户用JDBC创建他们自己定制的Trigger类型,JobStore并不知道如何存储实例的时候)
-- Create table
drop table QRTZ_BLOB_TRIGGERS cascade constraints;
create table QRTZ_BLOB_TRIGGERS
(
  trigger_name  VARCHAR2(200) not null,
  trigger_group VARCHAR2(200) not null,
  blob_data     BLOB
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table QRTZ_BLOB_TRIGGERS
  add primary key (TRIGGER_NAME, TRIGGER_GROUP);
alter table QRTZ_BLOB_TRIGGERS
  add foreign key (TRIGGER_NAME, TRIGGER_GROUP)
  references QRTZ_TRIGGERS (TRIGGER_NAME, TRIGGER_GROUP);

--4.存放日历信息， quartz可配置一个日历来指定一个时间范围。
-- Create table
drop table QRTZ_CALENDARS cascade constraints;
create table QRTZ_CALENDARS
(
  calendar_name VARCHAR2(200) not null,
  calendar      BLOB not null
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table QRTZ_CALENDARS
  add primary key (CALENDAR_NAME);


--5.存放cron类型的触发器
-- Create table
drop table QRTZ_CRON_TRIGGERS cascade constraints;
create table QRTZ_CRON_TRIGGERS
(
  trigger_name    VARCHAR2(200) not null,
  trigger_group   VARCHAR2(200) not null,
  cron_expression VARCHAR2(120) not null,
  time_zone_id    VARCHAR2(80)
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table QRTZ_CRON_TRIGGERS
  add primary key (TRIGGER_NAME, TRIGGER_GROUP);
alter table QRTZ_CRON_TRIGGERS
  add foreign key (TRIGGER_NAME, TRIGGER_GROUP)
  references QRTZ_TRIGGERS (TRIGGER_NAME, TRIGGER_GROUP);


--6.存放已触发的触发器
-- Create table
drop table QRTZ_FIRED_TRIGGERS cascade constraints;
create table QRTZ_FIRED_TRIGGERS
(
  entry_id          VARCHAR2(95) not null,
  trigger_name      VARCHAR2(200) not null,
  trigger_group     VARCHAR2(200) not null,
  is_volatile       VARCHAR2(1) not null,
  instance_name     VARCHAR2(200) not null,
  fired_time        NUMBER(13) not null,
  priority          NUMBER(13) not null,
  state             VARCHAR2(16) not null,
  job_name          VARCHAR2(200),
  job_group         VARCHAR2(200),
  is_stateful       VARCHAR2(1),
  requests_recovery VARCHAR2(1)
);
-- Create/Recreate indexes 
create index IDX_QRTZ_FT_JOB_GROUP on QRTZ_FIRED_TRIGGERS (JOB_GROUP);
create index IDX_QRTZ_FT_JOB_NAME on QRTZ_FIRED_TRIGGERS (JOB_NAME);
create index IDX_QRTZ_FT_JOB_REQ_RECOVERY on QRTZ_FIRED_TRIGGERS (REQUESTS_RECOVERY);
create index IDX_QRTZ_FT_JOB_STATEFUL on QRTZ_FIRED_TRIGGERS (IS_STATEFUL);
create index IDX_QRTZ_FT_TRIG_GROUP on QRTZ_FIRED_TRIGGERS (TRIGGER_GROUP);
create index IDX_QRTZ_FT_TRIG_INST_NAME on QRTZ_FIRED_TRIGGERS (INSTANCE_NAME);
create index IDX_QRTZ_FT_TRIG_NAME on QRTZ_FIRED_TRIGGERS (TRIGGER_NAME);
create index IDX_QRTZ_FT_TRIG_NM_GP on QRTZ_FIRED_TRIGGERS (TRIGGER_NAME, TRIGGER_GROUP);
create index IDX_QRTZ_FT_TRIG_VOLATILE on QRTZ_FIRED_TRIGGERS (IS_VOLATILE);
-- Create/Recreate primary, unique and foreign key constraints 
alter table QRTZ_FIRED_TRIGGERS
  add primary key (ENTRY_ID);


--7.job监听器
-- Create table
drop table QRTZ_JOB_LISTENERS cascade constraints;
create table QRTZ_JOB_LISTENERS
(
  job_name     VARCHAR2(200) not null,
  job_group    VARCHAR2(200) not null,
  job_listener VARCHAR2(200) not null
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table QRTZ_JOB_LISTENERS
  add primary key (JOB_NAME, JOB_GROUP, JOB_LISTENER);
alter table QRTZ_JOB_LISTENERS
  add foreign key (JOB_NAME, JOB_GROUP)
  references QRTZ_JOB_DETAILS (JOB_NAME, JOB_GROUP);


--8.存储程序的非观锁的信息(假如使用了悲观锁)。
-- Create table
drop table QRTZ_LOCKS cascade constraints;
create table QRTZ_LOCKS
(
  lock_name VARCHAR2(40) not null
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table QRTZ_LOCKS
  add primary key (LOCK_NAME);


--9.存放暂停掉的触发器
-- Create table
drop table QRTZ_PAUSED_TRIGGER_GRPS cascade constraints;
create table QRTZ_PAUSED_TRIGGER_GRPS
(
  trigger_group VARCHAR2(200) not null
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table QRTZ_PAUSED_TRIGGER_GRPS
  add primary key (TRIGGER_GROUP);


--10.调度器状态
-- Create table
drop table QRTZ_SCHEDULER_STATE cascade constraints;
create table QRTZ_SCHEDULER_STATE
(
  instance_name     VARCHAR2(200) not null,
  last_checkin_time NUMBER(13) not null,
  checkin_interval  NUMBER(13) not null
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table QRTZ_SCHEDULER_STATE
  add primary key (INSTANCE_NAME);


--11.简单触发器的信息
-- Create table
drop table QRTZ_SIMPLE_TRIGGERS cascade constraints;
create table QRTZ_SIMPLE_TRIGGERS
(
  trigger_name    VARCHAR2(200) not null,
  trigger_group   VARCHAR2(200) not null,
  repeat_count    NUMBER(7) not null,
  repeat_interval NUMBER(12) not null,
  times_triggered NUMBER(7) not null
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table QRTZ_SIMPLE_TRIGGERS
  add primary key (TRIGGER_NAME, TRIGGER_GROUP);
alter table QRTZ_SIMPLE_TRIGGERS
  add foreign key (TRIGGER_NAME, TRIGGER_GROUP)
  references QRTZ_TRIGGERS (TRIGGER_NAME, TRIGGER_GROUP);


--12.触发器监听器
-- Create table
drop table QRTZ_TRIGGER_LISTENERS cascade constraints;
create table QRTZ_TRIGGER_LISTENERS
(
  trigger_name     VARCHAR2(200) not null,
  trigger_group    VARCHAR2(200) not null,
  trigger_listener VARCHAR2(200) not null
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table QRTZ_TRIGGER_LISTENERS
  add primary key (TRIGGER_NAME, TRIGGER_GROUP, TRIGGER_LISTENER);
alter table QRTZ_TRIGGER_LISTENERS
  add foreign key (TRIGGER_NAME, TRIGGER_GROUP)
  references QRTZ_TRIGGERS (TRIGGER_NAME, TRIGGER_GROUP);

--13.初始化数据
delete from qrtz_locks;
insert into qrtz_locks (LOCK_NAME)
values ('CALENDAR_ACCESS');

insert into qrtz_locks (LOCK_NAME)
values ('JOB_ACCESS');

insert into qrtz_locks (LOCK_NAME)
values ('MISFIRE_ACCESS');

insert into qrtz_locks (LOCK_NAME)
values ('STATE_ACCESS');

insert into qrtz_locks (LOCK_NAME)
values ('TRIGGER_ACCESS');
commit;
--业务表
--14.调度配置表
-- Create table
drop table QRTZ_TRIGGER_CONFIG cascade constraints;
create table QRTZ_TRIGGER_CONFIG
(
  id          NUMBER(10) not null,
  bean_name   VARCHAR2(300),
  bean_desc   VARCHAR2(300),
  notes       VARCHAR2(1000),
  validity    VARCHAR2(4) default '1',
  create_time DATE,
  creater     VARCHAR2(30),
  ext1        VARCHAR2(500),
  ext2        VARCHAR2(100)
);
-- Add comments to the table 
comment on table QRTZ_TRIGGER_CONFIG
  is '调度配置表';
-- Add comments to the columns 
comment on column QRTZ_TRIGGER_CONFIG.id
  is '调度配置流水号';
comment on column QRTZ_TRIGGER_CONFIG.bean_name
  is '实体名称';
comment on column QRTZ_TRIGGER_CONFIG.bean_desc
  is '实体描述';
comment on column QRTZ_TRIGGER_CONFIG.notes
  is '备注';
comment on column QRTZ_TRIGGER_CONFIG.validity
  is '是否有效';
comment on column QRTZ_TRIGGER_CONFIG.create_time
  is '创建时间';
comment on column QRTZ_TRIGGER_CONFIG.creater
  is '创建人';
comment on column QRTZ_TRIGGER_CONFIG.ext1
  is '扩展字段1';
comment on column QRTZ_TRIGGER_CONFIG.ext2
  is '扩展字段2';
-- Create/Recreate indexes 
create index QRTZ_TRIGGER_CONFIG_INDEX on QRTZ_TRIGGER_CONFIG (BEAN_NAME);
-- Create/Recreate primary, unique and foreign key constraints 
alter table QRTZ_TRIGGER_CONFIG
  add constraint PK_QRTZ_TRIGGER_CONFIG primary key (ID);

--15.调度配置明细表
-- Create table
drop table QRTZ_TRIGGER_CONFIG_RELEVANCE cascade constraints;
create table QRTZ_TRIGGER_CONFIG_RELEVANCE
(
  id                NUMBER(10) not null,
  trigger_config_id NUMBER(10),
  trigger_name      VARCHAR2(300),
  validity          VARCHAR2(4) default '1',
  creater           VARCHAR2(30),
  create_time       DATE,
  ext1              VARCHAR2(100),
  ext2              VARCHAR2(100),
  trigger_type      VARCHAR2(20),
  start_time        DATE,
  end_time          DATE,
  cron_expr         VARCHAR2(1000),
  exeucount         NUMBER(10),
  exeuspace         NUMBER(10)
);
-- Add comments to the table 
comment on table QRTZ_TRIGGER_CONFIG_RELEVANCE
  is '调度配置明细表';
-- Add comments to the columns 
comment on column QRTZ_TRIGGER_CONFIG_RELEVANCE.id
  is '配置明细流水号';
comment on column QRTZ_TRIGGER_CONFIG_RELEVANCE.trigger_config_id
  is '配置流水号';
comment on column QRTZ_TRIGGER_CONFIG_RELEVANCE.trigger_name
  is '名称';
comment on column QRTZ_TRIGGER_CONFIG_RELEVANCE.validity
  is '是否有效';
comment on column QRTZ_TRIGGER_CONFIG_RELEVANCE.creater
  is '创建人';
comment on column QRTZ_TRIGGER_CONFIG_RELEVANCE.create_time
  is '创建时间';
comment on column QRTZ_TRIGGER_CONFIG_RELEVANCE.ext1
  is '扩展字段1';
comment on column QRTZ_TRIGGER_CONFIG_RELEVANCE.ext2
  is '扩展字段2';
comment on column QRTZ_TRIGGER_CONFIG_RELEVANCE.trigger_type
  is '配置类型';
comment on column QRTZ_TRIGGER_CONFIG_RELEVANCE.start_time
  is '开始时间';
comment on column QRTZ_TRIGGER_CONFIG_RELEVANCE.end_time
  is '结束时间';
comment on column QRTZ_TRIGGER_CONFIG_RELEVANCE.cron_expr
  is '当前表达式';
comment on column QRTZ_TRIGGER_CONFIG_RELEVANCE.exeucount
  is '执行次数';
comment on column QRTZ_TRIGGER_CONFIG_RELEVANCE.exeuspace
  is '执行间隔';
-- Create/Recreate indexes 
create index QRTZ_TRIGGER_RELEVANCE_INDEX on QRTZ_TRIGGER_CONFIG_RELEVANCE (TRIGGER_CONFIG_ID, TRIGGER_NAME, TRIGGER_TYPE, START_TIME, END_TIME, CRON_EXPR);
-- Create/Recreate primary, unique and foreign key constraints 
alter table QRTZ_TRIGGER_CONFIG_RELEVANCE
  add constraint PK_QRTZ_TRIGGER_CONFIG_RELEVAN primary key (ID);
alter table QRTZ_TRIGGER_CONFIG_RELEVANCE
  add constraint FK_QRTZ_TRI_30 foreign key (TRIGGER_CONFIG_ID)
  references QRTZ_TRIGGER_CONFIG (ID);


--16.日志
-- Create table
drop table BL_QUARTZ cascade constraints;
create table BL_QUARTZ
(
  id                NUMBER(10) not null,
  trigger_time      DATE,
  trigger_task_name VARCHAR2(300),
  relation_id       VARCHAR2(20),
  trigger_task_desc VARCHAR2(1000),
  notes             VARCHAR2(1000),
  create_date       DATE,
  create_by         VARCHAR2(30)
);
-- Add comments to the table 
comment on table BL_QUARTZ
  is '调度任务日志表';
-- Add comments to the columns 
comment on column BL_QUARTZ.id
  is '调度任务日志流水号';
comment on column BL_QUARTZ.trigger_time
  is '触发时间';
comment on column BL_QUARTZ.trigger_task_name
  is '触发任务名称';
comment on column BL_QUARTZ.relation_id
  is '关联ID';
comment on column BL_QUARTZ.trigger_task_desc
  is '触发任务描述';
comment on column BL_QUARTZ.notes
  is '备注(记录数据类型等)';
comment on column BL_QUARTZ.create_date
  is '创建时间';
comment on column BL_QUARTZ.create_by
  is '创建人';
-- Create/Recreate indexes 
create index BL_QUARTZ_INDEX on BL_QUARTZ (TRIGGER_TIME, TRIGGER_TASK_NAME, CREATE_DATE);
-- Create/Recreate primary, unique and foreign key constraints 
alter table BL_QUARTZ
  add constraint PK_QUARTZ_LOG primary key (ID);

--sequence
-- Create sequence 
drop  sequence SEQ_QRTZ_TRIGGER_RELEVANCE;
create sequence SEQ_QRTZ_TRIGGER_RELEVANCE
minvalue 1
maxvalue 9999999999999999
start with 1000
increment by 1
nocache;

-- Create sequence 
drop  sequence SEQ_QRTZ_TRIGGER_CONFIG;
create sequence SEQ_QRTZ_TRIGGER_CONFIG
minvalue 1
maxvalue 9999999999999999
start with 1000
increment by 1
nocache;

-- Create sequence 
drop  sequence SEQ_BL_QUARTZ;
create sequence SEQ_BL_QUARTZ
minvalue 1
maxvalue 9999999999999999
start with 1000
increment by 1
nocache;

-------------------------------------------------------------------------------------------------

