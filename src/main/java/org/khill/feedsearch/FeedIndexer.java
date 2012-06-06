/**
 * 
 */
package org.khill.feedsearch;

import java.net.URL;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * Class for indexing information from an RSS feed.
 * 
 * @author khill
 *
 */
public class FeedIndexer {

    private static final Logger LOG = LoggerFactory.getLogger(FeedIndexer.class);
    
    /*
     * Constructs an IndexWriter instance for indexing feed data
     */
    private IndexWriter getFeedIndexWriter() throws Exception {
        Directory directory = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36, new StandardAnalyzer(Version.LUCENE_36));
        return new IndexWriter(directory, config);
    }
    
    /**
     * Indexes the syndication feed at the given URL and returns an
     * IndexSearcher instance which can search the feed data.
     * 
     * @param feedUrl       the URL of a syndication feed
     * @return  an IndexSearcher instance which can search through the feed contents
     * @throws Exception    in the event of a parsing or indexing error
     */
    @SuppressWarnings("unchecked")
    public IndexSearcher indexFeed(URL feedUrl) throws Exception {
        IndexWriter writer = this.getFeedIndexWriter();
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedUrl));
        for(SyndEntryImpl entry : (List <SyndEntryImpl>)feed.getEntries()) {
            writer.addDocument(this.indexFeedEntry(entry));
        }
        Directory d = writer.getDirectory();
        writer.close();
        return new IndexSearcher(IndexReader.open(d));
    }
    
    /**
     * Constructs a Lucene Document instance containing the relevant information
     * from the syndication entry object.
     * 
     * @param entry     a feed syndication entry
     * @return  an instance of Document containing search data
     * @throws Exception in the event of a data extraction problem
     */
    private Document indexFeedEntry(SyndEntryImpl entry) throws Exception {
        Document doc = new Document();
        doc.add(new Field(SyndicationFields.TITLE.toString(), entry.getTitle(), Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field(SyndicationFields.AUTHOR.toString(), entry.getAuthor(), Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field(SyndicationFields.LINK.toString(), entry.getLink(), Field.Store.YES, Field.Index.NO));
        doc.add(new Field(SyndicationFields.DESCRIPTION.toString(), entry.getDescription().getValue(), Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field(SyndicationFields.PUB_DATE.toString(), DateTools.dateToString(entry.getPublishedDate(), DateTools.Resolution.YEAR), 
                Field.Store.YES, Field.Index.NOT_ANALYZED));
        return doc;
    }
    
}
