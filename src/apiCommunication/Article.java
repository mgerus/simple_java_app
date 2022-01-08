package apiCommunication;

public class Article {
	private String title;
	private String description;
	private String author;

	public Article(String title, String description, String author) {
		this.title = title;
		this.description = description;
		this.author = author;
	}
	
	public String formatForOutput() {
		return (title+":"+description+":"+author);
	}

}
