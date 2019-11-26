if(nfc.tagPresent())
  {    
    NdefMessage message = NdefMessage();
    message.addUriRecord(s);
      
    bool success = nfc.write(message);
    if(success)
    {
      [...]
      
    }else{
      [...]
    }
  }
  } else
    Serial.println("\nTag non inserito\n");
}