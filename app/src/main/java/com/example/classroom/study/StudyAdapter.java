package com.example.classroom.study;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classroom.R;

import java.util.List;

public class StudyAdapter extends RecyclerView.Adapter<StudyAdapter.EbookViewHolder> {
    public StudyAdapter(Context context, List<StudyData> list) {
        this.context = context;
        this.list = list;
    }

    private Context context;
    private List<StudyData>list;

    @NonNull
    @Override
    public EbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.study_item_layout,parent,false);
        return new EbookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EbookViewHolder holder, int position) {
        holder.ebookName.setText(list.get(holder.getAbsoluteAdapterPosition()).getPdfTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,PdfViewerActivity.class);
                intent.putExtra("pdfUrl",list.get(holder.getAbsoluteAdapterPosition()).getPdfUrl());
                context.startActivity(intent);
            }
        });
        holder.ebookDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(holder.getAbsoluteAdapterPosition()).getPdfUrl()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EbookViewHolder extends RecyclerView.ViewHolder {
        private TextView ebookName;
        private ImageView ebookDownload;

        public EbookViewHolder(@NonNull View itemView) {
            super(itemView);
            ebookDownload=itemView.findViewById(R.id.ebookDownload);
            ebookName=itemView.findViewById(R.id.ebookName);
        }
    }
}
