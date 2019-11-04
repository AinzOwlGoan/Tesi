#include <SPI.h>
#include "PN532_SPI.h"
#include "PN532.h"
#include "NfcAdapter.h"

PN532_SPI interface(SPI, 10); 
NfcAdapter nfc = NfcAdapter(interface); 
 
void setup(void) 
{
    Serial.begin(9600); // start serial comm
    Serial.println("NDEF Reader");
    nfc.begin(); // begin NFC comm
}

void loop(void) 
{
  String s = Serial.readString();
  if(s.equals("conversion"))
    Serial.println("\nAbbonamento riconosciuto\n");
  else
    Serial.println("\nScan an NFC tag\n");
  if (nfc.tagPresent()) 
  {
      NfcTag tag = nfc.read(); 
      if(tag.hasNdefMessage())
      {
        NdefMessage message = tag.getNdefMessage();
        for(int i=0;i<message.getRecordCount();i++)
        {
          NdefRecord record = message.getRecord(i);
          int payloadLength = record.getPayloadLength();
          byte payload[payloadLength];
          record.getPayload(payload);
          Serial.write(payload,payloadLength);
        }
      }
  }
  delay(500); 
} 
