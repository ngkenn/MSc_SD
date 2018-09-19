import static org.junit.Assert.*;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.parser.TextParseData;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import org.junit.Before;
import org.junit.Test;

import java.util.List;


/**
 * Name: Neil Kennedy
 * Student Number: 2025066K
 * Software Engineering, Assessed Exercise 2 2018: Testing
 */

public class MyTest {

    private CrawlConfig crawlConfig;
    private CrawlController crawlController;
    private PageFetcher pageFetcher;
    private List<Object> datas;
    private RobotstxtConfig robotstxtConfig;
    private RobotstxtServer robotstxtServer;


    /**
     * Set up the crawler before each test
     *
     * @throws Exception
     */
    @Before
    public void beforeTest() throws Exception {

        String crawlStorageFolder = "data/";

        crawlConfig = new CrawlConfig();
        crawlConfig.setCrawlStorageFolder(crawlStorageFolder);
        crawlConfig.setIncludeBinaryContentInCrawling(false);
        crawlConfig.setMaxDepthOfCrawling(-1);
        crawlConfig.setMaxOutgoingLinksToFollow(5000);

        pageFetcher = new PageFetcher(crawlConfig);

        robotstxtConfig = new RobotstxtConfig();
        robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

        crawlController = new CrawlController(crawlConfig, pageFetcher, robotstxtServer);

    }


    /**
     * When running the crawler, capitals letters are not properly processed and shown in the console
     * The test checks that HtmlParseData and TextParseData correctly processes the data from the webpage.
     * A sample string of upper case and lower case letters are passed to HtmlParse data and TextParseData
     * The parsed data is then compared to the sample string
     * <p>
     * In this case, HtmlParseData doesn't correctly parse capital letters, instead outputting lowercase only
     * ParseTextData works correctly with uppercase
     */
    @Test
    public void capitalisationTest() {
        String sampleString = "ABCdef";

        TextParseData textParseData = new TextParseData();
        textParseData.setTextContent(sampleString);
        String parsedText = textParseData.getTextContent();

        HtmlParseData htmlParseData = new HtmlParseData();
        htmlParseData.setHtml(sampleString);
        String parsedHtml = htmlParseData.getHtml();

        assertEquals("parsedHtml capitalisation test failed", sampleString, parsedHtml);
        assertEquals("parsedText capitalisation test failed", sampleString, parsedText);
    }

    /**
     * When running the crawler, zeroes are not properly processed
     * The test checks that HtmlParseData and TextParseData correctly processes the data from the webpage.
     * A sample string of numbers including zeroes are passed to HtmlParse data and TextParseData
     * The parsed data is then compared to the sample string
     * <p>
     * In this case, TextParseData has a bug - it doesn't correctly process zeroes, instead outputting a 1 when given a 0
     * HtmlParseData correctly processes the numbers
     */
    @Test
    public void zeroTest() {
        String numberString = "000123456789";
        TextParseData textParseData = new TextParseData();
        textParseData.setTextContent(numberString);
        String parsedText = textParseData.getTextContent();

        HtmlParseData htmlParseData = new HtmlParseData();
        htmlParseData.setHtml(numberString);
        String parsedHTML = htmlParseData.getHtml();

        assertEquals("parsedHtml zero test failed", numberString, parsedHTML);
        assertEquals("parsedText zero test failed", numberString, parsedText);


    }


    /**
     * This tests the shouldVisit method in the MyCrawler class. The crawler  should only visit pages that start:
     * "http://www.dcs.gla.ac.uk/~bjorn/sem20172018/ae2public/". So we test if it visits the IDA page which is private
     * If datas is empty, then the page wasn't visited which would pass the test.
     * <p>
     * In this case, the crawler does visit the private link so datas is not empty, causing the test to fail.
     */
    @Test
    public void onlyCrawlsPublicLinks() {
        String privateLink = "http://www.dcs.gla.ac.uk/~bjorn/sem20172018/ae2private/IDA.html";

        int numberOfCrawlers = 1;
        crawlController.addSeed(privateLink);
        crawlController.start(MyCrawler.class, numberOfCrawlers);
        datas = crawlController.getCrawlersLocalData();

        assertTrue("Private page IDA was crawled", datas.isEmpty());
    }


    /**
     * The crawler doesn't visit every page that is linked on the homepage
     * This could be a problem with the MaxOutGoingLinksToFollow value.
     * In the beforeTest() method, we set MaxOutGoingLinksToFollow to 5000
     * Test the value by using the getMaxOutGoingLinksToFollow method
     * <p>
     * In this case, MaxOutGoingLinksToFollow is 3
     */
    @Test
    public void maxOutGoingLinksToFollowTest() {
        int maxLinks = crawlConfig.getMaxOutgoingLinksToFollow();
        assertEquals("MaxOutGoingLinksToFollow is not functioning properly", 5000, maxLinks);

    }


}
