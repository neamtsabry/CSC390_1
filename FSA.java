import java.util.*; 
import java.lang.StringBuilder;
// import State;
// import Transit;

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

        int rand = 1 + (int)(Math.random() * 20);
        int counter = 0;

        while ((! currentState.isFinal) || (counter < rand)){
            // search in the statesByListOfTransits for possible transits -> transit.toState
            List<Transit> transitList = statesByListOfTransits.get(currentState);
            
            for(Transit transit : transitList){
                counter ++;
                State toState = transit.toState;

                // int random_int = (int)(Math.random() * 2);
                // update finalLanguage(StringBuilder)
                finalLanguage.append(transit.weight);
                    
                currentState = toState;
            }
        }

        return finalLanguage.toString();
    }
}
