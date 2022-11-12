package com.example.classroom.file;

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


public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder>{
    private Context context;
    private List<FileData> list;

    public FileAdapter(Context context, List<FileData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.file_item_layout,parent,false);
        return new FileViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        holder.FileName.setText(list.get(holder.getAbsoluteAdapterPosition()).getPdfTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, PDFVIEWActivity.class);
                intent.putExtra("pdfUrl",list.get(holder.getAbsoluteAdapterPosition()).getPdfUrl());
                context.startActivity(intent);
            }
        });
        holder.FileDownload.setOnClickListener(new View.OnClickListener() {
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

    public class FileViewHolder extends RecyclerView.ViewHolder {
        private TextView FileName;
        private ImageView FileDownload;
        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            FileName=itemView.findViewById(R.id.FileName);
            FileDownload=itemView.findViewById(R.id.FileDownload);
        }
    }
}
