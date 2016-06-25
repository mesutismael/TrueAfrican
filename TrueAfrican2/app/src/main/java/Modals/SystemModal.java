package Modals;

import java.util.List;

public class SystemModal {
	private List <String> cpu;
	private Memory memory;
	
	public List<String> getCpu() {
		return cpu;
	}

	public void setCpu(List<String> cpu) {
		this.cpu = cpu;
	}

	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}

	public SystemModal(List<String> cpu, Memory memory) {
		super();
		this.cpu = cpu;
		this.memory = memory;
	}
	
	

}
