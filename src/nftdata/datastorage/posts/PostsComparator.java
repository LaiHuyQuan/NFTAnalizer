package nftdata.datastorage.posts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class PostsComparator {
    public static class DateComparator implements Comparator<String>{
        private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        @Override
        public int compare(String o1, String o2){
            try {
                Date date1 = dateFormat.parse(o1);
                Date date2 = dateFormat.parse(o2);
                return date1.compareTo(date2);
            } catch (ParseException e) {
                return 0;
            }
        }
    }

    public static class numStatComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2){
            Double numStat1 = Double.parseDouble(o1);
            Double numStat2 = Double.parseDouble(o2);
            return Double.compare(numStat1, numStat2);
        }
    }
}
