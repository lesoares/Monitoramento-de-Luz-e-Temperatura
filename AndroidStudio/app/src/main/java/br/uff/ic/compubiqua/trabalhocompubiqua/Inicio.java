package br.uff.ic.compubiqua.trabalhocompubiqua;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Inicio extends AppCompatActivity {

    Button bt_ajudante, bt_emergencia;
    ImageButton ib_notificacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        bt_ajudante = (Button) findViewById(R.id.bt_ajudante);
        bt_emergencia = (Button) findViewById(R.id.bt_emergencia);
        ib_notificacao = (ImageButton) findViewById(R.id.ib_notificacao);

        bt_ajudante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Inicio.this,CadastroAjudante.class);
                startActivity(i);
            }
        });

        bt_emergencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Inicio.this,Emergencia.class);
                startActivity(i);
            }
        });

        ib_notificacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Inicio.this,Notificacao.class);
                startActivity(i);
            }
        });
    }
}
