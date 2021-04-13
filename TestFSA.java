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

    private static List <String> nouns;
    private static List <String> verbs;
    private static List <String> adj;
    private static List <String> det;
    private static List <String> prep;
    private static List <String> aux;
    private static List <String> whAdverb;

    public static void main(String[] args) throws IOException {
        nouns = Files.readAllLines(new File("word-files/nouns.txt").toPath(), Charset.defaultCharset());
        verbs = Files.readAllLines(new File("word-files/verbs.txt").toPath(), Charset.defaultCharset());
        adj = Files.readAllLines(new File("word-files/adj.txt").toPath(), Charset.defaultCharset());
        det = Files.readAllLines(new File("word-files/det.txt").toPath(), Charset.defaultCharset());
        prep = Files.readAllLines(new File("word-files/prep.txt").toPath(), Charset.defaultCharset());
        aux = Files.readAllLines(new File("word-files/aux.txt").toPath(), Charset.defaultCharset());
        whAdverb = Files.readAllLines(new File("word-files/whAdverb.txt").toPath(), Charset.defaultCharset());
        System.out.println(generateS(10));
    }


    public static String generateS(int depth){

        Random rd = new Random();

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

        sfsa.addTransit(new Transit(s0, Arrays.asList(s1), generateNP(depth-1)));
        sfsa.addTransit(new Transit(s0, Arrays.asList(s3), aux.get(rd.nextInt(aux.size()-1))));
        sfsa.addTransit(new Transit(s1, Arrays.asList(s2), space));
        sfsa.addTransit(new Transit(s2, Arrays.asList(s4), generateVP(depth-1)));
        sfsa.addTransit(new Transit(s2, Arrays.asList(s5), aux.get(rd.nextInt(aux.size()-1))));
        sfsa.addTransit(new Transit(s4, Arrays.asList(s6), period));
        sfsa.addTransit(new Transit(s5, Arrays.asList(s7), space));
        sfsa.addTransit(new Transit(s7, Arrays.asList(s4), generateVP(depth-1)));
        sfsa.addTransit(new Transit(s3, Arrays.asList(s8), space));
        sfsa.addTransit(new Transit(s8, Arrays.asList(s9), generateNP(depth-1)));
        sfsa.addTransit(new Transit(s9, Arrays.asList(s10), space));
        sfsa.addTransit(new Transit(s10, Arrays.asList(s11), generateVP(depth-1)));
        sfsa.addTransit(new Transit(s11, Arrays.asList(s12), qMark));

        // generates a random S phrase structure sequence
        String sampleS = sfsa.generateRandomLanguage();
        return "Sample S: " + sampleS;
    }

    public static String generateNP(int depth){
        if (depth <= 0){
            return empty;
        }

        Random rd = new Random();

        State s0 = new State(0, false);
        State s1 = new State(1, false);
        State s2 = new State(2, false);
        State s3 = new State(3, false);
        State s4 = new State(4, false);
        State s5 = new State(5, true);
        State s6 = new State(6, true);
        State s7 = new State(7, true);

        FSA npfsa = new FSA(s0);
        for (State state : Arrays.asList(s1, s2, s3, s4, s5, s6, s7)){
            npfsa.addState(state);
        }

        npfsa.addTransit(new Transit(s0, Arrays.asList(s1), det.get(rd.nextInt(det.size()-1))));
        npfsa.addTransit(new Transit(s0, Arrays.asList(s1), adj.get(rd.nextInt(adj.size()-1))));
        npfsa.addTransit(new Transit(s0, Arrays.asList(s3), nouns.get(rd.nextInt(nouns.size()-1))));
        npfsa.addTransit(new Transit(s1, Arrays.asList(s2), space));
        npfsa.addTransit(new Transit(s2, Arrays.asList(s1), adj.get(rd.nextInt(adj.size()-1))));
        npfsa.addTransit(new Transit(s2, Arrays.asList(s3), nouns.get(rd.nextInt(nouns.size()-1))));
        npfsa.addTransit(new Transit(s3, Arrays.asList(s6), empty));
        npfsa.addTransit(new Transit(s3, Arrays.asList(s4), space));
        npfsa.addTransit(new Transit(s4, Arrays.asList(s5), generatePP(depth-1)));
        npfsa.addTransit(new Transit(s4, Arrays.asList(s7), generateWHNP(depth-1)));
    
        // generates a random NP phrase structure sequence
        String sampleNP = npfsa.generateRandomLanguage();
        return sampleNP;
    }

    public static String generatePP(int depth){
        if (depth <= 0){
            return empty;
        }
        Random rd = new Random();
        State s0 = new State(0, false);
        State s1 = new State(1, false);
        State s2 = new State(2, false);
        State s3 = new State(2, true);

        FSA ppfsa = new FSA(s0);
        for (State state : Arrays.asList(s1, s2, s3)){
            ppfsa.addState(state);
        }

        ppfsa.addTransit(new Transit(s0, Arrays.asList(s1), prep.get(rd.nextInt(prep.size()-1))));
        ppfsa.addTransit(new Transit(s1, Arrays.asList(s2), space));
        ppfsa.addTransit(new Transit(s2, Arrays.asList(s3), generateNP(depth-1)));

        // generates a random NP phrase structure sequence
        String samplePP = ppfsa.generateRandomLanguage();
        return samplePP;
    }

    public static String generateVP(int depth){
        if (depth <= 0){
            return empty;
        }
        Random rd = new Random();
        State s0 = new State(0, false);
        State s1 = new State(1, true);
        State s2 = new State(2, false);
        State s3 = new State(3, true);
        State s4 = new State(4, false);
        State s5 = new State(5, true);
        State s6 = new State(6, true);
        State s7 = new State(7, false);
        State s8 = new State(8, true);

        FSA vpfsa = new FSA(s0);
        for (State state : Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8)){
            vpfsa.addState(state);
        }

        vpfsa.addTransit(new Transit(s0, Arrays.asList(s1), verbs.get(rd.nextInt(verbs.size()-1))));
        vpfsa.addTransit(new Transit(s1, Arrays.asList(s2), space));
        vpfsa.addTransit(new Transit(s2, Arrays.asList(s3), generateNP(depth-1)));
        vpfsa.addTransit(new Transit(s3, Arrays.asList(s4), space));
        vpfsa.addTransit(new Transit(s4, Arrays.asList(s5), generatePP(depth-1)));
        vpfsa.addTransit(new Transit(s5, Arrays.asList(s4), space));
        vpfsa.addTransit(new Transit(s0, Arrays.asList(s6), verbs.get(rd.nextInt(verbs.size()-1))));
        vpfsa.addTransit(new Transit(s6, Arrays.asList(s7), space));
        vpfsa.addTransit(new Transit(s7, Arrays.asList(s8), adj.get(rd.nextInt(adj.size()-1))));

        // generates a random NP phrase structure sequence
        String sampleVP = vpfsa.generateRandomLanguage();
        return sampleVP;
    }

    public static String generateWHNP(int depth){
        if (depth <= 0){
            return empty;
        }
        Random rd = new Random();
        State s0 = new State(0, false);
        State s1 = new State(1, false);
        State s2 = new State(2, false);
        State s3 = new State(3, false);
        State s4 = new State(4, false);
        State s5 = new State(5, false);
        State s6 = new State(6, false);
        State s7 = new State(7, false);
        State s8 = new State(8, false);
        State s9 = new State(9, false);
        State s10 = new State(10, false);
        State s11 = new State(11, false);
        State s12 = new State(12, true);

        FSA wfsa = new FSA(s0);
        for (State state : Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12)){
            wfsa.addState(state);
        }

        wfsa.addTransit(new Transit(s0, Arrays.asList(s1), comma));
        wfsa.addTransit(new Transit(s1, Arrays.asList(s2), space));
        wfsa.addTransit(new Transit(s2, Arrays.asList(s3), whAdverb.get(rd.nextInt(whAdverb.size()-1))));
        wfsa.addTransit(new Transit(s3, Arrays.asList(s4), space));
        wfsa.addTransit(new Transit(s4, Arrays.asList(s5), generateNP(depth-1)));
        wfsa.addTransit(new Transit(s4, Arrays.asList(s6), aux.get(rd.nextInt(aux.size()-1))));
        wfsa.addTransit(new Transit(s5, Arrays.asList(s7), space));
        wfsa.addTransit(new Transit(s7, Arrays.asList(s8), aux.get(rd.nextInt(aux.size()-1))));
        wfsa.addTransit(new Transit(s6, Arrays.asList(s8), empty));
        wfsa.addTransit(new Transit(s8, Arrays.asList(s9), space));
        wfsa.addTransit(new Transit(s9, Arrays.asList(s10), generateVP(depth-1)));
        wfsa.addTransit(new Transit(s10, Arrays.asList(s11), comma));
        wfsa.addTransit(new Transit(s11, Arrays.asList(s12), space));

        // generates a random S phrase structure sequence
        String sampleW = wfsa.generateRandomLanguage();
        return sampleW;
    }
}