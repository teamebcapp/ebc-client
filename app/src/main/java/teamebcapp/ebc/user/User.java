package teamebcapp.ebc.user;

public class User {
    public int UserSeq;
    public int BcSeq;
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

    public User(String UserId, String Password, String Name, String Company, String Position, String Duty, String Phone, String Email) {
        this.UserId = UserId;
        this.Password = Password;
        this.Name = Name;
        this.Company = Company;
        this.Position = Position;
        this.Duty = Duty;
        this.Phone = Phone;
        this.Email = Email;
    }

    //for creating user


    //retrieve BC
    public User(int BcSeq) {
        this.BcSeq = BcSeq;
    }


    //BC list to get bcSeq
    public User(String UserId) {
        this.UserId = UserId;
    }
}