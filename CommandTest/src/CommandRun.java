import java.io.BufferedReader;  
import java.io.InputStreamReader;  
  
/** 
 * @author: (le.qiao) 
 * @e-mail: qiaolevip@gmail.com 
 * @myblog: <a href="http://qiaolevip.iteye.com">http://qiaolevip.iteye.com</a> 
 * @date: 2012-10-8 
 *  
 */  
public class CommandRun {  
  
    public static void main(String[] args) { 
    	System.out.println("adfsdafsadf");
        try {  
        	Process pr = new ProcessBuilder("cmd", "/C", "set").inheritIO().start();
        	 
        	BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream(), "GBK"));
        	String line = null;    		  
            while ((line = input.readLine()) != null) {  
                System.out.println(line);
            }  
        	System.out.println("Exited with error code " + pr.waitFor());  
  
        } catch (Exception e) {  
            System.out.println(e.toString());  
            e.printStackTrace();  
        }  
    }  
}  