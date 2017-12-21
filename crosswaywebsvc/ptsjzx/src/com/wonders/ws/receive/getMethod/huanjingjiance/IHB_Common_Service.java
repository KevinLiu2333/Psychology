/**
 * IHB_Common_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.wonders.ws.receive.getMethod.huanjingjiance;

public interface IHB_Common_Service extends java.rmi.Remote {
    public void doWork() throws java.rmi.RemoteException;
    public java.lang.String getStationsInfo(java.lang.String pkey, java.lang.String returntype) throws java.rmi.RemoteException;
    public java.lang.String getMonitorInfo(java.lang.String pkey, java.lang.String datatype, java.lang.String frequency, java.lang.String stationid, java.lang.String pDate, java.lang.String returntype) throws java.rmi.RemoteException;
}
