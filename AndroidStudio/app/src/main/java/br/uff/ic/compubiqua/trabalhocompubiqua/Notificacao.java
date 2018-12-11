package br.uff.ic.compubiqua.trabalhocompubiqua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Notificacao extends AppCompatActivity {

    ImageButton ib_maps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacao);

        ib_maps = (ImageButton) findViewById(R.id.ib_maps);

        ib_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Notificacao.this,MapsActivity.class);
                startActivity(i);
            }
        });
    }
}
