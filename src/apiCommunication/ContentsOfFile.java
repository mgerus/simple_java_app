package apiCommunication;

import java.util.ArrayList;
import java.util.Collection;

public class ContentsOfFile {
	private Collection<Article> articles;

	public ContentsOfFile() {
		this.articles = new ArrayList<Article>();
	}

	public void add(Article article) {
		articles.add(article);
	}

	public String writeOutAllArticles() {
		String result = "";
		for (Article article:articles) {
			result += article.formatForOutput();
			result += "\n";
		}
		return result;		
	}
}
