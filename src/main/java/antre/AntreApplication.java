package antre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import antre.db.MealModel;
import antre.model.DayMenuModel;
import antre.model.googlesearchapi.GoogleSearchObject;
import antre.model.googlesearchapi.Item;
import antre.service.MealService;

import com.google.gson.Gson;

@ComponentScan(basePackages = { "antre" })
@EnableScheduling
@RestController
@EnableAutoConfiguration
public class AntreApplication {

	@Autowired
	MealService mealService;

	@Deprecated
	@RequestMapping("/week")
	List<DayMenuModel> getTodayMenu() throws ParseException {
		List<DayMenuModel> result = new ArrayList<DayMenuModel>();
		Document doc = null;
		try {
			doc = Jsoup.connect("http://www.wantrejce.pl").get();
		} catch (IOException e) {
			// TODO problem with connection... probably. Do something!
			System.out.println("IOException:" + e.getMessage());
		}

		Elements days = doc.select("ul#subList2 li:matches(Pon|Wt|Śr|Czw|Pią)");

		for (Element day : days) {
			DayMenuModel dayMenu = new DayMenuModel();
			List<MealModel> meals = new ArrayList<MealModel>();
			System.out.println(day.toString());
			Element dayname = day.select("h2").first(); // DATA

			dayMenu.setDate(getDateFromString(dayname.text()));
			Elements daymenu = day.select("h4>p:has(strong)");

			System.out.println(dayname.text());
			System.out.println("---------------------");

			// soup
			meals.add(new MealModel("", daymenu.first().text(), null, null, null));

			Double mealPrice = 0.0d;
			Double mealWithoutSoup = 0.0d;
			for (int i = 1; i < daymenu.size(); i++) {
				String mealName = daymenu.get(i).text();
				if (mealName.isEmpty())
					continue;
				if (mealName.startsWith("Cena")) {

					// TODO EXTRACT
					Matcher matcher = Pattern.compile("-?\\d+").matcher(
							mealName);
					List<Double> matches = new ArrayList<Double>();
					while (matcher.find()) {
						matches.add(Double.valueOf(matcher.group()));
					}
					mealPrice = matches.get(0);
					mealWithoutSoup = matches.get(1);

					System.out.println(mealPrice + "," + mealWithoutSoup);
				} else {
					meals.add(new MealModel("", mealName, null, null,  null));
				}
			}
			for (MealModel m : meals) {
				m.setPrice(mealWithoutSoup);

				m.getName();
			}

			meals.get(0).setPrice(mealPrice - mealWithoutSoup);

			dayMenu.setMeals(meals);
			System.out.println("");
			result.add(dayMenu);

		}
		return result;
	}

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"HH:mm:ss");

	// ANTR FORMAT = [FullDayNaem dd.MM.yyyy]
	private Date getDateFromString(String date) throws ParseException {

		String[] day_date = date.split(" ");

		String myFormat = "dd.MM.yyyy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
		return sdf.parse(day_date[1]);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AntreApplication.class, args);
	}

	// SCHEDULER
	@Scheduled(cron="0 0 8 ? * MON") //every monday at 8:00 am
	public void reportCurrentTime() throws Exception {
		mealService.initWeek();
	}

	@Bean
	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
		return hemf.getSessionFactory();
	}


}
