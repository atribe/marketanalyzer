package com.ar.marketanalyzer.ibd50.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.ar.marketanalyzer.ibd50.models.Ibd50Rank;
import com.ar.marketanalyzer.ibd50.services.interfaces.Ibd50RankServiceInterface;

@Component
@PropertySource({ "classpath:common.properties" })
public class Ibd50FileService {

	@Resource
	private Environment env;
	@Autowired
	private Ibd50RankServiceInterface rankService;

	public List<Ibd50Rank> loadIbd50() {
		final File folder = new File(env.getProperty("ibd50.fileDirectory"));
		List<Ibd50Rank> rowsFromIBD50 = null;
		List<Ibd50Rank> bigIbd50List = new ArrayList<Ibd50Rank>();
		
		List<File> fileList = listFilesForFolder(folder);
		for (final File file : fileList) {
			try {
				InputStream is = new FileInputStream(file);
				rowsFromIBD50 = rankService.parseIbd50HTMLToBeanList(is);
				bigIbd50List.addAll(rowsFromIBD50);
				
			} catch (IOException e) {
				
			}
		}
		return bigIbd50List;
	}
	
	public List<File> listFilesForFolder(final File folder) {
	    List<File> fileList = new ArrayList<File>();
		
		for (final File fileEntry : folder.listFiles()) {					// Loops through all files in the given folder
	        if (fileEntry.isDirectory()) {									// If the folder is a directory
	            listFilesForFolder(fileEntry);								// 		Dive in
	        } else {														// Else					
	        	if( fileEntry.getName().endsWith(".xls") ) {				// If the file ends with .xls
	        		System.out.println(fileEntry.getName());				// Print the name, for debugging
	        		fileList.add(fileEntry);								// Add the file to the list
	        	}	            
	        }
	    }
		
		return fileList;
	}
	
}
