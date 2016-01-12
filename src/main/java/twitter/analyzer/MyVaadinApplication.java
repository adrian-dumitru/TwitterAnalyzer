package twitter.analyzer;

import javax.persistence.EntityManager;

import com.vaadin.Application;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinApplication extends Application {

	public static final String PERSISTENCE_UNIT = "twitter.analyzer";

	private static EntityManager em;

	static {
		em = JPAContainerFactory
				.createEntityManagerForPersistenceUnit(PERSISTENCE_UNIT);
	}

	@Override
	public void init() {
		final ApplicationUI window = new ApplicationUI();
		window.getFindButton().addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				String textToFind = window.getFindField().getValue().toString();
				Tweet newTweet = new Tweet();
				newTweet.setTextToFind(textToFind);
				em.getTransaction().begin();
				em.persist(newTweet);
				em.getTransaction().commit();
			}
		});
		setMainWindow(window);
	}

}
