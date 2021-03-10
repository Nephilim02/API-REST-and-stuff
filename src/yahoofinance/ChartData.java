package yahoofinance;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class ChartData {
    ArrayList timestamps = new ArrayList();
    ArrayList dates = new ArrayList();
    ArrayList open = new ArrayList();
    ArrayList high = new ArrayList();
    ArrayList low = new ArrayList();
    ArrayList close = new ArrayList();
    ArrayList volume = new ArrayList();
    ArrayList adjclose = new ArrayList();
    String error;
    
    public ChartData (String s){
        
        Collections.addAll(timestamps, s.substring(s.indexOf("timestamp") + 12, s.indexOf("]", s.indexOf("timestamp"))).split(","));
        for(int i=0; i<timestamps.size(); i++)
            dates.add(new Date(( new Timestamp(Long.parseLong((String)timestamps.get(i))*1000)).getTime()));
        System.out.println("timestamps: " + timestamps.toString());
        System.out.println("dates: " + dates.toString());
        
        Collections.addAll(open, s.substring(s.indexOf("open") + 7, s.indexOf("]", s.indexOf("open"))).split(","));
        System.out.println("open: " + open.toString());
        
        Collections.addAll(high, s.substring(s.indexOf("high") + 7, s.indexOf("]", s.indexOf("high"))).split(","));
        System.out.println("high: " + high.toString());
        
        Collections.addAll(low, s.substring(s.indexOf("low") + 6, s.indexOf("]", s.indexOf("low"))).split(","));
        System.out.println("low: " + low.toString());
        
        Collections.addAll(close, s.substring(s.indexOf("close") + 8, s.indexOf("]", s.indexOf("close"))).split(","));
        System.out.println("close: " + close.toString());
        
        Collections.addAll(volume, s.substring(s.indexOf("volume") + 9, s.indexOf("]", s.indexOf("volume"))).split(","));
        System.out.println("volume: " + volume.toString());
        
        Collections.addAll(adjclose, s.substring(s.indexOf("adjclose") + 24, s.indexOf("]", s.indexOf("adjclose"))).split(","));
        for(int i=0; i<adjclose.size(); i++){
            adjclose.set(i, Double.parseDouble((String)adjclose.get(i)));
        }
        System.out.println("adjclose: " + adjclose.toString());
        
        error = s.substring(s.indexOf("error") + 7, s.length()-2);
        System.out.println("error: " + error);
        
    }
}
