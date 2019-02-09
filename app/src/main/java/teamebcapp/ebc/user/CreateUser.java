package teamebcapp.ebc.user;

public class CreateUser
{
    public String UserID;
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

    public CreateUser(String ResultMessage, String ResultCode,String ResultCount){
        this.ResultMessage=ResultMessage;
        this.ResultCode=ResultCode;
        this.ResultCount=ResultCount;
    }
    public CreateUser(String UserID, String Name, String Company, String Position, String Duty, String Phone, String Email,
                      String Depart,String Team, String Tel, String Fax, String Address){
        this.UserID=UserID;
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
