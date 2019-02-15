package teamebcapp.ebc.common.global;

public class User {
    static public int UserSeq;
    static public int BcSeq;
    static public String UserId;
    static public String Password;
    static public String Name;
    static public String Company;
    static public String Position;
    static public String Duty;
    static public String Phone;
    static public String Email;
    static public String Depart;
    static public String Team;
    static public String Tel;
    static public String Fax;
    static public String Address;
    static public String access_token;

    public static void setUser(teamebcapp.ebc.user.User user) {
        UserSeq = user.UserSeq;
        BcSeq = user.BcSeq;
        UserId = user.UserId;
        Name = user.Name;
        Company = user.Company;
        Position = user.Position;
        Duty = user.Duty;
        Phone = user.Phone;
        Email = user.Email;
        Depart = user.Depart;
        Team = user.Team;
        Tel = user.Tel;
        Fax = user.Fax;
        Address = user.Address;
    }

}
