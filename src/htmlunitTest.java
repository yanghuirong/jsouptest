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


public class htmlunitTest  {
	public static void main(String args[])  {
		try {
			submittingForm();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void submittingForm() throws Exception {
	    try (final WebClient webClient = new WebClient()) {
	      
	     
	        // Get the first page
	        final HtmlPage page1 = webClient.getPage("http://113.240.255.146:802/CompList.aspx");
	        
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
	        final HtmlForm form = page1.getFormByName("aspnetForm");
	        final HtmlAnchor div = page1.getHtmlElementById("ctl00_ContentPlaceHolder1_PageNavigator1_LnkBtnNext");
	       // final HtmlAnchor anchor = page1.getAnchorByName("ctl00_ContentPlaceHolder1_PageNavigator1_LnkBtnNext");

//
	        //final HtmlSubmitInput button = form.getInputByName("ctl00_ContentPlaceHolder1_PageNavigator1_LnkBtnNext");
//	        //final HtmlTextInput textField = form.getInputByName("userid");
//
//	        // Change the value of the text field
//	        //textField.setValueAttribute("root");
//
//	        // Now submit the form by clicking the button and get back the second page.
	        final HtmlPage page2 = div.click();
	        System.out.println("aa"+page2.getWebResponse().getContentAsString());
	    }
	}
}
