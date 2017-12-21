/**
 * GetAllcorpBaseInfoList.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.wonders.ws.receive.getMethod.building;

public class GetAllcorpBaseInfoList  implements java.io.Serializable {
    private java.lang.String keyValue;

    private java.lang.String CORPNAME;

    private java.lang.String buildingName;

    private java.lang.String type;

    public GetAllcorpBaseInfoList() {
    }

    public GetAllcorpBaseInfoList(
           java.lang.String keyValue,
           java.lang.String CORPNAME,
           java.lang.String buildingName,
           java.lang.String type) {
           this.keyValue = keyValue;
           this.CORPNAME = CORPNAME;
           this.buildingName = buildingName;
           this.type = type;
    }


    /**
     * Gets the keyValue value for this GetAllcorpBaseInfoList.
     * 
     * @return keyValue
     */
    public java.lang.String getKeyValue() {
        return keyValue;
    }


    /**
     * Sets the keyValue value for this GetAllcorpBaseInfoList.
     * 
     * @param keyValue
     */
    public void setKeyValue(java.lang.String keyValue) {
        this.keyValue = keyValue;
    }


    /**
     * Gets the CORPNAME value for this GetAllcorpBaseInfoList.
     * 
     * @return CORPNAME
     */
    public java.lang.String getCORPNAME() {
        return CORPNAME;
    }


    /**
     * Sets the CORPNAME value for this GetAllcorpBaseInfoList.
     * 
     * @param CORPNAME
     */
    public void setCORPNAME(java.lang.String CORPNAME) {
        this.CORPNAME = CORPNAME;
    }


    /**
     * Gets the buildingName value for this GetAllcorpBaseInfoList.
     * 
     * @return buildingName
     */
    public java.lang.String getBuildingName() {
        return buildingName;
    }


    /**
     * Sets the buildingName value for this GetAllcorpBaseInfoList.
     * 
     * @param buildingName
     */
    public void setBuildingName(java.lang.String buildingName) {
        this.buildingName = buildingName;
    }


    /**
     * Gets the type value for this GetAllcorpBaseInfoList.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this GetAllcorpBaseInfoList.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetAllcorpBaseInfoList)) return false;
        GetAllcorpBaseInfoList other = (GetAllcorpBaseInfoList) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.keyValue==null && other.getKeyValue()==null) || 
             (this.keyValue!=null &&
              this.keyValue.equals(other.getKeyValue()))) &&
            ((this.CORPNAME==null && other.getCORPNAME()==null) || 
             (this.CORPNAME!=null &&
              this.CORPNAME.equals(other.getCORPNAME()))) &&
            ((this.buildingName==null && other.getBuildingName()==null) || 
             (this.buildingName!=null &&
              this.buildingName.equals(other.getBuildingName()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType())));
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
        if (getKeyValue() != null) {
            _hashCode += getKeyValue().hashCode();
        }
        if (getCORPNAME() != null) {
            _hashCode += getCORPNAME().hashCode();
        }
        if (getBuildingName() != null) {
            _hashCode += getBuildingName().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetAllcorpBaseInfoList.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetAllcorpBaseInfoList"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keyValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "KeyValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CORPNAME");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CORPNAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buildingName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "BuildingName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
