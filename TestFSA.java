import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;


public class TestFSA {
    // Punctuations
    private static final String period = ".";
    private static final String comma = ",";
    private static final String qMark = "?";
    private static final String space = " ";
    private static final String empty = "";

    // Phrase Structures
    private static final String np = "NP";
    private static final String sentence = "S";
    private static final String aux = "AUX";
    private static final String verbPhrase = "VP";
    private static final String nounPhrase = "NP";
    private static final String determiner = "DET";
    private static final String adjective = "ADJ";
    private static final String noun = "N";
    private static final String whAdverbPhrase = "WHNP";
    private static final String preposition = "PREP";
    private static final String prepositionalPhrase = "PP";
    private static final String verb = "V";
    private static final String whAdverb = "WP";


    public static void main(String[] args) throws IOException {
        System.out.println(generateNP());
        System.out.println(generateS());
    }

    // This function should be implemented in the next assignment so we can leave it here
    public static void generateTrueSentences() throws IOException {
        // List of nouns, verbs, adjectives, determiners, prepositions
        List <String> nouns = Files.readAllLines(new File("word-files/nouns.txt").toPath(), Charset.defaultCharset());
        List <String> verbs = Files.readAllLines(new File("word-files/verbs.txt").toPath(), Charset.defaultCharset());
        List <String> adj = Files.readAllLines(new File("word-files/adj.txt").toPath(), Charset.defaultCharset());
        List <String> det = Files.readAllLines(new File("word-files/det.txt").toPath(), Charset.defaultCharset());
        List <String> prep = Files.readAllLines(new File("word-files/prep.txt").toPath(), Charset.defaultCharset());
    }

    public static String generateS(){
        State s0 = new State(0, false);
        State s1 = new State(1, false);
        State s2 = new State(2, false);
        State s3 = new State(3, false);
        State s4 = new State(4, false);
        State s5 = new State(5, false);
        State s6 = new State(6, true);
        State s7 = new State(7, false);
        State s8 = new State(8, false);
        State s9 = new State(9, false);
        State s10 = new State(10, false);
        State s11 = new State(11, false);
        State s12 = new State(12, true);

        FSA sfsa = new FSA(s0);
        for (State state : Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12)){
            sfsa.addState(state);
        }

        sfsa.addTransit(new Transit(s0, Arrays.asList(s1), nounPhrase));
        sfsa.addTransit(new Transit(s0, Arrays.asList(s3), aux));
        sfsa.addTransit(new Transit(s1, Arrays.asList(s2), space));
        sfsa.addTransit(new Transit(s2, Arrays.asList(s4), verbPhrase));
        sfsa.addTransit(new Transit(s2, Arrays.asList(s5), aux));
        sfsa.addTransit(new Transit(s4, Arrays.asList(s6), period));
        sfsa.addTransit(new Transit(s5, Arrays.asList(s7), space));
        sfsa.addTransit(new Transit(s7, Arrays.asList(s4), verbPhrase));
        sfsa.addTransit(new Transit(s3, Arrays.asList(s8), space));
        sfsa.addTransit(new Transit(s8, Arrays.asList(s9), nounPhrase));
        sfsa.addTransit(new Transit(s9, Arrays.asList(s10), space));
        sfsa.addTransit(new Transit(s10, Arrays.asList(s11), verbPhrase));
        sfsa.addTransit(new Transit(s11, Arrays.asList(s12), qMark));

        // generates a random S phrase structure sequence
        String sampleS = sfsa.generateRandomLanguage();
        return "Sample S: " + sampleS;
    }

    public static String generateNP(){
        State s0 = new State(0, false);
        State s1 = new State(1, false);
        State s2 = new State(2, false);
        State s3 = new State(3, true);
        State s4 = new State(4, false);
        State s5 = new State(5, false);


        FSA npfsa = new FSA(s0);
        for (State state : Arrays.asList(s1, s2, s3, s4, s5)){
            npfsa.addState(state);
        }

        npfsa.addTransit(new Transit(s0, Arrays.asList(s1), determiner));
        npfsa.addTransit(new Transit(s0, Arrays.asList(s1), adjective));
        npfsa.addTransit(new Transit(s0, Arrays.asList(s3), noun));
        npfsa.addTransit(new Transit(s1, Arrays.asList(s2), space));
        npfsa.addTransit(new Transit(s2, Arrays.asList(s1), adjective));
        npfsa.addTransit(new Transit(s2, Arrays.asList(s3), noun));
        npfsa.addTransit(new Transit(s3, Arrays.asList(s4), space));
        npfsa.addTransit(new Transit(s3, Arrays.asList(s5), space));
        npfsa.addTransit(new Transit(s4, Arrays.asList(s3), prepositionalPhrase));
        npfsa.addTransit(new Transit(s5, Arrays.asList(s3), whAdverbPhrase));

        // generates a random NP phrase structure sequence
        String sampleNP = npfsa.generateRandomLanguage();
        return "Sample NP: " + sampleNP;
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