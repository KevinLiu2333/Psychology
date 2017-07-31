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
	}
};