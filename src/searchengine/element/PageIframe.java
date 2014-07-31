package searchengine.element;

import java.net.*;

import searchengine.url.URLFixer;

/**
 * A hyperlink in a web page.
 * 
 */
public class PageIframe implements PageElementInterface {

	public PageIframe(String h) throws MalformedURLException {
		href = new URL(h);
	}

	public PageIframe(URL context, String h) throws MalformedURLException {
		href = URLFixer.fix(context, h);
	}

	public String toString() {
		if (href == null)
			return null;
		return href.toString();
	}

	private URL href;
}
