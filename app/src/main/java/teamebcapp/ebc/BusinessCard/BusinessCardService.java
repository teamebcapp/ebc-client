package teamebcapp.ebc.BusinessCard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface BusinessCardService {
    //명함수정
    @PUT("bc")
    Call<BusinessCard> PutBC(
            @Query("bcSeq")int bcSeq);

    //명함등록
    @POST("bc")
    Call<BusinessCard> PostBC(
            @Body BusinessCard user);

    //사용자 명함리스트조회
    //id입력시 가지고있는 명함 조회가능
    @GET("bcs")
    Call<List<BusinessCard>> GetUserBCs(
            @Query("userid") String userid);

    //명함조회
    @GET("bc")
    Call<BusinessCard>ListUserBC(
            @Query("bcSeq") int bcSeq);
}
