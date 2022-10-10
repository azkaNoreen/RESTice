package restice.azka.noreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class Details extends AppCompatActivity {
    TextView n,e,g,s;
    ProgressBar pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        n=findViewById(R.id.name);
        e=findViewById(R.id.email);
        g=findViewById(R.id.gender);
        s=findViewById(R.id.status);
        pb=findViewById(R.id.progressBar3);


        Intent intent=getIntent();
        String sid=intent.getStringExtra("ID");


        RetrofitClient retrofitClient= new RetrofitClient();
        Call<List<Students>> studentCall= retrofitClient.getStudentService().getStudent(sid);
        studentCall.enqueue(new Callback<List<Students>>() {
            @Override
            public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
                if(response!=null){
                    List<Students> dataList= response.body();
                    if(dataList != null && dataList.size()>0){
                        pb.setVisibility(View.GONE);
                        String sname = dataList.get(0).getName();
                        String semail = dataList.get(0).getEmail();
                        String sstatus = dataList.get(0).getStatus();

                        n.setText("Name: "+sname);
                        e.setText("Email: "+semail);
                        g.setText("ID: "+sid);
                        s.setText("Status: "+sstatus);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Students>> call, Throwable t) {
                Toast.makeText(Details.this, "Invalid Credentials,please enter correct", Toast.LENGTH_SHORT).show();
            }
        });

    }
}