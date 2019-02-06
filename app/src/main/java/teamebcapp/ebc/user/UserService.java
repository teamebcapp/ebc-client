package teamebcapp.ebc.user;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @POST("user")
    Call<User> PostUser(
            @Body User user);

    @GET("user")
    Call<User> GetUser(
            @Query("userid") String UserId,
            @Query("password") String Password);

    @GET("user")
    Call<User> GetMyUser(
            @Query("UserId") String UserId,
            @Query("Password") String Password,
            @Query("Name") String Name,
            @Query("Company") String Company,
            @Query("Position") String Position,
            @Query("Duty") String Duty,
            @Query("Phone") String Phone,
            @Query("Email") String Email);


}