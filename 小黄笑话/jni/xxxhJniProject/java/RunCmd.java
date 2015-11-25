public class RunCmd  {

	public static void main(String[] args) {
		String cmdStr="";
		for (String arg : args) {
			cmdStr+=arg+" ";
		}
		System.out.println("\n$"+cmdStr+"\n\n");		
		CmdResult cr = runCommand(cmdStr);
		System.out.println(cr);
			
	}
	
	public static native CmdResult runCommand(String cmdStr);
	static{
		System.loadLibrary("xxxhJniProject");
	}

}
