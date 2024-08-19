import java.util.Map;

public class Table {
        private final String NON_NAME = "_____";
        private final Map<String,Integer> literPosition = Map.of(
                "QUTHCRDMZ",0,
                "WEVOXING",3,
                "JFABKPLY",6,
                "S",9);
        private String[] places = new String[12];

        public Table(String[] theDead){
            searchPosition(theDead);
        }

        private void searchPosition(String[] theDead){
            int place = 0;
            for (String name : theDead){
                for (String liter : literPosition.keySet()){
                    for (char ch : liter.toCharArray()){
                        if (name.charAt(0) == ch){
                            place = literPosition.get(liter);
                            placing(name,place);
                        }
                    }
                }
            }

        }

        private void placing (String nameDead,int place){
            if (places[place] == null){
                places[place] = nameDead;
                return;
            }
            for (int i=1; i<=places.length/2; i++){
                int L = place - i;
                int R = place + i;

                if(L<0){L = places.length + L;}
                if (R>=places.length){R = R - places.length;}

                if (places[L]==null){
                    places[L] = nameDead;
                    return;
                }
                if (places[R]==null){
                    places[R] = nameDead;
                    return;
                }
            }
        }

        public String[] getTable(){
            for (int i = 0; i < places.length; i++) {
                if(places[i] == null){places[i] = NON_NAME;}}
            return places;
        }
    }

