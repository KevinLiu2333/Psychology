/**
 * GetBuildingInfoServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.wonders.ws.receive.getMethod.building;

public class GetBuildingInfoServiceLocator extends org.apache.axis.client.Service implements com.wonders.ws.receive.getMethod.building.GetBuildingInfoService {

    public GetBuildingInfoServiceLocator() {
    }


    public GetBuildingInfoServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public GetBuildingInfoServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for GetBuildingInfoServiceSoap
    private java.lang.String GetBuildingInfoServiceSoap_address = "http://31.1.104.37/ptly/webservice/GetBuildingInfoService.asmx";

    public java.lang.String getGetBuildingInfoServiceSoapAddress() {
        return GetBuildingInfoServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String GetBuildingInfoServiceSoapWSDDServiceName = "GetBuildingInfoServiceSoap";

    public java.lang.String getGetBuildingInfoServiceSoapWSDDServiceName() {
        return GetBuildingInfoServiceSoapWSDDServiceName;
    }

    public void setGetBuildingInfoServiceSoapWSDDServiceName(java.lang.String name) {
        GetBuildingInfoServiceSoapWSDDServiceName = name;
    }

    public com.wonders.ws.receive.getMethod.building.GetBuildingInfoServiceSoap getGetBuildingInfoServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(GetBuildingInfoServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getGetBuildingInfoServiceSoap(endpoint);
    }

    public com.wonders.ws.receive.getMethod.building.GetBuildingInfoServiceSoap getGetBuildingInfoServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.wonders.ws.receive.getMethod.building.GetBuildingInfoServiceSoapStub _stub = new com.wonders.ws.receive.getMethod.building.GetBuildingInfoServiceSoapStub(portAddress, this);
            _stub.setPortName(getGetBuildingInfoServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setGetBuildingInfoServiceSoapEndpointAddress(java.lang.String address) {
        GetBuildingInfoServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.wonders.ws.receive.getMethod.building.GetBuildingInfoServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.wonders.ws.receive.getMethod.building.GetBuildingInfoServiceSoapStub _stub = new com.wonders.ws.receive.getMethod.building.GetBuildingInfoServiceSoapStub(new java.net.URL(GetBuildingInfoServiceSoap_address), this);
                _stub.setPortName(getGetBuildingInfoServiceSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("GetBuildingInfoServiceSoap".equals(inputPortName)) {
            return getGetBuildingInfoServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "GetBuildingInfoService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "GetBuildingInfoServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("GetBuildingInfoServiceSoap".equals(portName)) {
            setGetBuildingInfoServiceSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
