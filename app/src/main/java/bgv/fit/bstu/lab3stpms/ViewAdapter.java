package bgv.fit.bstu.lab3stpms;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder>{

    interface OnStateClickListener{
        void onStateClick(User user, int position);
    }
    private final OnStateClickListener onClickListener;

    private final LayoutInflater inflater;
    private final List<User> users;

    ViewAdapter(Context context, List<User> users, OnStateClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.users = users;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public ViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewAdapter.ViewHolder holder,int position) {
        User user = users.get(position);
        //holder.flagView.setImageResource(user.getFlagResource());
        holder.nameView.setText(user.name);
        holder.capitalView.setText(user.surname);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onStateClick(user, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView flagView;
        final TextView nameView, capitalView;
        ViewHolder(View view){
            super(view);
            flagView = (ImageView)view.findViewById(R.id.flag);
            nameView = (TextView) view.findViewById(R.id.name);
            capitalView = (TextView) view.findViewById(R.id.capital);
        }
    }

}
