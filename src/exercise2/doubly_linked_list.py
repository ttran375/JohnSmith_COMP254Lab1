class Node:
    def __init__(self, data):
        self.data = data
        self.previous = None
        self.next = None


class DoublyLinkedList:
    def __init__(self):
        self.head = None
        self.tail = None

        # TO check weather list is empty or not?

    def is_empty(self):
        return self.head is None

    def add_node(self, data):
        #  Creating New Node
        new_node = Node(data)

        # Checking where the head and tail pointer is pointing.
        # if first node is added to the list then below condition should be
        # true.
        if self.head is None:
            self.head = new_node
            new_node.previous = new_node.next = None

        else:
            # Addding new node at the last
            self.tail.next = new_node
            new_node.previous = self.tail
            new_node.next = None
        self.tail = new_node

    def display_list(self):
        # Creating temp pointer to traverse
        temp = self.head
        if temp is not None:
            while temp is not None:
                print(temp.data, end=" -> ")
                temp = temp.next
            print("NULL")
        else:
            print("List does not have any nodes")

    def concatenate(self, new_list):
        """
        Concatenates another doubly linked list to the end of this list.
        """
        # Set head and tail to the new list's head and tail if the current list is empty
        if self.is_empty():
            self.head = new_list.head
            self.tail = new_list.tail

        # If the new list is not empty
        elif not new_list.is_empty():

            # Link the last node of the current list to the first node of the new list
            self.tail.next = new_list.head

            # Set the previous pointer of the new list's head to the current list's tail
            new_list.head.previous = self.tail

            # Update the tail to be the last node of the new list
            self.tail = new_list.tail


def clone_linked_list(l1):
    dl = DoublyLinkedList()
    temp = l1.head
    if temp is not None:
        while temp is not None:
            dl.add_node(temp.data)
            temp = temp.next
    return dl
