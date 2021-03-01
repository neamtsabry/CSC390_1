// import State;

/* A data structure representing the FSA state transitions. */
public class Transit {
    public State fromState;
    public State toState;
    // either 0 or 1
    public int weight;

    public Transit(State fromState, State toState, int weight){
        this.fromState = fromState;
        this.toState = toState;
        this.weight = weight;
    }
}