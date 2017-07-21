/**
 * Created by Hyman on 2017/7/4.
 *
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * You must do this in-place without altering the nodes' values.
 *
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 *
 */
public class Solution {

    public static void main(String[] args) {

        ListNode head = new ListNode(5);
        ListNode head1 = new ListNode(3);
        ListNode head2 = new ListNode(6);
        ListNode head3 = new ListNode(8);
        ListNode head4 = new ListNode(4);
        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = null;

        reorderList(head);

        while(head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static void reorderList(ListNode head) {

        if(head == null || head.next == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head;

        // 1、先找到中间节点，slow会指向它
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2、对后半部分链表进行反转，使用头插法或者借助指针顺序逆置都可
        ListNode rear = slow.next;
        slow.next = null;
        ListNode ne = rear.next;
        rear.next = null;

        // 最终rear会指向反转后的首节点
        while(ne != null) {
            ListNode tmp = ne.next;
            ne.next = rear;
            rear = ne;
            ne = tmp;
        }

        // 3、开始将后半部分链表插入
        ListNode pre = head;
        while(pre != null && rear != null) {
            ListNode preN = pre.next;
            ListNode rearN = rear.next;

            pre.next = rear;
            rear.next = preN;

            pre = preN;
            rear = rearN;
        }

    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}


