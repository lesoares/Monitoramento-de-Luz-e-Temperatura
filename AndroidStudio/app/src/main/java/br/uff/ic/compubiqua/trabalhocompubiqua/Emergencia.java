package br.uff.ic.compubiqua.trabalhocompubiqua;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Emergencia extends AppCompatActivity {
    private static final int MAKE_CALL_PERMISSION_REQUEST_CODE = 1;
    private Button dial;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencia);

        final Button button = findViewById(R.id.telefonar);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                if (checkPermission(Manifest.permission.CALL_PHONE)) {
                    System.out.println("cliquei");
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:+5521982191409"));
                    startActivity(callIntent);
                }

            }
        });
    }

    private boolean checkPermission(String permission) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }
}
