/**
 * 
 */
package org.khill.feedsearch;

/**
 * Enumeration of field names used in syndication indexing
 * 
 * @author khill
 *
 */
public enum SyndicationFields {

    TITLE("title"),
    AUTHOR("author"),
    LINK("link"),
    DESCRIPTION("description"),
    PUB_DATE("pub_date");
    
    private String name;
    
    private SyndicationFields(String name) {
        this.name = name;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return name;
    }
    
}
