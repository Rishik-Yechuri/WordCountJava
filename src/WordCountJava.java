import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordCountJava {
    public static void main(String args[]) throws IOException {
        //Creates a scanned to read from Quickstart.dat
        Scanner s = new Scanner(new File("Quickstart.dat"));
        //Creates an arraylist to store the words
        ArrayList<String> wordlist = new ArrayList<String>();
        //Adds every words in the file into the arraylist
        while (s.hasNext()) {
            wordlist.add(s.next());
        }
        s.close();
        //Creates an arraylist of words to remove
        ArrayList<String> wordsToRemove = new ArrayList<>(Arrays.asList("for", "and", "nor", "but", "or", "yet", "so"));
        //Removes the words from wordList
        wordlist.removeAll(wordsToRemove);
        //Gets the number of words in wordList
        double totalWords = wordlist.size();
        //Stores wordList as an object array
        Object[] objectArray = wordlist.toArray();
        //Stores wordList as a String array
        String[] finalArray = Arrays.asList(objectArray).toArray(new String[objectArray.length]);
        //Does some sorting
        Map<String, Long> result =
                Arrays.stream(finalArray)
                        .collect(Collectors.groupingBy(Function.identity(),
                                Collectors.counting()));
        //Stores the count values for the 20 most common words
        List<Long> collect = result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(20)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
        LinkedHashMap<String, Long> reverseSortedMap = new LinkedHashMap<>();

        result.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        //Stores the 20 most common words
        List<String> finalList = reverseSortedMap.entrySet()
                .stream()
                .map(entry -> entry.getKey())
                .limit(20)
                .collect(Collectors.toList());
        //Loops through finalList
        for(int x=0;x<finalList.size();x++){
            //Prints the words,and the percentage of that words
            System.out.println(finalList.get(x) + ":" + (double)collect.get(x)/totalWords*100 + "%");
        }
    }
}
