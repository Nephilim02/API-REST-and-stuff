
package yahoofinance;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 *
 * @author ricca
 */
public class YahooFinance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException {
        try {
            URL server;
            HttpsURLConnection service;
            BufferedReader input;
            BufferedWriter output;
            String line;
            
            String url = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-chart?interval=1d&symbol=TSLA&range=3mo&region=US"; // costruzione dello URL di interrogazione del servizio
            server = new URL(url);
            service = (HttpsURLConnection)server.openConnection();
            service.setRequestProperty("x-rapidapi-key", "ba0630735amshd36a3e65b7a10b4p14764fjsn331223dbe47a"); // impostazione header richiesta: formato risposta (XML)
            service.setRequestProperty("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com");//todo di richiesta GET
            System.out.println(service.getRequestMethod());
            service.setDoInput(true); // attivazione ricezione
            service.connect(); // connessione al servizio
            int status = service.getResponseCode(); // verifica stato risposta
            if (status != 200) {
                return; // errore
            }
            BufferedReader response = new BufferedReader(new InputStreamReader(service.getInputStream(), "UTF-8"));

            String s = response.lines().collect(Collectors.joining());
            System.out.println(s);
            response.close();
            input = new BufferedReader(new InputStreamReader(service.getInputStream(), "UTF-8"));
            
            input.close();
        } catch (IOException ex) {
            Logger.getLogger(YahooFinance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

