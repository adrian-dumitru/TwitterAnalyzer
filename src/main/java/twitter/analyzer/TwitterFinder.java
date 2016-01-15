package twitter.analyzer;

import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import twitter4j.*;
import twitter4j.api.SearchResource;
import twitter4j.conf.ConfigurationBuilder;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.ArrayList;

public class TwitterFinder {

    public static final String PERSISTENCE_UNIT = "twitter.analyzer";

    private static Twitter twitter;

    private static EntityManager em;

    public static ArrayList<String> tweets = new ArrayList<String>();

    public static int[] emotions = new int[5];

    static{
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("GRJZ7StS5z5xSQWOHBXJIJKV2")
                .setOAuthConsumerSecret("dgjnclgJliWKUaLbRYYif7I558bTaGy8UtOTlaXFzApyq1MCjE")
                .setOAuthAccessToken("4505834073-QTSWmrjxaD5fM8OFAqgFsjr16OOvMEvMAqRmiao")
                .setOAuthAccessTokenSecret("3kOM1noWCWLEUtk9Yr7mQ7fDeOMoNvrJQgucWujpeOxcK");
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();

        em = JPAContainerFactory
                .createEntityManagerForPersistenceUnit(PERSISTENCE_UNIT);
    }


    public static void getTweets(String textToFind) throws IOException {
        Query query = new Query(textToFind+ " -filter:retweets -filter:links -filter:replies -filter:images");
        query.setCount(10);

        //query.
        query.setSince("2015-01-01");
        query.setLang("en");
        query.setLocale("en");
        int maxNumber = 10;
        int index = 0;


        QueryResult result;
        try {
            result = ((SearchResource) twitter).search(query);
            while(query!=null && index < maxNumber){
                for (Status status : result.getTweets()) {

                    if(status.getRetweetedStatus() == null || status.isRetweet() == false){

                        int aux = NLPSearch.findSentiment(status.getText());
                        emotions[aux]++;
                        tweets.add(status.getText()+" ---> "+aux + " = " + intToString(aux));
                        index++;
                    }
                }
                query = result.nextQuery();
                if(query!=null)
                    result = ((SearchResource) twitter).search(query);
            }
            System.out.println("------------------------------ " + index);
        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static String intToString(int emotionId){
        switch (emotionId) {
            case 4:
                return "Very Happy";
            case 3:
                return "Happy";
            case 2:
                return "Neutral";
            case 1:
                return "Sad";
            case 0:
                return "Very Sad";
            default:
                return "";
        }
    }

}
