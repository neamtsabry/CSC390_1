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


        Random rd = new Random(); // creating Random object
        Boolean randBool = true; 
        // Since the length of a sentence is not restricted, it is not neccessary to use counter in this case

        while (randBool){
            // search in the statesByListOfTransits for possible transits -> transit.toState
            List<Transit> transitList = statesByListOfTransits.get(currentState);
            // System.out.println(transitList.toString());

            if(transitList.size() == 0) break;

            Random randomINT = new Random();
            Transit randTransit = transitList.get(randomINT.nextInt(transitList.size()));

            // get to states list
            List<State> toStates = randTransit.toStates;

            if(toStates.size() == 0) break;

            // update finalLanguage(StringBuilder)
            finalLanguage.append(randTransit.weight);
            
            //currentState = toStates;
            Random randElem = new Random();
            State randToState = toStates.get(randElem.nextInt(toStates.size()));
            currentState = randToState;

            // only when state is final
            if(currentState.isFinal){
                randBool = rd.nextBoolean();
            }
        }

        return finalLanguage.toString();
    }
}
