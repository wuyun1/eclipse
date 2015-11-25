public class CmdResult{
	public int levelError;
	public String consoleStr;
	public String toString(){
		return (consoleStr)+"\n\nExited with error code:" + levelError; 
	}
}