import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
 
/**
* Java Program to parse/read HTML documents from File using Jsoup library.
* Jsoup is an open source library which allows Java developer to parse HTML
* files and extract elements, manipulate data, change style using DOM, CSS and
* JQuery like method.
*
* @author Javin Paul
*/
public class HTMLParser{
 
	static String companyName[] = new String[2000];
	static String companyNameSave[] = new String[2000];
	static String projectName[] = new String[2000];
	static String settlementPrice[] = new String[2000];
	static String completionDate[] = new String[2000];
	static String quality[] = new String[2000];
	static String mainJob[] = new String[2000];
	static String status[] = new String[2000];
	static int totalNumOfProject = 0;

    public static void main(String args[]) {
    	//getData();
    }
    public static void getData(HtmlPage c){
 
       
        int size = 0;
        String[] companyLinks=new String[100];
        //try {	
            //doc = Jsoup.connect("http://113.240.255.146:802/CompList.aspx").get();
//        	Connection.Response res = Jsoup.connect("http://113.240.255.146:802/CompList.aspx")
//        			.data("aspnetForm.__EVENTTARGET","ctl00$ContentPlaceHolder1$PageNavigator1$LnkBtnNext","aspnetForm.__EVENTARGUMENT","")
//        			.data("aspnetForm.submit()","")
//        			.method(Method.POST)
//        			.execute();
//        			//method(Method.POST).execute();
//        			//.post();,
//      
//        	Document doc2 = res.parse();
//        	System.out.println(doc2.text());
//        	Map<String, String> loginCookies = res.cookies();
//        	Document doc = Jsoup.connect("http://113.240.255.146:802/CompList.aspx")
//        		    .cookies(loginCookies)
//        		    .get();
//        	System.out.println(doc.text());

        	
//        	Connection.Response res = Jsoup.connect("http://113.240.255.146:802/CompList.aspx")
//			.data("aspnetForm.__EVENTTARGET","ctl00$ContentPlaceHolder1$PageNavigator1$LnkBtnNext","aspnetForm.__EVENTARGUMENT","")
//			//ctl00_ContentPlaceHolder1_PageNavigator1_LnkBtnNext
//			//.data("aspnetForm.submit()","true")
//			.method(Method.POST)
//			.execute();
        	String stringOfPage = c.getWebResponse().getContentAsString();
        	Document doc = Jsoup.parse(stringOfPage);
	        //System.out.println("bb"+c.getWebResponse().getContentAsString());

        	//System.out.println("cc"+doc.html());

            Elements links= doc.select("a[href]");
            System.out.println("\nLinks: (%d)"+ links.size());
            String companyString="CompMain1.aspx?T";
            int i = 0;
            
            for (Element link : links) {
            	//if(link!=null && link.attr("abs:href").toLowerCase().contains(companyString.toLowerCase())){
            	if(link!=null && link.toString().toLowerCase().contains(companyString.toLowerCase())){
					//companyLinks[i] = link.attr("abs:href").replace(companyString, "Comp_Yeji.aspx?P");
					companyLinks[i] = "http://113.240.255.146:802/"+link.attr("href").replace(companyString, "Comp_Yeji.aspx?P");
					//System.out.println("company*********"+companyLinks[i]);
					i++;
					size = i;
            	}
            }
            //
            Elements trs = doc.select("table").select("tr");
            int z = 0;
            for(int q = 0;q<trs.size();q++){
                Elements tds = trs.get(q).select("td");
                if (tds.size()>=6){
                    String text = tds.get(1).text();
                    if (text.contains("公司")){
                    	//System.out.println(text);
                    	companyName[z] = text;
                    	z++;
                    }
                }
            }

//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    	int j = 0;
    	boolean hasProject = false;
    	while (j<size){
    		getCompanyWebsite(companyLinks[j], j );	
    		j++;
    	}
//    	for(int l=0; l< totalNumOfProject; l++){
//    		System.out.println(companyNameSave[l]);
//    		System.out.println(projectName[l]);
//    		System.out.println(settlementPrice[l]);
//    		System.out.println(completionDate[l]);
//    		System.out.println(quality[l]);
//    		System.out.println(mainJob[l]);
//    		System.out.println(status[l]);
//    	}
    }
    public static void getCompanyWebsite(String website, int companyNum)
    {
      	Document page;
        String[] projectLinks = new String[100];
        int projectSize = 0;
        boolean flag = false;
        try {
            page = Jsoup.connect(website).get();
            Elements sites = page.select("a[href]");
            String companyString = "Yeji";
            int k = 0;
            for (Element site : sites) {
//            	if(site!=null && site.attr("abs:href").toLowerCase().contains(companyString.toLowerCase())){
//            		projectLinks[k] = site.attr("abs:href");
//					//System.out.println(projectLinks[k]);
//					flag = true;
//					k++;
//					projectSize = k;
//            	}         
            	if(site!=null && site.attr("href").toLowerCase().contains(companyString.toLowerCase())){
            		projectLinks[k] = "http://113.240.255.146:802/"+site.attr("href");
					//System.out.println(projectLinks[k]);
					flag = true;
					k++;
					projectSize = k;
            	}            
        }
        }catch (IOException e) {
            e.printStackTrace();
        }
    	int p = 0;
        while (p<projectSize){
    		getProjectInfo(projectLinks[p], totalNumOfProject, companyNum);
    		totalNumOfProject++;
    		p++;
    	}
    }
    public static void getProjectInfo(String projectWebsite, int p, int companyNum)
    {
    	Document page;
        String[] projectLinks = new String[2000];
        int projectSize = 0;
        try {
            page = Jsoup.connect(projectWebsite).get();
            companyNameSave[p]=companyName[companyNum];
			System.out.println(companyNameSave[p]);

            projectName[p] = page.getElementById("ctl00_ContentPlaceHolder1_lbXiangmumingcheng").text();
        	settlementPrice[p] = page.getElementById("ctl00_ContentPlaceHolder1_lbJiesuanjia").text();
        	completionDate[p] = page.getElementById("ctl00_ContentPlaceHolder1_lbJungongriqi").text();
        	quality[p] = page.getElementById("ctl00_ContentPlaceHolder1_lbzhiliangpingdingqingkuang").text();
        	mainJob[p] = page.getElementById("ctl00_ContentPlaceHolder1_lbzhuyaogongchengliang").text();
        	status[p] = page.getElementById("ctl00_ContentPlaceHolder1_lbjianshezhuangtai").text();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}