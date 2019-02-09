package teamebcapp.ebc.common.utils;

import android.support.annotation.NonNull;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FileUploader {
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    public static RequestBody createRequestBody(@NonNull File file) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), file);
    }

    public static RequestBody createRequestBody(@NonNull String s) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), s);
    }


    public boolean fileUploade(String url, File file, Map<String, String> params) {

//        File file = new File("/storage/emulated/0/Download/Corrections 6.jpg");
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

// MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);

// add another part within the multipart request
//        RequestBody fullName =
//                RequestBody.create(MediaType.parse("multipart/form-data"), "Your Name");
        IFileService fileService = teamebcapp.ebc.Retrofit.retrofit.create(IFileService.class);

        Map<String, RequestBody> paramBody = new HashMap<>();
        Set<String> keys = params.keySet();
        for(String key : keys) {
            paramBody.put("key", this.createRequestBody(params.get(key)));
        }

        Call<Map<String, Object>> call = fileService.uploadImage(url, body, paramBody);

        call.enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
            }
            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
            }
        });

        return true;
    }
}
