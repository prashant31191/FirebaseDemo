package firebase_demo.fg.com.android_firebase_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import firebase_demo.fg.com.android_firebase_demo.storage.StorageMainActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DataListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Firebase database reference
        this.mDatabase = FirebaseDatabase.getInstance().getReference();

        // Init user list
        ListView list = (ListView) this.findViewById(R.id.dataList);
        Button btnStorgae = (Button) this.findViewById(R.id.btnStorgae);

        this.listAdapter = new DataListAdapter(this, R.layout.list_view_cell);
        list.setAdapter(listAdapter);

        btnStorgae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, StorageMainActivity.class));
            }
        });

        // Add listener
        mDatabase.addChildEventListener(new FirebaseEventListener(this.listAdapter));



      /*
      testing for the connnection

      DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    System.out.println("##################connected###############");
                } else {
                    System.out.println("###############not connected###############");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("###############Listener was cancelled###############");
            }
        });*/



    }
/*

    @Override
    protected void onStart() {
        super.onStart();

        // Add listener
        mDatabase.addChildEventListener(new FirebaseEventListener(this.listAdapter));
    }
*/



}
