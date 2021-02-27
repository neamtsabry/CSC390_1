/* A data structure representing the FSA states. */
public class State {
    public int id;
    // tell if a state is a final state
    public bool isFinal;

    public State(int id, bool isFinal){
        this.id = id;
        this.isFinal = isFinal;
    }
}