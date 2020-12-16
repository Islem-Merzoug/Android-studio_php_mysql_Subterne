package solution.citec.oxo.i.com.citecsolution.helper;


import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HourAxisValueFormatter extends ValueFormatter {

    private long referenceTimestamp; // minimum timestamp in your data set
    private DateFormat mDataFormat;
    private Date mDate;

    public HourAxisValueFormatter(long referenceTimestamp) {
        this.referenceTimestamp = referenceTimestamp;
        this.mDataFormat = new SimpleDateFormat("HH:mm:ss");
        this.mDate = new Date();
    }


    @Override
    public String getFormattedValue(float value) {
         //convertedTimestamp = originalTimestamp - referenceTimestamp;
        long convertedTimestamp = (long) value;

        // Retrieve original timestamp
        long originalTimestamp = referenceTimestamp + convertedTimestamp;

        // Convert timestamp to hour:minute
        return getHour(originalTimestamp);
    }



    private String getHour(long timestamp){
        try{
            mDate = new Date(timestamp );


            return mDataFormat.format(mDate);
        }
        catch(Exception ex){
            return "xx";
        }
    }



}