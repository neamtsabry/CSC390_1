import java.util.ArrayList;
import java.util.List;

/* A data structure representing the FSA state transitions. */
public class Transit {
    public State fromState;
    public List<State> toStates;
    public String weight;
    
    public Transit(State fromState, List<State> toStates, String weight){
        this.fromState = fromState;
        this.toStates = toStates;
        this.weight = weight;
    }
}