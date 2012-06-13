/**
 * 
 */
package org.khill.feedsearch;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO representing a specific alert created for feeds.
 * 
 * Each time a user creates an alert on the site, an instance of this class represents 
 * that configuration.
 * 
 * @author khill
 *
 */
public class FeedAlert {

    private String feedUrl;
    
    private String emailAddress;
    
    private Set <String> terms = new HashSet<String>();
    
    private Date createDate;

    /**
     * @return the feedUrl
     */
    public String getFeedUrl() {
        return feedUrl;
    }

    /**
     * @param feedUrl the feedUrl to set
     */
    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the terms
     */
    public Set<String> getTerms() {
        return terms;
    }

    /**
     * @param terms the terms to set
     */
    public void setTerms(Set<String> terms) {
        this.terms = terms;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
}
