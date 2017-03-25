/**
 * Created by Hyman on 2017/3/23.
 */

/**********************************************************************************
 *
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * Follow up:
 * Can you solve it without using extra space?
 *
 * 思路：
 * 1）同linked-list-cycle一题，使用快慢指针方法，判定是否存在环，并记录两指针相遇位置(Z)；
 * 2）将两指针分别放在链表头(X)和相遇位置(Z)，并改为相同速度推进，则两指针在环开始位置相遇(Y)。
 *
 * 证明见：https://www.nowcoder.com/questionTerminal/6e630519bf86480296d0f1c868d425ad
 *
 **********************************************************************************/
public class Solution {

    public ListNode detectCycle(ListNode head) {

        if(head == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                break;
            }
        }

        if(fast == null || fast.next == null) {
            return null;
        }

        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    /**
     * 为什么用这个会超时？
     * @param head
     * @return
     */
    public ListNode detectCycle2(ListNode head) {

        if(head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head.next.next;

        while(fast != null && fast.next != null && fast != slow) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if(fast != slow) {
            return null;
        }

        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
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
