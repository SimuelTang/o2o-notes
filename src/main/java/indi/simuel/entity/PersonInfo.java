package indi.simuel.entity;

import java.util.Date;

/**
 * @ClassName PersonInfo
 * @Author txm
 * @Date 2021/1/20 10:23
 * @Version 1.0
 */
public class PersonInfo {
    private Long userId;
    private String username;
    private String profileImg;
    private String userEmail;
    private String userGender;
    // whether have access to the shop system (in db bool will be interpreted as 0 or 1)
    private Integer enableStatus;
    // 1: customer 2: merchant 3: administrator
    private Integer userType;
    // when the user had been created
    private Date createTime;
    // the latest time user info updated
    private Date lastEditTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}
