package com.example.classroom.Upload;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.example.classroom.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class UploadActivity extends AppCompatActivity {
    private  Button addPdf;
    private EditText pdfTitle;
    private Button goAhead;
    private TextView dynamicText;
    private final int REQ = 1;
    private Uri pdfData;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private ProgressDialog pd;
    private String pdfName,title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Upload");
        databaseReference = FirebaseDatabase.getInstance().getReference("Submission");
        storageReference = FirebaseStorage.getInstance().getReference();

        pd = new ProgressDialog(this);

        addPdf = findViewById(R.id.Selectbtn);
        pdfTitle = (EditText) findViewById(R.id.addPostDescription);
        goAhead = (Button) findViewById(R.id.uploadfilebtn);
        dynamicText = findViewById(R.id.txtView);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        addPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        goAhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = pdfTitle.getText().toString();
                if (title.isEmpty()) {
                    pdfTitle.setError("Empty Name");
                    pdfTitle.requestFocus();
                } else if (pdfData == null) {
                    Toast.makeText(UploadActivity.this, "Please select a PDF before Upload", Toast.LENGTH_SHORT).show();
                } else {
                    uploadPdf();
                }
            }
        });
    }

    private void uploadPdf() {
        pd.setTitle("Please Wait");
        pd.setMessage("We're Uploading...");
        pd.show();
        StorageReference reference = storageReference.child("Submission" + pdfName + "-" + System.currentTimeMillis() + ".pdf");
        reference.putFile(pdfData)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete()) ;
                        Uri uri = uriTask.getResult();
                        uploadData(String.valueOf(uri));

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(UploadActivity.this, "oops!! Something Went Wrong, Please try again in few seconds : |", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadData(String downloadUrl) {
        String uniqueKey = databaseReference.child("Submission").push().getKey();
        HashMap data = new HashMap();
        data.put("pdfTitle", title);
        data.put("pdfUrl", downloadUrl);
        databaseReference.child("Submission").child(uniqueKey).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss();
                Toast.makeText(UploadActivity.this, "PDF Uploaded Successfully : )", Toast.LENGTH_SHORT).show();
                pdfTitle.setText("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UploadActivity.this, "Failed to Upload : (", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select PDF File"), REQ);
    }

    @SuppressLint("Range")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ && resultCode == RESULT_OK) {
            pdfData = data.getData();
            if (pdfData.toString().startsWith("content://")) {
                Cursor cursor = null;
                try {
                    cursor = UploadActivity.this.getContentResolver().query(pdfData, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        pdfName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (pdfData.toString().startsWith("file://")) {
                pdfName = new File(pdfData.toString()).getName();
            }

            dynamicText.setText(pdfName);
        }

    }
}


