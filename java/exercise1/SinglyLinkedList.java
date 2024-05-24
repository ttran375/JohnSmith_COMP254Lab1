package exercise1;

public class SinglyLinkedList<E> implements Cloneable {

  private static class Node<E> {
    private E element;
    private Node<E> next;

    public Node(E e, Node<E> n) {
      element = e;
      next = n;
    }

    public E getElement() {
      return element;
    }

    public Node<E> getNext() {
      return next;
    }

    public void setNext(Node<E> n) {
      next = n;
    }
  }

  private Node<E> head = null;
  private Node<E> tail = null;
  private int size = 0;

  public SinglyLinkedList() {
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public E first() {
    if (isEmpty())
      return null;
    return head.getElement();
  }

  public E last() {
    if (isEmpty())
      return null;
    return tail.getElement();
  }

  public void addFirst(E e) {
    head = new Node<>(e, head);
    if (size == 0)
      tail = head;
    size++;
  }

  public void addLast(E e) {
    Node<E> newest = new Node<>(e, null);
    if (isEmpty())
      head = newest;
    else
      tail.setNext(newest);
    tail = newest;
    size++;
  }

  public E removeFirst() {
    if (isEmpty())
      return null;
    E answer = head.getElement();
    head = head.getNext();
    size--;
    if (size == 0)
      tail = null;
    return answer;
  }

  @SuppressWarnings({ "unchecked" })
  public boolean equals(Object o) {
    if (o == null)
      return false;
    if (getClass() != o.getClass())
      return false;
    SinglyLinkedList other = (SinglyLinkedList) o;
    if (size != other.size)
      return false;
    Node walkA = head;
    Node walkB = other.head;
    while (walkA != null) {
      if (!walkA.getElement().equals(walkB.getElement()))
        return false;
      walkA = walkA.getNext();
      walkB = walkB.getNext();
    }
    return true;
  }

  @SuppressWarnings({ "unchecked" })
  public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
    SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone();
    if (size > 0) {
      other.head = new Node<>(head.getElement(), null);
      Node<E> walk = head.getNext();
      Node<E> otherTail = other.head;
      while (walk != null) {
        Node<E> newest = new Node<>(walk.getElement(), null);
        otherTail.setNext(newest);
        otherTail = newest;
        walk = walk.getNext();
      }
    }
    return other;
  }

  public int hashCode() {
    int h = 0;
    for (Node walk = head; walk != null; walk = walk.getNext()) {
      h ^= walk.getElement().hashCode();
      h = (h << 5) | (h >>> 27);
    }
    return h;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("(");
    Node<E> walk = head;
    while (walk != null) {
      sb.append(walk.getElement());
      if (walk != tail)
        sb.append(", ");
      walk = walk.getNext();
    }
    sb.append(")");
    return sb.toString();
  }

  public void swapTwoNodes(Node<E> node1, Node<E> node2) {
    if (node1 == node2)
      return;

    Node<E> prevNode1 = null, prevNode2 = null;
    Node<E> currentNode = head;

    while (currentNode != null) {
      if (currentNode.getNext() == node1) {
        prevNode1 = currentNode;
      } else if (currentNode.getNext() == node2) {
        prevNode2 = currentNode;
      }
      currentNode = currentNode.getNext();
    }

    if (prevNode1 == null || prevNode2 == null) {
      return;
    }

    if (prevNode1 != null) {
      prevNode1.setNext(node2);
    } else {
      head = node2;
    }

    if (prevNode2 != null) {
      prevNode2.setNext(node1);
    } else {
      head = node1;
    }

    Node<E> temp = node1.getNext();
    node1.setNext(node2.getNext());
    node2.setNext(temp);

    if (tail == node1) {
      tail = node2;
    } else if (tail == node2) {
      tail = node1;
    }
  }

  public static void main(String[] args) {
    SinglyLinkedList<String> list = new SinglyLinkedList<String>();
    list.addFirst("MSP");
    list.addLast("ATL");
    list.addLast("BOS");
    list.addFirst("LAX");
    System.out.println("Before swapping: " + list);

    Node<String> node1 = list.head.getNext();
    Node<String> node2 = list.tail;
    list.swapTwoNodes(node1, node2);
    System.out.println("After swapping: " + list);
  }
}
