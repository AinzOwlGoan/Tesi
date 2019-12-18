if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
	try { 
	
		byte []b = val.getBytes();
		String inputLine=input.readLine();

		if(inputLine.contains("AB")) 
			{
			    int []zoneDivision = new int[inputLine.length()/2];
			    [...]
			
				for(int i = 3; i<inputLine.length(); i++) {
							
				      char valoreI = inputLine.charAt(i);
					if((valoreI == '0') && (ch == false)) {
								inizio = i;
								ch = true;
								}
							if((valoreI == 'A')&& (ch == true)) {
								fine = i;
								break;
							}
							
						}
						
						for(int i = inizio; i<fine; i+=2) {
							
							char partial1 = inputLine.charAt(i);
							char partial2 = inputLine.charAt(i+1);
							
							// vado ad identificare la zona
							String zone = Character.toString(partial1) + 										              Character.toString(partial2);
							
							
							zoneDivision[j] = Integer.parseInt(zone);
							
							j++;
					} 
					[...]
					
				}

				
			} catch (Exception e) {
				[...]
			}
		}