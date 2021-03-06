package aohara.tinkertime.crawlers.pageLoaders;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import aohara.tinkertime.crawlers.Crawler;
	
/**
 * PageLoader for loading and caching HTML documents from the web.
 * 
 * @author Andrew O'Hara
 */
public class WebpageLoader implements PageLoader<Document>{
	
	private static final int TIMEOUT_MS = 10000;
		
	private final Map<URL, Document> documentCache = new HashMap<>();
	
	public Document getPage(Crawler<Document> crawler, URL url) throws IOException {
		if (!documentCache.containsKey(url)){
			documentCache.put(url, Jsoup.parse(url, TIMEOUT_MS));
		}
		return documentCache.get(url);
	}
}
