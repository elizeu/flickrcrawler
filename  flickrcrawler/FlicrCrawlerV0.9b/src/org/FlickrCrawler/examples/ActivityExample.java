package org.FlickrCrawler.examples;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.REST;
import com.aetrion.flickr.RequestContext;
import com.aetrion.flickr.activity.ActivityInterface;
import com.aetrion.flickr.activity.Event;
import com.aetrion.flickr.activity.Item;
import com.aetrion.flickr.activity.ItemList;
import com.aetrion.flickr.auth.Auth;
import com.aetrion.flickr.auth.Permission;
import com.aetrion.flickr.util.IOUtilities;

/**
 * Demonstration of howto use the ActivityInterface.
 *
 * @author mago
 * @version $Id: ActivityExample.java,v 1.1 2007/07/22 20:34:23 x-mago Exp $
 */
public class ActivityExample {

    public ActivityExample() throws ParserConfigurationException, IOException {

    }

    public void showActivity(Flickr f) throws FlickrException, IOException, SAXException {
        ActivityInterface iface = f.getActivityInterface();
        ItemList list = iface.userComments(10, 0);
        for (int j = 0; j < list.size(); j++) {
            Item item = (Item) list.get(j);
            System.out.println("Item " + (j + 1) + "/" + list.size() + " type: " + item.getType());
            System.out.println("Item-id:       " + item.getId() + "\n");
            ArrayList events = (ArrayList) item.getEvents();
            for (int i = 0; i < events.size(); i++) {
                System.out.println("Event " + (i + 1) + "/" + events.size() + " of Item " + (j + 1));
                System.out.println("Event-type: " + ((Event) events.get(i)).getType());
                System.out.println("User:       " + ((Event) events.get(i)).getUser());
                System.out.println("Username:   " + ((Event) events.get(i)).getUsername());
                System.out.println("Value:      " + ((Event) events.get(i)).getValue() + "\n");
            }
        }
        ActivityInterface iface2 = f.getActivityInterface();
        list = iface2.userPhotos(50, 0, "300d");
        for (int j = 0; j < list.size(); j++) {
            Item item = (Item) list.get(j);
            System.out.println("Item " + (j + 1) + "/" + list.size() + " type: " + item.getType());
            System.out.println("Item-id:       " + item.getId() + "\n");
            ArrayList events = (ArrayList) item.getEvents();
            for (int i = 0; i < events.size(); i++) {
                System.out.println("Event " + (i + 1) + "/" + events.size() + " of Item " + (j + 1));
                System.out.println("Event-type: " + ((Event) events.get(i)).getType());
                if (((Event) events.get(i)).getType().equals("note")) {
                    System.out.println("Note-id:    " + ((Event) events.get(i)).getId());
                } else if (((Event) events.get(i)).getType().equals("comment")) {
                    System.out.println("Comment-id: " + ((Event) events.get(i)).getId());
                }
                System.out.println("User:       " + ((Event) events.get(i)).getUser());
                System.out.println("Username:   " + ((Event) events.get(i)).getUsername());
                System.out.println("Value:      " + ((Event) events.get(i)).getValue());
                System.out.println("Dateadded:  " + ((Event) events.get(i)).getDateadded() + "\n");
            }
        }
    }

}
