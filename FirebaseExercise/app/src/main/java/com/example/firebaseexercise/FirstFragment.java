package com.example.firebaseexercise;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirstFragment extends Fragment {

    private static final String TAG = "FirstFrag";
    private EditText ftext;
    private EditText ltext;
    private int count = 3;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        String empList = "";

        View fragmentView =inflater.inflate(R.layout.fragment_first, container, false);
        TextView tv = (TextView)fragmentView.findViewById(R.id.textview_first);

        ftext = (EditText)fragmentView.findViewById(R.id.firstNameField);
        ltext = (EditText)fragmentView.findViewById(R.id.lastNameField);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("employees");

        //Employee emp = new Employee("Abby", "Cas");
        //myRef.child("id4").setValue(emp);
        //myRef.setValue("Hello,orld!");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Employee empl;
                int counter = 0;
                tv.setText("");
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    empl= (Employee)ds.getValue(Employee.class);
                    tv.append(" " + empl.getFirstName() + " " + empl.getLastName()  + "\n");
                    counter++;
                }

                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class)  +" poptarts  ";
                //tv.setText(value);

                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        return fragmentView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firName = ftext.getText().toString();
                String laName = ltext.getText().toString();

                if (!firName.equals("") && !laName.equals("")) {
                    count++;


                    Employee emp = new Employee(laName, firName);

                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("employees");
                    myRef.child(Integer.toString(count)).setValue(emp);
                }
                //text.setText("GRUMBLE");

                //Toast toast = Toast.makeText(getContext(), firName, Toast.LENGTH_LONG);
                //toast.show();

                //NavHostFragment.findNavController(FirstFragment.this)
                  //      .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
}