package br.uff.ic.compubiqua.trabalhocompubiqua;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import br.uff.tempo.client.Cycle;
import br.uff.tempo.server.HelloCoreServer;

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

        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(getApplicationContext());
        BroadcastReceiver mBroadcast = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent i) {
                /* Obtain the action string from the intent that we are receiving */
                String action = i.getAction();
                /* Compare this intent with our action declared earlier */

                System.out.println("OIOIOIOI \n\n***************8");
                if (action.equals("ActionID")) {
                    System.out.println("Entrou");
                    // Action matches, do something.
                }
            }
        };

        /* Second and Third: create a IntentFilter and assign this IntentFilter to the Local Broadcast */
        IntentFilter filter = new IntentFilter();
        /* Assign to this IntentFilter the action */
        filter.addAction("ActionID");
        /* Register this intent filter with this broadcast receiver */
        lbm.registerReceiver(mBroadcast, filter);
    }


}
