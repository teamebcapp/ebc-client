package teamebcapp.ebc.user;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserService {
    //회원가입
    @POST("user")
    Call<User> PostUser(
            @Body User user);

    //로그인
    @GET("user")
    Call<User> GetUser(
            @Query("userid") String UserId,
            @Query("password") String Password);

    //내정보조회
    @GET("user")
    Call<User> GetMyUser(
            @Query("Name") String Name,
            @Query("Company") String Company,
            @Query("Position") String Position,
            @Query("Duty") String Duty,
            @Query("Phone") String Phone,
            @Query("Email") String Email);

    //명함수정
   /* @PUT("bc")
    Call<ModiUser> PutUser(
            @Body ModiUser modiuser);
*/
    //명함등록
    @PUT("bc")
    Call<CreateUser> PutBC(
            @Body CreateUser createuser);
}