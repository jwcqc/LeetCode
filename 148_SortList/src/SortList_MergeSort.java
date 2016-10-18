/**
 * Created by Hyman on 2016/10/17.
 *
 * 归并排序
 */

/**********************************************************************************
 *
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 **********************************************************************************/
public class SortList_MergeSort {

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

        ListNode sorted = sort(head);

        while(sorted != null){
            System.out.print(sorted.val + " ");
            sorted = sorted.next;
        }
        System.out.println();
    }

    public static ListNode sort(ListNode head) {

        if(head == null || head.next == null) {
            return head;
        }

        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode right = sort(slow.next);
        slow.next = null;
        ListNode left = sort(head);

        return merge(left, right);
    }


    /**
     * 归并操作
     * @param first
     * @param second
     * @return
     */
    public static ListNode merge(ListNode first, ListNode second) {

        ListNode pointer = new ListNode(0);
        ListNode head = pointer;

        while(first != null && second != null) {

            if(first.val < second.val) {
                pointer.next = first;
                first = first.next;
            } else {
                pointer.next = second;
                second = second.next;
            }

            pointer = pointer.next;
        }

        if(first != null) {
            pointer.next = first;
        }

        if(second != null) {
            pointer.next = second;
        }

        return head.next;
    }



    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
