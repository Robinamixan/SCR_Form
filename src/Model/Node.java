package Model;

public class Node<T> {
	
	private T Data;
	
	private Node<T> NextNode;

	public Node(T data, Node<T> Next)
	{
		this.Data = data;
		this.NextNode = Next;
	}
	
	public T getData()
	{
		return this.Data;
	}
	
	public void setData(T data)
	{
		this.Data = data;
	}
	
	public boolean DataIsEqual(T data)
	{
		if (this.Data instanceof String)
			if (this.Data.equals(data))
				return true;

		if (this.Data instanceof Number)
			if (this.Data == data)
				return true;
		return false;
	}
	
	public Node<T> getNext()
	{
		return this.NextNode;
	}
	
	public void setNext(Node<T> Next)
	{
		this.NextNode = Next;
	}
	
	
}
