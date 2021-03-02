import java.util.Arrays;

public class TestFSA {

    public static void main(String[] args) {
        System.out.println("Language for first FSA: " + generateFSA1()); 
        System.out.println("Language for first FSA: " + generateFSA2()); 
    }

    public static String generateFSA1(){
        // initialize states
        State s0 = new State(0, false); //start state
        State s1 = new State(1, true);
        State s2 = new State(2, false);

        // initialize the FSA
        FSA fsa = new FSA(s0);
        fsa.addState(s1);
        fsa.addState(s2);
        fsa.addTransit(new Transit(s0, Arrays.asList(s1), 0));
        fsa.addTransit(new Transit(s1, Arrays.asList(s2), 0));
        fsa.addTransit(new Transit(s2, Arrays.asList(s1), 1));

        // generates the random language
        String sampleLang = fsa.generateRandomLanguage();
        return "A sample language for '0(01)*' is: " + sampleLang;
    }

    public static String generateFSA2(){
        // initialize states
        State s0 = new State(0, false); //start state
        State s1 = new State(1, true);
        State s2 = new State(2, false);
        State s3 = new State(3, false);
        State s4 = new State(4, true);
        State s5 = new State(5, true);

        // initialize the FSA
        FSA fsa = new FSA(s0);
        fsa.addState(s1);
        fsa.addState(s2);
        fsa.addState(s3);
        fsa.addState(s4);
        fsa.addState(s5);
        fsa.addTransit(new Transit(s0, Arrays.asList(s1), 1));
        fsa.addTransit(new Transit(s1, Arrays.asList(s2), 0));
        fsa.addTransit(new Transit(s2, Arrays.asList(s3), 1));
        fsa.addTransit(new Transit(s3, Arrays.asList(s4), 1));
        fsa.addTransit(new Transit(s4, Arrays.asList(s5,s2), 0));
        fsa.addTransit(new Transit(s5, Arrays.asList(s5), 0));

        // generates the random language
        String sampleLang = fsa.generateRandomLanguage();
        return "A sample language for '1(011)*0*' is: " + sampleLang;


    }
}