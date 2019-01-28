#include "WiFiEsp.h"

char ssid[] = "DESKTOP_WIFI";      // your network SSID (name)
char pass[] = "przemek123";        // your network password
int status = WL_IDLE_STATUS;     // the Wifi radio's status

int pushButton = 8;

char server[] = "192.168.137.1";

WiFiEspClient client;

void setup()
{
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

void loop()
{
    if (client.connect(server, 8090)) {
      Serial.println("Connected to server");
      client.println("GET /get HTTP/1.1");
      client.println("Host: 192.168.137.1:8090");
      client.println("Connection: close");
      client.println();
      client.stop();
      Serial.println("message is sent");
    }
    delay(5000);
}


void printWifiStatus()
{
  // print the SSID of the network you're attached to
  Serial.print("SSID: ");
  Serial.println(WiFi.SSID());

  // print your WiFi shield's IP address
  IPAddress ip = WiFi.localIP();
  Serial.print("IP Address: ");
  Serial.println(ip);

  // print the received signal strength
  long rssi = WiFi.RSSI();
  Serial.print("Signal strength (RSSI):");
  Serial.print(rssi);
  Serial.println(" dBm");
}
