package com.example.elearning;

public class UserData {

    public String uname,uid,uemail,uphone,ucollege,ukey,imgurl;

    public UserData() {
    }

    public UserData(String uname, String uid, String uemail, String uphone, String ucollege, String ukey, String imgurl) {
        this.uname = uname;
        this.uid = uid;
        this.uemail = uemail;
        this.uphone = uphone;
        this.ucollege = ucollege;
        this.ukey = ukey;
        this.imgurl = imgurl;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUcollege() {
        return ucollege;
    }

    public void setUcollege(String ucollege) {
        this.ucollege = ucollege;
    }

    public String getUkey() {
        return ukey;
    }

    public void setUkey(String ukey) {
        this.ukey = ukey;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
