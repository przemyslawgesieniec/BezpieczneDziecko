#include "WiFiEsp.h"
#include <SPI.h>
#include <MFRC522.h>

#define SS_PIN 3
#define RST_PIN 2
MFRC522 rfid(SS_PIN, RST_PIN);
MFRC522::MIFARE_Key key;

char ssid[] = "DESKTOP_WIFI";      // your network SSID (name)
char pass[] = "przemek123";        // your network password
int status = WL_IDLE_STATUS;     // the Wifi radio's status

int pushButton = 8;

char server[] = "192.168.137.1";

WiFiEspClient client;

void setup()
{
  SPI.begin();
  rfid.PCD_Init();

  pinMode(pushButton, INPUT);
  Serial.begin(9600); // initialize serial for ESP module
  Serial1.begin(115200); // initialize ESP module
  WiFi.init(&Serial1);

  if (WiFi.status() == WL_NO_SHIELD) {
    Serial.println("WiFi shield not present");
    while (true);
  }
  while ( status != WL_CONNECTED) {
    Serial.print("Attempting to connect to WPA SSID: ");
    Serial.println(ssid);
    status = WiFi.begin(ssid, pass);
  }

  Serial.println("You're connected to the network");
  printWifiStatus();
  Serial.println();
  Serial.println("Starting connection to server...");

}

void loop(){
  if (rfid.PICC_IsNewCardPresent() && rfid.PICC_ReadCardSerial()){
   
    String rfidValue = getRfidValue();

    if (client.connect(server, 8090)) {
      Serial.println("Connected to server");
      client.println("GET /get/"+rfidValue+" HTTP/1.1");
      client.println("Host: 192.168.137.1:8090");
      client.println("Connection: close");
      client.println();
      client.stop();
      Serial.println("message is sent");
    }
    delay(5000);
  }
}

void printWifiStatus()
{
  Serial.print("SSID: ");
  Serial.println(WiFi.SSID());

  IPAddress ip = WiFi.localIP();
  Serial.print("IP Address: ");
  Serial.println(ip);

  // print the received signal strength
  long rssi = WiFi.RSSI();
  Serial.print("Signal strength (RSSI):");
  Serial.print(rssi);
  Serial.println(" dBm");
}

String getRfidValue() {

  String value = "";
  value += rfid.uid.uidByte[0], HEX;
  value += ":";
  value += rfid.uid.uidByte[1], HEX;
  value += ":";
  value += rfid.uid.uidByte[2], HEX;
  value += ":";
  value += rfid.uid.uidByte[3], HEX;

  Serial.println(value);
  rfid.PICC_HaltA();
  rfid.PCD_StopCrypto1();
  return value;
}

