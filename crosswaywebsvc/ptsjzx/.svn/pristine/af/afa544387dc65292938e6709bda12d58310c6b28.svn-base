/**
 * HB_Common_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.wonders.ws.receive.getMethod.huanjingjiance;

public class HB_Common_ServiceLocator extends org.apache.axis.client.Service implements com.wonders.ws.receive.getMethod.huanjingjiance.HB_Common_Service {

    public HB_Common_ServiceLocator() {
    }


    public HB_Common_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public HB_Common_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_IHB_Common_Service
    //private java.lang.String BasicHttpBinding_IHB_Common_Service_address = "http://180.153.146.100:8282/HB_Common_Service.svc";
    private java.lang.String BasicHttpBinding_IHB_Common_Service_address = "http://31.1.208.172:8087/HB_Common_Service.svc";

    public java.lang.String getBasicHttpBinding_IHB_Common_ServiceAddress() {
        return BasicHttpBinding_IHB_Common_Service_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpBinding_IHB_Common_ServiceWSDDServiceName = "BasicHttpBinding_IHB_Common_Service";

    public java.lang.String getBasicHttpBinding_IHB_Common_ServiceWSDDServiceName() {
        return BasicHttpBinding_IHB_Common_ServiceWSDDServiceName;
    }

    public void setBasicHttpBinding_IHB_Common_ServiceWSDDServiceName(java.lang.String name) {
        BasicHttpBinding_IHB_Common_ServiceWSDDServiceName = name;
    }

    public com.wonders.ws.receive.getMethod.huanjingjiance.IHB_Common_Service getBasicHttpBinding_IHB_Common_Service() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_IHB_Common_Service_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_IHB_Common_Service(endpoint);
    }

    public com.wonders.ws.receive.getMethod.huanjingjiance.IHB_Common_Service getBasicHttpBinding_IHB_Common_Service(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.wonders.ws.receive.getMethod.huanjingjiance.BasicHttpBinding_IHB_Common_ServiceStub _stub = new com.wonders.ws.receive.getMethod.huanjingjiance.BasicHttpBinding_IHB_Common_ServiceStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_IHB_Common_ServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_IHB_Common_ServiceEndpointAddress(java.lang.String address) {
        BasicHttpBinding_IHB_Common_Service_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.wonders.ws.receive.getMethod.huanjingjiance.IHB_Common_Service.class.isAssignableFrom(serviceEndpointInterface)) {
                com.wonders.ws.receive.getMethod.huanjingjiance.BasicHttpBinding_IHB_Common_ServiceStub _stub = new com.wonders.ws.receive.getMethod.huanjingjiance.BasicHttpBinding_IHB_Common_ServiceStub(new java.net.URL(BasicHttpBinding_IHB_Common_Service_address), this);
                _stub.setPortName(getBasicHttpBinding_IHB_Common_ServiceWSDDServiceName());
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
        if ("BasicHttpBinding_IHB_Common_Service".equals(inputPortName)) {
            return getBasicHttpBinding_IHB_Common_Service();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "HB_Common_Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpBinding_IHB_Common_Service"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BasicHttpBinding_IHB_Common_Service".equals(portName)) {
            setBasicHttpBinding_IHB_Common_ServiceEndpointAddress(address);
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
