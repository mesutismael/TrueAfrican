package Modals;

public class Memory {
	private String Used_memory;
	private String total_memory;
	private String free_memory;
	
	public String getUsed_memory() {
		return Used_memory;
	}

	public void setUsed_memory(String used_memory) {
		Used_memory = used_memory;
	}

	public String getTotal_memory() {
		return total_memory;
	}

	public void setTotal_memory(String total_memory) {
		this.total_memory = total_memory;
	}

	public String getFree_memory() {
		return free_memory;
	}

	public void setFree_memory(String free_memory) {
		this.free_memory = free_memory;
	}

	public Memory(String used_memory, String total_memory, String free_memory) {
		super();
		Used_memory = used_memory;
		this.total_memory = total_memory;
		this.free_memory = free_memory;
	}
	
	
	

}
