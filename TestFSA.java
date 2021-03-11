import java.util.*;

public class TestFSA {

    public static void main(String[] args) {
        // System.out.println("Language for first FSA: " + generateFSA1()); 
        // System.out.println("Language for second FSA: " + generateFSA2()); 

        for(int i = 0; i < 10; i++){
            System.out.println(generateFSAWords(i+1));
        }
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