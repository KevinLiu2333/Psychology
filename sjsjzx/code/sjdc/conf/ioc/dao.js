var ioc = {
	dataSource : {
		type : "org.apache.commons.dbcp.BasicDataSource",
		events : {
			depose : 'close'
		},
		fields : {
			driverClassName : 'oracle.jdbc.driver.OracleDriver',
			url : 'jdbc:oracle:thin:@10.2.8.169:1521:ORCL',
			username : 'sjdc',
			password : 'sjdc',
			
			//url : 'jdbc:mysql://localhost:3306/sjdc?useUnicode=true&characterEncoding=utf-8&autoReconnect=true',
			//driverClassName : 'com.mysql.jdbc.Driver',
			//username : 'root',
		    //password : '',
		    
			initialSize : 5,
			maxActive : 30,
			minIdle : 1,
			maxIdle : 20
		}
	},
	dao : {
		type : "org.nutz.dao.impl.NutDao",
		fields : {
			dataSource : {
				refer : 'dataSource'
			}
		}
	},
	userService : {
		type : "com.wonders.sjdc.jk.WdUserService",
		fields : {
			dao : {
				refer : 'dao'
			}
		}
	}
};