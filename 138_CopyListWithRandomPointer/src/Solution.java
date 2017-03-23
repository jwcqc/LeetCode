/**
 * Created by Hyman on 2017/3/22.
 */

import java.util.HashMap;
import java.util.Map;

/**********************************************************************************
 *
 * A linked list is given such that each node contains an additional random pointer
 * which could point to any node in the list or null.
 *
 * Return a deep copy of the list.
 *
 *
 **********************************************************************************/
public class Solution {

    public static void main(String[] args) {

    }

    public RandomListNode copyRandomList(RandomListNode head) {

        if(head == null) {
            return null;
        }

        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

        RandomListNode head2 = head;
        RandomListNode newHead = null;
        RandomListNode temp = null;
        RandomListNode pointer = null;

        while(head2 != null) {
            temp = new RandomListNode(head2.label);
            if(newHead == null) {
                newHead = temp;
            } else {
                pointer.next = temp;
            }
            pointer = temp;
            map.put(head2, temp);

            head2 = head2.next;
        }

        RandomListNode random = null;
        RandomListNode head3 = newHead;
        while(head3 != null && head != null) {

            random = map.get(head.random);
            head3.random = random;

            head3 = head3.next;
            head = head.next;
        }

        return newHead;
    }

    class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

}
