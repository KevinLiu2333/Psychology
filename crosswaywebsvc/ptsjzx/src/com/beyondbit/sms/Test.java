package com.beyondbit.sms;

import java.rmi.RemoteException;

public class Test {
	public static void main(String[] args) throws Exception { 
		
				sendSms();
				
	}
	public static void sendSms(){
		SmsReceiveServiceSoapProxy service = new SmsReceiveServiceSoapProxy();
		try {
			service.manage(getXml());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static String getXml() {
		String xml = "<sms:Request xmlns:req=\"http://www.beyondbit.com/sms/sao/domains/request600001\" xmlns:sms=\"http://www.beyondbit.com/sms\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
				+ "<sms:Head><sms:TrCode>600001</sms:TrCode><sms:ApplicationCode>ptsjzx</sms:ApplicationCode><sms:ApplicationPassword>123456</sms:ApplicationPassword><sms:UserUid>__sao__</sms:UserUid>"
				+ "<sms:UserPassword>App1234</sms:UserPassword><sms:ClientTxSeq></sms:ClientTxSeq><sms:MacCode></sms:MacCode></sms:Head><sms:Body xsi:type=\"req:RequestBody600001\"><req:SenderAccount>kewei</req:SenderAccount>"
				+ "<req:ReceiverAccount></req:ReceiverAccount><req:ReceiverMobile>18616236930</req:ReceiverMobile><req:Group>0</req:Group><req:Signer></req:Signer><req:Priority>5</req:Priority><req:Level>L</req:Level>"
				+ "<req:SMSContent>先生！您好！普陀区政府向您发来问候，普陀短信已调通，普陀人民欢迎您！（测试短信）</req:SMSContent><req:Encrypt>0</req:Encrypt><req:SMSBatchNo></req:SMSBatchNo><req:SaveAsSMSCenter>0</req:SaveAsSMSCenter></sms:Body></sms:Request>";
		return xml;
	}

}
