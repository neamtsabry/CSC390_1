import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

public class TestFSA {

    public static void main(String[] args) throws IOException {
        // System.out.println("Language for first FSA: " + generateFSA1());
        // System.out.println("Language for second FSA: " + generateFSA2());

        // for (int i = 0; i < 10; i++) {
        //     System.out.println(generateFSAWords(i + 1));
        // }

        generateSentences();

    
    }

    public static void generateSentences() throws IOException {
        
        // List of nouns, verbs, adjectives, determiners, prepositions
        List <String> nouns = Files.readAllLines(new File("word-files/nouns.txt").toPath(), Charset.defaultCharset());
        List <String> verbs = Files.readAllLines(new File("word-files/verbs.txt").toPath(), Charset.defaultCharset());
        List <String> adj = Files.readAllLines(new File("word-files/adj.txt").toPath(), Charset.defaultCharset());
        List <String> det = Files.readAllLines(new File("word-files/det.txt").toPath(), Charset.defaultCharset());
        List <String> prep = Files.readAllLines(new File("word-files/prep.txt").toPath(), Charset.defaultCharset());

        // Punctuations
        String period = ".";
        String comma = ",";
        String qMark = "?";
        String space = " ";
        

        // // initialize states
        // State s0 = new State(0, false); //start state

        // // initialize the FSA
        // FSA fsa = new FSA(s0);

        // // generate the random language
        // String sampleLang = fsa.generateRandomLanguage();
        // return sampleLang;



    }


    public static String generateFSAWords(int count){
        // initialize states
        State s0 = new State(0, true); //start state
        State s1 = new State(1, true);
        State s2 = new State(2, true);

        // initialize the FSA
        FSA fsa = new FSA(s0);
        fsa.addState(s1);
        fsa.addState(s2);

        List<String> consonants = new ArrayList<>();
        consonants.addAll(Arrays.asList("b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z",  "ch", "sh", "th", "zh", "qu"));

        List<String> vowels = new ArrayList<>();
        vowels.addAll(Arrays.asList("a", "e", "i", "o", "u", "ou", "or", "ire", "y"));
        
        for(String cons : consonants){
            fsa.addTransit(new Transit(s0, Arrays.asList(s1), cons));
        }
        
        for(String vowel : vowels){
            fsa.addTransit(new Transit(s1, Arrays.asList(s2), vowel));
            fsa.addTransit(new Transit(s2, Arrays.asList(s1), vowel));
            fsa.addTransit(new Transit(s1, Arrays.asList(s0), vowel));
        }

        // generates the random language
        String sampleLang = fsa.generateRandomLanguage();
        return "Strange Word #" + count + ": " + sampleLang;
    }
}