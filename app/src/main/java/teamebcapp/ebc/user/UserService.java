package teamebcapp.ebc.user;

import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface UserService {
    @FormUrlEncoded
    @POST("user")
    Call<User> PostUser(
            @Field("UserId") String UserId,
            @Field ("Password") String Password);

    @GET("user")
    Call<User> GetUser(
            @Query("userid") String UserId,
            @Query("password") String Password);

}