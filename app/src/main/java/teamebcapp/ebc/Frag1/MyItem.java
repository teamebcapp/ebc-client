package teamebcapp.ebc.Frag1;

import android.graphics.drawable.Drawable;

public class MyItem {

    public int BcSeq;
    public String UserId;
    public String Name;
    public String Company;
    public String Position;
    public String Duty;
    public String Phone;
    public String Email;
    public String Depart;
    public String Team;
    public String Tel;
    public String Fax;
    public String Address;
    public Drawable icon;
    public int OwnerBcSeq;
    public int OwnerSeq;

    public Drawable getIcon() {
        return icon;
    }

    public void setUserId(String userid) {
        UserId = userid;
    }
    public void setOwnerBcSeq(int ownerbcseq){
        OwnerBcSeq=ownerbcseq;
    }
    public void setOwnerSeq(int ownerseq){
        OwnerSeq=ownerseq;
    }
    public void setBcSeq(int seq) {
        BcSeq = seq;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public void setCompany(String com) { Company = com; }

    public void setPosition(String position) {
        Position = position;
    }

    public void setDuty(String duty) {
        Duty = duty;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setEmail(String mail) {
        Email = mail;
    }

    public void setDepart(String depart) {
        Depart = depart;
    }

    public void setTeam(String team) {
        Team = team;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getUserId() {
        return this.UserId;
    }

    public String getPhone() {
        return this.Phone;
    }

    public String getName() {
        return this.Name;
    }

    public String getCompany() {
        return this.Company;
    }

    public String getPosition() {
        return this.Position;
    }

    public int getBcSeq() {
        return this.BcSeq;
    }

    public String getDuty() {
        return this.Duty;
    }

    public String getEmail() {
        return this.Email;
    }

    public String getDepart() {
        return this.Depart;
    }

    public String getTeam() {
        return this.Team;
    }

    public String getTel() {
        return this.Tel;
    }

    public String getFax() {
        return this.Fax;
    }

    public String getAddress() {
        return this.Address;
    }

    public int getOwnerBcSeq(){ return this.OwnerBcSeq;}
    public int getOwnerSeq(){return this.OwnerSeq;}
    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
