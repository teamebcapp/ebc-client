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

}