var ioc = {
	dataSource : {
		type : "org.apache.commons.dbcp.BasicDataSource",
		events : {
			depose : 'close'
		},
		fields : {
			url : 'jdbc:mysql://10.2.8.169:3306/wddc?useUnicode=true&characterEncoding=utf-8&autoReconnect=true',
			driverClassName : 'com.mysql.jdbc.Driver',
			username : 'wddc',
			password : 'wddc',
			// 最简配置
			//driverClassName : 'oracle.jdbc.driver.OracleDriver',
			//url : 'jdbc:oracle:thin:@10.1.8.73:1521:ORCL',
			//username : 'teamplay',
			//password : 'teamplay',
			initialSize : 5,
			maxActive : 30,
			minIdle : 1,
			maxIdle : 20,
			timeBetweenEvictionRunsMillis : 20000,
	        testWhileIdle : true,
	        validationQuery : 'SELECT 1'
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
		type : "com.wonders.wdsp.jk.WdUserService",
		fields : {
			dao : {
				refer : 'dao'
			}
		}
	}
};