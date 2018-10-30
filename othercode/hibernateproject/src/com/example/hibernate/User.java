package com.example.hibernate;
import java.util.Date;  

public class User {  
    private String id;  
    private String username;  
    private String password;  
    private Date createTime;  
    private Date expireTime;  
      
    public String getId() {  
        return id;  
    }  
    public void setId(String id) {  
        this.id = id;  
    }  
    public String getUsername() {  
        return username;  
    }  
    public void setUsername(String userName) {  
        this.username = userName;  
    }  
    public String getPassword() {  
        return password;  
    }  
    public void setPassword(String password) {  
        this.password = password;  
    }  
    public Date getCreateTime() {  
        return createTime;  
    }  
    public void setCreateTime(Date createTime) {  
        this.createTime = createTime;  
    }  
    public Date getExpireTime() {  
        return expireTime;  
    }  
    public void setExpireTime(Date expireTime) {  
        this.expireTime = expireTime;  
    }  
}  
