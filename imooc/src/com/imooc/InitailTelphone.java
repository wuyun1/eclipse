package com.imooc;

public class InitailTelphone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Telphone phone =  new Telphone();
		
		phone.sendMessage();
		
		phone.cpu=2;
		phone.screen=(float) 2.4;
		phone.mem=232;
		phone.sendMessage();
	}

}
