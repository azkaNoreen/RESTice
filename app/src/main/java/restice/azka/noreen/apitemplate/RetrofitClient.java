package restice.azka.noreen.apitemplate;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static String baseURL= "https://studenttestx.000webhostapp.com/";

    public StudentService getStudentService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(StudentService.class);
    }
}
