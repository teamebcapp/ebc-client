package teamebcapp.ebc.BusinessCard;

public class BusinessCard {
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
    public String ownerUserId;
    public int ownerBcSeq;

    public BusinessCard(String owneruserid,int ownerbcseq){
        this.ownerUserId=owneruserid;
        this.ownerBcSeq=ownerbcseq;
    }

    public BusinessCard(String UserId, String Name, String Company, String Position, String Duty, String Phone, String Email,
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
}
