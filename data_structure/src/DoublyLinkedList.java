/*
* Implementation of leetcode 707.Design linked list
* https://leetcode.com/problems/design-linked-list/
* */
public class DoublyLinkedList {
        Node head;
        Node tail;
        int size;

        private class Node {
            int val;
            Node prev;
            Node next;
            Node(int val, Node prev, Node next){
                this.val = val;
                this.prev = prev;
                this.next = next;
            }
        }

        /** Initialize your data structure here. */
        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
        public int get(int index) {
            //print the linked list
            if (index > this.size-1 || index < 0) return -1;
            int counter = 0;
            Node currNode = this.head;
            while(currNode != null){
                if (counter == index) return currNode.val;
                counter++;
                currNode = currNode.next;
            }
            return -1;
        }

        /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
        public void addAtHead(int val) {
            Node newHead = new Node(val, null, null);
            if (head == null) {
                this.head = newHead;
                this.tail = newHead;
            } else {
                Node oldHead = this.head;
                newHead.next = oldHead;
                oldHead.prev = newHead;
                this.head = newHead;
            }
            this.size++;
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            Node newTail = new Node(val, null, null);
            if(head == null){
                this.head = newTail;
                this.tail = newTail;
            } else {
                Node oldTail = this.tail;
                oldTail.next = newTail;
                newTail.prev = oldTail;
                this.tail = newTail;
            }
            this.size++;
        }

        /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
        public void addAtIndex(int index, int val) {
            if(index>this.size || index<0) {
                return;
            } else if(index==0) {
                this.addAtHead(val);
            } else if(index == this.size) {
                this.addAtTail(val);
            } else {
                Node prevNode = this.getNodeAtIndex(index-1);
                Node nextNode = prevNode.next;
                Node newNode = new Node(val, prevNode, nextNode);
                prevNode.next = newNode;
                nextNode.prev = newNode;
                this.size++;
            }
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            if(index>this.size-1 || index<0) {
                return;
            } else if(index == 0) {
                this.deleteAtHead();
            } else if(index == this.size-1) {
                this.deleteAtTail();
            } else {
                Node prevNode = this.getNodeAtIndex(index-1);
                Node nextNode = prevNode.next.next;
                prevNode.next = nextNode;
                nextNode.prev = prevNode;
                this.size--;
            }
        }

        private Node getNodeAtIndex(int index){
            if (index > this.size-1 || index < 0) return null;
            int counter = 0;
            Node currNode = this.head;
            while(currNode != null){
                if (counter == index) return currNode;
                counter++;
                currNode = currNode.next;
            }
            return null;
        }

        private void deleteAtHead(){
            if (this.head == null) return;
            Node oldHead = this.head;
            this.head = oldHead.next;
            if (this.head == null){
                this.tail = this.head;
            } else {
                this.head.prev = null;
            }
            this.size--;
        }

        private void deleteAtTail(){
            if (this.head == null) return;
            Node oldTail = this.tail;
            this.tail = oldTail.prev;
            if (this.tail==null){
                this.head = this.tail;
            } else {
                this.tail.next = null;
            }
            this.size--;
    }

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
}
