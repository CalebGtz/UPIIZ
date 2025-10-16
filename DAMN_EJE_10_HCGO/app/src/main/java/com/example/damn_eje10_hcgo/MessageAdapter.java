package com.example.damn_eje10_hcgo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_SENT = 1;
    private static final int TYPE_RECEIVED = 2;

    private final ArrayList<Message> data;
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

    public MessageAdapter(ArrayList<Message> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).isFromMe() ? TYPE_SENT : TYPE_RECEIVED;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_SENT) {
            View v = inf.inflate(R.layout.item_message_sent, parent, false);
            return new SentVH(v);
        } else {
            View v = inf.inflate(R.layout.item_message_received, parent, false);
            return new ReceivedVH(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message m = data.get(position);
        String time = timeFormat.format(new Date(m.getTimestamp()));

        if (holder instanceof SentVH) {
            ((SentVH) holder).tvText.setText(m.getText());
            ((SentVH) holder).tvTime.setText(time);
        } else if (holder instanceof ReceivedVH) {
            ((ReceivedVH) holder).tvText.setText(m.getText());
            ((ReceivedVH) holder).tvTime.setText(time);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class SentVH extends RecyclerView.ViewHolder {
        TextView tvText, tvTime;
        SentVH(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tvText);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }

    static class ReceivedVH extends RecyclerView.ViewHolder {
        TextView tvText, tvTime;
        ReceivedVH(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tvText);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}
