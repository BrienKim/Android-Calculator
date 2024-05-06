package function;

public class OutputQueue<E>
{
    private Node front;
    private Node rear;
    private int size;

    public OutputQueue()
    {
        front = null;
        rear = null;
        size = 0;
    }

    private class Node
    {
        private E data;
        private Node next;

        Node (E data)
        {
            this.data = data;
            this.next = null;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(E e) {
        Node temp = new Node(e);

        if (this.isEmpty())
        {
            front = temp;
        }
        else
        {
            rear.next = temp;
        }

        rear = temp;
        size++;
    }

    public E dequeue()
    {
        if (this.isEmpty())
        {
            return null;
        }

        E removed = front.data;
        front = front.next;

        if (front == null)
        {
            rear = null;
        }

        size--;
        return removed;
    }

    public String toString()
    {
        if (isEmpty())
        {
            return "";
        }

        String str = "[";
        Node current = front;
        while (current != null)
        {
            str += current.data  + ", ";
            current = current.next;
        }

        str += "\b\b]";
        return str;
    }
}
