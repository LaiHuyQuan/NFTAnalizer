package nftdata.datastorage.nft;

import java.util.Comparator;

public class NFTTokenComparator{
    public static class RankSalesOwnersItemsComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return Integer.compare(Integer.parseInt(o1), Integer.parseInt(o2));
        }
    }

    public static class PriceComparator implements Comparator<String>{
        private final double MATIC_TO_ETH = 0.00038033;
        private final double PRIME_TO_ETH = 0.0042;
        private final double AVAX_TO_ETH = 0.01604829;
        private final double SAND_TO_ETH = 0.00023009;
        @Override
        public int compare(String o1, String o2){
            Double price1 = currencyConvert(o1);
            Double price2 = currencyConvert(o2);
            return Double.compare(price1, price2);
        }

        private double currencyConvert(String s){
            String[] subStrings = s.split(" ");
            if(subStrings.length != 2){
                return 0;
            }else {
                double price;
                String sub1 = subStrings[0];
                if (sub1.contains(",")) sub1 = sub1.replace(",", "").trim();
                if (sub1.contains("~")) sub1 = sub1.replace("~", "").trim();
                if (sub1.contains("<")) sub1 = sub1.replace("<", "").trim();
                if (sub1.toUpperCase().endsWith("K")) {
                    String numericPart = sub1.substring(0, sub1.length() - 1);
                    price = Double.parseDouble(numericPart) * 1000;
                }else{
                    price = Double.parseDouble(sub1);
                }
                price = switch (subStrings[1].toUpperCase()) {
                    case "MATIC" -> price * MATIC_TO_ETH;
                    case "PRIME" -> price * PRIME_TO_ETH;
                    case "AVAX" -> price * AVAX_TO_ETH;
                    case "SAND" -> price * SAND_TO_ETH;
                    default -> price;
                };
                return price;
            }
        }
    }

    public static class ChangeComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2){
            double percent1 = changeConvert(o1);
            double percent2 = changeConvert(o2);
            return Double.compare(percent1, percent2);
        }

        private double changeConvert(String s){
            double percent;
            String sub = s;
            if(sub.contains(",")) sub = sub.replace(",","").trim();
            if(sub.contains(" ")) sub = sub.replace(" ", "").trim();
            if(sub.contains("%")) sub = sub.replace("%", "").trim();
            percent = Double.parseDouble(sub);
            return percent;
        }
    }
}
