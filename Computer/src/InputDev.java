import java.util.Scanner;
public class InputDev{
	 private String keyBoard;
	 private String mouse;
	 private String microPhone;
	 
	public String getKeyBoard() {
		return keyBoard;
	}

	public void setKeyBoard(String keyBoard) {
		this.keyBoard = keyBoard;
	}

	public String getMouse() {
		return mouse;
	}

	public void setMouse(String mouse) {
		this.mouse = mouse;
	}

	public String getMicroPhone() {
		return microPhone;
	}

	public void setMicroPhone(String microPhone) {
		this.microPhone = microPhone;
	}

	final Scanner input = new Scanner(System.in);
	
	public static void main(String[] args)
	 { 
 		
	 }

	 String getMsg(){
	 	return input.nextLine();
	 }

	public String toString(){
		return	"������Ϣ:"+keyBoard+"\n"+
				"�����Ϣ:"+mouse+"\n"+
				"��˷���Ϣ:"+microPhone;
				
	 }

 } 