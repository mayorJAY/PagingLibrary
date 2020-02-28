package com.example.josycom.paginglibrary.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.josycom.paginglibrary.R;
import com.example.josycom.paginglibrary.model.User;

public class UserAdapter extends PagedListAdapter<User, UserAdapter.UserViewHolder> {

    public UserAdapter(){
        super(USER_COMPARATOR);
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        userViewHolder.bind(getItem(i));

    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView userName, userEmail;
        private ImageView userPic;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_name);
            userEmail = itemView.findViewById(R.id.user_email);
            userPic = itemView.findViewById(R.id.user_avatar);
        }

        void bind(User user){
            //Bind UserName to its TextView
            if (user.getFirstName() != null && user.getLastName() != null){
                userName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
            }

            //Bind User email to its TextView
            if (user.getEmail() != null){
                userEmail.setText(user.getEmail());
            }

            //Bind User avatar to ImageView
            if (user.getAvatar() != null){
                Glide.with(itemView.getContext())
                        .load(user.getAvatar())
                        .placeholder(R.drawable.loading)
                        .into(userPic);
            }
        }
    }
    private static final DiffUtil.ItemCallback<User> USER_COMPARATOR = new DiffUtil.ItemCallback<User>() {
        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.equals(newItem);
        }
    };
}