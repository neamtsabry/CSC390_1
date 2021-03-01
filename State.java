/* A data structure representing the FSA states. */
public class State {
    public int id;
    // tell if a state is a final state
    public Boolean isFinal;

    public State(int id, Boolean isFinal){
        this.id = id;
        this.isFinal = isFinal;
    }
}