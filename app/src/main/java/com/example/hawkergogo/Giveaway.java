package com.example.hawkergogo;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Giveaway extends AppCompatActivity{
    static String staticText;
    public static void setText(String text) {
        staticText = text;
    }

    RecyclerView foodTitleRecycler;
    RecyclerView.LayoutManager foodTitleRecyclerViewLayoutManager;
    LinearLayoutManager horizontalLayout;
    TitleRecyclerViewAdapter titleRecyclerViewAdapter;
    ArrayList<FoodTitleItem> foodTitleItemSource;

    private boolean edit;

    private int id;

    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;
    private Uri downloadUrl;
    private ImageView imageView;
    FirebaseStorage storage;
    StorageReference storageRef;

    String imageName;

    int [] foodImages = {R.drawable.food_caifan, R.drawable.food_chickenrice, R.drawable.food_fishballnood, R.drawable.food_nasilemat, R.drawable.food_rotiplate};

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST  && resultCode == RESULT_OK  && data != null && data.getData() != null) {
            // Get the Uri of data
            filePath = data.getData();
            try {
                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }

            catch (IOException e) {
                e.printStackTrace();
            }
        }
        uploadImage();
    }

    private void uploadImage()
    {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference

            StorageReference ref = storageRef.child("images/" + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                          @Override
                                          public void onSuccess(Uri uri) {
                                              downloadUrl = uri;
                                              progressDialog.dismiss();
                                          }
                                      });
                                    System.out.println("******");

//                                    downloadUrl = taskSnapshot.
//                                    System.out.println("******");
//                                    progressDialog.dismiss();
//                                    Toast.makeText(Giveaway.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            // Error, Image not uploaded
                            progressDialog.dismiss();Toast.makeText(Giveaway.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage("Uploaded " + (int)progress + "%");
                                }
                            });
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giveaway);

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        imageView = findViewById(R.id.icCamera);
        LinearLayout openCamera = (LinearLayout) findViewById(R.id.cameraContainer);
        TextView selectedTitle = findViewById(R.id.titlePlaceholder);
        EditText portionInput = (EditText) findViewById(R.id.portionInput);
        TextView openLocationName = (TextView) findViewById(R.id.locationNameInput);
        EditText descriptionInput = (EditText) findViewById(R.id.descriptionInput);
        EditText timeInput = (EditText) findViewById(R.id.timeInput);
        Button buttonAdd = (Button) findViewById(R.id.addListing);

        if(getIntent().hasExtra("repeatOrder")){
            Listing item = (Listing) getIntent().getSerializableExtra("repeatOrder");
            setImagePrefill(item.getImage());
            downloadUrl = Uri.parse(item.getImage());
            selectedTitle.setText(item.getTitle());
            portionInput.setText(String.valueOf(item.getPortions()));
            openLocationName.setText(item.getLocation());
            descriptionInput.setText(item.getDescription());
            timeInput.setText(item.getTime());
        }
        else if (getIntent().hasExtra("editOrder")) {
            edit = true;
            buttonAdd.setText("Save");
            // get the Serializable data model class with the details in it
            Listing item = (Listing) getIntent().getSerializableExtra("editOrder");
            id = item.getId();
            setImagePrefill(item.getImage());
            selectedTitle.setText(item.getTitle());
            portionInput.setText(String.valueOf(item.getPortions()));
            openLocationName.setText(item.getLocation());
            descriptionInput.setText(item.getDescription());
            timeInput.setText(item.getTime());
        }

        // =========== CAMERA INPUT () ===========
        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);

                startActivityForResult(
                        Intent.createChooser(
                                intent,
                                "Select image"),
                        PICK_IMAGE_REQUEST);
