package com.inhatc.vaccinationcenter;

import com.fasterxml.jackson.annotation.*;
import java.time.OffsetDateTime;

public class Datum {
    private String address;
    private String centerName;
    private CenterType centerType;
    private OffsetDateTime createdAt;
    private String facilityName;
    private long id;
    private String lat;
    private String lng;
    private String org;
    private String phoneNumber;
    private String sido;
    private String sigungu;
    private OffsetDateTime updatedAt;
    private String zipCode;

    @JsonProperty("address")
    public String getAddress() { return address; }
    @JsonProperty("address")
    public void setAddress(String value) { this.address = value; }

    @JsonProperty("centerName")
    public String getCenterName() { return centerName; }
    @JsonProperty("centerName")
    public void setCenterName(String value) { this.centerName = value; }

    @JsonProperty("centerType")
    public CenterType getCenterType() { return centerType; }
    @JsonProperty("centerType")
    public void setCenterType(CenterType value) { this.centerType = value; }

    @JsonProperty("createdAt")
    public OffsetDateTime getCreatedAt() { return createdAt; }
    @JsonProperty("createdAt")
    public void setCreatedAt(OffsetDateTime value) { this.createdAt = value; }

    @JsonProperty("facilityName")
    public String getFacilityName() { return facilityName; }
    @JsonProperty("facilityName")
    public void setFacilityName(String value) { this.facilityName = value; }

    @JsonProperty("id")
    public long getID() { return id; }
    @JsonProperty("id")
    public void setID(long value) { this.id = value; }

    @JsonProperty("lat")
    public String getLat() { return lat; }
    @JsonProperty("lat")
    public void setLat(String value) { this.lat = value; }

    @JsonProperty("lng")
    public String getLng() { return lng; }
    @JsonProperty("lng")
    public void setLng(String value) { this.lng = value; }

    @JsonProperty("org")
    public String getOrg() { return org; }
    @JsonProperty("org")
    public void setOrg(String value) { this.org = value; }

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() { return phoneNumber; }
    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String value) { this.phoneNumber = value; }

    @JsonProperty("sido")
    public String getSido() { return sido; }
    @JsonProperty("sido")
    public void setSido(String value) { this.sido = value; }

    @JsonProperty("sigungu")
    public String getSigungu() { return sigungu; }
    @JsonProperty("sigungu")
    public void setSigungu(String value) { this.sigungu = value; }

    @JsonProperty("updatedAt")
    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    @JsonProperty("updatedAt")
    public void setUpdatedAt(OffsetDateTime value) { this.updatedAt = value; }

    @JsonProperty("zipCode")
    public String getZipCode() { return zipCode; }
    @JsonProperty("zipCode")
    public void setZipCode(String value) { this.zipCode = value; }

    public Datum(String address, String centerName, String phoneNumber, String lat, String lng){
        this.address = address;
        this.centerName = centerName;
        this.phoneNumber = phoneNumber;
        this.lat = lat;
        this.lng = lng;
    }
}
