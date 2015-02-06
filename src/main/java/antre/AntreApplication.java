package antre;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import antre.model.DayMenu;

@EnableScheduling
@RestController
@EnableAutoConfiguration
public class AntreApplication {

    @RequestMapping("/week") 
    List<DayMenu> getTodayMenu() throws ParseException {
    	List<DayMenu> result = new ArrayList<DayMenu>();
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.wantrejce.pl").get();
        } catch (IOException e) {
        }
        
        Elements days = doc.select("ul#subList2 li:matches(Pon|Wt|Śr|Czw|Pią)");
        for(Element day : days ) {
        	DayMenu dayMenu = new DayMenu();
        	System.out.println(day.toString());
        	Element dayname = day.select("h2").first();  //DATA
        	
        	dayMenu.setDate(getDateFromString(dayname.text()));
        	
        	Elements daymenu = day.select("h4>p:has(strong)");
        	
        	
        	System.out.println(dayname.text());
        	System.out.println("---------------------");
        	
        	dayMenu.setSoup(daymenu.first().text());
        	dayMenu.setMealA(daymenu.get(1).text());
        	dayMenu.setMealB(daymenu.get(2).text());

        	//for(Element menupos : daymenu) {
        	//	System.out.println(menupos.text());
        	//}
        	System.out.println("");
        	result.add(dayMenu);
        	
        }
        return result;
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 86400)
    public void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
    }
    
    //ANTR FORMAT = [FullDayNaem dd.MM.yyyy]
    private Date getDateFromString(String date) throws ParseException {
    	
    	String[] day_date = date.split(" ");
    	
    	String myFormat = "dd.MM.yyyy"; // In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        return sdf.parse(day_date[1]);
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AntreApplication.class, args);
        //SpringApplication.run(WeekMenuDownloader.class, args);
        
    }
    
    //TESTs

}
