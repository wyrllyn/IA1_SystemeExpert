
public class Event {
	private EventType type;
	private String comment;
	
	public Event(EventType type, String comment) {
		this.type = type;
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Event [type=" + type + ": " + comment + "]";
	}
	
}
