#include <SPI.h>
#include "PN532_SPI.h"
#include "PN532.h"
#include "NfcAdapter.h"

PN532_SPI interface(SPI, 10); // create a SPI interface for the shield with the SPI CS terminal at digital pin 10

NfcAdapter nfc = NfcAdapter(interface); // create an NFC adapter object
 
void setup(void) 
{
    Serial.begin(9600); // start serial comm
    Serial.println("NDEF Reader");
    nfc.begin(); // begin NFC comm
}

void loop(void) 
{
  Serial.println("Place a formatted Mifare Classic NFC tag on the reader.");

  // parte nuova INIZIO
  String s = Serial.readString();

  if(s.indexOf("AB")>0){
   Serial.println("\nAbbonamento da mettere\n");
      Serial.println("Messaggio arrivato"+s);
      String messaggio = s;
    // parte nuova FINE
  if(nfc.tagPresent())
  {
    
    NdefMessage message = NdefMessage();
    message.addUriRecord(s);
   
    
    bool success = nfc.write(message);
    if(success)
    {
      Serial.println("Successo");
      
    }else{
      Serial.println("Message write failed.");
    }
  }
  } else
    Serial.println("\nNulla Inserito\n");
  
  delay(5000);
}
