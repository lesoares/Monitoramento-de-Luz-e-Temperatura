#include <Javino.h>


int LM35 = A4;
int LDR = A0;
int led = 13;

double temperature = 0;
int light =0;
Javino javino;

void setup() {
  pinMode(LM35, INPUT);
  pinMode(led, OUTPUT);
  pinMode(LDR, INPUT);
  Serial.begin(9600);

}

void loop() {
  String msg = "";
  if(javino.availablemsg()){
   msg = javino.getmsg();

   if(msg = "dados"){
    digitalWrite(led, HIGH);
    temperature = (analogRead(LM35) * 0.488759);
    delay(1000);

    String temp ="";
    temp+= temperature;
       
    light = analogRead(LDR);

    javino.sendmsg(temp +" "+ String(light));
 
    digitalWrite(led, LOW);
    }
    
  }
 
}
