/**
 * Created by Hyman on 2017/3/23.
 */

/**********************************************************************************
 *
 * Given a linked list, determine if it has a cycle in it.
 *
 * Follow up:
 * Can you solve it without using extra space?
 *
 *
 **********************************************************************************/
public class Solution {



    public boolean hasCycle(ListNode head) {

        if(head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        do {
            slow = slow.next;
            fast = fast.next.next;
        } while(fast != null && fast.next != null && fast != slow);

        return fast == slow ? true : false;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
