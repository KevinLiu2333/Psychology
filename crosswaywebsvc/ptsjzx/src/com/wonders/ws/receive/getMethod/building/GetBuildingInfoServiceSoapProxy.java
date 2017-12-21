package com.wonders.ws.receive.getMethod.building;

public class GetBuildingInfoServiceSoapProxy implements com.wonders.ws.receive.getMethod.building.GetBuildingInfoServiceSoap {
  private String _endpoint = null;
  private com.wonders.ws.receive.getMethod.building.GetBuildingInfoServiceSoap getBuildingInfoServiceSoap = null;
  
  public GetBuildingInfoServiceSoapProxy() {
    _initGetBuildingInfoServiceSoapProxy();
  }
  
  public GetBuildingInfoServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initGetBuildingInfoServiceSoapProxy();
  }
  
  private void _initGetBuildingInfoServiceSoapProxy() {
    try {
      getBuildingInfoServiceSoap = (new com.wonders.ws.receive.getMethod.building.GetBuildingInfoServiceLocator()).getGetBuildingInfoServiceSoap();
      if (getBuildingInfoServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)getBuildingInfoServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)getBuildingInfoServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (getBuildingInfoServiceSoap != null)
      ((javax.xml.rpc.Stub)getBuildingInfoServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.wonders.ws.receive.getMethod.building.GetBuildingInfoServiceSoap getGetBuildingInfoServiceSoap() {
    if (getBuildingInfoServiceSoap == null)
      _initGetBuildingInfoServiceSoapProxy();
    return getBuildingInfoServiceSoap;
  }
  
  public com.wonders.ws.receive.getMethod.building.Response getBuildingInfoByBuildingName(java.lang.String keyValue, java.lang.String buildingName, java.lang.String type) throws java.rmi.RemoteException{
    if (getBuildingInfoServiceSoap == null)
      _initGetBuildingInfoServiceSoapProxy();
    return getBuildingInfoServiceSoap.getBuildingInfoByBuildingName(keyValue, buildingName, type);
  }
  
  public com.wonders.ws.receive.getMethod.building.Response getAllBuildingInfoList(java.lang.String keyValue, java.lang.String type) throws java.rmi.RemoteException{
    if (getBuildingInfoServiceSoap == null)
      _initGetBuildingInfoServiceSoapProxy();
    return getBuildingInfoServiceSoap.getAllBuildingInfoList(keyValue, type);
  }
  
  public com.wonders.ws.receive.getMethod.building.Response getAllcorpBaseInfoList(java.lang.String keyValue, java.lang.String CORPNAME, java.lang.String buildingName, java.lang.String type) throws java.rmi.RemoteException{
    if (getBuildingInfoServiceSoap == null)
      _initGetBuildingInfoServiceSoapProxy();
    return getBuildingInfoServiceSoap.getAllcorpBaseInfoList(keyValue, CORPNAME, buildingName, type);
  }
  
  
}