// import FSA;
// import State;
// import Transit;
// import TestFSA.FSA;

/* 
    The sample testing class for generating a language based on the prompts:
    (TODO)
    This part so far only tests for "0(01)*", but also needs "1(011)*0*"
*/
public class TestFSA {
    
    public static void main(String[] args) {
        //+ "\n" + "Language for first FSA: " + generateFSA2();
        System.out.println("Language for first FSA: " + generateFSA1()); 
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
        fsa.addTransit(new Transit(s0, s1, 0));
        fsa.addTransit(new Transit(s1, s2, 0));
        fsa.addTransit(new Transit(s2, s1, 1));

        // generates the random language
        String sampleLang = fsa.generateRandomLanguage();
        return "A sample language for '0(01)*' is: " + sampleLang;
    }

    public String generateFSA2(){

    }
}