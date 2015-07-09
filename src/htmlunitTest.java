import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.CSSParseException;
import org.w3c.css.sac.ErrorHandler;

import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HTMLParserListener;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;

/**
 * use htmlunit to go through next page button and find the all the html file
 * 
 */

public class htmlunitTest  {
	public static void main(String args[])  {
		try {
			submittingForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static HtmlPage[] submittingForm() throws Exception {
	    try (final WebClient webClient = new WebClient()) {
	      
	        final HtmlPage page[] = new HtmlPage[9];

	        // Get the first page
	        page[0] = webClient.getPage("http://113.240.255.146:802/CompList.aspx");
	        // delete the warnings, does not work
	        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");

	        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF); 
	        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
	        webClient.setIncorrectnessListener(new IncorrectnessListener() {

	            @Override
	            public void notify(String arg0, Object arg1) {
	                // TODO Auto-generated method stub
	            }
	        });
	        webClient.getOptions().setCssEnabled(false);
	        webClient.setCssErrorHandler(new ErrorHandler() {

	            @Override
	            public void warning(CSSParseException exception) throws CSSException {
	                // TODO Auto-generated method stub

	            }

	            @Override
	            public void fatalError(CSSParseException exception) throws CSSException {
	                // TODO Auto-generated method stub

	            }

	            @Override
	            public void error(CSSParseException exception) throws CSSException {
	                // TODO Auto-generated method stub

	            }
	        });
	        webClient.getOptions().setThrowExceptionOnScriptError(false);
	        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
	        // Get the form that we are dealing with and within that form, 
	        // find the submit button and the field that we want to change.
	        int i = 0;
	        for (; i < 8; i++){
	        	final HtmlForm form = page[i].getFormByName("aspnetForm");
	        	final HtmlAnchor div = page[i].getHtmlElementById("ctl00_ContentPlaceHolder1_PageNavigator1_LnkBtnNext");
	        	page[i+1] = div.click();        
	        }
	        return page;
	    }
	}
}
