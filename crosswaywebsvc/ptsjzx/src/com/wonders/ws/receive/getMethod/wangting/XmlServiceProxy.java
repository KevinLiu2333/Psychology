package com.wonders.ws.receive.getMethod.wangting;

public class XmlServiceProxy implements com.wonders.ws.receive.getMethod.wangting.XmlService_PortType {
  private String _endpoint = null;
  private com.wonders.ws.receive.getMethod.wangting.XmlService_PortType xmlService_PortType = null;
  
  public XmlServiceProxy() {
    _initXmlServiceProxy();
  }
  
  public XmlServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initXmlServiceProxy();
  }
  
  private void _initXmlServiceProxy() {
    try {
      xmlService_PortType = (new com.wonders.ws.receive.getMethod.wangting.XmlService_ServiceLocator()).getGXmlServiceImplPort();
      if (xmlService_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)xmlService_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)xmlService_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (xmlService_PortType != null)
      ((javax.xml.rpc.Stub)xmlService_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.wonders.ws.receive.getMethod.wangting.XmlService_PortType getXmlService_PortType() {
    if (xmlService_PortType == null)
      _initXmlServiceProxy();
    return xmlService_PortType;
  }
  
  public java.lang.String getXml(java.lang.String arg0) throws java.rmi.RemoteException{
    if (xmlService_PortType == null)
      _initXmlServiceProxy();
    return xmlService_PortType.getXml(arg0);
  }
  
  public java.lang.String getXmlList(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (xmlService_PortType == null)
      _initXmlServiceProxy();
    return xmlService_PortType.getXmlList(arg0, arg1);
  }
  
  
}