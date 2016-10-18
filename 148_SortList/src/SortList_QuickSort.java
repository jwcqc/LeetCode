/**
 * Created by Hyman on 2016/10/17.
 *
 * 快速排序
 */

/**********************************************************************************
 *
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 **********************************************************************************/
public class SortList_QuickSort {

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
        quickSort(head, null);
        return head;
    }

    public static void quickSort(ListNode begin, ListNode end) {

        if(begin != end){

            ListNode pivot = partition(begin, end);

            quickSort(begin, pivot);
            quickSort(pivot.next, end);
        }
    }

    private static ListNode partition(ListNode begin, ListNode end) {

        int x = begin.val;
        ListNode dest = begin;
        ListNode pointer = dest.next;

        while(pointer != end) {
            if(pointer.val < x) {
                dest = dest.next;
                int tmp = dest.val;
                dest.val = pointer.val;
                pointer.val = tmp;
            }
            pointer = pointer.next;
        }

        int tmp = dest.val;
        dest.val = begin.val;
        begin.val = tmp;

        return dest;
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
