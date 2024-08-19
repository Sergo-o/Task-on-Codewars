import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static final String EARTHENWARE = "Earthenware";
    public static final String WATERFALL = "Waterfall";
    public static final String FIREPLACE = "Fireplace";
    public static final String WINDOWSILL = "Windowsill";
    public static final String NON_NAME = "_____";

    static Character[] earthenware = {'Q','U','T','H','C','R','D','M','Z'};
    static Character[] waterfall = {'W','E','V','O','X','I','N','G'};
    static Character[] fireplace = {'J','F','A','B','K','P','L','Y'};
    static Character[] windowsill = {'S'};

    static Integer[] placeEarthenware = {1,12,2,11,3,10,4,9,5,8,6,7};
    static Integer[] placeWaterfall = {4,3,5,2,6,1,7,12,8,11,9,10};
    static Integer[] placeFireplace = {7,6,8,5,9,4,10,3,11,2,12,1};
    static Integer[] placeWindowsill = {10,9,11,8,12,7,1,6,2,5,3,4};

    public static String[] places = new String[]{"","","","","","","","","","","",""};

    public static Map<String,Character[]> namePreference = new HashMap<>();
    static {
        namePreference.put(EARTHENWARE,earthenware);
        namePreference.put(WATERFALL,waterfall);
        namePreference.put(FIREPLACE,fireplace);
        namePreference.put(WINDOWSILL,windowsill);
    }
    public static Map<String,Integer[]> nearestPlaces = new HashMap<>();
    static {
        nearestPlaces.put(EARTHENWARE,placeEarthenware);
        nearestPlaces.put(WATERFALL,placeWaterfall);
        nearestPlaces.put(FIREPLACE,placeFireplace);
        nearestPlaces.put(WINDOWSILL,placeWindowsill);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            String line = scan.nextLine();
            List<String> arr = Arrays.stream(line.split(",")).map((str) -> {
                return str.replace("\"","").trim();
            }).toList();
            System.out.println(Arrays.toString(arr.toArray()));
            String[] array = new String[arr.size()];
            System.out.println(Arrays.toString(setTable(arr.toArray(array))));
        }

    }
    public static String[] setTable(String[] theDead) {

        for (int i = 0; i < theDead.length; i++) {
            if(NON_NAME.equals(theDead[i])) continue;
            String nameDead = theDead[i];
            char firsLiterName = nameDead.charAt(0);
            String attribute = preferenceAttribute(firsLiterName);
            if(attribute.equals("not attribute!")){
                return new String[]{"not attribute!"};
            } else{
                seatingPlace(attribute,nameDead);
            }
        }
        for (int i=0 ; i<places.length; i++){
            if(places[i].isEmpty()){
                places[i] = NON_NAME;
            }
        }

        String[] result = places.clone();
        Arrays.fill(places,"");
        return result;
    }

    public static String preferenceAttribute(char ch){
        for(Map.Entry mapEntry : namePreference.entrySet()){
            Character[] arrayCharOfMap = (Character[]) mapEntry.getValue();
            for (char liter : arrayCharOfMap) {
                if(ch == liter){
                    return (String)mapEntry.getKey();}
            }
        }
        return "not attribute!";
    }

    public static Boolean checkingFreePlace(int place, String nameDead){
        if(places[place-1].isEmpty()){
            places[place-1] = nameDead;
            return true;
        }
        return false;
    }

    public static Boolean seatingPlace(String attribute, String nameDead){
        for (int i : nearestPlaces.get(attribute)){
            if(checkingFreePlace(i,nameDead)){
                return true;
            }
        }
        return false;
    }
}
