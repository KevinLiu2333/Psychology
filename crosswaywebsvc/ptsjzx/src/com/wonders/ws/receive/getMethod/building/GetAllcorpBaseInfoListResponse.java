/**
 * GetAllcorpBaseInfoListResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.wonders.ws.receive.getMethod.building;

public class GetAllcorpBaseInfoListResponse  implements java.io.Serializable {
    private com.wonders.ws.receive.getMethod.building.Response getAllcorpBaseInfoListResult;

    public GetAllcorpBaseInfoListResponse() {
    }

    public GetAllcorpBaseInfoListResponse(
           com.wonders.ws.receive.getMethod.building.Response getAllcorpBaseInfoListResult) {
           this.getAllcorpBaseInfoListResult = getAllcorpBaseInfoListResult;
    }


    /**
     * Gets the getAllcorpBaseInfoListResult value for this GetAllcorpBaseInfoListResponse.
     * 
     * @return getAllcorpBaseInfoListResult
     */
    public com.wonders.ws.receive.getMethod.building.Response getGetAllcorpBaseInfoListResult() {
        return getAllcorpBaseInfoListResult;
    }


    /**
     * Sets the getAllcorpBaseInfoListResult value for this GetAllcorpBaseInfoListResponse.
     * 
     * @param getAllcorpBaseInfoListResult
     */
    public void setGetAllcorpBaseInfoListResult(com.wonders.ws.receive.getMethod.building.Response getAllcorpBaseInfoListResult) {
        this.getAllcorpBaseInfoListResult = getAllcorpBaseInfoListResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetAllcorpBaseInfoListResponse)) return false;
        GetAllcorpBaseInfoListResponse other = (GetAllcorpBaseInfoListResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getAllcorpBaseInfoListResult==null && other.getGetAllcorpBaseInfoListResult()==null) || 
             (this.getAllcorpBaseInfoListResult!=null &&
              this.getAllcorpBaseInfoListResult.equals(other.getGetAllcorpBaseInfoListResult())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getGetAllcorpBaseInfoListResult() != null) {
            _hashCode += getGetAllcorpBaseInfoListResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetAllcorpBaseInfoListResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetAllcorpBaseInfoListResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getAllcorpBaseInfoListResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetAllcorpBaseInfoListResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Response"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
