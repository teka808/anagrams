package co.kr.ibeauty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/*
* TODO
* @Input:String
* @Output ArrayList<ArrayList<String>>
* */
public class Anagram {

    //Input text function
    public ArrayList<ArrayList<String>> findAnagrams(String sentence) {
        //removing all quotes and get anagrams
        sentence = sentence.replaceAll("\"", "").replaceAll("\'", "");
        return getWordsWithAnagrams(sentence.toLowerCase());
    }

    //finding anagrams
    private ArrayList<ArrayList<String>> getWordsWithAnagrams(String sentence) {
        Map<Integer, Map<Integer, String>> map = new HashMap<>();
        String[] arr = sentence.split("\\W+", -1);

        //finding anagrams
        for (int i = 0; i < arr.length; i++) {

            int hash = sortStringAndGetHash(arr[i]);
            int hash_origin = arr[i].hashCode();

            if (hash < 1) continue;

            if (!map.containsKey(hash)) {
                map.put(hash, addNewItem(hash_origin, arr[i]));
            } else if (!map.get(hash).containsKey(hash_origin)) { //checking for repeat word in HashMap
                map.get(hash).put(hash_origin, arr[i]);
            }
        }
        return filteringArray(map);
    }

    //adding new word to HashMap
    private Map<Integer, String> addNewItem(int hash_origin, String value){
        Map<Integer, String> newItem = new HashMap<>();
        newItem.put(hash_origin, value);
        return newItem;
    }

    //sorting word by A-Z and get hashcode
    private int sortStringAndGetHash(String inputString) {
        char tempArray[] = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray).hashCode();
    }

    //clearing HashMap from single words,
    //switch to ArrayList
    private ArrayList<ArrayList<String>> filteringArray(Map<Integer, Map<Integer, String>> map) {
        Map<Integer, Map<Integer, String>> _map = new HashMap<>(map);
        ArrayList<ArrayList<String>> arr = new ArrayList<>();
        Iterator it = _map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (map.get(pair.getKey()).size() >= 2){
                arr.add(new ArrayList<>(map.get(pair.getKey()).values()));
            }
        }
        return arr;
    }

    @Test
    public void testAnagram() {
        String text = "stop post. tops pots, hello olleh lab";
        Anagram anagram = new Anagram();
        ArrayList<ArrayList<String>> arr = anagram.findAnagrams(text);
        for (int i = 0; i < arr.size(); i++) {
            ArrayList<String> values = arr.get(i);
            System.out.println("===========Anagrams=============== ");
            for (int j = 0; j < values.size(); j++) {
                System.out.println(values.get(j));
            }
        }
    }

}
