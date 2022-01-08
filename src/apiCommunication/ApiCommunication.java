package apiCommunication;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.json.*;

public class ApiCommunication {
	final String apiKey = "2038b5149ed543338d0f287ed0fce27a";
	final String urlDomain = "https://newsapi.org/v2/top-headlines?";
	String country = "pl";
	String category = "business";

	public ApiCommunication() {
	}
	
	public String getArticlesFromApiInStrings() {
		try {
			JSONObject obj = getJSONsFromApi();
			String articlesInFormatedString = extractArticles(obj);
			return articlesInFormatedString;
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return "";
			}
	}

	private JSONObject getJSONsFromApi() throws Exception {
		// establish connection
			URL url = new URL(prepareURL());

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			// was connection successful ?
			int responsecode = conn.getResponseCode();
			if (responsecode != 200) {
				throw new RuntimeException("Communication with NewsApi failed:(");
			} else {

				// retrieve JSON
				String serverResponse = "";
				Scanner scanner = new Scanner(url.openStream(),StandardCharsets.UTF_8);

				while (scanner.hasNext()) {
					serverResponse += scanner.nextLine();
				}
				scanner.close();

				// prepare JSON for reading
				JSONObject obj = new JSONObject(serverResponse);
				return obj;
			}
		
	}

	private String extractArticles(JSONObject obj) {
		JSONArray array = obj.getJSONArray("articles");
		ContentsOfFile result = new ContentsOfFile();
		// in loop:
		for (int i = 0; i < array.length(); i++) {
			// acesss articles
			JSONObject current = array.getJSONObject(i);

			// collect information needed
			//problem - they might be nulls and not_a_string  will be thrown
			try {
				String title = current.getString("title");
				String description = current.getString("description");
				String author = current.getString("author");

				// prepare for writing
				Article article = new Article(title, description, author);
				result.add(article);
			} catch (JSONException e) {
				System.out.println("An article was ommited due to missing information, proceeding");
			}
		}
		String outputForWriting = result.writeOutAllArticles();
		return outputForWriting;
	}

	private String prepareURL() {
		String stringFormURL = urlDomain + "country=" + country + "&" + "category=" + category + "&" + "apiKey="
				+ apiKey;
		return stringFormURL;
	}
	// https://newsapi.org/v2/top-headlines?country=pl&category=business&apiKey=2038b5149ed543338d0f287ed0fce27a

}
