package za.edu.st10112216.numap.displays;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import za.edu.st10112216.numap.activities.MapsActivity;
import za.edu.st10112216.numap.R;
import za.edu.st10112216.numap.classes.PlaceDetailsClass;

public class DisplayPlaceDetail extends AppCompatActivity {

    String name;
    PlaceDetailsClass placeDetailsClass = new PlaceDetailsClass();

    // Array and Adapter
    private ListView placeDetails;
    private TextView placeName;
    private  List<String> placeList;
    private ArrayAdapter<String> placeAdapter;

    // Firebase
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference placeRef = database.getReference("tmp");

    // Firebase
    private final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference mUserRef = mDatabase.getReference("users");
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_place_detail);

        final String[] name = new String[1];
        placeDetailsClass.setUserId(user.getUid());

        // Buttons
        ImageButton back = findViewById(R.id.displayPlace_return);
        Button fav = findViewById(R.id.btn_add_fav);

        // Initialize Array
        placeList = new ArrayList<>();

        // Array Output Resource
        placeDetails = findViewById(R.id.displayPlace_scrollview);
        placeName = findViewById(R.id.displayPlace_title);

        // Firebase Interaction Place Details
        placeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot pulledUser : snapshot.getChildren()){
                    PlaceDetailsClass placeDetailsClass  = pulledUser.getValue(PlaceDetailsClass.class);
                    assert placeDetailsClass != null;
                    placeList.add(placeDetailsClass.toString());
                    placeName.setText(placeDetailsClass.getName());
                }

                // Adapter to View User Details
                placeAdapter = new ArrayAdapter<>(DisplayPlaceDetail.this,
                        android.R.layout.simple_list_item_1, placeList);
                placeDetails.setAdapter(placeAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DisplayPlaceDetail.this, "Database Error: " + error.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        fav.setOnClickListener(v -> {
            String uid = user.getUid();

            // Firebase Interaction Place Details
            placeRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot pulledUser : snapshot.getChildren()){
                        PlaceDetailsClass placeDetailsClass  = pulledUser.getValue(PlaceDetailsClass.class);
                        assert placeDetailsClass != null;
                        name[0] = placeDetailsClass.getName();
                        Log.d("LOG", name[0]);

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(DisplayPlaceDetail.this, "Database Error: " + error.getCode(), Toast.LENGTH_SHORT).show();
                }
            });

            writeLocationDetailFull(placeDetailsClass.getUserId(), placeDetailsClass.getName());

        });

        back.setOnClickListener(view -> {
            Intent intent;
            intent = new Intent(DisplayPlaceDetail.this, MapsActivity.class);
            startActivity(intent);
            placeRef.removeValue();
            placeList.clear();

        });

    }

    // Writes Current Location to Detail Class
    public void writeLocationDetailFull(String userId, String name) {

        placeDetailsClass = new PlaceDetailsClass(userId, name);

        //mUserRef.child("users").child(userId).setValue(placeDetailsClass);
    }
}
