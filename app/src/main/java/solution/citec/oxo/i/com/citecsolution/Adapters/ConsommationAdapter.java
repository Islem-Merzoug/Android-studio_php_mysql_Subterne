package solution.citec.oxo.i.com.citecsolution.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import solution.citec.oxo.i.com.citecsolution.Models.MyConsommation;
import solution.citec.oxo.i.com.citecsolution.R;

public class ConsommationAdapter extends RecyclerView.Adapter<ConsommationAdapter.ConsommationHolder> {

    ArrayList<MyConsommation> ListConsommation;
    Context context;

    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);

    public ConsommationAdapter(ArrayList<MyConsommation> listConsommation, Context context) {
        ListConsommation = listConsommation;
        this.context = context;
    }

    @NonNull
    @Override
    public ConsommationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.consommation_row, parent, false );

        return new ConsommationHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull ConsommationHolder holder, int position) {

        MyConsommation maConsommation = ListConsommation.get( position );
        if(position==0){

           holder.Month.setText( "January "+year );
        }if(position==1){
            holder.Month.setText( "February "+year );
        }if(position==2){
            holder.Month.setText( "March "+year );
        }if(position==3){
            holder.Month.setText( "April "+year );
        }if(position==4){
            holder.Month.setText( "May "+year );
        }if(position==5){
            holder.Month.setText( "June "+year );
        }if(position==6){
            holder.Month.setText( "July "+year );
        }if(position==7){
            holder.Month.setText( "August "+year );
        }if(position==8){
            holder.Month.setText( "September "+year );
        }if(position==9){
            holder.Month.setText( "October "+year );
        }if(position==10){
            holder.Month.setText( "November "+year );
        }if(position==11){

        }

        holder.Month.setText( maConsommation.getMonth()+ " " +year );

        holder.Consommation.setText(  maConsommation.getSUMConsommation() );
    }

    @Override
    public int getItemCount() {
        return ListConsommation.size();
    }


    public class ConsommationHolder extends RecyclerView.ViewHolder{


        TextView Month,Consommation;


        public ConsommationHolder(@NonNull View view) {
            super( view );
            Month=(TextView)view.findViewById( R.id.tv_month );
            Consommation=(TextView)view.findViewById( R.id.tv_consommation );
        }
    }
}
