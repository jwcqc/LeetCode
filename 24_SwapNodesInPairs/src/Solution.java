/**
 * Created by Hyman on 17/9/15.
 */
/**********************************************************************************
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 *
 * Your algorithm should use only constant space. You may not modify the values in the list,
 * only nodes itself can be changed.
 *
 *
 **********************************************************************************/
public class Solution {


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode swapPairs(ListNode head) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode p = dummy;
        for(; p.next!=null && p.next.next!=null; p=p.next.next) {
            p.next = swap(p.next, p.next.next);
        }

        return dummy.next;
    }

    private ListNode swap(ListNode node1, ListNode node2) {
        node1.next = node2.next;
        node2.next = node1;
        return node2;
    }

}
