/**
 * XmlService_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.wonders.ws.receive.getMethod.wangting;

public class XmlService_ServiceLocator extends org.apache.axis.client.Service implements com.wonders.ws.receive.getMethod.wangting.XmlService_Service {

    public XmlService_ServiceLocator() {
    }


    public XmlService_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public XmlService_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for GXmlServiceImplPort
    private java.lang.String GXmlServiceImplPort_address = "http://31.1.104.14/share/ws/xmlService";

    public java.lang.String getGXmlServiceImplPortAddress() {
        return GXmlServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String GXmlServiceImplPortWSDDServiceName = "GXmlServiceImplPort";

    public java.lang.String getGXmlServiceImplPortWSDDServiceName() {
        return GXmlServiceImplPortWSDDServiceName;
    }

    public void setGXmlServiceImplPortWSDDServiceName(java.lang.String name) {
        GXmlServiceImplPortWSDDServiceName = name;
    }

    public com.wonders.ws.receive.getMethod.wangting.XmlService_PortType getGXmlServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(GXmlServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getGXmlServiceImplPort(endpoint);
    }

    public com.wonders.ws.receive.getMethod.wangting.XmlService_PortType getGXmlServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.wonders.ws.receive.getMethod.wangting.XmlServiceSoapBindingStub _stub = new com.wonders.ws.receive.getMethod.wangting.XmlServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getGXmlServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setGXmlServiceImplPortEndpointAddress(java.lang.String address) {
        GXmlServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.wonders.ws.receive.getMethod.wangting.XmlService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.wonders.ws.receive.getMethod.wangting.XmlServiceSoapBindingStub _stub = new com.wonders.ws.receive.getMethod.wangting.XmlServiceSoapBindingStub(new java.net.URL(GXmlServiceImplPort_address), this);
                _stub.setPortName(getGXmlServiceImplPortWSDDServiceName());
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
        if ("GXmlServiceImplPort".equals(inputPortName)) {
            return getGXmlServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.xml.wondersgroup.com/", "XmlService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.xml.wondersgroup.com/", "GXmlServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("GXmlServiceImplPort".equals(portName)) {
            setGXmlServiceImplPortEndpointAddress(address);
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
