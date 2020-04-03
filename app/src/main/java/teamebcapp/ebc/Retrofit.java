package teamebcapp.ebc;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

    public static final retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
            .baseUrl("http://1.230.221.92:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
