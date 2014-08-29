package com.ar.marketanalyzer.ibd50.services;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.ibd50.models.Ibd50Rank;
import com.ar.marketanalyzer.ibd50.services.interfaces.Ibd50RankServiceInterface;
import com.ar.marketanalyzer.securities.models.Symbol;

@Component
public class Ibd50WebService{
	private final static String username = "teedit@gmail.com";
	private final static String password = "aaronnhugh";
	
	@Autowired
	private Ibd50RankServiceInterface rankService;
	/**
	 * Main method for getting the Ibd 50 from the website.
	 * 
	 * @return List of Ibd50Ranking Beans ready to be inserted into the DB
	 */
	public InputStream grabIbd50() {
		InputStream downloadedFileInputStream = null;
		List<Ibd50Rank> ibd50List = null;
		try {
			downloadedFileInputStream = authenticatedIbd50Download();
			
			ibd50List = rankService.parseIbd50HTMLToBeanList(downloadedFileInputStream);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return downloadedFileInputStream;
	}
	


	/**
	 * This method signs into research.investors.com and requests a text file
	 * that contains the current IBD 50 in a whitespace delimited txt file.
	 * 
	 * @return InputStream that contains the ibd 50 text file that is whitespace deliminated
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private InputStream authenticatedIbd50Download() throws ClientProtocolException, IOException {
		//Cookie setup
		RequestConfig globalConfig = RequestConfig.custom()
							.setCookieSpec(CookieSpecs.BEST_MATCH)
							.build();
	    CookieStore cookieStore = new BasicCookieStore();
	    		
		// Creating a normal HttpClient object.
	    HttpClient httpclient = HttpClientBuilder.create()
	    					.setDefaultCookieStore(cookieStore)
	    					.setDefaultRequestConfig(globalConfig)
	    					.build();
	    
		// HttpClient httpclient = HttpClientBuilder.create().build();
		// As it's just a get call so no need for overhead of calling post.
		HttpGet httpget = new HttpGet("http://research.investors.com");
		// A response object
		HttpResponse response = httpclient.execute(httpget);
		// Entity in Client 4 and above holds all the response information.
		HttpEntity entity = response.getEntity();
		
		System.out.println("Login form get: " + response.getStatusLine());
		if (entity != null) {
			EntityUtils.consume(entity);
		}
		
		System.out.println("Initial set of cookies:");
		// Getting all the cookies information available for www.investors.com
		List<Cookie> cookies = cookieStore.getCookies();
		if (cookies.isEmpty()) {
			System.out.println("None");
		} else {
			for (int i = 0; i < cookies.size(); i++) {
				System.out.println("- " + cookies.get(i).toString());
			}
		}
		
		// Calling the service to get the Login information.
		HttpPost httpost = new HttpPost("http://www.investors.com/Services/SiteAjaxService.asmx/MemberSingIn");
		
		// Creating Name Value pairs to pass information.
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("strEmail", username));
		nvps.add(new BasicNameValuePair("strPassword", password));
		nvps.add(new BasicNameValuePair("blnRemember", "true"));
		
		httpost.setEntity(new UrlEncodedFormEntity(nvps));
		
		response = httpclient.execute(httpost);
		entity = response.getEntity();
		
		System.out.println("Login form get: " + response.getStatusLine());
		if (entity != null) {
			EntityUtils.consume(entity);
		}
		
		System.out.println("Post logon cookies:");
		cookies = cookieStore.getCookies();
		// String mySessionId = null;
		if (cookies.isEmpty()) {
			System.out.println("None");
		} else {
			for (int i = 0; i < cookies.size(); i++) {
				System.out.println("- " + cookies.get(i).toString());
			}
		}
		
		// URL from http://news.investors.com/otheribddata.aspx
		// Text file version of the list
		//httpget = new HttpGet("http://news.investors.com/StockResearch/ScreenCenter/ExportScreen.aspx?start=ibd&exportType=text");
		// Excel file version of the list
		//httpget = new HttpGet("http://news.investors.com/StockResearch/ScreenCenter/ExportScreen.aspx?start=ibd");
		httpget = new HttpGet("http://research.investors.com/etables/IBD50XLS.aspx?from=etables&tabView=IBD100&columnsort1=ibd100rank&columnsorttype1=DESC&columnsort2=&columnsorttype2=DESC");
		response = httpclient.execute(httpget);
		Header[] headers = response.getAllHeaders();
		for(int i=0;i<headers.length;i++){
			System.out.println("- " + "Header: "+headers[i].toString());
		}
		System.out.println(response.toString());
		
		return response.getEntity().getContent();
	}
}
