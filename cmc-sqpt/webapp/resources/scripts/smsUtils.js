mini.namespace("Sms.util");
	    var smsalertwin = null;
		var checkSmsTimer = null;
		var smsCheckCounts =0 ;

Sms.util.showSmsPage=function showSmsPage() {
			mini.hideMessageBox(smsalertwin);
			smsalertwin = null;
			//Wssip.util.selectMenu('10001');
			Sms.util.checkSms(60000);
		}

Sms.util.callCheckSms=function callCheckSms() {
			Wssip.util.request({
				nochecksms : true,
				url : __CONTEXT_PATH__+'/sms/checkSms.action',
				data : {},
				success : function(result) {
					checkSmsTimer = null;
					if (result && result.msgcount > 0) {
						//Wssip.mini.info("1");
						var html = "<a href=\"javascript:showSmsPage();\">您有" + result.msgcount + "封未读消息</a>";
						smsalertwin = mini.showMessageBox({
							showModal : false,
							width : 200,
							title : "提示",
							iconCls : "mini-messagebox-info",
							message : html,
							x : "right",
							y : "bottom",
							callback : function(action) {
								smsalertwin = null;
								Sms.util.checkSms();
							}
						});

					}else{
						Sms.util.checkSms();
					}

				}
			});
		}

Sms.util.checkSms=function checkSms(sleeptimes) {

			if (sleeptimes && sleeptimes > 100) {
				//按传入时间执行
				if (checkSmsTimer) {
					clearTimeout(checkSmsTimer);
					checkSmsTimer = null;
				}

			} else {
				//默认每隔40秒到60秒刷新一次
				sleeptimes = Math.floor(Math.random() * 100); //0-100秒
				if (sleeptimes < 40) {
					sleeptimes = 40;
				}
				if (sleeptimes > 60) {
					sleeptimes = 60;
				}
				sleeptimes = 3 * 1000;
			}

			if (checkSmsTimer) {//进行中， 不处理
				return;
			}

			if (smsalertwin) {//窗口打开中，不处理
				return;
			}

			if (smsCheckCounts > 30) {//每次主动调用会重置时间，如果次数超过了30次，大概20-30分钟，说明业务无操作
				return;
			}
			smsCheckCounts = smsCheckCounts + 1;
			checkSmsTimer = setTimeout(function() {
				Sms.util.callCheckSms();
			}, sleeptimes);

		}
