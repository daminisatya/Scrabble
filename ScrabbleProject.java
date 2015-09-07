import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class ScrabbleProject {

	public static void main(String[] str) throws IOException {
		
		if(str==null || str.length<=0 || str[0]==null || str[0].trim().length()!=7){
			
			System.out.println("Must provide input value wih exact 7 letters");
			System.exit(-1);
		
		}
		
		String input = str[0];
		System.out.println("Input letters:"+str[0]);
		Map<String, Integer> wordScoringMap = new HashMap<String, Integer>();
		List<String> wordList = getWordsList();
		Map<String, Integer> scoringTable = getScoringTable();

		for (String word : wordList) {
			word = word.trim();
			if (word.equals(""))
				continue;
			int score = 0;
			for (int i = 0; i < input.length(); i++) {
				String ch = input.substring(i, i + 1);
				for (int j = 0; j < word.length(); j++) {
					String charW = word.substring(j, j + 1);
					if (charW.equalsIgnoreCase(ch))
						score = score + scoringTable.get(ch);
				}
			}
			wordScoringMap.put(word, score);
		}

		wordScoringMap = sortMapByValue(wordScoringMap);
		for (String word : wordScoringMap.keySet()) {
			System.out.println(word + ":" + wordScoringMap.get(word));
		}
		
		outputToFile(input, wordScoringMap);

	}

	public static List<String> getWordsList() throws IOException {

		List<String> wordList = new ArrayList<String>();
		FileReader reader = new FileReader("sowpods.txt");
		BufferedReader br = new BufferedReader(reader);
		String line = null;
		while ((line = br.readLine()) != null) {
			wordList.add(line);
		}

		br.close();
		reader.close();
		return wordList;
	}

	public static Map<String, Integer> getScoringTable() {
		Map<String, Integer> scoringTable = new HashMap<String, Integer>();
		scoringTable.put("A", 1);
		scoringTable.put("B", 3);
		scoringTable.put("C", 3);
		scoringTable.put("D", 2);
		scoringTable.put("E", 1);
		scoringTable.put("F", 4);
		scoringTable.put("G", 2);
		scoringTable.put("H", 4);
		scoringTable.put("I", 1);
		scoringTable.put("J", 8);
		scoringTable.put("K", 5);
		scoringTable.put("L", 1);
		scoringTable.put("M", 13);
		scoringTable.put("N", 1);
		scoringTable.put("O", 1);
		scoringTable.put("P", 3);
		scoringTable.put("Q", 10);
		scoringTable.put("R", 1);
		scoringTable.put("S", 1);
		scoringTable.put("T", 1);
		scoringTable.put("U", 1);
		scoringTable.put("V", 4);
		scoringTable.put("W", 4);
		scoringTable.put("X", 8);
		scoringTable.put("Y", 4);
		scoringTable.put("Z", 10);
		return scoringTable;
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(
			Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(
				map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				// return (o1.getValue()).compareTo( o2.getValue() );
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	
	public static void outputToFile(String inputString,Map<String, Integer> wordScoringMap) throws IOException{
		
		FileWriter fw=new FileWriter("output.txt");
		fw.write("Input String:"+inputString);
		fw.write("\nResult:");
		for (String word : wordScoringMap.keySet()) {
			fw.write("\n"+word + ":" + wordScoringMap.get(word));
		}
		
		fw.close();
	}

}