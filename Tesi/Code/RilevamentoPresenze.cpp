   NfcTag tag = nfc.read(); 
      if(tag.hasNdefMessage())
      {[...]    
          NdefRecord record = message.getRecord(i);
          int payloadLength = record.getPayloadLength();
          byte payload[payloadLength];
          record.getPayload(payload);
          Serial.write(payload,payloadLength);
        [...]}