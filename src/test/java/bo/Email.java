package bo;

// Builder pattern
public class Email {

	private String adress; //required
	private String subject; //optional
	private String body; //optional

	public Email() {
		super();
	}

	private Email(EmailBuilder builder) {
		super();
		this.adress = builder.adress;
		this.subject = builder.subject;
		this.body = builder.body;
	}

	public String getAdress() {
		return adress;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		if (adress == null) {
			if (other.adress != null)
				return false;
		} else if (!adress.equals(other.adress))
			return false;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Email [adress=" + adress + ", subject=" + subject + ", body=" + body + "]";
	}

	public static class EmailBuilder{

		private String adress;
		private String subject;
		private String body;

		public EmailBuilder(String adress){
			this.adress = adress;
		}

		public EmailBuilder subject(String subject){
			this.subject = subject;
			return this;
		}

		public EmailBuilder body(String body){
			this.body = body;
			return this;
		}

		public Email build(){
	    	return new Email(this);
		}
	}

}
