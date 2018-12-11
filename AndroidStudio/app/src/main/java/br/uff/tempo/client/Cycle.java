package br.uff.tempo.client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import SddlOpenSpliceTopics.MessageMetaHolder;

public class Cycle implements Runnable {

	private EmbeddedClient client = new EmbeddedClient();

	public void run() {
		BufferedReader StrR = null;
		try {
			StrR = new BufferedReader(new FileReader("dados.txt"));
			String Str;
			while((Str = StrR.readLine())!= null){
				this.sendData(Str);
				Thread.sleep(1000);
			}
			StrR.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	private void sendData(String data) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String mensagem = timestamp.getTime() + data; 
		this.client.sendMessage(mensagem);
	}

}
