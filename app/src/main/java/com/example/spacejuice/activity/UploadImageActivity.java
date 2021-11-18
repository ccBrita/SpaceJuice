package com.example.spacejuice.activity;

<<<<<<< Updated upstream
import androidx.annotation.NonNull;
=======
import static android.content.ContentValues.TAG;

>>>>>>> Stashed changes
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
<<<<<<< Updated upstream
import android.graphics.BitmapFactory;
=======
>>>>>>> Stashed changes
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
<<<<<<< Updated upstream
import android.os.Handler;
=======
import android.provider.MediaStore;
>>>>>>> Stashed changes
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.spacejuice.Habit;
import com.example.spacejuice.MainActivity;
import com.example.spacejuice.R;
<<<<<<< Updated upstream
import com.example.spacejuice.Upload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
=======
import com.example.spacejuice.controller.HabitEventController;
>>>>>>> Stashed changes
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

<<<<<<< Updated upstream
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
=======
import java.io.IOException;
>>>>>>> Stashed changes

public class UploadImageActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;
    private Button chooseImage;
    private Button uploadImage;
    private Button backButton;
    private ImageView imageView;
    private ProgressBar progressBar;

    private Uri imageUri;
    private StorageReference storageReference;
    private DocumentReference documentReference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        chooseImage = findViewById(R.id.button_choose_image);
        uploadImage = findViewById(R.id.upload_button);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progress_bar);
        backButton = findViewById(R.id.back_button);

        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        documentReference = db.collection("Members")
                .document(MainActivity.getUser().getMemberName())
                .collection("Habits").document(getIntent().getExtras().getString("habit"));
        // use logincontroller

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });


        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            Log.d(TAG, "The start event");
            imageUri = data.getData();
<<<<<<< Updated upstream
            //Picasso.get().load(imageUri).into(imageView);
            //imageView.setImageURI(imageUri);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){
        if(imageUri != null){
            StorageReference fileReference = storageReference.child(
                    MainActivity.getUser().getMemberName()+ getIntent().getExtras().getString("habit")
                            +"."+ getFileExtension(imageUri));
            fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            }, 500);
                            Toast.makeText(UploadImageActivity.this, "Upload successful"
                                    , Toast.LENGTH_LONG).show();
                            String url = taskSnapshot.getMetadata().getReference()
                                    .getDownloadUrl().toString().trim();
                            Map<String,Object> uri = new HashMap<>();
                            uri.put("url", url);
                            uri.put("url2",imageUri.toString().trim());
                            documentReference.update(uri)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Log.d("message", "URL has been added successfully");
                                        }
                                    });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UploadImageActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                            progressBar.setProgress((int) progress);
                        }
                    });

        }else{
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
=======
            Log.d(TAG, "The mid event");
            Picasso.with(getApplicationContext()).load(imageUri).into(imageView);
            //imageView.setImageURI(imageUri);
            Log.d(TAG, "The FINISH event");
>>>>>>> Stashed changes
        }
    }

}