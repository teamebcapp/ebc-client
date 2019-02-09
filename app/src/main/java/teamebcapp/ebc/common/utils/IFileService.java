package teamebcapp.ebc.common.utils;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface IFileService {
    @Multipart
    @POST("/{urls}")
    Call<Map<String, Object>> uploadImage(
            @Path("urls") String urls,
            @Part MultipartBody.Part File,
            @PartMap Map<String, RequestBody> params);
}
