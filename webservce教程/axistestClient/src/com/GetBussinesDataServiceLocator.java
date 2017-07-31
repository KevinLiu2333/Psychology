/**
 * GetBussinesDataServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com;

public class GetBussinesDataServiceLocator extends org.apache.axis.client.Service implements com.GetBussinesDataService {

    public GetBussinesDataServiceLocator() {
    }


    public GetBussinesDataServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public GetBussinesDataServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for XZSP_CI_JCJ_JCF_BusinessCollectService
    private java.lang.String XZSP_CI_JCJ_JCF_BusinessCollectService_address = "http://localhost:82/services/XZSP_CI_JCJ_JCF_BusinessCollectService";

    public java.lang.String getXZSP_CI_JCJ_JCF_BusinessCollectServiceAddress() {
        return XZSP_CI_JCJ_JCF_BusinessCollectService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String XZSP_CI_JCJ_JCF_BusinessCollectServiceWSDDServiceName = "XZSP_CI_JCJ_JCF_BusinessCollectService";

    public java.lang.String getXZSP_CI_JCJ_JCF_BusinessCollectServiceWSDDServiceName() {
        return XZSP_CI_JCJ_JCF_BusinessCollectServiceWSDDServiceName;
    }

    public void setXZSP_CI_JCJ_JCF_BusinessCollectServiceWSDDServiceName(java.lang.String name) {
        XZSP_CI_JCJ_JCF_BusinessCollectServiceWSDDServiceName = name;
    }

    public com.GetBussinesData getXZSP_CI_JCJ_JCF_BusinessCollectService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(XZSP_CI_JCJ_JCF_BusinessCollectService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getXZSP_CI_JCJ_JCF_BusinessCollectService(endpoint);
    }

    public com.GetBussinesData getXZSP_CI_JCJ_JCF_BusinessCollectService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.XZSP_CI_JCJ_JCF_BusinessCollectServiceSoapBindingStub _stub = new com.XZSP_CI_JCJ_JCF_BusinessCollectServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getXZSP_CI_JCJ_JCF_BusinessCollectServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setXZSP_CI_JCJ_JCF_BusinessCollectServiceEndpointAddress(java.lang.String address) {
        XZSP_CI_JCJ_JCF_BusinessCollectService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.GetBussinesData.class.isAssignableFrom(serviceEndpointInterface)) {
                com.XZSP_CI_JCJ_JCF_BusinessCollectServiceSoapBindingStub _stub = new com.XZSP_CI_JCJ_JCF_BusinessCollectServiceSoapBindingStub(new java.net.URL(XZSP_CI_JCJ_JCF_BusinessCollectService_address), this);
                _stub.setPortName(getXZSP_CI_JCJ_JCF_BusinessCollectServiceWSDDServiceName());
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
        if ("XZSP_CI_JCJ_JCF_BusinessCollectService".equals(inputPortName)) {
            return getXZSP_CI_JCJ_JCF_BusinessCollectService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost:82/services/XZSP_CI_JCJ_JCF_BusinessCollectService", "GetBussinesDataService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://localhost:82/services/XZSP_CI_JCJ_JCF_BusinessCollectService", "XZSP_CI_JCJ_JCF_BusinessCollectService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("XZSP_CI_JCJ_JCF_BusinessCollectService".equals(portName)) {
            setXZSP_CI_JCJ_JCF_BusinessCollectServiceEndpointAddress(address);
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
