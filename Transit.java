import java.util.ArrayList;
import java.util.List;

// import State;
/* A data structure representing the FSA state transitions. */
public class Transit {
    public State fromState;
    public List<State> toStates;
    // either 0 or 1
    public int weight;
    public Transit(State fromState, List<State> toStates, int weight){
        this.fromState = fromState;
        this.toStates = toStates;
        this.weight = weight;
    }
}