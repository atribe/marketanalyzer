package com.ar.marketanalyzer.ibd50.dao;

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

import com.ar.marketanalyzer.ibd50.models.Ibd50Rank;
import com.ar.marketanalyzer.securities.models.Symbol;

public class Ibd50WebDao{
	private final static String username = "teedit@gmail.com";
	private final static String password = "aaronnhugh";
	
	/**
	 * Main method for getting the Ibd 50 from the website.
	 * 
	 * @return List of Ibd50Ranking Beans ready to be inserted into the DB
	 */
	public List<Ibd50Rank> grabIbd50() {
		InputStream downloadedFileInputStream = null;
		List<Ibd50Rank> rowsFromIBD50 = null;
		
		try {
			downloadedFileInputStream = authenticatedIbd50Download();
			
			rowsFromIBD50 = parseIbd50HTMLToBeanList(downloadedFileInputStream);
			//rowsFromIBD50 = parseIBD50toBeanList(downloadedTextFile);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rowsFromIBD50;
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
		httpget = new HttpGet("http://news.investors.com/StockResearch/ScreenCenter/ExportScreen.aspx?start=ibd");
		response = httpclient.execute(httpget);
		Header[] headers = response.getAllHeaders();
		for(int i=0;i<headers.length;i++){
			System.out.println("- " + "Header: "+headers[i].toString());
		}
		System.out.println(response.toString());
		
		return response.getEntity().getContent();
	}
	
	private List<Ibd50Rank> parseIbd50HTMLToBeanList(InputStream downloadedFileInputStream) throws IOException {
		List<Ibd50Rank> rowsFromIBD50 = new ArrayList<Ibd50Rank>();
		
		Document doc = Jsoup.parse(downloadedFileInputStream, "UTF-8", "");
		Elements rows = doc.getElementsByTag("table").get(0).getElementsByTag("tr");
		
		//loop throw all the rows of the table
		for(Element row : rows) {
			//Get all the cells in the row
			Elements cells = row.getElementsByTag("td");
			List<String> rowOfStrings = new ArrayList<String>();
			
			if( cells.hasText()) {			//if the list cells is not empty (aka it isn't the header row)
				//Loop through all the cells in the row
				for(Element cell : cells) {
					//Add every cell of the row to a List<String>
					String cellContents = cell.getElementsByTag("td").text();
					if( !cellContents.equalsIgnoreCase("N/A") ) {
						rowOfStrings.add(cellContents);
					} else {
						rowOfStrings.add(null);
					}
				}
				//parse the List<String> into a bean and then add it to the list<bean>
				rowsFromIBD50.add(parseListToBean(rowOfStrings));
			}
		}
		
		//return the list of beans
		return rowsFromIBD50;
	}
	
	/**
	 * Turns a IBD50 List of Strings into a IBD50 Ranking Bean
	 * 
	 * @param ibd50tokenizedList
	 * @return IBD50 Ranking Bean
	 */
	private Ibd50Rank parseListToBean(List<String> ibd50tokenizedList) {
		Ibd50Rank ibdRow = new Ibd50Rank();
		
		Symbol company = new Symbol();
		
		company.setSymbol(ibd50tokenizedList.get(0));
		company.setName(ibd50tokenizedList.get(1));
		company.setType("Stock");
		ibdRow.setTicker(company);
		ibdRow.setActiveRanking(Boolean.TRUE);
		ibdRow.setRank(parseIntOrNull(ibd50tokenizedList.get(2)));
		ibdRow.setCurrentPrice(new BigDecimal(ibd50tokenizedList.get(3)));
		ibdRow.setPriceChange(new BigDecimal(ibd50tokenizedList.get(4)));
		ibdRow.setPricePercentChange(parseDoubleOrNull(ibd50tokenizedList.get(5)));
		ibdRow.setPercentOffHigh(parseDoubleOrNull(ibd50tokenizedList.get(6)));
		ibdRow.setVolume(parseLongOrNull(ibd50tokenizedList.get(7)));
		ibdRow.setVolumePercentChange(parseDoubleOrNull(ibd50tokenizedList.get(8)));
		ibdRow.setCompositeRating(parseDoubleOrNull(ibd50tokenizedList.get(9)));
		ibdRow.setEpsRating(parseDoubleOrNull(ibd50tokenizedList.get(10)));
		ibdRow.setRsRating(parseDoubleOrNull(ibd50tokenizedList.get(11)));
		ibdRow.setSmrRating(ibd50tokenizedList.get(12));
		ibdRow.setAccDisRating(ibd50tokenizedList.get(13));
		ibdRow.setGroupRelStrRating(ibd50tokenizedList.get(14));
		ibdRow.setEpsPercentChangeLastQtr(parseDoubleOrNull(ibd50tokenizedList.get(15)));
		ibdRow.setEpsPercentChangePriorQtr(parseDoubleOrNull(ibd50tokenizedList.get(16)));
		ibdRow.setEpsPercentChangeCurrentQtr(parseDoubleOrNull(ibd50tokenizedList.get(17)));
		ibdRow.setEpsEstPercentChangeCurrentYear(parseDoubleOrNull(ibd50tokenizedList.get(18)));
		ibdRow.setSalesPercentChangeLastQtr(parseDoubleOrNull(ibd50tokenizedList.get(19)));
		ibdRow.setAnnualROELastYear(parseDoubleOrNull(ibd50tokenizedList.get(20)));
		ibdRow.setAnnualProfitMarginLatestYear(parseDoubleOrNull(ibd50tokenizedList.get(21)));
		ibdRow.setManagmentOwnPercent(parseDoubleOrNull(ibd50tokenizedList.get(22)));
		ibdRow.setQtrsRisingSponsorship(parseIntOrNull(ibd50tokenizedList.get(23)));
		
		return ibdRow;
	}
	
	private Double parseDoubleOrNull(String str) {
		return str != null ? Double.parseDouble(str) : null;
	}
	
	private Integer parseIntOrNull(String str) {
		return str != null ? Integer.parseInt(str) : null;
	}
	
	private Long parseLongOrNull(String str) {
		return str != null ? Long.parseLong(str) : null;
	}
}
