public class HostDev {
	private String cpuName;
	private String memoryName;
	private String hardDiskName;
	private String mainBoardName;
	private String displayCard;
	private OutputDev output;
	private InputDev input;

	public OutputDev getOutput() {
		return output;
	}

	public void setOutput(OutputDev output) {

		this.output = output;
	}

	public InputDev getInput() {
		return input;
	}

	public void setInput(InputDev input) {
		this.input = input;
	}

	public String getCpuName() {
		return cpuName;
	}

	public void setCpuName(String cpuName) {
		this.cpuName = cpuName;
	}

	public String getMemoryName() {
		return memoryName;
	}

	public void setMemoryName(String memoryName) {
		this.memoryName = memoryName;
	}

	public String getHardDiskName() {
		return hardDiskName;
	}

	public void setHardDiskName(String hardDiskName) {
		this.hardDiskName = hardDiskName;
	}

	public String getMainBoardName() {
		return mainBoardName;
	}

	public void setMainBoardName(String mainBoardName) {
		this.mainBoardName = mainBoardName;
	}

	public String getDisplayCard() {
		return displayCard;
	}

	public void setDisplayCard(String displayCard) {
		this.displayCard = displayCard;
	}

	public static void main(String[] args) {
		HostDev host = new HostDev();
		host.setCpuName("Intel");
		host.setMemoryName("三星");
		host.setHardDiskName("西部数据！");
		host.setMainBoardName("技嘉");
		host.setDisplayCard("NVIDIA");

		InputDev input = new InputDev();
		input.setKeyBoard("摩天轮");
		input.setMouse("蝰蛇");

		OutputDev output = new OutputDev();
		output.setDisplay("乐视");
		output.setPrinter("Canon");

		host.setInput(input);
		host.setOutput(output);

		host.run();

	}

	void run() {
		
		systemSelfTest();

		output.showMsg("请输入信息：");

		String s = input.getMsg();

		output.showMsg("您输入的字符串中有" + countDigital(s) + "个数字,有" + countLetter(s) + "个字母");

	}

	int countLetter(String msg) {
		int r = 0;
		for (int i = 0; i < msg.length(); i++) {
			char c = msg.charAt(i);
			if ((c > 'a' && c < 'z') || (c > 'a' && c < 'z'))
				r++;
		}
		return r;
	}

	int countDigital(String msg) {
		int r = 0;
		for (int i = 0; i < msg.length(); i++) {
			char c = msg.charAt(i);
			if (c > '0' && c < '9')
				r++;
		}
		return r;
	}

	void systemSelfTest() {
		if (output == null) {
			System.out.println("输出设备未接入！系统无法启动");
			System.exit(1);
		}
		if (input == null) {
			System.out.println("输入设备未接入！系统无法启动");
			System.exit(1);
		}

		output.showMsg("开机自检。。。");

		output.showMsg("\n主机信息：\n" + this);

		output.showMsg("\n输入设备信息：\n" + input);

		output.showMsg("\n输出设备信息：\n" + output);

		output.showMsg("自检完成。\n\n");

	}

	public String toString() {
		return "CPU信息:" + cpuName + "\n" + "内存信息:" + memoryName + "\n" + "主板信息:" + hardDiskName + "\n" + "显卡信息:"
				+ displayCard;
	}
}