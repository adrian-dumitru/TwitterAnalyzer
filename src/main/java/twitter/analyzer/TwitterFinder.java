package twitter.analyzer;

import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import twitter4j.*;
import twitter4j.api.SearchResource;
import twitter4j.conf.ConfigurationBuilder;

import javax.persistence.EntityManager;
import java.io.IOException;

/**
 * Created by adumitru on 1/14/2016.
 */
public class TwitterFinder {

    public static final String PERSISTENCE_UNIT = "twitter.analyzer";

    private static Twitter twitter;

    private static EntityManager em;

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
        Query query = new Query(textToFind);
        query.setCount(1000);

        //query.
        query.setSince("2015-01-01");
        query.setLang("en");

        int[] lala = new int[5];

        QueryResult result;
        try {
            result = ((SearchResource) twitter).search(query);
            while(query!=null){
                for (Status status : result.getTweets()) {

                    if(status.getRetweetedStatus() == null || status.isRetweet() == false){
//                        Tweet newTweet = new Tweet();
//                        newTweet.setTextToFind(textToFind);
//                        newTweet.setTweetMessage(status.getText());
//                        newTweet.setUser(status.getUser().getScreenName());
//                        em.getTransaction().begin();
//                        em.persist(newTweet);
//                        em.getTransaction().commit();

                        int aux = NLPSearch.findSentiment(status.getText());
                        lala[aux-1]++;

                    }
                }
                query = result.nextQuery();
                if(query!=null)
                    result = ((SearchResource) twitter).search(query);
            }
            System.out.println(lala);
        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
