public class OutputDev{
	private String display;
   	private String printer;
   	
   	
   	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getPrinter() {
		return printer;
	}

	public void setPrinter(String printer) {
		this.printer = printer;
	}

public static void main(String[] args)
   { 
	try {
		Thread.sleep(3);
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e.getMessage());
	}
  
   }

   void showMsg(String c){
   		System.out.println(c);
   }
   	public String toString(){
		return	"显示器信息:"+display+"\n"+
				"打印机信息:"+printer;
				
	 }
 } 