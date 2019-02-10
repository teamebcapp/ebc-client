package teamebcapp.ebc.user;

import javax.xml.transform.Result;

public class User {
    public int UserSeq;
    public int bcSeq;
    public String UserId;
    public String Password;
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

    public String ResultMessage;
    public String ResultCode;
    public String ResultCount;

    public User(String ResultMessage, String ResultCode, String ResultCount) {
        this.ResultMessage = ResultMessage;
        this.ResultCode = ResultCode;
        this.ResultCount = ResultCount;
    }

    //for login
    public User(String UserId, String Password, String Name, String Company, String Position, String Duty, String Phone, String Email,
                String Depart, String Team, String Tel, String Fax, String Address) {
        this.UserId = UserId;
        this.Password = Password;
        this.Name = Name;
        this.Company = Company;
        this.Position = Position;
        this.Duty = Duty;
        this.Phone = Phone;
        this.Email = Email;
        this.Depart = Depart;
        this.Team = Team;
        this.Tel = Tel;
        this.Fax = Fax;
        this.Address = Address;

    }

    //for creating user
    public User(String UserId, String Name, String Company, String Position, String Duty, String Phone, String Email,
                String Depart, String Team, String Tel, String Fax, String Address) {
        this.UserId = UserId;
        this.Name = Name;
        this.Company = Company;
        this.Position = Position;
        this.Duty = Duty;
        this.Phone = Phone;
        this.Email = Email;
        this.Depart = Depart;
        this.Team = Team;
        this.Tel = Tel;
        this.Fax = Fax;
        this.Address = Address;
    }

    //retrieve BC
    public User(int bcSeq) {
        this.bcSeq = bcSeq;
    }

    //BC list to get bcSeq
    public User(String UserId) {
        this.UserId = UserId;
    }
}