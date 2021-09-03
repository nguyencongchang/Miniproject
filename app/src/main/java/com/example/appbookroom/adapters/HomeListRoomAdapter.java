package com.example.appbookroom.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbookroom.R;
import com.example.appbookroom.entity.Room;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeListRoomAdapter extends RecyclerView.Adapter<HomeListRoomAdapter.HomeListRoomViewHolder> {
    private Context context;
    private List<Room> mList;

    public void setData(List<Room> list){
        this.mList = list;
        notifyDataSetChanged();
    }
    @Override
    public HomeListRoomViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info_room,parent,false);
        context = parent.getContext();
        return new HomeListRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeListRoomAdapter.HomeListRoomViewHolder holder, int position) {
        Room room = mList.get(position);
        holder.tvNameRoom.setText(room.getName());
        holder.tvCapacity.setText("Max: "+room.getCapacity()+" người");
        Picasso.get().load(room.getImage()).into(holder.imageViewRoom);
    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        return 0;
    }

    public class HomeListRoomViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameRoom;
        private TextView tvCapacity;
        private ImageView imageViewRoom;

        public HomeListRoomViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            initUI();
        }

        private void initUI() {
            tvCapacity = itemView.findViewById(R.id.numberPeople);
            tvNameRoom = itemView.findViewById(R.id.textTittleRoom);
            imageViewRoom = itemView.findViewById(R.id.imageRoom);
        }
    }
}
