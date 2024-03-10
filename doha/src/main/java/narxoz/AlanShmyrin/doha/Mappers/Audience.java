package narxoz.AlanShmyrin.doha.Mappers;

public class Audience {
	
	private int id;
	private String status;
	private String reason;
	private String videocard;
	private String processor;
	private String freeDiskSpace;
	private String ram;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getVideocard() {
		return videocard;
	}
	public void setVideocard(String videocard) {
		this.videocard = videocard;
	}
	public String getProcessor() {
		return processor;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	public String getFreeDiskSpace() {
		return freeDiskSpace;
	}
	public void setFreeDiskSpace(String freeDiskSpace) {
		this.freeDiskSpace = freeDiskSpace;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}

}
