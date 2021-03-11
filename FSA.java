import java.util.*; 
import java.lang.StringBuilder;

public class FSA {
    // A StringBuilder for building the final language
    public StringBuilder finalLanguage;

    // A hashmap maps a state to a list of the OUTGOING transits (excluding incoming transits)
    private Map<State, List<Transit>> statesByListOfTransits = new HashMap<State, List<Transit>>();
    
    // A start state of the FSA
    private State startState;

    public FSA(State startState){
        this.startState = startState;
        List<Transit> transits = new ArrayList<>();
        statesByListOfTransits.put(startState, transits);
        this.finalLanguage = new StringBuilder(); 
    }

    // Add the new states into the FSA, not including the start state b/c it was already taken in 
    // as a param for the constructor
    public void addState(State newState){
        // update the Map statesByListOfTransits by puting the state and an empty list of Transits
        statesByListOfTransits.put(newState, new ArrayList<>());
    }

    public void addTransit(Transit newTransit){
        // update the Transit list
        List<Transit> updatedList = statesByListOfTransits.get(newTransit.fromState);
        updatedList.add(newTransit);

        // update the Map statesByListOfTransits by locating "newTransit.fromState" in the map
        statesByListOfTransits.put(newTransit.fromState, updatedList);
    }

    public String generateRandomLanguage(){
        State currentState = startState;

        // int rand = 3 + (int)(Math.random() * 15);
        Random r = new Random();
        int max = 15;
        int min = 3;
        int rand = r.nextInt((max - min) + 1) + min;
        int counter = 0;

        while (counter < rand){
            // search in the statesByListOfTransits for possible transits -> transit.toState
            List<Transit> transitList = statesByListOfTransits.get(currentState);
            // System.out.println(transitList.toString());

            Random random = new Random();
            Transit randTransit = transitList.get(random.nextInt(transitList.size()));

            // incremennt counter until it reaches the desired random int
            counter ++;

            // get to states list
            List<State> toStates = randTransit.toStates;

            // update finalLanguage(StringBuilder)
            finalLanguage.append(randTransit.weight);
            
            //currentState = toStates;
            Random randElem = new Random();
            State randToState = toStates.get(randElem.nextInt(toStates.size()));
            currentState = randToState;
        }

        return finalLanguage.toString();
    }
}
