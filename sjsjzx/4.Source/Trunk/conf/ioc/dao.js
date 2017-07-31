var ioc = {
	dataSource : {
		type : "org.apache.commons.dbcp.BasicDataSource",
		events : {
			depose : 'close'
		},
		fields : {
			driverClassName : 'oracle.jdbc.driver.OracleDriver',
			// 最简配置
			url : 'jdbc:oracle:thin:@10.1.8.73:1521:ORCL',
			//url : 'jdbc:oracle:thin:@localhost:1521:ORCL',
			username : 'sjsjzx',
			password : 'sjsjzx',
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
	dataSource1 : {
		type : "org.apache.commons.dbcp.BasicDataSource",
		events : {
			depose : 'close'
		},
		fields : {
			driverClassName : 'oracle.jdbc.driver.OracleDriver',
			// 最简配置
			url : 'jdbc:oracle:thin:@10.1.8.73:1521:ORCL',
			username : 'sjsjzx',
			password : 'sjsjzx',
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
				refer : 'dataSource1'
			}
		}
	}
	
	/*dataSource2 : {
		type : "org.apache.commons.dbcp.BasicDataSource",
		events : {
			depose : 'close'
		},
		fields : {
			driverClassName : 'oracle.jdbc.driver.OracleDriver',
			// 最简配置
			//url : 'jdbc:oracle:thin:@localhost:1521:ORCL',
			url : 'jdbc:oracle:thin:@192.168.104.6:1521:ORCL',
			username : 'sj_gis',
			password : 'sj_gis',
			initialSize : 5,
			maxActive : 30,
			minIdle : 1,
			maxIdle : 20
		}
	},
	dao2 : {
		type : "org.nutz.dao.impl.NutDao",
		fields : {
			dataSource : {
				refer : 'dataSource2'
			}
		}
	}*/
	
	
};