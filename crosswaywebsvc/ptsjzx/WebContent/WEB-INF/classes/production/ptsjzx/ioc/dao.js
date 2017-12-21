var ioc = {
	dataSource : {
		type : "org.apache.commons.dbcp.BasicDataSource",
		events : {
			depose : 'close'
		},
		fields : {
			driverClassName : 'oracle.jdbc.driver.OracleDriver',
			// 最简配置
			//url : 'jdbc:oracle:thin:@192.168.1.101:1521:ORCL',
//			url : 'jdbc:oracle:thin:@10.1.8.73:1521:ORCL',
//			url : 'jdbc:oracle:thin:@10.208.250.100:1521:ORCL',
//			url : 'jdbc:oracle:thin:@localhost:1521:ORCL',
			url : 'jdbc:oracle:thin:@10.2.8.116:1521:ORCL',
//			username : 'ptsjzx',
//			password : 'ptsjzx',
		    username : 'ptfrk',
			password : 'Wonders_1#',
			initialSize : 10,
			maxActive : 50,
			minIdle : 5,
			maxIdle : 20,
			timeBetweenEvictionRunsMillis:20000,
			testWhileIdle:true,
			validationQuery:'SELECT 1 FROM dual'
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
	dataSource1 : {
		type : "org.apache.commons.dbcp.BasicDataSource",
		events : {
			depose : 'close'
		},
		fields : {
			driverClassName : 'oracle.jdbc.driver.OracleDriver',
			// 最简配置
			//url : 'jdbc:oracle:thin:@192.168.0.102:1521:ORCL',
			//url : 'jdbc:oracle:thin:@10.1.8.73:1521:ORCL',
			//url : 'jdbc:oracle:thin:@localhost:1521:ORCL',
			url : 'jdbc:oracle:thin:@10.208.250.100:1521:ORCL',
			username : 'pt_rkk',
			password : 'Wonders_1#',
			//username : 'sjsjzx',
			//password : 'sjsjzx',
			initialSize : 5,
			maxActive : 30,
			minIdle : 1,
			maxIdle : 20
		}
	},
	dao1 : {
		type : "org.nutz.dao.impl.NutDao",
		fields : {
			dataSource : {
				refer : 'dataSource'
			}
		}
	}
	
	
};