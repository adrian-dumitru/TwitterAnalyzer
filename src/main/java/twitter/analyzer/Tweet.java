package twitter.analyzer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by adumitru on 1/7/2016.
 */
@Entity
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String emotion;
    private String textToFind;
    private String tweetMessage;

    public String getTextToFind() {
        return textToFind;
    }

    public void setTextToFind(String textToFind) {
        this.textToFind = textToFind;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getTweetMessage() {
        return tweetMessage;
    }

    public void setTweetMessage(String tweetMessage) {
        this.tweetMessage = tweetMessage;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "emotion='" + emotion + '\'' +
                ", tweetMessage='" + tweetMessage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweet = (Tweet) o;

        if (!emotion.equals(tweet.emotion)) return false;
        return tweetMessage != null ? tweetMessage.equals(tweet.tweetMessage) : tweet.tweetMessage == null;

    }

    @Override
    public int hashCode() {
        int result = emotion.hashCode();
        result = 31 * result + (tweetMessage != null ? tweetMessage.hashCode() : 0);
        return result;
    }
}
