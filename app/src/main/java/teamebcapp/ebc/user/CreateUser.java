package teamebcapp.ebc.user;

import java.util.List;

public class CreateUser
{
    public String ID;
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
    final List<Result> Result=null;
    public class Result {
        String ResultMessage;
        String ResultCode;
        String ResultCount;
    }
    public CreateUser(String ID, String Name, String Company, String Position, String Duty, String Phone, String Email,
                      String Depart,String Team, String Tel, String Fax, String Address){
        this.ID=ID;
        this.Name =Name;
        this.Company =Company;
        this.Position =Position;
        this.Duty =Duty;
        this.Phone =Phone;
        this.Email =Email;
        this.Depart =Depart;
        this.Team=Team;
        this.Tel=Tel;
        this.Fax=Fax;
        this.Address=Address;

    }
}
