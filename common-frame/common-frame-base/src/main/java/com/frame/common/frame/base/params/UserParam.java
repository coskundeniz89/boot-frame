package com.frame.common.frame.base.params;

public class UserParam extends RequestParam {

    private static final long serialVersionUID = 1L;

    private String userType;
    private String userNo;
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getUserNo() {
        return userNo;
    }
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserParam{");
        sb.append("userType='").append(userType).append('\'');
        sb.append(", userNo='").append(userNo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}