//                try{
//                    Intent cameraIntent = new Intent();
//                    cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivity(cameraIntent);
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//
//                setImagePrefill(R.drawable.kunyah_rendang);
            }
        });

        // =========== TITLE INPUT ===========
        RelativeLayout titleContainer = findViewById(R.id.titleContainer);
        selectedTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog foodDialog = new Dialog(view.getRootView().getContext());
                foodDialog.setContentView(R.layout.foodtitle_options);

                foodTitleRecycler = (RecyclerView) foodDialog.findViewById(R.id.foodRecycler);

                addItemsToFoodTitleArrayList();
                titleRecyclerViewAdapter = new TitleRecyclerViewAdapter(foodTitleItemSource);

                horizontalLayout = new LinearLayoutManager(foodDialog.getContext());
                horizontalLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
                foodTitleRecycler.setLayoutManager(horizontalLayout);

                foodTitleRecycler.setAdapter(titleRecyclerViewAdapter);

                foodDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                foodDialog.show();

                Button dialog_close = foodDialog.findViewById(R.id.close);
                dialog_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        foodDialog.dismiss();
                    }
                });

                Button closeButton = foodDialog.findViewById(R.id.cancelButton);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        foodDialog.dismiss();
                    }
                });

                Button addButton = foodDialog.findViewById(R.id.addButton);
                EditText newTitle = foodDialog.findViewById(R.id.descriptionInput);
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView titleInput = (TextView) findViewById(R.id.titlePlaceholder);
                        if(!newTitle.getText().toString().equals("")){
                            titleInput.setText(newTitle.getText().toString());
                        } else {
                            titleInput.setText(staticText);
                        }
                        foodDialog.dismiss();
                    }
                });
            }
        });

        // =========== LOCATION INPUT () ===========
        LinearLayout openLocation = (LinearLayout) findViewById(R.id.locationContainer);
        openLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLocationName.setText("SMU School of Economics");
            }
        });

        // =========== TIME INPUT ===========
        //  initiate the edit text
        timeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Giveaway.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        int hour = selectedHour;
                        int minutes = minute;
                        String timeSet = "";
                        if (hour > 12) {
                            hour -= 12;
                            timeSet = "PM";
                        } else if (hour == 0) {
                            hour += 12;
                            timeSet = "AM";
                        } else if (hour == 12) {
                            timeSet = "PM";
                        } else {
                            timeSet = "AM";
                        }

                        String min = "";
                        if (minutes < 10)
                            min = "0" + minutes;
                        else
                            min = String.valueOf(minutes);

                        // Append in a StringBuilder
                        String setTime = new StringBuilder().append(hour).append(':')
                                .append(min).append(" ").append(timeSet).toString();
                        timeInput.setText(setTime);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.show();
            }
        });

        // =========== SEND DATA ===========

        Button addButton = (Button) findViewById(R.id.addListing);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Send function
                // Adding data
                String url = "http://100.24.242.101:3000/listings";
                Map<String, String> params = new HashMap();
                params.put("title", selectedTitle.getText().toString());
                params.put("portionremaining", portionInput.getText().toString());
                params.put("location", openLocationName.getText().toString());
                params.put("picture", downloadUrl.toString());
                params.put("description", descriptionInput.getText().toString());
                params.put("endtime", timeInput.getText().toString());
                params.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                if (edit == true){
                    params.put("id", String.valueOf(id));
                    JSONObject parameters = new JSONObject(params);
                    JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.PUT, url, parameters, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Giveaway.super.finish();
                            Giveaway.super.onRestart();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    queue.add(jsonRequest);
                    edit = false;
                } else {
                    JSONObject parameters = new JSONObject(params);
                    JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Giveaway.super.finish();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    queue.add(jsonRequest);
                }


//                Lisiting newListing = new Lisiting(R.drawable.food_caifan, "Cai Fan", Integer.getInteger(portionInput.getText().toString()),
//                        openLocationName.toString(), descriptionInput.getText().toString(), timeInput.getText().toString());
//
//                Giveaway.super.finish();
            }
        });

    }



    public void addItemsToFoodTitleArrayList() {
        String [] foodTitleArray = getResources().getStringArray(R.array.food_name);

        foodTitleItemSource = new ArrayList<>();
        for (int i = 0; i < foodTitleArray.length; i++) {
            foodTitleItemSource.add(new FoodTitleItem(foodTitleArray[i], foodImages[i]));
        }
//        foodTitleItemSource.add(new FoodTitleItem("Add Item", R.drawable.ic_add_button));
    }

    public void setImagePrefill(String imgName){
        Glide.with(this).load(imgName).into(imageView);
//        LinearLayout openCamera = (LinearLayout) findViewById(R.id.cameraContainer);
//
//        openCamera.removeAllViewsInLayout();
//        float scale = getResources().getDisplayMetrics().density;
//        int dpAsPixels = (int) (10*scale + 0.5f);
//        RelativeLayout rootLayout = new RelativeLayout(Giveaway.this);
//        rootLayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        params.addRule(RelativeLayout.CENTER_IN_PARENT);
//
//        //to retrieve image in res/drawable and set image in ImageView
//        ImageView ivOne = new ImageView(Giveaway.this);
//        ivOne.setImageResource(imgName);
//        ivOne.setPadding(dpAsPixels, dpAsPixels, dpAsPixels, dpAsPixels);
//        ivOne.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        ivOne.setLayoutParams(params);
//        openCamera.addView(ivOne);
    }

    public void goBack(View view) {
        super.finish();
    }


}