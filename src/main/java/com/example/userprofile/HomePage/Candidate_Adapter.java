package com.example.userprofile.HomePage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.userprofile.Data.Candidate_details;
import com.example.userprofile.*;
import java.util.List;

public class Candidate_Adapter extends RecyclerView.Adapter<Candidate_Adapter.ViewHolder> {

    private HomePageInteractor MainActivityInteractor;
    private Context mContext;
    private List<Candidate_details> candidate_details;
    private Candidate_Adapter.OnItemClickListener mOnItemClickListener;
    private Bitmap defaultImage;

    public Candidate_Adapter(Context mContext, List<Candidate_details> womenList, Candidate_Adapter.OnItemClickListener mOnItemClickListener) {
        MainActivityInteractor = new HomePageInteractor();
        this.mContext = mContext;
        this.candidate_details = womenList;
        this.mOnItemClickListener = mOnItemClickListener;
        defaultImage = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.default_image);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.anc_list_view, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.bindData(candidate_details.get(i));
    }



    @Override
    public int getItemCount() {
        return candidate_details.size();
    }

    public void swapDataList(List<Candidate_details> womenList) {
        this.candidate_details = womenList;
    }

    public interface OnItemClickListener {
        void onItemClick(String uniqueId, ImageView profileImage);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewDetails,textViewcontact,textviewAddress, incompleteVisitlabel;
        ImageView accept,decline;
        ConstraintLayout constraintLayout;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textview_name);
            textViewDetails = itemView.findViewById(R.id.textview_details);
            textViewcontact = itemView.findViewById(R.id.textview_contact);
            textviewAddress = itemView.findViewById(R.id.textview_address);
            accept = itemView.findViewById(R.id.accept_button);
            decline = itemView.findViewById(R.id.decline_button);
            imageView = itemView.findViewById(R.id.imageview_photo);
            cardView = itemView.findViewById(R.id.card_view);
        }

        private void bindData(final Candidate_details listModel) {
            if (listModel != null) {
                textViewName.setText(listModel.getTitle()+". "+listModel.getFirst_name()+" "+listModel.getLast_name());
                String temp = listModel.getDob();
                String dob = temp.substring(0,10);
                textViewDetails.setText("DOB : "+dob);
                textViewcontact.setText(" Age : "+listModel.getAge()+" Cell : "+listModel.getCell());
                textviewAddress.setText(" City : "+listModel.getCity()+" State : "+listModel.getState()+" Country : "+listModel.getCountry());
                String status = listModel.getStatus();
              if(status == null || status.length() == 0) {
                  accept.setImageResource(R.drawable.acc);
                  decline.setImageResource(R.drawable.dec);
              }
              else if (status.equalsIgnoreCase("Accept")) {
                      accept.setImageResource(R.drawable.accept);
                      decline.setImageResource(R.drawable.dec);
                  }
              else if (status.equalsIgnoreCase("decline")) {
                      accept.setImageResource(R.drawable.acc);
                      decline.setImageResource(R.drawable.decline);
                  }

                String url = listModel.getUrl();
                Glide.with(mContext)
                        .load(url).placeholder(R.drawable.default_image).into(imageView);
            }

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int val = Integer.parseInt(listModel.getId());
                    MainActivityInteractor.UpdateStatus(val,"Accept");
                    accept.setImageResource(R.drawable.accept);
                    decline.setImageResource(R.drawable.dec);
                }
            });

            decline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int val1 = Integer.parseInt(listModel.getId());
                    MainActivityInteractor.UpdateStatus(val1,"decline");
                    accept.setImageResource(R.drawable.acc);
                    decline.setImageResource(R.drawable.decline);
                }
            });

        }
    }

}
