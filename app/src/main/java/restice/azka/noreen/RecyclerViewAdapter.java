package restice.azka.noreen;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import restice.azka.noreen.apitemplate.Students;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Students> studentArrayList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.studentitem,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Students st=studentArrayList.get(position);
        StudentViewHolder studentViewHolder= (StudentViewHolder) holder;

        studentViewHolder.studentPhone.setText("Name: "+st.getName());
        studentViewHolder.studentTextName.setText("Id: "+st.getId()+"");
        studentViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(), st.getName(), Toast.LENGTH_SHORT).show();
                Intent in=new Intent(view.getContext(), Details.class);
                in.putExtra("Name",st.getName());
                in.putExtra("Email",st.getEmail());
                in.putExtra("Status",st.getStatus());
                in.putExtra("ID",st.getId());

                view.getContext().startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }
    public void setData(List<Students> StudentArrayList){
        this.studentArrayList=StudentArrayList;
        notifyDataSetChanged();
    }
//to find views of single list xml file
    public static class StudentViewHolder extends RecyclerView.ViewHolder{

        TextView studentTextName;
        TextView studentPhone;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            studentTextName=itemView.findViewById(R.id.name);
            studentPhone=itemView.findViewById(R.id.email);

        }
    }

}
