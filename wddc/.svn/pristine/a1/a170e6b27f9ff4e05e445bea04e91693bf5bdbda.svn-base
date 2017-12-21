var ioc = {
		
	logger : {
		type : 'com.wonders.tiles.logger.interceptor.LoggerInterceptor',
		fields : {
			loggerInfo : {refer : 'loggerInfo'}
		}
	},
	
	loggerInfo : {
		type : "com.wonders.tiles.logger.entity.LoggerInfo",
		fields : {
			// itemList :[{refer : 'dBLoggerOutlet'}, {refer :
			// 'consoleLoggerOutlet'},{refer : 'fileLoggerOutlet'}]
			itemList :[
			           {refer : 'consoleLoggerOutlet'},
			           //{refer : 'fileLoggerOutlet'},
			           {refer : 'dBLoggerOutlet'}
			]
		},
		singleton:false
	},
	
	dBLoggerOutlet : {
		type : "com.wonders.tiles.logger.outlet.impl.DBLoggerOutlet",
    	fields: {
    		dao : {refer: 'dao'}
		}
	},
	
	consoleLoggerOutlet : {
		type : "com.wonders.tiles.logger.outlet.impl.ConsoleLoggerOutlet"
	},
	
	fileLoggerOutlet : {
		type : "com.wonders.tiles.logger.outlet.impl.FileLoggerOutlet"
	}
		
};