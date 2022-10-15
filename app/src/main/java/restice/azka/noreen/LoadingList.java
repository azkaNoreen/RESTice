package restice.azka.noreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import restice.azka.noreen.apitemplate.RetrofitClient;
import restice.azka.noreen.apitemplate.Students;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingList extends AppCompatActivity {
    Toolbar toolba;
    TextView tn;
    ProgressBar pb;
    RecyclerView  rcl;
    List<Students> userss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_list);

        toolba=findViewById(R.id.toolba);
        pb=findViewById(R.id.progressBar);
        rcl=findViewById(R.id.rcl);
        Intent intent=getIntent();
        String n=intent.getStringExtra("Name");
        toolba.setTitle(n);
        setSupportActionBar(toolba); //set toolbar to act as action bar
        getAllUser();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    private void getAllUser(){
        RetrofitClient retrofitClient= new RetrofitClient();
        Call<List<Students>> userCall= retrofitClient.getStudentService().getAllStudents();
//        final List<Users> dataList;
        userCall.enqueue(new Callback<List<Students>>() {
            @Override
            public void onResponse(Call<List<Students>> call, Response<List<Students>> response) {
                if(response!=null){
                    List<Students> dataList= response.body();
                    if(dataList != null && dataList.size()>0){
                        String name= dataList.get(3).getName();
                        pb.setVisibility(View.GONE);
                        initRCL(dataList);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Students>> call, Throwable t) {

            }
        });
    }
    public void initRCL(List<Students> ls){
        RecyclerViewAdapter rva=new RecyclerViewAdapter();
        rcl.setAdapter(rva);
        rcl.setLayoutManager(new LinearLayoutManager(this));
        rva.setData(ls);
    }
}