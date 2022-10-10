package restice.azka.noreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import restice.azka.noreen.apitemplate.RetrofitClient;
import restice.azka.noreen.apitemplate.Students;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    Button login;
    EditText username,password;
    SharedPreferences sharedPreferences;
    ProgressBar progressBar;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initSharedPref();

        username=findViewById(R.id.name);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        progressBar=findViewById(R.id.progressBar3);
        signup=findViewById(R.id.signup);

        progressBar.setVisibility(View.GONE);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,MainActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String un=username.getText().toString();
                String pass=password.getText().toString();
                String[] preferences=getPrefernceValues();

                if(!(un.equals(""))){
                    if(!(pass.equals(""))){
                        Toast.makeText(Login.this, un, Toast.LENGTH_SHORT).show();

                        RetrofitClient retrofitClient= new RetrofitClient();
                        Call<List<Students>> studentCall= retrofitClient.getStudentService().getStudent(un);
                        studentCall.enqueue(new Callback<List<Students>>() {
                            @Override
                            public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
                                if(response!=null){
                                    List<Students> dataList= response.body();
                                    if(dataList != null && dataList.size()>0){
                                        String passa = dataList.get(0).getPassword();

                                        if((pass.equals(passa))){
                                            Toast.makeText(Login.this, "Successfull", Toast.LENGTH_SHORT).show();
                                            Intent intent=new Intent(Login.this,LoadingList.class);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(Login.this, "Invalid Credentials,please enter correct", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                            @Override
                            public void onFailure(Call<List<Students>> call, Throwable t) {
                                Toast.makeText(Login.this, "Invalid Credentials,please enter correct", Toast.LENGTH_SHORT).show();
                            }
                        });

            }else{
                    Toast.makeText(Login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
            } else{
                Toast.makeText(Login.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
            }}
        });
    }
    public void initSharedPref(){
        sharedPreferences=getSharedPreferences("practicePreferences",MODE_PRIVATE);

    }
    public String[] getPrefernceValues(){
        String np=sharedPreferences.getString("Name","no Name");
        String nr=sharedPreferences.getString("Password","no Password");

        String[] signupValues={np,nr};
        return signupValues;
    }
}