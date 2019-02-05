package teamebcapp.ebc.user;

public class User {
    public int UserSeq;
    public String UserId;
    public String Password;
    public String Name;
    public String Company;
    public String Position;
    public String Duty;
    public String Phone;
    public String Email;

    public User(String UserId,String Password, String Name, String Company, String Position, String Duty, String Phone, String Email){
            this.UserId = UserId;
            this.Password =Password;
            this.Name =Name;
            this.Company =Company;
            this.Position =Position;
            this.Duty =Duty;
            this.Phone =Phone;
            this.Email =Email;
        }
}