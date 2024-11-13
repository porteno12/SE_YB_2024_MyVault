package com.example.mvault;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mvault.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<SafeData> safeDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize View Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        safeDataList = new ArrayList<SafeData>();

        SafeData sf1 = new SafeData("ronny", "123", "www.youtube.com");
        SafeData sf2 = new SafeData("ruben", "12345", "www.spotify.com");
        SafeData sf3 = new SafeData("noob", "123456", "www.brawlstars.com");

        safeDataList.add(sf1);
        safeDataList.add(sf2);
        safeDataList.add(sf3);

        Adapter_SafeData adapter = new Adapter_SafeData(MainActivity.this,safeDataList);
        binding.lvData.setAdapter(adapter);
        
        
        
        binding.lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SafeData tmp = safeDataList.get(position);
                String tmpUrl = tmp.getUrl();
                openUrl(tmpUrl);
            }
        });
        
        
        binding.fabAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "no browser found...",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "No app found to open the Google Play Store", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInputDialog();
            }
        });
    }

    private void openInputDialog() {
        // Create a custom layout for the dialog
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_input, null);

        EditText unm = dialogView.findViewById(R.id.et_uname);
        EditText pss = dialogView.findViewById(R.id.et_pass);
        EditText ur = dialogView.findViewById(R.id.et_url);

        // Create AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter your details");
        builder.setView(dialogView);

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String uname = unm.getText().toString();
                String pass = pss.getText().toString();
                String url = ur.getText().toString();
                SafeData newEntry = new SafeData(uname, pass, url);
                safeDataList.add(newEntry);

                // Notify the adapter that the data has changed
                ((Adapter_SafeData) binding.lvData.getAdapter()).notifyDataSetChanged();

                Toast.makeText(MainActivity.this, "Data Saved:\n" + uname + "\n" + pass + "\n" + url, Toast.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            //no browser is installed on the device
            showSnackBar();
        }
    }

    private void showSnackBar() {
        Snackbar snackbar = Snackbar.make(binding.getRoot(), "select an option:", Snackbar.LENGTH_LONG)
                .setAction("Google Play", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openGooglePlayStore();
                    }
                });

        snackbar.setActionTextColor(getResources().getColor(android.R.color.holo_blue_light));
        snackbar.show();
    }

    private void openGooglePlayStore() {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No app found to open the Google Play Store", Toast.LENGTH_SHORT).show();
        }
    }
}