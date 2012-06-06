package org.khill.feedsearch;

import java.net.URL;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test class for FeedExtractor
 */
public class FeedIndexerTest {

    private static final Logger LOG = LoggerFactory.getLogger(FeedIndexerTest.class);
    
    private FeedIndexer extractor = new FeedIndexer();
    
    @Test
    public void indexFeed() throws Exception {
        URL testFeed = this.getClass().getClassLoader().getResource("craigslist-example.rss");
        IndexSearcher searcher = extractor.indexFeed(testFeed);
        Assert.assertNotNull(searcher);
        QueryParser parser = new QueryParser(Version.LUCENE_36, SyndicationFields.TITLE.toString(), 
                new StandardAnalyzer(Version.LUCENE_36));
        Query q = parser.parse("+" + SyndicationFields.TITLE.toString() + ":bike");
        TopDocs topDocs = searcher.search(q, 10);
        Assert.assertTrue(topDocs.totalHits > 0);
        Document doc;
        // just printing matching docs for debugging 
        for(ScoreDoc sd: topDocs.scoreDocs) {
            doc = searcher.doc(sd.doc);
            LOG.debug("Hit: {}", doc.get(SyndicationFields.TITLE.toString()));
        }
    }
}
