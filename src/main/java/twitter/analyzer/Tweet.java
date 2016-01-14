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
    private Integer emotionId;
    private String textToFind;
    private String tweetMessage;
    private String user;

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

    public Integer getEmotionId() {
        return emotionId;
    }

    public void setEmotionId(Integer emotionId) {
        switch (emotionId) {
            case 0:
                emotion = "Very Happy";
                break;
            case 1:
                emotion = "Happy";
                break;
            case 2:
                emotion = "Neutral";
                break;
            case 3:
                emotion = "Sad";
                break;
            case 4:
                emotion = "Very Sad";
                break;
        }
        this.emotionId = emotionId;
    }

    public String getTweetMessage() {
        return tweetMessage;
    }

    public void setTweetMessage(String tweetMessage) {
        this.tweetMessage = tweetMessage;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", emotion='" + emotion + '\'' +
                ", textToFind='" + textToFind + '\'' +
                ", tweetMessage='" + tweetMessage + '\'' +
                ", user='" + user + '\'' +
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
