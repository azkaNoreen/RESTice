package restice.azka.noreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import restice.azka.noreen.apitemplate.RetroResponse;
import restice.azka.noreen.apitemplate.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText name,password,repassword,email,status;
    Button signup;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSharedPref();


        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        status=findViewById(R.id.status);
        repassword=findViewById(R.id.repassword);
        progressBar=findViewById(R.id.progressBar2);

        signup=findViewById(R.id.signup);
        progressBar.setVisibility(View.GONE);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=name.getText().toString();
                String passwor=password.getText().toString();
                String repasswor=repassword.getText().toString();
                String emai=email.getText().toString();
                String statu=status.getText().toString();

                if(passwor.equals("")){
                    if(statu.equals("")){
                    if(emai.equals("")){
                if(username.equals("")){
                if(passwor.equals(repasswor)){
                    putPrefernceValues(username,passwor);
                    progressBar.setVisibility(View.VISIBLE);
                    RetrofitClient retrofitClient= new RetrofitClient();
                    Call<RetroResponse> studentCall= retrofitClient.getStudentService().addStudent(username,
                            passwor,emai,statu);

                    studentCall.enqueue(new Callback<RetroResponse>() {
                        @Override
                        public void onResponse(Call<RetroResponse> call, Response<RetroResponse> response) {
                            progressBar.setVisibility(View.GONE);
                            if(response!=null){
                                RetroResponse dataList= response.body();
                                if(dataList != null){
                                    String resp=dataList.getResponse();
                                    if(resp.contains("successfully")){
                                        Toast.makeText(MainActivity.this,"Signed up", Toast.LENGTH_SHORT).show();
                                        finish();

                                    }else{
                                        Toast.makeText(MainActivity.this, "Account already exists", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<RetroResponse> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();

                        }
                    });
                }else{
                    Toast.makeText(MainActivity.this, "Please enter same password and repassword", Toast.LENGTH_SHORT).show();
                }
                }else{
                    Toast.makeText(MainActivity.this, "Please enter Name", Toast.LENGTH_SHORT).show();
                }}else{
                    Toast.makeText(MainActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                }}else{
                    Toast.makeText(MainActivity.this, "Please enter Status", Toast.LENGTH_SHORT).show();
                }}else{
                    Toast.makeText(MainActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }
    public void initSharedPref(){
        sharedPreferences=getSharedPreferences("practicePreferences",MODE_PRIVATE);
        sharedPreferencesEditor=sharedPreferences.edit();

    }
    public void putPrefernceValues(String n, String p){
        sharedPreferencesEditor.putString("Name",n);
        sharedPreferencesEditor.putString("Password",p);
        sharedPreferencesEditor.apply();
    }}
