package lt.bumbis.rpsim.demo;

public class Request {
	
	private String id;
	private String personId;
	private Long amount;
	private boolean valid = true;
	private String invalidReason;
	private boolean canceled = false;

	public Request(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}
	
	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public void setInvalid(String reason) {
		this.valid = false;
		this.invalidReason = reason;
	}

	public boolean isValid() {
		return valid;
	}

	public String getInvalidReason() {
		return invalidReason;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Request) {
			return this.id.equals(((Request) o).id);
		}
		return false;
	}
	
	public int hashCode() {
		return this.id.hashCode();
	}

}
