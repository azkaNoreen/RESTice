package restice.azka.noreen.apitemplate;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

////base url
//// https://studenttestx.000webhostapp.com/
//
//// new user (post)
////https://studenttestx.000webhostapp.com/new_student.php
//
//// get user by name / id (get or post)
////https://studenttestx.000webhostapp.com/get_student.php
//
//// all students
////https://studenttestx.000webhostapp.com/all_students.php
//
//// upload file
////https://studenttestx.000webhostapp.com/upload_file.php
public interface StudentService {

    @FormUrlEncoded
    @POST("new_student.php")
    Call<RetroResponse> addStudent(@Field("name") String name,
                                   @Field("password") String password,
                                   @Field("email") String email,
                                   @Field("status") String status);

    @FormUrlEncoded
    @POST("get_student.php")
    Call<List<Students>> getStudent(@Field("id") String id);

    @FormUrlEncoded
    @POST("get_student.php")
    Call<List<Students>> getStudentByName(@Field("name") String name);

    @POST("all_students.php")
    Call<List<Students>> getAllStudents();


}
