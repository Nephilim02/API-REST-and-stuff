
package yahoofinance;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.SwingUtilities;

public class YahooFinance {

    public static void main(String[] args) throws MalformedURLException {
        try {
            URL server;
            HttpsURLConnection service;
            BufferedReader input;
            BufferedWriter output;
            String line;
            
            ChartData stonks;
            //parametri richiesta
            String interval = "1d";     //1m|2m|5m|15m|60m|1d
            String symbol = "TSLA";     //nome azienda
            String range = "1mo";       //1d|5d|1mo|3mo|6mo|1y|2y|5y|10y|ytd|max
            
            //String url = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-chart?interval=1d&symbol=TSLA&range=3mo&region=US"; // costruzione dello URL di interrogazione del servizio
            String url = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-chart?interval="+interval+"&symbol="+symbol+"&range="+range+"&region=US"; // costruzione dello URL di interrogazione del servizio
            server = new URL(url);
            service = (HttpsURLConnection)server.openConnection();
            service.setRequestProperty("x-rapidapi-key", "ba0630735amshd36a3e65b7a10b4p14764fjsn331223dbe47a"); // impostazione header richiesta
            service.setRequestProperty("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com"); // impostazione header richiesta
            service.setDoInput(true); // attivazione ricezione
            service.connect(); // connessione al servizio
            int status = service.getResponseCode(); // verifica stato risposta
            if (status != 200) {
                return; // errore
            }
            BufferedReader response = new BufferedReader(new InputStreamReader(service.getInputStream(), "UTF-8"));

            String s = response.lines().collect(Collectors.joining());  //risposta
            System.out.println(s);
            stonks = new ChartData(s);    //interpretazione risposta
            response.close();
            input = new BufferedReader(new InputStreamReader(service.getInputStream(), "UTF-8"));
            input.close();
            
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    GraphPanel.createAndShowGui(stonks.adjclose);
            }
      });
            
        } catch (IOException ex) {
            Logger.getLogger(YahooFinance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

