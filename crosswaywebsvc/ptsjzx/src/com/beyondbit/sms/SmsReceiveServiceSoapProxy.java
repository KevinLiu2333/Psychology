package com.beyondbit.sms;

public class SmsReceiveServiceSoapProxy implements com.beyondbit.sms.SmsReceiveServiceSoap {
  private String _endpoint = null;
  private com.beyondbit.sms.SmsReceiveServiceSoap smsReceiveServiceSoap = null;
  
  public SmsReceiveServiceSoapProxy() {
    _initSmsReceiveServiceSoapProxy();
  }
  
  public SmsReceiveServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initSmsReceiveServiceSoapProxy();
  }
  
  private void _initSmsReceiveServiceSoapProxy() {
    try {
      smsReceiveServiceSoap = (new com.beyondbit.sms.SmsReceiveServiceLocator()).getSmsReceiveServiceSoap();
      if (smsReceiveServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)smsReceiveServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)smsReceiveServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (smsReceiveServiceSoap != null)
      ((javax.xml.rpc.Stub)smsReceiveServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.beyondbit.sms.SmsReceiveServiceSoap getSmsReceiveServiceSoap() {
    if (smsReceiveServiceSoap == null)
      _initSmsReceiveServiceSoapProxy();
    return smsReceiveServiceSoap;
  }
  
  public java.lang.String access(java.lang.String requestXml) throws java.rmi.RemoteException{
    if (smsReceiveServiceSoap == null)
      _initSmsReceiveServiceSoapProxy();
    return smsReceiveServiceSoap.access(requestXml);
  }
  
  public java.lang.String manage(java.lang.String requestXml) throws java.rmi.RemoteException{
    if (smsReceiveServiceSoap == null)
      _initSmsReceiveServiceSoapProxy();
    return smsReceiveServiceSoap.manage(requestXml);
  }
  
  
}