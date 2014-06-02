package com.atomrockets.marketanalyzer.ibd50;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.atomrockets.marketanalyzer.beans.IBD50Bean;
import com.atomrockets.marketanalyzer.helpers.WhitespaceToCSVReader;

public class IBD50Grabber{
	private final static String username = "teedit@gmail.com";
	private final static String password = "aaronnhugh";
	
	public static void grabIbd50() {
		InputStream downloadedTextFile = null;
		
		try {
			downloadedTextFile = authenticatedIbd50Download();
			
			List<IBD50Bean> rowsFromIBD50 = parseIBD50toBean(downloadedTextFile);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static InputStream authenticatedIbd50Download() throws ClientProtocolException, IOException {
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
		httpget = new HttpGet("http://news.investors.com/StockResearch/ScreenCenter/ExportScreen.aspx?start=ibd&exportType=text");
		response = httpclient.execute(httpget);
		Header[] headers = response.getAllHeaders();
		for(int i=0;i<headers.length;i++){
			System.out.println("- " + "Header: "+headers[i].toString());
		}
		System.out.println(response.toString());
		
		return response.getEntity().getContent();
	}
	
	private static List<IBD50Bean> parseIBD50toBean(InputStream is) throws IOException {
		List<IBD50Bean> rowsFromIBD50 = new ArrayList<IBD50Bean>();
		IBD50Bean ibdRow = new IBD50Bean();
		
		WhitespaceToCSVReader reader = new WhitespaceToCSVReader(new InputStreamReader(is));
		
		//reads the first 6 lines, it's just headers
		for(int i = 0; i<6 ; i++) {
			reader.readLine();
		}
		
		/*
		 * Manually convert this to a bean because OpenCSV only works for comma separated, which this isn't
		 * 1. Read a line
		 * 2. tokenize on comma
		 * 3. create a new bean
		 * 4. throw each part of the list into the correct field of the bean
		 * 5. add bean to bean list
		 * 6. Repeat
		 */
		String line = reader.readLine();
		
		while (line != null) {
			//splitting the line into an array
			List<String> ibd50tokenizedList = Arrays.asList(line.split(","));
			
			ibdRow = parseListToBean(ibd50tokenizedList);
			
			//adding the bean to the bean list
			rowsFromIBD50.add(ibdRow);

			//reading a new line for the next loop
			line = reader.readLine();
		}
		
		reader.close();

		return rowsFromIBD50;
	}
	
	private static IBD50Bean parseListToBean(List<String> ibd50tokenizedList) {
		IBD50Bean ibdRow = new IBD50Bean();
		
		ibdRow.setSymbol(ibd50tokenizedList.get(0));
		ibdRow.setCompanyName(ibd50tokenizedList.get(1));
		ibdRow.setRank(Integer.parseInt(ibd50tokenizedList.get(2)));
		ibdRow.setCurrentPrice(new BigDecimal(ibd50tokenizedList.get(3)));
		ibdRow.setPriceChange(new BigDecimal(ibd50tokenizedList.get(4)));
		ibdRow.setPricePercentChange(Double.parseDouble(ibd50tokenizedList.get(5)));
		ibdRow.setPercentOffHigh(Double.parseDouble(ibd50tokenizedList.get(6)));
		ibdRow.setVolume(Long.parseLong(ibd50tokenizedList.get(7)));
		ibdRow.setVolumePercentChange(Double.parseDouble(ibd50tokenizedList.get(8)));
		ibdRow.setCompositeRating(Double.parseDouble(ibd50tokenizedList.get(9)));
		ibdRow.setEpsRating(Double.parseDouble(ibd50tokenizedList.get(10)));
		ibdRow.setRsRating(Double.parseDouble(ibd50tokenizedList.get(11)));
		ibdRow.setSmrRating(ibd50tokenizedList.get(12));
		ibdRow.setAccDisRating(Integer.parseInt(ibd50tokenizedList.get(13)));
		ibdRow.setGroupRelStrRating(Integer.parseInt(ibd50tokenizedList.get(14)));
		ibdRow.setEpsPercentChangeLastQtr(Double.parseDouble(ibd50tokenizedList.get(15)));
		ibdRow.setEpsPercentChangePriorQtr(Double.parseDouble(ibd50tokenizedList.get(16)));
		ibdRow.setEpsPercentChangeCurrentQtr(Double.parseDouble(ibd50tokenizedList.get(17)));
		ibdRow.setEpsEstPercentChangeCurrentYear(Double.parseDouble(ibd50tokenizedList.get(18)));
		ibdRow.setSalesPercentChangeLastQtr(Double.parseDouble(ibd50tokenizedList.get(19)));
		ibdRow.setAnnualROELastYear(Double.parseDouble(ibd50tokenizedList.get(20)));
		ibdRow.setAnnualProfitMarginLatestYear(Double.parseDouble(ibd50tokenizedList.get(21)));
		ibdRow.setManagmentOwnPercent(Double.parseDouble(ibd50tokenizedList.get(22)));
		ibdRow.setQtrsRisingSponsorship(Integer.parseInt(ibd50tokenizedList.get(23)));
		
		return ibdRow;
	}
}
