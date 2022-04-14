/**
 * Created by chengbin on 2022-04-06.
 */
public class Solution {

    /**
     * 203. 移除链表元素
     * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     *
     *
     * 示例 1：
     * 输入：head = [1,2,6,3,4,5,6], val = 6
     * 输出：[1,2,3,4,5]
     *
     * 示例 2：
     * 输出：[]
     *
     * 示例 3：
     * 输入：head = [7,7,7,7], val = 7
     * 输出：[]
     */

    public ListNode removeElements(ListNode head, int val) {

        if(head == null) {
            return null;
        }

        ListNode currrent;
        ListNode next;

        while(head != null && head.val == val) {
            next = head.next;
            head.next = null;
            head = next;
        }

        if(head == null) {
            return null;
        }

        currrent = head;
        next = head.next;

        while(next != null) {
            if(next.val == val) {
                currrent.next = next.next;
                next = next.next;
            } else {
                currrent = next;
                next = next.next;
            }
        }

        return head;
    }


    // 使用虚拟节点指向head，统一操作，避免需要添加逻辑单独处理头结点
    public ListNode removeElementsUseDummyNode(ListNode head, int val) {

        if(head == null) {
            return null;
        }

        ListNode dummy = new ListNode(-1, head);

        ListNode pre = dummy;
        ListNode cur = head;

        while(cur != null) {
            if(cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return dummy.next;
    }


    public static void main(String[] args) {

        ListNode head = getTestCase1();
        int val = 6;

//        head = getTestCase2();
//        val = 7;

        ListNode listNode = new Solution().removeElements(head, val);
        ListNode listNodeUseDummy = new Solution().removeElementsUseDummyNode(head, val);

        System.out.println(listNode);
        System.out.println(listNodeUseDummy);
    }

    private static ListNode getTestCase1() {
        ListNode node4 = new ListNode(3, null);
        ListNode node3 = new ListNode(6, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        return node1;
    }

    private static ListNode getTestCase2() {
        ListNode node4 = new ListNode(7, null);
        ListNode node3 = new ListNode(7, node4);
        ListNode node2 = new ListNode(7, node3);
        ListNode node1 = new ListNode(7, node2);
        return node1;
    }
}
