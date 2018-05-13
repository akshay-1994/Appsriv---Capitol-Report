package com.appsirv.admin.capitolreport_appsirvtechnologies;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class CapitolReport_Adaptor extends RecyclerView.Adapter<CapitolReport_Adaptor.ViewHolder> {

    //We define a list from the Capitol Report Data!

    private List<CapitolReport_DataModel> capitolReport_dataModels;
    private Context context;

    public CapitolReport_Adaptor(List<CapitolReport_DataModel> capitolReport_dataModels, Context context) {
        this.capitolReport_dataModels = capitolReport_dataModels;
        this.context = context;
    }

    @NonNull
    @Override
    public CapitolReport_Adaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // This method will be called when our ViewHolder will be created!
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_card_view, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CapitolReport_Adaptor.ViewHolder holder, final int position) {

        final CapitolReport_DataModel capitolReport_dataModel = capitolReport_dataModels.get(position);

        holder.title.setText(capitolReport_dataModel.getTitle());
        holder.date.setText(capitolReport_dataModel.getDate());

        // Will take care of item click!

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CapitolReport_DataModel cp_DataModels = capitolReport_dataModels.get(position);
                Intent linkIntent = new Intent(v.getContext(),LinkActivity.class);
                linkIntent.putExtra(Configurations.KEY_LINK,cp_DataModels.getLink());
                v.getContext().startActivity(linkIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return capitolReport_dataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Define the View Objects!

        public TextView title;
        public TextView date;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            // initialize the View Objects

            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            relativeLayout =itemView.findViewById(R.id.layout_items);


        }
    }
}
