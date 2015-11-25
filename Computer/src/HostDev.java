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
		host.setMemoryName("����");
		host.setHardDiskName("�������ݣ�");
		host.setMainBoardName("����");
		host.setDisplayCard("NVIDIA");

		InputDev input = new InputDev();
		input.setKeyBoard("Ħ����");
		input.setMouse("����");

		OutputDev output = new OutputDev();
		output.setDisplay("����");
		output.setPrinter("Canon");

		host.setInput(input);
		host.setOutput(output);

		host.run();

	}

	void run() {
		
		systemSelfTest();

		output.showMsg("��������Ϣ��");

		String s = input.getMsg();

		output.showMsg("��������ַ�������" + countDigital(s) + "������,��" + countLetter(s) + "����ĸ");

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
			System.out.println("����豸δ���룡ϵͳ�޷�����");
			System.exit(1);
		}
		if (input == null) {
			System.out.println("�����豸δ���룡ϵͳ�޷�����");
			System.exit(1);
		}

		output.showMsg("�����Լ졣����");

		output.showMsg("\n������Ϣ��\n" + this);

		output.showMsg("\n�����豸��Ϣ��\n" + input);

		output.showMsg("\n����豸��Ϣ��\n" + output);

		output.showMsg("�Լ���ɡ�\n\n");

	}

	public String toString() {
		return "CPU��Ϣ:" + cpuName + "\n" + "�ڴ���Ϣ:" + memoryName + "\n" + "������Ϣ:" + hardDiskName + "\n" + "�Կ���Ϣ:"
				+ displayCard;
	}
}