import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordCountJava {
    public static void main(String args[]) throws IOException {
        Scanner s = new Scanner(new File("Quickstart.dat"));
        ArrayList<String> wordlist = new ArrayList<String>();
        while (s.hasNext()) {
            wordlist.add(s.next());
        }
        s.close();
        ArrayList<String> wordsToRemove = new ArrayList<>(Arrays.asList("for", "and", "nor", "but", "or", "yet", "so"));
        wordlist.removeAll(wordsToRemove);
        double totalWords = wordlist.size();
        Object[] objectArray = wordlist.toArray();
        String[] finalArray = Arrays.asList(objectArray).toArray(new String[objectArray.length]);
        Map<String, Long> result =
                Arrays.stream(finalArray)
                        .collect(Collectors.groupingBy(Function.identity(),
                                Collectors.counting()));
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

        List<String> finalList = reverseSortedMap.entrySet()
                .stream()
                .map(entry -> entry.getKey())
                .limit(20)
                .collect(Collectors.toList());
        for(int x=0;x<finalList.size();x++){
            System.out.println(finalList.get(x) + ":" + (double)collect.get(x)/totalWords*100 + "%");
        }
    }
}
