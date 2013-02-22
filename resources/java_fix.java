				BufferedInputStream is = new BufferedInputStream(new ByteArrayInputStream(message), message.length);
				try {
					com.drew.metadata.Metadata metadata = ImageMetadataReader.readMetadata(is, true);
					
					ExifSubIFDDirectory exifdir = metadata.getDirectory(ExifSubIFDDirectory.class);
					ExifSubIFDDescriptor exifdes = new ExifSubIFDDescriptor(exifdir);
					
					System.out.println(exifdes.getUserCommentDescription().toString());
					
					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
					Date date = new Date();
					
					String nodeData = new String(exifdes.getUserCommentDescription().toString()) + " "+ dateFormat.format(date);
					
					String[] nodeDataSplit = nodeData.split(" ");
					//this.server.processReceivedData(nodeDataSplit);
					//writeMessage(nodeData.getBytes());
					
	        		if(nodeDataSplit.length >= 2){
	        			Vehicle capturedVehicle = new Vehicle(nodeDataSplit[0], nodeDataSplit[1]);
	                    capturedVehicle.setImageBytes(message);
	                    this.server.processReceivedData(capturedVehicle);
	        		}
					
				} catch (ImageProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
