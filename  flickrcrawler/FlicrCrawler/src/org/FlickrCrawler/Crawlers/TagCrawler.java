package org.FlickrCrawler.Crawlers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.FlickrCrawler.database.Database;
import org.xml.sax.SAXException;

import sun.text.CompactShortArray.Iterator;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.tags.TagsInterface;

public class TagCrawler {
	
	TagsInterface tagsinterface;
	Database db;
	Photo photo;
	Collection tags;
	
	public TagCrawler(Flickr f){
		tagsinterface = f.getTagsInterface();
		db = new Database();
		photo = new Photo();
		tags = new ArrayList();
		
	}
	
	public void crawl(String PhotoId){
		try {
			photo = tagsinterface.getListPhoto(PhotoId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FlickrException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tags =  photo.getTags();
		Iterator itr= (Iterator) tags.iterator();
		while(itr.hasNext()){
			try {
				char temp = itr.next();
				db.addTagToPic(PhotoId, "na", "na", "na");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
