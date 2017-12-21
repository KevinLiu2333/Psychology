package com.wonders.ws.receive.getMethod.huanjingjiance;

public class IHB_Common_ServiceProxy implements com.wonders.ws.receive.getMethod.huanjingjiance.IHB_Common_Service {
  private String _endpoint = null;
  private com.wonders.ws.receive.getMethod.huanjingjiance.IHB_Common_Service iHB_Common_Service = null;
  
  public IHB_Common_ServiceProxy() {
    _initIHB_Common_ServiceProxy();
  }
  
  public IHB_Common_ServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initIHB_Common_ServiceProxy();
  }
  
  private void _initIHB_Common_ServiceProxy() {
    try {
      iHB_Common_Service = (new com.wonders.ws.receive.getMethod.huanjingjiance.HB_Common_ServiceLocator()).getBasicHttpBinding_IHB_Common_Service();
      if (iHB_Common_Service != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iHB_Common_Service)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iHB_Common_Service)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iHB_Common_Service != null)
      ((javax.xml.rpc.Stub)iHB_Common_Service)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.wonders.ws.receive.getMethod.huanjingjiance.IHB_Common_Service getIHB_Common_Service() {
    if (iHB_Common_Service == null)
      _initIHB_Common_ServiceProxy();
    return iHB_Common_Service;
  }
  
  public void doWork() throws java.rmi.RemoteException{
    if (iHB_Common_Service == null)
      _initIHB_Common_ServiceProxy();
    iHB_Common_Service.doWork();
  }
  
  public java.lang.String getStationsInfo(java.lang.String pkey, java.lang.String returntype) throws java.rmi.RemoteException{
    if (iHB_Common_Service == null)
      _initIHB_Common_ServiceProxy();
    return iHB_Common_Service.getStationsInfo(pkey, returntype);
  }
  
  public java.lang.String getMonitorInfo(java.lang.String pkey, java.lang.String datatype, java.lang.String frequency, java.lang.String stationid, java.lang.String pDate, java.lang.String returntype) throws java.rmi.RemoteException{
    if (iHB_Common_Service == null)
      _initIHB_Common_ServiceProxy();
    return iHB_Common_Service.getMonitorInfo(pkey, datatype, frequency, stationid, pDate, returntype);
  }
  
  
}