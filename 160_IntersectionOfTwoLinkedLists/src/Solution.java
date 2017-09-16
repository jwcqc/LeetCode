/**
 * Created by Hyman on 17/9/15.
 */
/**********************************************************************************
 *
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 * For example, the following two linked lists:
 *
 *
 *    A:          a1 → a2
 *                       ↘
 *                         c1 → c2 → c3
 *                       ↗
 *    B:     b1 → b2 → b3
 *
 * begin to intersect at node c1.
 *
 * Notes:
 *
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
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

    public static void main(String[] args) {



    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if(headA == null || headB == null) {
            return null;
        }

        ListNode p = headA;
        int len1 = 0;
        int len2 = 0;
        while(p.next != null) {
            p = p.next;
            len1++;
        }
        p = headB;
        while(p.next != null) {
            p = p.next;
            len2++;
        }

        ListNode pA = headA;
        ListNode pB = headB;

        if(len1 > len2) {
            for(int i=0; i<len1-len2; i++) {
                pA = pA.next;
            }
        } else {
            for(int i=0; i<len2-len1; i++) {
                pB = pB.next;
            }
        }

        while(pA != pB) {
            pA = pA.next;
            pB = pB.next;
        }

        return pA;
    }
}
