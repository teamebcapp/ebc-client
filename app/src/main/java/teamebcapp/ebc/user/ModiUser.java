package teamebcapp.ebc.user;

import java.util.HashMap;

public class ModiUser {
    public int UserSeq;
    public final String UserId;
    public final String Password;
    public final String Name;
    public final String Company;
    public final String Position;
    public final String Duty;
    public final String Phone;
    public final String Email;
    public String Depart;
    public String Team;
    public String Tel;
    public String Fax;
    public String Address;


    public ModiUser(HashMap<String, Object> parameters) {
        this.UserId = (String) parameters.get("UserId");
        this.Password = (String) parameters.get("Password");
        this.Name = (String) parameters.get("Name");
        this.Company = (String) parameters.get("Company");
        this.Position = (String) parameters.get("Position");
        this.Duty = (String) parameters.get("Duty");
        this.Phone = (String) parameters.get("Phone");
        this.Email = (String) parameters.get("Email");


        this.Depart=(String) parameters.get("Depart");
        this.Team=(String) parameters.get("Team");
        this.Tel=(String) parameters.get("Tel");
        this.Fax=(String) parameters.get("Fax");
        this.Address=(String) parameters.get("Address");

    }

}