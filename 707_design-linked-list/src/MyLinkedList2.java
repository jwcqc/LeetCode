/**
 * Created by chengbin on 2022-04-12.
 *
 * 707. 设计链表
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。
 * 如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 *
 * 在链表类中实现这些功能：
 *
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。
 *      如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 *
 */
public class MyLinkedList2 {

//    private ListNode head = null;
    private ListNode tail;
    private ListNode preHead;
    private int size = 0;


    public MyLinkedList2() {
        tail = null;
        preHead = new ListNode(-1, tail);
    }

    public int get(int index) {
        if(index < 0 || index >= size) {
            return -1;
        }
        ListNode node = preHead.next;
        int i = 0;
        while(i < index) {
            node = node.next;
            i++;
        }
        return node.val;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val, preHead.next);
        preHead.next = newNode;

        if(size == 0) {
            tail = newNode;
        }

        size++;
    }

    public void addAtTail(int val) {

        ListNode newNode = new ListNode(val, null);

        if(tail == null) {
            tail = newNode;
            preHead.next = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    public void addAtIndex(int index, int val) {

        if(index <= 0) {
            addAtHead(val);
        } else if(index > size) {
            return;
        } else if(index == size) {
            addAtTail(val);
        } else {

            ListNode node = preHead.next;
            int i = 0;
            while(i < index-1) {
                node = node.next;
                i++;
            }

            ListNode newNode = new ListNode(val, node.next);
            node.next = newNode;

            size++;
        }
    }

    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size) {
            return;
        }

        ListNode preNode = preHead;
        ListNode node = preHead.next;

        int i = 0;
        while(i < index) {
            node = node.next;
            preNode = preNode.next;
            i++;
        }

        preNode.next = node.next;
        node = null;

        size--;

    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        ListNode node = preHead.next;
        while(node != null) {
            stringBuilder.append(node.val).append("->");
            node = node.next;
        }
        return stringBuilder.toString();
    }
}
