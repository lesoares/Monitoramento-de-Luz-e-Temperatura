package br.uff.tempo.server;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.uff.ic.compubiqua.trabalhocompubiqua.Inicio;
import br.uff.ic.compubiqua.trabalhocompubiqua.Login;
import br.uff.ic.compubiqua.trabalhocompubiqua.R;
import br.uff.tempo.dispatcher.*;
import lac.cnclib.sddl.message.ApplicationMessage;
import lac.cnclib.sddl.serialization.Serialization;
import lac.cnet.sddl.objects.ApplicationObject;
import lac.cnet.sddl.objects.Message;
import lac.cnet.sddl.objects.PrivateMessage;
import lac.cnet.sddl.udi.core.SddlLayer;
import lac.cnet.sddl.udi.core.UniversalDDSLayerFactory;
import lac.cnet.sddl.udi.core.listener.UDIDataReaderListener;

public class HelloCoreServer extends AppCompatActivity implements UDIDataReaderListener<ApplicationObject> {
	SddlLayer core;
	int counter;
	//private Ambient ambient = new Ambient();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);
		System.out.println("oi");
		Logger.getLogger("").setLevel(Level.OFF);

		new HelloCoreServer();
	}

	public HelloCoreServer() {
		core = UniversalDDSLayerFactory.getInstance();
		core.createParticipant(UniversalDDSLayerFactory.CNET_DOMAIN);


		core.createPublisher();
		core.createSubscriber();

		Object receiveMessageTopic = core.createTopic(Message.class, Message.class.getSimpleName());
		core.createDataReader(this, receiveMessageTopic);

		Object toMobileNodeTopic = core.createTopic(PrivateMessage.class, PrivateMessage.class.getSimpleName());
		core.createDataWriter(toMobileNodeTopic);

		counter = 0;
		System.out.println("=== Server Started (Listening) ===");
		synchronized (this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onNewData(ApplicationObject topicSample) {
		Message message = (Message) topicSample;
		String data = (String) Serialization.fromJavaByteStream(message.getContent());
		System.out.println(data);


		PrivateMessage privateMessage = new PrivateMessage();
		privateMessage.setGatewayId(message.getGatewayId());
		privateMessage.setNodeId(message.getSenderId());


		String alerta = detectaAlerta(data);
		if(alerta != null && !alerta.isEmpty()){
			System.out.println(alerta);
	//		message.setContent(alerta.getBytes());

			ApplicationMessage  appMsg = new ApplicationMessage();
			appMsg.setContentObject(alerta);
			privateMessage.setMessage(Serialization.toProtocolMessage(appMsg));
			core.writeTopic(PrivateMessage.class.getSimpleName(), privateMessage);

      //      LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(Inicio.);
            String action = "ActionID"; /* Action Identification as String*/
            Intent intent = new Intent(action);
     //       lbm.sendBroadcast(intent);

		}
	}

	public String detectaAlerta(String data){
		String[] separa = data.split(":");
		String cabecalho = separa[0] + ":" +separa[1];
		String alerta = null;

		String alertaTemp = detectaAlteracaoTemperatura(separa[2]);
		if(!alertaTemp.isEmpty()){
			alerta = cabecalho + alertaTemp;
		}
		if(detectaQueda(separa[3])){
			if(alerta == null || alerta.isEmpty())
				alerta = cabecalho;
			alerta += ":QUEDA";
		}

		return alerta;
	}

	private Boolean detectaQueda(String data){
		return data.equals("QUEDA");
	}

	private String detectaAlteracaoTemperatura(String data){
		String alerta = "";
		Double temperatura = Double.valueOf(data);
		if(temperatura < 36){
			alerta += ":Hipotermia";
		}
		else if (temperatura > 37.5 && temperatura < 39.5){
			alerta += ":Febre";
		}
		else if (temperatura > 39.5 &&  temperatura < 41){
			alerta += ":Febre alta";
		}
		else if(temperatura > 41){
			alerta += ":Hipertermia";
		}
		return alerta;
	}

	private void mountingDevice() {
		// It receives a XML.
		// do nothing.
	}

}