package teamebcapp.ebc.Frag3_BC;

import android.graphics.drawable.Drawable;

public class MyItem {

    private String contents;
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

    //result of my BC
    public MyItem(int BcSeq, String UserId, String Name, String Company, String Position, String Duty, String Phone, String Email,
                String Depart, String Team, String Tel, String Fax, String Address) {
        this.BcSeq= BcSeq;
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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

}
