package tp5.ejercicio1;

import java.util.ArrayList;
import java.util.List;

import tp5.ejercicio1.Sequence;

public class Queue <T> extends Sequence {
    protected List<T> data;
    
    public Queue() {
        this.data = new ArrayList<T>();
    }
    
    public void enqueue(T t) {
        data.add(t);
    }
    
    public T dequeue() {
        return data.remove(0);
    }
    
    public T head() {
        return data.get(0);
    }
    
    @Override
    public int size() {
        return data.size();
    }
    
    @Override
    public boolean isEmpty() {
        return data.size() == 0;
    }
    
    @Override
    public String toString() {
        String str = "[";
	for(T d: data)
            str = str + d +", ";
	str = str.substring(0, str.length()-2)+"]";
	return str;
    }
}
