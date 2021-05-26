package com.inhatc.vaccinationcenter;

import java.util.HashMap;
import java.util.Map;

public class UserInfo {
    public String strId;
    public String strPassword;
    public String strAge;
    public String strInfection;
    public String strInoculation;

    public UserInfo() {

    }

    public UserInfo(String id, String password, String age, String infection, String inoculation) {
        this.strId = id;
        this.strPassword = password;
        this.strAge = age;
        this.strInfection = infection;
        this.strInoculation = inoculation;
    }

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public String getStrAge() {
        return strAge;
    }

    public void setStrAge(String strAge) {
        this.strAge = strAge;
    }

    public String getStrInfection() {
        return strInfection;
    }

    public void setStrInfection(String strInfection) {
        this.strInfection = strInfection;
    }

    public String getStrInoculation() {
        return strInoculation;
    }

    public void setStrInoculation(String strInoculation) {
        this.strInoculation = strInoculation;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", strId);
        result.put("password", strPassword);
        result.put("age", strAge);
        result.put("infection", strInfection);
        result.put("inoculation", strInoculation);
        return result;
    }
}
